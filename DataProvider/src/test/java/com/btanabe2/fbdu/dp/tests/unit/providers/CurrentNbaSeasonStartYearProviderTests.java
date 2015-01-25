package com.btanabe2.fbdu.dp.tests.unit.providers;

import com.btanabe2.fbdu.dp.leagues.providers.CurrentNbaSeasonStartYearProvider;
import org.junit.Test;

import java.time.Month;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 1/25/15.
 */
public class CurrentNbaSeasonStartYearProviderTests {

    @Test
    public void shouldBeAbleToCreateOurTestCalendarObjectProperly() {
        Calendar testCalendar = getCalendarForDate(2015, Month.JANUARY, 25);
        assertEquals("Did not set the year properly", 2015, testCalendar.get(Calendar.YEAR));
        assertEquals("Did not set the month properly", 0, testCalendar.get(Calendar.MONTH));
        assertEquals("Did not set the day of the month properly", 25, testCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void shouldBeAbleToKnowWhenTheCurrentSeasonStartsWhenTheCurrentDateIsAfterJanuary() {
        int testYear = CurrentNbaSeasonStartYearProvider.getCurrentNbaSeasonStartYear(getCalendarForDate(2015, Month.JANUARY, 2));
        assertEquals("Did not calculate the year properly for a date of 1/2/2015", 2014, testYear);
    }

    @Test
    public void shouldBeAbleToKnowWhenTheCurrentSeasonStartsWhenTheCurrentDateIsAfterTheNbaFinals() {
        int testYear = CurrentNbaSeasonStartYearProvider.getCurrentNbaSeasonStartYear(getCalendarForDate(2014, Month.JULY, 1));
        assertEquals("Did not calculate the year properly for a date of 7/1/2014", 2014, testYear);
    }


    private Calendar getCalendarForDate(int year, Month monthOfYear, int dayOfMonth){
        Calendar testDate = Calendar.getInstance();
        testDate.set(Calendar.YEAR, year);
        testDate.set(Calendar.MONTH, monthOfYear.getValue() - 1);
        testDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        return testDate;
    }
}
