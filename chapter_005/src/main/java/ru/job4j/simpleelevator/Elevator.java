package ru.job4j.simpleelevator;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type Elevator.
 */
public class Elevator implements Runnable {
    /**
     * Number of floors in the building.
     */
    private final int floors;
    /**
     * Height of floor in meters.
     */
    private final int heightOfFloor;
    /**
     * Speed of the lift in meters per second.
     */
    private final int speed;
    /**
     * Door delay in seconds.
     */
    private final int doorDelay;
    /**
     * Door speed in meters per second.
     */
    private final int doorSpeed;

    /**
     * The Works.
     */
    private LinkedBlockingQueue<Work> works;

    /**
     * Current floor number.
     */
    private int currentFloor;
    /**
     * Current height of lift.
     */
    private int currentHeight;
    /**
     * Is waiting for destination call.
     */
    private boolean waitingForDestinationCall;
    /**
     * Is door is closed.
     */
    private boolean doorIsClosed;
    /**
     * Is lift busy.
     */
    private ReentrantLock isBusy;
    /**
     * Is shut down.
     */
    private boolean shutDown;

    /**
     * Instantiates a new Elevator.
     *
     * @param parameters the parameters
     */
    public Elevator(Parameters parameters) {
        this.floors = parameters.getFloors();
        this.heightOfFloor = parameters.getHeightOfFloor();
        this.speed = parameters.getSpeed();
        this.doorDelay = parameters.getDoorDelay();

        this.works = new LinkedBlockingQueue<>();
        this.isBusy = new ReentrantLock();

        this.doorSpeed = 3;
        this.currentFloor = 1;
        this.currentHeight = 0;
        this.waitingForDestinationCall = false;
        this.doorIsClosed = true;
        this.shutDown = false;

        System.out.println("Elevator is on the first floor. Waiting for calling...");
    }

    /**
     * Is waiting for destination call boolean.
     *
     * @return the boolean
     */
    public boolean isWaitingForDestinationCall() {
        return waitingForDestinationCall;
    }

    /**
     * Open door.
     *
     * @throws InterruptedException the interrupted exception
     */
    public void openDoor() throws InterruptedException {
        System.out.println("    - door is opening");
        Thread.sleep(doorSpeed * 1000);
        this.doorIsClosed = false;
        System.out.println("Door has opened");
    }

    /**
     * Close door.
     *
     * @throws InterruptedException the interrupted exception
     */
    public void closeDoor() throws InterruptedException {
        System.out.println("    - door is closing");
        Thread.sleep(doorSpeed * 1000);
        this.doorIsClosed = true;
        System.out.println("Door has closed");    }

    /**
     * Is free boolean.
     *
     * @return the boolean
     */
    public boolean isFree() {
        return !isBusy.isLocked();
    }

    /**
     * Sets busy.
     *
     * @return the busy
     */
    public boolean setBusy() {
        return this.isBusy.tryLock();
    }

    /**
     * Sets free.
     */
    public void setFree() {
        this.isBusy.unlock();
    }

    /**
     * Sets waiting for destination call.
     *
     * @param waitingForDestinationCall the waiting for destination call
     */
    public void setWaitingForDestinationCall(boolean waitingForDestinationCall) {
        this.waitingForDestinationCall = waitingForDestinationCall;
    }

    /**
     * Floor to meters int.
     *
     * @param floor the floor
     * @return the int
     */
    public int floorToMeters(int floor) {
        return (floor - 1) * this.heightOfFloor;
    }

    /**
     * Move lift from startHeight to finishHeight.
     * @param startHeight start height
     * @param finishHeight finish height
     * @throws InterruptedException the interrupted exception
     */
    private void move(int startHeight, int finishHeight) throws InterruptedException {
        if (startHeight > finishHeight) {
            if (this.currentHeight != finishHeight) {
                while (this.currentHeight > finishHeight) {
                    showStage("    - going down: ");
                    Thread.sleep(1000);
                    this.currentHeight = this.currentHeight - this.speed;
                }
                showStage("    - going down: ");
            }
            System.out.println("Elevator has stopped.");
        } else if (startHeight < finishHeight) {
            if (this.currentHeight != finishHeight) {
                while (this.currentHeight < finishHeight) {
                    showStage("    - going up: ");
                    Thread.sleep(1000);
                    this.currentHeight = this.currentHeight + this.speed;
                }
                showStage("    - going up: ");
            }
            System.out.println("Elevator has stopped.");
        }
    }

    /**
     * Gets current floor.
     *
     * @return the current floor
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Shut down.
     *
     * @throws InterruptedException the interrupted exception
     */
    public void shutDown() throws InterruptedException {
        System.out.println("Elevator is shutting down...");
        Thread.sleep(3000);
        this.shutDown = true;
        System.out.println("Bye!");
    }

    /**
     * Is lift shutdown.
     * @return boolean
     */
    private boolean isShutDown() {
        return this.shutDown;
    }

    /**
     * Show current stage of the lift.
     * @param s stage
     */
    private void showStage(String s) {
        System.out.println(s + this.currentHeight + " meters");
        if (this.currentHeight % this.heightOfFloor == 0) {
            currentFloor = this.currentHeight / this.heightOfFloor + 1;
            System.out.println("Current floor: " + currentFloor);
        }
    }

    /**
     * Do work.
     * @param work work
     * @throws InterruptedException the interrupted exception
     */
    private void doWork(Work work) throws InterruptedException {
        if (work.getWorkType() == WorkType.stop) {
            shutDown();
        } else if (work.getWorkType() == WorkType.externalCall) {
            setBusy();
            move(currentHeight, floorToMeters(work.getFloor()));
            openDoor();
            setWaitingForDestinationCall(true);
            System.out.println("Elevator is waiting for destination floor...");

        } else if (work.getWorkType() == WorkType.internalCall) {
            closeDoor();
            setWaitingForDestinationCall(false);
            move(currentHeight, floorToMeters(work.getFloor()));
            openDoor();
            System.out.println("Elevator has arrived. Please, exit...");
            if (!doorIsClosed) {
                Thread.sleep(doorDelay * 1000);
            }
            closeDoor();
            setFree();
            System.out.println(String.format("Waiting for calling on %d floor...", currentFloor));
        }
    }

    /**
     * Add work.
     *
     * @param work the work
     */
    public void addWork(Work work) {
        works.add(work);
    }

    /**
     * Is correct floor call boolean.
     *
     * @param callFloor the call floor
     * @return the boolean
     */
    public boolean isCorrectFloorCall(int callFloor) {
        return callFloor <= this.floors & callFloor > 0;
    }

    @Override
    public void run() {
        while (!isShutDown()) {
            if (!isFree() & !isWaitingForDestinationCall()) {
                continue;
            }
            try {
                doWork(works.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
}
