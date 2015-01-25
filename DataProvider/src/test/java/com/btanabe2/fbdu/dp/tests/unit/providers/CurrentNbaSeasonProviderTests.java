package com.btanabe2.fbdu.dp.tests.unit.providers;

import com.btanabe2.fbdu.dp.leagues.providers.CurrentNbaSeasonProvider;
import org.junit.Test;

import java.time.Month;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 1/25/15.
 */
public class CurrentNbaSeasonProviderTests {

    @Test
    public void shouldBeAbleToCreateOurTestCalendarObjectProperly() {
        Calendar testCalendar = getCalendarForDate(2015, Month.JANUARY, 25);
        assertEquals("Did not set the year properly", 2015, testCalendar.get(Calendar.YEAR));
        assertEquals("Did not set the month properly", 0, testCalendar.get(Calendar.MONTH));
        assertEquals("Did not set the day of the month properly", 25, testCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void shouldBeAbleToKnowWhenTheCurrentSeasonStartsWhenTheCurrentDateIsAfterJanuary() {
        int testYear = CurrentNbaSeasonProvider.getCurrentNbaSeasonStartYear(getCalendarForDate(2014, Month.JANUARY, 2));
        assertEquals("Did not calculate the year properly", 2014, testYear);
    }

    private Calendar getCalendarForDate(int year, Month monthOfYear, int dayOfMonth){
        Calendar testDate = Calendar.getInstance();
        testDate.set(Calendar.YEAR, year);
        testDate.set(Calendar.MONTH, monthOfYear.getValue() - 1);
        testDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        return testDate;
    }
}
