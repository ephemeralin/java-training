package ru.job4j.customlock;

/**
 * The type Lock.
 */
public class Lock {

    /**
     * Is locked flag.
     */
    private boolean isLocked = false;

    /**
     * Lock.
     *
     * @throws InterruptedException the interrupted exception
     */
    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    /**
     * Unlock.
     */
    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}
