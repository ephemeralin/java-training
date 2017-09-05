package ru.job4j.examadd_v2;

import ru.job4j.examadd.Department;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * The type Application.
 */
public class Application {
    /**
     * Prepare array list.
     *
     * @param departments the initial array list
     * @return the prepared array list
     */
    public ArrayList<Department> prepare(ArrayList<Department> departments) {
        addMissingDepartments(departments);
        Collections.sort(departments);
        return departments;
    }

    /**
     * Prepare array list with reverse order.
     *
     * @param departments the initial array list
     * @return the array list
     */
    public ArrayList<Department> prepareWithReverseOrder(ArrayList<Department> departments) {
        addMissingDepartments(departments);
        Collections.sort(departments, Collections.reverseOrder());
        return departments;
    }


    /**
     * Contains boolean.
     *
     * @param list       the list
     * @param department the department
     * @return the boolean
     */
    public boolean contains(List<Department> list, Department department) {
        for (Department d : list) {
            if (department.equals(d)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add missing departments.
     *
     * @param departments the departments
     * @return departments list with added missing deps
     */
    public ArrayList<Department> addMissingDepartments(ArrayList<Department> departments) {
        ListIterator<Department> listIterator = departments.listIterator();

        ArrayList<Department> createdDepartments = new ArrayList<Department>();

        while (listIterator.hasNext()) {
            String[] piecesOfName = listIterator.next().getName().split("/");
            String searchName = "";
            for (String piece : piecesOfName) {
                searchName = searchName.isEmpty() ? piece : String.format("%s/%s", searchName, piece);
                Department searchDepartment = new Department(searchName);
                if (!contains(createdDepartments, searchDepartment) & !contains(departments, searchDepartment)) {
                    listIterator.add(searchDepartment);
                    createdDepartments.add(searchDepartment);
                }
            }
        }
        return departments;
    }

}
