package ru.job4j.inheritance;

/**
 * Created by ephemeralin on 15.05.17.
 */
public class Application {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Client defaultMan = new Client();

        System.out.println(defaultMan.getName());

        defaultMan.getDamage(40);

        Doctor mrDoc = new Doctor("House", 50, "California", "divorsed");

        mrDoc.treat(defaultMan);

        defaultMan.checkHealth();
    }
}