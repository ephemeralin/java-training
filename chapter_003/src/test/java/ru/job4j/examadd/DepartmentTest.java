package ru.job4j.examadd;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The type Department test.
 */
public class DepartmentTest {
    /**
     * When first department is higher then one.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenFirstDepartmentIsHigherThenOne() throws Exception {
        Department firstDepartment = new Department("K2/SK1");
        Department secondDepartment = new Department("K1/SK1/SSK2");
        assertThat(firstDepartment.compareTo(secondDepartment), is(1));
    }

    /**
     * When first department is lower then minus one.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenFirstDepartmentIsLowerThenMinusOne() throws Exception {
        Department firstDepartment = new Department("K1/SK2");
        Department secondDepartment = new Department("K2/SK1/SSK1");
        assertThat(firstDepartment.compareTo(secondDepartment), is(-1));
    }

}