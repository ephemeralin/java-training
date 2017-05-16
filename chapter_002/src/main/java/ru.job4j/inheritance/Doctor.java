package ru.job4j.inheritance;

/**
 * Created by ephemeralin on 15.05.17.
 */
public class Doctor extends Human {
    /**
     * The Degree.
     */
    private int degree;
    /**
     * The Number of assistance.
     */
    private int numberOfAssistance;

    /**
     * Instantiates a new Doctor.
     *
     * @param name     the name
     * @param age      the age
     * @param location the location
     * @param status   the status
     */
    public Doctor(String name, int age, String location, String status) {
        super(name, age, location, status);
    }

    /**
     * Treat.
     *
     * @param pacient the pacient
     */
    public void treat(Client pacient) {
        String nameOfPacient = pacient.getName();
        while (pacient.isIll()) {
            pacient.getMeds(10);
        }
        System.out.printf("%s treats %s", this.getName(), nameOfPacient);
        System.out.println();
    }

    /**
     * Gets degree.
     *
     * @return the degree
     */
    public int getDegree() {
        return degree;
    }

    /**
     * Gets number of assistance.
     *
     * @return the number of assistance
     */
    public int getNumberOfAssistance() {
        return numberOfAssistance;
    }
}
