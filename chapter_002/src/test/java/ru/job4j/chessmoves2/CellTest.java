package ru.job4j.chessmoves2;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ephemeralin on 05.08.17.
 */
public class CellTest {
    /**
     * Is same to.
     *
     * @throws Exception the exception
     */
    @Test
    public void isSameTo() throws Exception {
        Cell cell1 = new Cell(3, 1);
        Cell cell2 = new Cell(3, 1);
        assertThat(cell1.isSameTo(cell2), is(true));
    }

}