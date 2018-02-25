package ru.job4j.siteparser;

import java.util.Date;
import java.util.Objects;

/**
 * The type Vacancy.
 */
public class Vacancy {
    /**
     * Link.
     */
    private String link;
    /**
     * Topic.
     */
    private String topic;
    /**
     * Name.
     */
    private String name;
    /**
     * Date.
     */
    private Date date;

    /**
     * Instantiates a new Vacancy.
     *
     * @param topic the topic
     * @param link  the link
     * @param name  the name
     * @param date  the date
     */
    public Vacancy(String topic, String link, String name, Date date) {
        this.link = link;
        this.topic = topic;
        this.name = name;
        this.date = date;
    }

    /**
     * Gets topic.
     *
     * @return the topic
     */
    public String getTopic() {
        return topic;
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
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets topic.
     *
     * @param topic the topic
     */
    public void setTopic(String topic) {
        this.topic = topic;
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
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets link.
     *
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets link.
     *
     * @param link the link
     */
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(getLink(), vacancy.getLink())
                && Objects.equals(getName(), vacancy.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLink(), getName());
    }

    @Override
    public String toString() {
        return "Vacancy{"
                + "topic='" + topic + '\''
                + ", name='" + name + '\''
                + ", date=" + date
                + '}';
    }
}
