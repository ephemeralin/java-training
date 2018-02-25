package ru.job4j.siteparser;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * The type Date builder test.
 */
public class DateBuilderTest {

    /**
     * Given is first run when calculate finish date then first day of the year.
     */
    @Test
    public void givenIsFirstRunWhenCalculateFinishDateThenFirstDayOfTheYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date expectedDate = calendar.getTime();

        DateBuilder dateBuilder = new DateBuilder();
        Date resultDate = dateBuilder.calculateFinishDate(true);

        assertTrue("Dates aren't close enough to each other!",
                (Math.abs(expectedDate.getTime() - resultDate.getTime()) < 1000));
    }
}