package ru.job4j.tracker;

/**
 * Created by ephemeralin on 21.05.17.
 */
public class Item {
    /**
     * ID.
     */
    private String id;
    /**
     * Name.
     */
    private String name;
    /**
     * Description.
     */
    private String desc;
    /**
     * Date of creation.
     */
    private Long created;
    /**
     * Array of comments.
     */
    private String[] comments;

    /**
     * Instantiates a new Item for Tracker system.
     *
     * @param id   the id
     * @param name the name
     * @param desc the description
     */
    public Item(String id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    /**
     * Gets id of the Item.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets name of the Item.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets desc of the Item.
     *
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Gets created date of the Item.
     *
     * @return the created
     */
    public Long getCreated() {
        if (created == null) {
            return Long.valueOf(0);
        } else {
            return created;
        }
    }

    /**
     * Sets created.
     *
     * @param created the created
     */
    public void setCreated(Long created) {
        this.created = created;
    }

    /**
     * Get comments of the Item.
     *
     * @return the string [ ]
     */
    public String[] getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "[" + this.getId() + "] '" + this.getDesc() + "' (" + this.getName() + ")";
    }
}
