package com.btanabe2.fbdu.dp.data.providers;

import com.btanabe2.fbdu.dp.data.scrapers.EspnTeamsRosterLinkScraper;
import com.btanabe2.fbdu.dp.data.scrapers.workers.CallableEspnPlayerProfileLinkScraper;
import com.btanabe2.fbdu.dp.data.scrapers.workers.CallableEspnProfilePageIdScraper;
import com.btanabe2.fbdu.dp.system.WorkerPool;
import com.btanabe2.fbdu.dp.web.SecureWebRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.btanabe2.fbdu.dp.web.WebConstants.ESPN_TEAMS_PAGE_URL;

/**
 * Created by Brian on 2/7/15.
 */
public class EspnFantasyIdToStandardIdProvider extends WorkerPool<String, CallableEspnPlayerProfileLinkScraper, String> {
    private SecureWebRequest webRequest;

    public EspnFantasyIdToStandardIdProvider(SecureWebRequest webRequest) {
        this.webRequest = webRequest;
    }

    public Map<Integer, Integer> getFantasyIdMappedToNormalIdMap() throws IOException, ExecutionException, InterruptedException {
        List<String> allNbaPlayerProfilePageUrls = getAllNbaPlayerProfilePageUrls(getNbaTeamRosterPagesUrls());
        return mapAllPlayerFantasyIdsToTheirEspnIds(allNbaPlayerProfilePageUrls);
    }

    private List<String> getNbaTeamRosterPagesUrls() throws IOException {
        return EspnTeamsRosterLinkScraper.getTeamRosterPageLinks(webRequest.getPageAsDocument(ESPN_TEAMS_PAGE_URL));
    }

    private List<String> getAllNbaPlayerProfilePageUrls(List<String> teamPageUrls) throws IOException, ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(teamPageUrls.size());
        List<Future<List<String>>> futureList = new ArrayList<>(500);
        teamPageUrls.forEach(url -> futureList.add(executorService.submit(new CallableEspnPlayerProfileLinkScraper(webRequest, url))));

        List<String> allNbaPlayersProfilePageUrls = new ArrayList<>(500);
        for (Future<List<String>> future : futureList) {
            allNbaPlayersProfilePageUrls.addAll(future.get());
        }

        return allNbaPlayersProfilePageUrls;
    }

    private Map<Integer, Integer> mapAllPlayerFantasyIdsToTheirEspnIds(List<String> allNbaPlayerProfilePageUrls) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(allNbaPlayerProfilePageUrls.size());
        List<Future<Map<Integer, Integer>>> futureList = new ArrayList<>(allNbaPlayerProfilePageUrls.size());
        allNbaPlayerProfilePageUrls.forEach(url -> futureList.add(executorService.submit(new CallableEspnProfilePageIdScraper(webRequest, url))));

        Map<Integer, Integer> playerFantasyIdsMappedToTheirEspnIds = new HashMap<>(allNbaPlayerProfilePageUrls.size());
        for (Future<Map<Integer, Integer>> future : futureList) {
            if (future != null)
                playerFantasyIdsMappedToTheirEspnIds.putAll(future.get());
        }

        executorService.shutdown();

        return playerFantasyIdsMappedToTheirEspnIds;
    }
}
