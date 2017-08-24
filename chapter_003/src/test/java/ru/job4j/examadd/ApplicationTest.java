package ru.job4j.examadd;

import org.junit.Test;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The type Application test.
 */
public class ApplicationTest {

    /**
     * Prepare.
     *
     * @throws Exception the exception
     */
    @Test
    public void prepare() throws Exception {
        ArrayList<Department> actualDepartments = new ArrayList<>();

        actualDepartments.add(new Department("K2/SK1/SSK1"));
        actualDepartments.add(new Department("K2"));
        actualDepartments.add(new Department("K1/SK1/SSK2"));

        Application application = new Application();
        actualDepartments = application.prepare(actualDepartments);

        ArrayList<Department> expectedDepartments = new ArrayList<>();
        expectedDepartments.add(new Department("K1"));
        expectedDepartments.add(new Department("K1/SK1"));
        expectedDepartments.add(new Department("K1/SK1/SSK2"));
        expectedDepartments.add(new Department("K2"));
        expectedDepartments.add(new Department("K2/SK1"));
        expectedDepartments.add(new Department("K2/SK1/SSK1"));

        assertThat(actualDepartments, is(expectedDepartments));

    }


    /**
     * Prepare with reverse order.
     *
     * @throws Exception the exception
     */
    @Test
    public void prepareWithReverseOrder() throws Exception {
        ArrayList<Department> actualDepartments = new ArrayList<>();

        actualDepartments.add(new Department("K2/SK1/SSK1"));
        actualDepartments.add(new Department("K2"));
        actualDepartments.add(new Department("K1/SK1/SSK2"));

        Application application = new Application();
        actualDepartments = application.prepareWithReverseOrder(actualDepartments);

        ArrayList<Department> expectedDepartments = new ArrayList<>();
        expectedDepartments.add(new Department("K2/SK1/SSK1"));
        expectedDepartments.add(new Department("K2/SK1"));
        expectedDepartments.add(new Department("K2"));
        expectedDepartments.add(new Department("K1/SK1/SSK2"));
        expectedDepartments.add(new Department("K1/SK1"));
        expectedDepartments.add(new Department("K1"));

        assertThat(actualDepartments, is(expectedDepartments));

    }

}