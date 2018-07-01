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

    @Column(name = "sold")
    private boolean sold;

    @Column(name = "image")
    private byte[] image;

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
     * Gets make.
     *
     * @return the make
     */
    public Make getMake() {
        return make;
    }

    /**
     * Sets make.
     *
     * @param make the make
     */
    public void setMake(Make make) {
        this.make = make;
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

    /**
     * Gets model.
     *
     * @return the model
     */
    public Model getModel() {
        return model;
    }

    /**
     * Sets model.
     *
     * @param model the model
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * Get image byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * Gets base 64 image file.
     *
     * @return the base 64 image file
     */
    public String getBase64imageFile() {
        return base64imageFile;
    }

    /**
     * Sets base 64 image file.
     *
     * @param base64imageFile the base 64 image file
     */
    public void setBase64imageFile(String base64imageFile) {
        this.base64imageFile = base64imageFile;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Gets sold.
     *
     * @return the sold
     */
    public boolean getSold() {
        return sold;
    }

    /**
     * Sets sold.
     *
     * @param sold the sold
     */
    public void setSold(boolean sold) {
        this.sold = sold;
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
