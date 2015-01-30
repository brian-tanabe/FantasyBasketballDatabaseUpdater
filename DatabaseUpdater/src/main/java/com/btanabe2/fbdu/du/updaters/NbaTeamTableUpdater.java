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
        scrapeForNbaTeams(webRequest);
        saveOrUpdateAllNbaTeamsInDatabase(session);
    }

    private void scrapeForNbaTeams(WebRequest webRequest) throws IOException {
        nbaTeams = NbaTeamProvider.getAllNbaTeamEntities(webRequest);
    }

    private void saveOrUpdateAllNbaTeamsInDatabase(Session session) {
        Transaction transaction = session.beginTransaction();
//        nbaTeams.forEach(session::persist);
        nbaTeams.sort((lhs, rhs) -> lhs.getId() - rhs.getId());
        for(NbaTeamEntity nbaTeam : nbaTeams) {
            session.persist(nbaTeam);
            System.out.println("Persisted: " + nbaTeam.getName());
        }
        transaction.commit();
//        session.flush();
    }

    public List<NbaTeamEntity> getNbaTeams() {
        return nbaTeams;
    }
}
