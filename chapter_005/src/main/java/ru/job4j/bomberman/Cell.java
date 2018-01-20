package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Cell.
 */
public final class Cell extends ReentrantLock {
    /**
     * Instantiates a new Cell.
     */
    public Cell() {
        super();
    }

    /**
     * Lock cell.
     *
     * @return isLocked
     * @throws InterruptedException the interrupted exception
     */
    public boolean lockCell() throws InterruptedException {
        boolean isLocked;
        if (this.tryLock(500, TimeUnit.MILLISECONDS)) {
            this.lock();
            isLocked = true;
        } else {
            isLocked = false;
        }
        return isLocked;
    }
}
