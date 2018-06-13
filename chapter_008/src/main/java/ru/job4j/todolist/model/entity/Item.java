package ru.job4j.todolist.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * The type Item.
 */
@Entity
@Table(name = "item", schema = "public")
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "created")
    private Long created;

    @Column(name = "description")
    private String description;

    @Column(name = "done")
    private boolean done;

    /**
     * Instantiates a new Item.
     */
    public Item() {
    }

    /**
     * Instantiates a new Item.
     *
     * @param created     the created
     * @param description the description
     * @param done        the done
     */
    public Item(Long created, String description, boolean done) {
        this.created = created;
        this.description = description;
        this.done = done;
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
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets created.
     *
     * @return the created
     */
    public Long getCreated() {
        return created;
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Is done boolean.
     *
     * @return the boolean
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Sets done.
     *
     * @param done the done
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return getId() == item.getId() &&
                isDone() == item.isDone() &&
                Objects.equals(getCreated(), item.getCreated()) &&
                Objects.equals(getDescription(), item.getDescription());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getCreated(), getDescription(), isDone());
    }

    @Override
    public String toString() {
        return "Item {" +
                "id=" + id +
                ", created=" + created +
                ", description='" + description + '\'' +
                ", done=" + done +
                '}';
    }
}
