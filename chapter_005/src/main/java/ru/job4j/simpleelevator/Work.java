package ru.job4j.simpleelevator;

/**
 * The type Work.
 */
public class Work {
    /**
     * Target floor.
     */
    private int floor;
    /**
     * Work type.
     */
    private final WorkType workType;


    /**
     * Instantiates a new Work.
     *
     * @param floor    the floor
     * @param workType the work type
     */
    public Work(int floor, WorkType workType) {
        this.floor = floor;
        this.workType = workType;
    }

    /**
     * Instantiates a new Work.
     *
     * @param workType the work type
     */
    public Work(WorkType workType) {
        this.workType = workType;
    }

    /**
     * Gets floor.
     *
     * @return the floor
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Gets work type.
     *
     * @return the work type
     */
    public WorkType getWorkType() {
        return workType;
    }
}
