package com.btanabe2.fbdu.du.application;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dm.models.PositionsEntity;
import com.btanabe2.fbdu.dp.leagues.providers.NbaPositionProvider;
import com.btanabe2.fbdu.dp.stats.providers.NbaTeamProvider;
import com.btanabe2.fbdu.dp.web.WebRequest;
import com.btanabe2.fbdu.du.updaters.PlayerBiographyTableUpdater;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by brian on 11/8/14.
 */
public class Application {

    public static void main(String[] args) {
        try {
            List<NbaTeamEntity> nbaTeams = getNbaTeams();

            createPositionsTable();
            createPlayerBiographyTable(nbaTeams);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static List<NbaTeamEntity> getNbaTeams() throws IOException {
        return NbaTeamProvider.getAllNbaTeamEntities(new WebRequest());
    }

    private static void createPositionsTable(){
        List<PositionsEntity> positions = NbaPositionProvider.getAllPositions();

        // Get session:

        // session.persist()

        // session.flush() every 30-ish or whatever you're supposed to do

        // session.close() when done

        // session.save() will return the ID given to that object but will trigger even if the session is already closed.
        // i'm not sure if this will result in an exception or loss of data but I do know that session.persist() will
        // not do this.  persist() will not return the object's ID though since it's not actually inserted until the
        // session is flush()-ed.
    }

    private static void createPlayerBiographyTable(List<NbaTeamEntity> nbaTeams) throws IOException, ParseException {
        PlayerBiographyTableUpdater playerBiographyTableUpdater = new PlayerBiographyTableUpdater();
        playerBiographyTableUpdater.createPlayerBiographyTable(nbaTeams);
    }
}
