package ru.job4j.nonblocking.cache;

/**
 * The type Task.
 */
public class Task {
    /**
     * ID.
     */
    private int id;
    /**
     * Name.
     */
    private String name;
    /**
     * Data.
     */
    private String data;
    /**
     * Version.
     */
    private int version;

    /**
     * Instantiates a new Task.
     *
     * @param name the name
     * @param data the data
     */
    public Task(String name, String data) {
        this.name = name;
        this.data = data;
        this.version = 0;
    }

    /**
     * Instantiates a new Task.
     *
     * @param anotherTask the another task
     */
    public Task(Task anotherTask) {
        this.name = anotherTask.name;
        this.data = anotherTask.data;
        this.id = anotherTask.id;
        this.version = anotherTask.version;
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

    /**
     * Gets data.
     *
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Increase version.
     */
    protected void increaseVersion() {
        this.version++;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    protected void setId(int id) {
        this.id = id;
    }
}
