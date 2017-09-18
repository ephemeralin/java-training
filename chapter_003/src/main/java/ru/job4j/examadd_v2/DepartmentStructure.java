package ru.job4j.examadd_v2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * The type Department.
 */
public class DepartmentStructure {
    /**
     * The Name.
     */
    private ArrayList<String> departments;

    class Comp implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return 0;
        }
    }

    public DepartmentStructure(ArrayList<String> departments) {
        ArrayList<String> departmentsSorted = new ArrayList<String>();
        this.departments = departmentsSorted;
    }

    //    @Override
//    public int compareTo(Department another) {
//        int compareResult;
//        if (!this.name.contains("/")) {
//            compareResult = this.name.compareTo(another.name);
//        } else {
//            compareResult = this.toString().compareTo(another.toString());
//        }
//        return compareResult;
//    }


}
