package ru.job4j.siteparser;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * The type Local date formatter test.
 */
public class LocalDateFormatterTest {

    /**
     * When convert today date text then has converted date.
     */
    @Test
    public void whenConvertTodayDateTextThenHasConvertedDate() {
        String dateTextExpected =
                new SimpleDateFormat("dd MMM yy, 20:00").format(Calendar.getInstance().getTime());

        String testDateText = "сегодня, 20:00";
        Date date = new DateBuilder().convertTextToDate(testDateText);
        String resultDateText = new SimpleDateFormat("dd MMM yy, HH:mm").format(date);

        assertThat(resultDateText, is(dateTextExpected));
    }

    /**
     * When convert yesterday date text then has converted date.
     */
    @Test
    public void whenConvertYesterdayDateTextThenHasConvertedDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        String dateTextExpected =
                new SimpleDateFormat("dd MMM yy, 20:00").format(calendar.getTime());

        String testDateText = "вчера, 20:00";
        Date date = new DateBuilder().convertTextToDate(testDateText);
        String resultDateText = new SimpleDateFormat("dd MMM yy, HH:mm").format(date);

        assertThat(resultDateText, is(dateTextExpected));
    }

    /**
     * When convert local date text then has converted date.
     */
    @Test
    public void whenConvertLocalDateTextThenHasConvertedDate() {
        String dateTextExpected = "04 Jul 18, 20:00";

        String testDateText = "04 июл 18, 20:00";
        Date date = new DateBuilder().convertTextToDate(testDateText);
        String resultDateText = new SimpleDateFormat("dd MMM yy, HH:mm").format(date);

        assertThat(resultDateText, is(dateTextExpected));
    }
}