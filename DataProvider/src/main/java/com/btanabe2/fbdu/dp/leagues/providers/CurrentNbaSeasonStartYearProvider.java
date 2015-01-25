package com.btanabe2.fbdu.dp.leagues.providers;

import java.util.Calendar;

/**
 * Created by brian on 1/25/15.
 */
public class CurrentNbaSeasonStartYearProvider {

    public static int getCurrentNbaSeasonStartYear(Calendar currentDate) {
        if(currentDate.get(Calendar.MONTH) < Calendar.OCTOBER && currentDate.get(Calendar.MONTH) > Calendar.JUNE)
            return currentDate.get(Calendar.YEAR);
        else
            return currentDate.get(Calendar.YEAR) - 1;
    }
}
