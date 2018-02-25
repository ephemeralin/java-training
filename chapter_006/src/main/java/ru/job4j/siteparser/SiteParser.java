package ru.job4j.siteparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Site parser.
 */
public class SiteParser {
    /**
     * Finish parsing date.
     */
    private final Date finishDate;
    /**
     * Date builder instance.
     */
    private DateBuilder dateBuilder;
    /**
     * Logger instance.
     */
    private Logger logger;
    /**
     * Properties storage instance.
     */
    private PropertiesStorage propertiesStorage;

    /**
     * Instantiates a new Site parser.
     *
     * @param propertiesStorage the properties storage
     */
    public SiteParser(PropertiesStorage propertiesStorage) {
        this.dateBuilder = new DateBuilder();
        this.propertiesStorage = propertiesStorage;
        this.finishDate = dateBuilder.calculateFinishDate(propertiesStorage.getPropertyIsFirstRun());
        this.logger = LogManager.getLogger(SiteParser.class);
    }

    /**
     * Grab data hash set.
     *
     * @param url the url
     * @return the hash set
     */
    public HashSet<Vacancy> grabData(String url) {
        HashSet<Vacancy> vacancies = new HashSet<>();
        boolean containsFreshData = true;
        int pageNumber = 1;
        while (containsFreshData) {
            Document doc;
            try {
                doc = Jsoup.connect(String.format("%s/%s", url, String.valueOf(pageNumber))).get();
            } catch (IOException e) {
                logger.error("Trying to parse web-page", e);
                break;
            }
            Elements forumTable = doc.getElementsByClass("forumTable");
            Elements rows = forumTable.first().getElementsByTag("tbody").first().getElementsByTag("tr");
            for (Element row : rows) {
                if (row.getElementsByClass("postslisttopic").size() == 0) {
                    continue;
                }
                Elements topic = row.getElementsByClass("postslisttopic").first().getElementsByTag("a");
                String topicLink = topic.attr("href");
                String topicText = topic.text();
                if (!validTopic(topicText)) {
                    continue;
                }
                Elements author = row.getElementsByClass("altCol").first().getElementsByTag("a");
                String authorName = author.text();

                Element dateElement = row.getElementsByAttributeValue("class", "altCol").get(1);
                Date date = dateBuilder.convertTextToDate(dateElement.text());
                if (date.after(finishDate)) {
                    Vacancy vacancy = new Vacancy(topicText, topicLink, authorName, date);
                    logger.trace("Grabbed vacancy: \n {}", vacancy);
                    vacancies.add(vacancy);
                } else {
                    containsFreshData = false;
                    break;
                }
            }
            if (containsFreshData) {
                pageNumber++;
            }
        }
        propertiesStorage.setProperty("app.isFirstRun", "false");
        return vacancies;
    }

    /**
     * Valid topic boolean.
     *
     * @param topicText the topic text
     * @return the boolean
     */
    public boolean validTopic(String topicText) {
        Pattern p = Pattern.compile("^(.*([Jj][Aa][Vv][Aa])(\\W|$)((?![Ss][Cc][Rr][Ii][Pp][Tt]).*))$");
        Matcher m = p.matcher(topicText);
        return m.matches();
    }

}
