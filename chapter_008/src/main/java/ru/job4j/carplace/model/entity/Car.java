package ru.job4j.carplace.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * The type Car.
 */
@Data
@Entity
@Table(name = "cars", schema = "public")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "sold")
    private boolean sold;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "date")
    private long date;

    @ManyToOne
    @JoinColumn(name = "body_id", nullable = false)
    private Body body;

    @ManyToOne
    @JoinColumn(name = "engine_id", nullable = false)
    private Engine engine;

    @ManyToOne
    @JoinColumn(name = "transmission_id", nullable = false)
    private Transmission transmission;

    @ManyToOne
    @JoinColumn(name = "make_id", nullable = false)
    private Make make;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @Column(name = "image_base64")
    private String base64imageFile;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

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

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", body=" + body +
                ", engine=" + engine +
                ", transmission=" + transmission +
                ", make=" + make +
                ", model=" + model +
                '}';
    }
}
