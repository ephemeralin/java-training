package ru.job4j.simpleelevator;


/**
 * The type Application.
 */
public class Application {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        Parameters parameters = new Parameters(args);
        Elevator elevator = new Elevator(parameters);
        Thread elevatorThread = new Thread(elevator);
        elevatorThread.start();

        UserActivity userActivity = new UserActivity(elevator);
        Thread userActivityThread = new Thread(userActivity);
        userActivityThread.start();

        elevatorThread.join();
        userActivityThread.join();
    }
}
