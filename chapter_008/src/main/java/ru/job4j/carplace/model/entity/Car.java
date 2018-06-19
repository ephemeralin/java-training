package ru.job4j.carplace.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * The type Car.
 */
@Entity
@Table(name = "cars", schema = "public")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "body_id", nullable = false)
    private Body body;

    @ManyToOne
    @JoinColumn(name = "engine_id", nullable = false)
    private Engine engine;

    @ManyToOne
    @JoinColumn(name = "transmission_id", nullable = false)
    private Transmission transmission;

    /**
     * Instantiates a new Car.
     *
     * @param id the id
     */
    public Car(int id) {
        this.id = id;
    }

    /**
     * Instantiates a new Car.
     */
    public Car() {
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
     * Gets body.
     *
     * @return the body
     */
    public Body getBody() {
        return body;
    }

    /**
     * Sets body.
     *
     * @param body the body
     */
    public void setBody(Body body) {
        this.body = body;
    }

    /**
     * Gets engine.
     *
     * @return the engine
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * Sets engine.
     *
     * @param engine the engine
     */
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    /**
     * Gets transmission.
     *
     * @return the transmission
     */
    public Transmission getTransmission() {
        return transmission;
    }

    /**
     * Sets transmission.
     *
     * @param transmission the transmission
     */
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return getId() == car.getId() &&
                Objects.equals(getName(), car.getName()) &&
                Objects.equals(getBody(), car.getBody()) &&
                Objects.equals(getEngine(), car.getEngine()) &&
                Objects.equals(getTransmission(), car.getTransmission());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getBody(), getEngine(), getTransmission());
    }

    @Override
    public String toString() {
        return "Car {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", body=" + body +
                ", engine=" + engine +
                ", transmission=" + transmission +
                '}';
    }
}
