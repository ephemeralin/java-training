package ru.job4j.simpleelevator;

/**
 * The type Parameters.
 */
public class Parameters {
    /**
     * Number of floors in the building.
     */
    private int floors;
    /**
     * Height of floor in meters.
     */
    private int heightOfFloor;
    /**
     * Speed of the lift in meters per second.
     */
    private int speed;
    /**
     * Door delay in seconds.
     */
    private int doorDelay;

    /**
     * Instantiates a new Parameters.
     *
     * @param args the args
     * @throws Exception the exception
     */
    public Parameters(String[] args) throws Exception {
        if (args.length < 4) {
            throw new Exception("Not enough parameters!");
        }

        boolean hasErrors = false;

        try {
            this.floors = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.err.println("Wrong format of parameter 1 (Number of floors): " + args[0]);
            hasErrors = true;
        }
        try {
            this.heightOfFloor = Integer.parseInt(args[1]);
        } catch (Exception e) {
            System.err.println("Wrong format of parameter 2 (Height of floor, m): " + args[1]);
            hasErrors = true;
        }
        try {
            this.speed = Integer.parseInt(args[2]);
        } catch (Exception e) {
            System.err.println("Wrong format of parameter 3 (Speed of the Lift, m/sec): " + args[2]);
            hasErrors = true;
        }
        try {
            this.doorDelay = Integer.parseInt(args[3]);
        } catch (Exception e) {
            System.err.println("Wrong format of parameter 4 (Door open/close delay (sec): " + args[3]);
            hasErrors = true;
        }

        if (hasErrors) {
            throw new Exception("Wrong parameters format!");
        }
    }


    /**
     * Gets floors.
     *
     * @return the floors
     */
    public int getFloors() {
        return floors;
    }

    /**
     * Gets height of floor.
     *
     * @return the height of floor
     */
    public int getHeightOfFloor() {
        return heightOfFloor;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Gets door delay.
     *
     * @return the door delay
     */
    public int getDoorDelay() {
        return doorDelay;
    }
}
