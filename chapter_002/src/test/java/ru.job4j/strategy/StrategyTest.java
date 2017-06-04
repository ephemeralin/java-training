package ru.job4j.strategy;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ephemeralin on 04.06.17.
 */
public class StrategyTest {
    /**
     * When draw triangle using strategy then has triangle shape.
     * Test for methods: add, findAll
     */
    @Test
    public void whenDrawTriangleThenHasTriangleShape() {
        DrawContext context = new DrawContext(new Triangle());
        String picture = context.getPicture();
        assertThat(picture, is(
                new StringBuilder().append(" *").append(System.getProperty("line.separator")).append("***").toString()));
    }

    /**
     * When draw square using strategy then has square shape.
     * Test for methods: add, findAll
     */
    @Test
    public void whenDrawSquareThenHasSquareShape() {
        DrawContext context = new DrawContext(new Square());
        String picture = context.getPicture();
        assertThat(picture, is(
                new StringBuilder().append("***").append(System.getProperty("line.separator")).append("***").toString()));
    }
}

