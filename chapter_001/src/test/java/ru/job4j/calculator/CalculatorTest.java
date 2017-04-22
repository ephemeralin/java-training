package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test of Calculator.
*
* @author Viacheslav Piliugin (mailto:ephemeralin@gmail.com)
* @version $Id$
* @since 0.1
*/

public class CalculatorTest {
    /**
    * Test add.
    */
    @Test
    public final void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
    * Test subtract.
    */
    @Test
    public final void whenSubtractOneMinusOneThenZero() {
        Calculator calc = new Calculator();
        calc.subtract(1D, 1D);
        double result = calc.getResult();
        double expected = 0D;
        assertThat(result, is(expected));
    }

    /**
    * Test multiply.
    */
    @Test
    public final void whenMultiplyTwoAndOneThenTwo() {
        Calculator calc = new Calculator();
        calc.multiply(2D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
    * Test divide.
    */
    @Test
    public final void whenDivideTwoAndTwoThenOne() {
        Calculator calc = new Calculator();
        calc.divide(2D, 2D);
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }
}
