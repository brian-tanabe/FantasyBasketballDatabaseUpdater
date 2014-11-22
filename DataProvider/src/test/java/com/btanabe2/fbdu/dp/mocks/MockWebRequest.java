package com.btanabe2.fbdu.dp.mocks;

import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import com.btanabe2.fbdu.dp.web.WebRequest;
import com.btanabe2.fbdu.dp.web.auth.TestableCredentialProvider;
import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.btanabe2.fbdu.dp.fixtures.BasketballReferencePageFixture.*;
import static com.btanabe2.fbdu.dp.fixtures.EspnTeamsPageFixture.getEspnFantasyBasketballHomepagePage;
import static com.btanabe2.fbdu.dp.web.WebConstants.*;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by brian on 11/6/14.
 */
public class MockWebRequest {

    public static WebRequest getPlayerBiographyProviderMockWebRequest(){
        WebRequest webRequest = mock(WebRequest.class);

        try {
            when(webRequest.getPage(SPORTS_VU_NBA_TEAM_INFO_URL)).thenReturn(FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/nba-sportsvu-pages/nba-commonteamyear.json")));
            when(webRequest.getPage(SPORTS_VU_ALL_PLAYERS_URL)).thenReturn(FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/nba-sportsvu-pages/nba-commonallplayers.json"), Charset.forName("UTF8")));
            when(webRequest.getPage(contains("?PlayerID="))).thenReturn(FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/nba-sportsvu-pages/playerinfo-pages/playerinfo-afflalo_aaron.json"), Charset.forName("UTF8")));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return webRequest;
        }
    }

    public static WebRequest getSportsVuTeamsPageMockWebRequest(){
        WebRequest webRequest = mock(WebRequest.class);
        try {
            when(webRequest.getPage(SPORTS_VU_NBA_TEAM_INFO_URL)).thenReturn(FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/nba-sportsvu-pages/nba-commonteamyear.json")));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return webRequest;
        }
    }

    public static WebRequest getBasketballReferenceTeamsPageMockWebRequest(){
        Map<String, Document> urlToDocumentMap = new LinkedHashMap<>();
        urlToDocumentMap.put(BASKETBALL_REFERENCE_TEAM_PAGE_URL, getBasketballReferenceTeamsPage());
        return getMockWebRequest(urlToDocumentMap);
    }

    public static WebRequest getBasketballReferenceStandingsPageMockWebRequest(){
        Map<String, Document> urlToDocumentMap = new LinkedHashMap<>();
        urlToDocumentMap.put(BASKETBALL_REFERENCE_STANDINGS_PAGE_URL, getBasketballReferenceStandingsPage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_ATL_PAGE_URL, getBasketballReferenceAtlantaFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_BOS_PAGE_URL, getBasketballReferenceBrooklynFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_BKN_PAGE_URL, getBasketballReferenceBostonFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_CHA_PAGE_URL, getBasketballReferenceCharlotteFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_CHI_PAGE_URL, getBasketballReferenceChicagoFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_CLE_PAGE_URL, getBasketballReferenceClevelandFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_DAL_PAGE_URL, getBasketballReferenceDallasFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_DEN_PAGE_URL, getBasketballReferenceDenverFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_DET_PAGE_URL, getBasketballReferenceDetroitFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_GSW_PAGE_URL, getBasketballReferenceGoldenStateFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_HOU_PAGE_URL, getBasketballReferenceHoustonFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_IND_PAGE_URL, getBasketballReferenceIndianaFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_LAC_PAGE_URL, getBasketballReferenceClippersFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_LAL_PAGE_URL, getBasketballReferenceLakersFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_MEM_PAGE_URL, getBasketballReferenceMemphisFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_MIA_PAGE_URL, getBasketballReferenceMiamiFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_MIL_PAGE_URL, getBasketballReferenceMilwaukeeFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_MIN_PAGE_URL, getBasketballReferenceMinnesotaFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_NOH_PAGE_URL, getBasketballReferenceNewOrleansFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_NYK_PAGE_URL, getBasketballReferenceKnicksFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_OKC_PAGE_URL, getBasketballReferenceOklahomaFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_ORL_PAGE_URL, getBasketballReferenceOrlandoFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_PHI_PAGE_URL, getBasketballReferencePhiladelphiaFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_PHX_PAGE_URL, getBasketballReferencePhoenixFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_POR_PAGE_URL, getBasketballReferencePortlandFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_SAC_PAGE_URL, getBasketballReferenceSacramentoFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_SAS_PAGE_URL, getBasketballReferenceSanAntonioFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_TOR_PAGE_URL, getBasketballReferenceUtahFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_UTA_PAGE_URL, getBasketballReferenceWashingtonFranchisePage());
        urlToDocumentMap.put(BASKETBALL_REFERENCE_WAS_PAGE_URL, getBasketballReferenceTorontoFranchisePage());

        return getMockWebRequest(urlToDocumentMap);
    }

    public static SecureWebRequest getEspnLeagueIdScraperMockWebRequest() throws IOException {
        Map<String, Document> urlToDocumentMap = new LinkedHashMap<>();
        urlToDocumentMap.put(ESPN_FANTASY_BASKETBALL_HOMEPAGE, getEspnFantasyBasketballHomepagePage());

        return getMockSecureWebRequest(urlToDocumentMap);
    }

    private static WebRequest getMockWebRequest(Map<String, Document> urlToDocumentMap){
        try {
            WebRequest webRequest = mock(WebRequest.class);
            for(String url : urlToDocumentMap.keySet()){
                when(webRequest.getPageAsDocument(url)).thenReturn(urlToDocumentMap.get(url));
            }

            return webRequest;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    private static SecureWebRequest getMockSecureWebRequest(Map<String, Document> urlToDocumentMap){
        try {
            SecureWebRequest webRequest = mock(SecureWebRequest.class);
            for(String url : urlToDocumentMap.keySet()){
                when(webRequest.getPageAsDocument(url)).thenReturn(urlToDocumentMap.get(url));
            }

            when(webRequest.login(new TestableCredentialProvider())).thenReturn(webRequest);
            return webRequest;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
