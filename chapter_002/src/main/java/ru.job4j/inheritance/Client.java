package ru.job4j.inheritance;

/**
 * Created by ephemeralin on 15.05.17.
 */
public class Client extends Human {

    /**
     * Level of Health.
     */
    private int levelOfHealth;

    /**
     * Instantiates a new Client.
     */
    public Client() {
        super("John Doe", 30, "Moscow", "married");
        this.levelOfHealth = 100;
    }

    /**
     * Instantiates a new Client.
     *
     * @param name     the name
     * @param age      the age
     * @param location the location
     * @param status   the status
     */
    public Client(String name, int age, String location, String status) {
        super(name, age, location, status);
    }

    /**
     * Is ill boolean.
     *
     * @return the boolean
     */
    public boolean isIll() {
        return levelOfHealth < 80;
    }

    /**
     * Gets damage.
     *
     * @param damage the damage
     */
    public void getDamage(int damage) {
        this.levelOfHealth -= damage;
        System.out.println("Got damage = [" + damage + "] :(");
    }

    /**
     * Check health.
     */
    public void checkHealth() {
        System.out.println("Level of health = [" + this.levelOfHealth + "]");
    }

    /**
     * Gets meds.
     *
     * @param meds the meds
     */
    public void getMeds(int meds) {
        this.levelOfHealth += meds;
    }


    /**
     * Gets level of health.
     *
     * @return the level of health
     */
    public int getLevelOfHealth() {
        return levelOfHealth;
    }
}
