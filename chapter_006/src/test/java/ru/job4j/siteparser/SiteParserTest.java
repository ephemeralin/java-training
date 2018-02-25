package ru.job4j.siteparser;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * The type Site parser test.
 */
public class SiteParserTest {

    /**
     * Parser instance.
     */
    private SiteParser parser;

    /**
     * Prepare.
     */
    @Before
    public void prepare() {
        this.parser = new SiteParser(new PropertiesStorage("resources/"));
    }

    /**
     * Given correct when valid topic then true.
     */
    @Test
    public void givenCorrectWhenValidTopicThenTrue() {
        assertTrue("Is correct? Wrong string 1!",
                parser.validTopic("Программист Java, Банк, Санкт-Петербург"));
        assertTrue("Is correct? Wrong string 2!",
                parser.validTopic("Ищем java-разработчиков"));
        assertTrue("Is correct? Wrong string 2!",
                parser.validTopic("Разработчик JAVA. Москва"));
    }

    /**
     * Given in correct when valid topic then false.
     */
    @Test
    public void givenInCorrectWhenValidTopicThenFalse() {
        assertFalse("Is incorrect? Wrong string 1!",
                parser.validTopic("Full Stack разработчик (JavaScript/C#) | Москва"));
        assertFalse("Is incorrect? Wrong string 2!",
                parser.validTopic("Backend разработчик (PHP7, Symfony 3+) от 150 т.р."));
        assertFalse("Is incorrect? Wrong string 2!",
                parser.validTopic("Lead Java Script"));
    }
}