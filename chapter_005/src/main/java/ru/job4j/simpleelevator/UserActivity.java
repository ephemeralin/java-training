package ru.job4j.simpleelevator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The type User activity.
 */
public class UserActivity implements Runnable {

    /**
     * Elevator instance.
     */
    private final Elevator elevator;

    /**
     * Instantiates a new User activity.
     *
     * @param elevator the elevator
     */
    public UserActivity(Elevator elevator) {
        this.elevator = elevator;
    }

    /**
     * User activity input.
     */
    private void userActivityInput() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String userInput = "";
        while (true) {
            try {
                userInput = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (userInput.equalsIgnoreCase("quit")) {
                elevator.addWork(new Work(WorkType.stop));
                break;
            }

            int callFloor = 0;
            try {
                callFloor = Integer.parseInt(userInput);
                if (!elevator.isCorrectFloorCall(callFloor)) {
                    System.out.println("Incorrect floor number! It must between 1 and " + elevator.getFloors());
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong number of floor format! Try again...");
                continue;
            }


            if (elevator.isFree()) {
                //give the call of floor
                elevator.addWork(new Work(callFloor, WorkType.externalCall));
            } else if (elevator.isWaitingForDestinationCall()) {
                //give the destination call of floor
                if (elevator.getCurrentFloor() == callFloor) {
                    System.out.println("You are already on this floor!");
                } else {
                    elevator.addWork(new Work(callFloor, WorkType.internalCall));
                }
            } else {
                //elevator is busy
                System.out.println("Elevator is busy now. Try later!");
            }
        }

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        userActivityInput();
    }
}
