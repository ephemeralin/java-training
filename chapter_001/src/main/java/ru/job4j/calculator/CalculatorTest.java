package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenSubtractOneMinusOneThenZero() {
        Calculator calc = new Calculator();
        calc.subtract(1D, 1D);
        double result = calc.getResult();
        double expected = 0D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenMultiplyTwoAndThreeThenSix() {
        Calculator calc = new Calculator();
        calc.add(2D, 3D);
        double result = calc.getResult();
        double expected = 6D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenDivideSixAndThreeThenTwo() {
        Calculator calc = new Calculator();
        calc.add(6D, 3D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }
}
