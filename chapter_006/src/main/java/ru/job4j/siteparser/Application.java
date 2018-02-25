package ru.job4j.siteparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Application.
 */
public class Application extends TimerTask {
    /**
     * Logger instance.
     */
    private Logger logger;
    /**
     * Properties storage instance.
     */
    private PropertiesStorage propertiesStorage;
    /**
     * Period of the scheduled task running.
     */
    private Long period;
    /**
     * Is database prepared for usage.
     */
    private boolean dbPrepared;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.prepareApp("chapter_006/resources/");
        Timer timer = new Timer();
        timer.schedule(app, 100, app.getPeriod());
    }

    /**
     * Prepare app.
     *
     * @param resourcesPath the resources path
     */
    public void prepareApp(String resourcesPath) {
        setupLogger();
        this.logger = LogManager.getLogger(Application.class);
        this.propertiesStorage = new PropertiesStorage(resourcesPath);
        this.period = this.propertiesStorage.getPropertyPeriodInMillis();
        this.dbPrepared = false;
    }

    /**
     * Start.
     *
     * @param startUrl the start url
     */
    public void start(String startUrl) {
        logger.trace(" ======================== START ========================");
        SiteParser parser = new SiteParser(propertiesStorage);
        HashSet<Vacancy> vacancies = parser.grabData(startUrl);
        if (!vacancies.isEmpty()) {
            logger.trace("{} vacancies for such period", vacancies.size());
            DatabaseProcessor databaseProcessor = new DatabaseProcessor(this.propertiesStorage);
            if (databaseProcessor.initConnection()) {
                if (!dbPrepared) {
                    dbPrepared = databaseProcessor.prepareDatabaseStructure();
                }
            } else {
                logger.trace("Program was stopped because of database connection error");
                return;
            }
            databaseProcessor.putIntoDatabase(vacancies);
            databaseProcessor.closeConnection();
        } else {
            logger.trace("No vacancies for such period.");
        }
        logger.trace(" ======================== FINISH ========================");
    }

    /**
     * Sets logger.
     */
    private void setupLogger() {
        System.setProperty("log4j.configurationFile",  "chapter_006/resources/log4j2.xml");
        System.setProperty("logFilename", "logs/siteparser.log");
    }

    @Override
    public void run() {
        start("http://www.sql.ru/forum/job-offers");
    }

    /**
     * Gets period.
     *
     * @return the period
     */
    public Long getPeriod() {
        return period;
    }
}
