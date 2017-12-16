package ru.job4j.textfilesearch;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The type Search engine test.
 */
public class SearchEngineTest {
    /**
     * Search in file test.
     *
     * @throws Exception the exception
     */
    @Test
    @Ignore
    public void searchInFileTest() throws Exception {
        ArrayList<String> extencions = new ArrayList<String>() {
            {
                add(".txt");
                add(".xml");
                add(".html");
            }
        };
        String root = "/Users/ephemeralin/tmp";
        SearchEngine searchEngine = new SearchEngine("/Users/ephemeralin/tmp/", "java", extencions);

        boolean isContained = searchEngine.searchInFile("/Users/ephemeralin/tmp/java_text1.txt");

        assertThat(isContained, is(true));
    }

}