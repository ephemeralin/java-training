package ru.job4j.siteparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The type Date builder.
 */
public class DateBuilder {
    /**
     * Logger.
     */
    private final Logger logger;
    /**
     * Date today.
     */
    private Date dateToday;
    /**
     * Date today text.
     */
    private String dateTodayText;
    /**
     * Date yesterday text.
     */
    private String dateYesterdayText;

    /**
     * Instantiates a new Date builder.
     */
    public DateBuilder() {
        this.logger = LogManager.getLogger(SiteParser.class);
        Calendar calendar = Calendar.getInstance();
        this.dateToday = calendar.getTime();
        this.dateTodayText = new SimpleDateFormat("dd MMM yy").format(this.dateToday);
        calendar.add(Calendar.DATE, -1);
        this.dateYesterdayText = new SimpleDateFormat("dd MMM yy").format(calendar.getTime());
    }

    /**
     * Convert local text date to formatted text date string.
     *
     * @param textDate the text date
     * @return the string
     */
    private String convertLocalTextDateToFormattedTextDate(String textDate) {
        String formattedTextDate = "";
        if (textDate.contains("сегодня")) {
            formattedTextDate = textDate.replace("сегодня", dateTodayText);
        } else if (textDate.contains("вчера")) {
            formattedTextDate = textDate.replace("вчера", dateYesterdayText);
        } else {
            formattedTextDate = replaceLocalMonth(textDate);
        }
        return formattedTextDate;
    }

    /**
     * Replace local month string.
     *
     * @param textDate the text date
     * @return the string
     */
    private String replaceLocalMonth(String textDate) {
        String replacedLocalMonthDate = textDate;
        if (textDate.contains("янв")) {
            replacedLocalMonthDate = textDate.replace("янв", "Jan");
        } else if (textDate.contains("фев")) {
            replacedLocalMonthDate = textDate.replace("фев", "Feb");
        } else if (textDate.contains("мар")) {
            replacedLocalMonthDate = textDate.replace("мар", "Mar");
        } else if (textDate.contains("апр")) {
            replacedLocalMonthDate = textDate.replace("апр", "Apr");
        } else if (textDate.contains("май")) {
            replacedLocalMonthDate = textDate.replace("май", "May");
        } else if (textDate.contains("июн")) {
            replacedLocalMonthDate = textDate.replace("июн", "Jun");
        } else if (textDate.contains("июл")) {
            replacedLocalMonthDate = textDate.replace("июл", "Jul");
        } else if (textDate.contains("авг")) {
            replacedLocalMonthDate = textDate.replace("авг", "Aug");
        } else if (textDate.contains("сен")) {
            replacedLocalMonthDate = textDate.replace("сен", "Sep");
        } else if (textDate.contains("окт")) {
            replacedLocalMonthDate = textDate.replace("окт", "Oct");
        } else if (textDate.contains("ноя")) {
            replacedLocalMonthDate = textDate.replace("ноя", "Nov");
        } else if (textDate.contains("дек")) {
            replacedLocalMonthDate = textDate.replace("дек", "Dec");
        }
        return replacedLocalMonthDate;
    }

    /**
     * Convert text to date date.
     *
     * @param textDate the text date
     * @return the date
     */
    public Date convertTextToDate(String textDate) {
        String formattedTextDate = convertLocalTextDateToFormattedTextDate(textDate);
        Date date = null;
        try {
            date = new SimpleDateFormat("dd MMM yy, HH:mm").parse(formattedTextDate);
        } catch (ParseException e) {
            date = dateToday;
            logger.error("Unparseable date text: {}. Used current date instead.", textDate, e);
        }
        return date;
    }

    /**
     * Calculate finish date date.
     *
     * @param isFirstRun the is first run
     * @return the date
     */
    public Date calculateFinishDate(boolean isFirstRun) {
        Calendar calendar = Calendar.getInstance();
        Date date;
        if (isFirstRun) {
            calendar.set(Calendar.DAY_OF_YEAR, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            date = calendar.getTime();
        } else {
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.add(Calendar.DATE, -1);
            date = calendar.getTime();
        }
        return date;
    }
}
