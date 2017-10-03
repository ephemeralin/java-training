package ru.job4j.generics;

/**
 * The type Base.
 */
public abstract class Base {

    /**
     * ID.
     */
    private String id;

    /**
     * Name.
     */
    private String name;

    /**
     * Instantiates a new Base.
     *
     * @param id   the id
     * @param name the name
     */
    public Base(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    String getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    void setId(String id) {
        this.id = id;
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Base base = (Base) o;

        return id.equals(base.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
