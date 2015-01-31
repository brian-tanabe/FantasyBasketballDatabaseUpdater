package com.btanabe2.fbdu.du.updaters;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dp.stats.providers.NbaTeamProvider;
import com.btanabe2.fbdu.dp.web.WebRequest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

/**
 * Created by brian on 1/30/15.
 */
public class NbaTeamTableUpdater {
    private WebRequest webRequest;
    private List<NbaTeamEntity> nbaTeams;

    public NbaTeamTableUpdater(WebRequest webRequest) {
        this.webRequest = webRequest;
    }

    public void getTeamsAndCreateTable(Session session) throws IOException {
        Transaction transaction = session.beginTransaction();

        removeAllCurrentNbaTeamsFromDatabase(session);
        scrapeForNbaTeams(webRequest);
        saveOrUpdateAllNbaTeamsInDatabase(session);

        transaction.commit();
    }

    private void scrapeForNbaTeams(WebRequest webRequest) throws IOException {
        nbaTeams = NbaTeamProvider.getAllNbaTeamEntities(webRequest);
    }

    private void saveOrUpdateAllNbaTeamsInDatabase(Session session) {
        nbaTeams.forEach(session::saveOrUpdate);
    }

    private void removeAllCurrentNbaTeamsFromDatabase(Session session) {
        session.createCriteria(NbaTeamEntity.class).list().forEach(session::delete);
    }

    public List<NbaTeamEntity> getNbaTeams() {
        return nbaTeams;
    }
}
