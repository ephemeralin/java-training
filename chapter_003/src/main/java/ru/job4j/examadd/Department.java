package ru.job4j.examadd;

/**
 * The type Department.
 */
public class Department implements Comparable<Department> {
    /**
     * The Name.
     */
    private String name;

    /**
     * Getter for Name.
     * @return name
     */
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Department another) {
        int compareResult;
        if (!this.name.contains("/")) {
            compareResult = this.name.compareTo(another.name);
        } else {
            compareResult = this.toString().compareTo(another.toString());
        }
        return compareResult;
    }

    /**
     * Instantiates a new Department.
     *
     * @param name the name
     */
    public Department(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object another) {
        return this.toString().equals(another.toString());
    }

    @Override
    public int hashCode() {
        return this.hashCode();
    }
}
