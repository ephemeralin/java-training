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

        System.out.printf("Man's name is %s", defaultMan.getName());
        System.out.println();

        defaultMan.getDamage(40);

        Doctor mrDoc = new Doctor("Dr.House", 50, "California", "divorsed");

        mrDoc.treat(defaultMan);

        defaultMan.checkHealth();
    }
}