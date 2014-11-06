package com.btanabe2.fbdu.dp.tests.providers;

import com.btanabe2.fbdu.dp.providers.NbaTeamProvider;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 11/6/14.
 */
public class NbaTeamProviderTests {

    @Test
    public void shouldBeAbleToFindThirtyTeams(){
        assertEquals("Did not find the proper number of NBA teams", 30, NbaTeamProvider.getAllCurrentNbaTeams());
    }
}
