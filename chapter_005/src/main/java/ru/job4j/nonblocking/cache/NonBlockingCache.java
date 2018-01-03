package ru.job4j.nonblocking.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Non blocking cache.
 */
public class NonBlockingCache {
    /**
     * The Container.
     */
    private ConcurrentHashMap<Integer, Task> container;
    /**
     * The Last id.
     */
    private int lastID;

    /**
     * Instantiates a new Non blocking cache.
     */
    public NonBlockingCache() {
        this.container = new ConcurrentHashMap<>();
        this.lastID = 0;
    }

    /**
     * Add task.
     *
     * @param task the task
     * @return the boolean
     */
    public boolean add(Task task) {
        Task addedTask = container.computeIfAbsent(this.lastID, k -> {
            task.setId(this.lastID++);
            return task;
        });
        return addedTask != null;
    }

    /**
     * Get task.
     *
     * @param id the id
     * @return the task
     */
    public Task get(int id) {
        Task foundTask = container.get(id);
        return foundTask != null ? new Task(foundTask) : null;
    }

    /**
     * Update task.
     *
     * @param newTask the new value
     */
    public void update(Task newTask) {
        System.out.println("new task v" + newTask.getVersion());

        Task updatedTask = container.computeIfPresent(newTask.getId(), (k, v) -> {
            Task oldValue = container.get(newTask.getId());
            System.out.println("old task v" + oldValue.getVersion());
            if (oldValue.getVersion() == newTask.getVersion()) {
                newTask.increaseVersion();
                return newTask;
            } else {
                return oldValue;
            }
        });

        System.out.println("updated task v" + updatedTask.getVersion());
        System.out.println("updated task name " + updatedTask.getName());
        if (!newTask.equals(updatedTask)) {
            System.out.println("Version conflict. Task has not beet updated.");
        }
    }

    /**
     * Delete task.
     *
     * @param id the id
     * @return the task
     */
    public Task delete(int id) {
        return container.remove(id);
    }
}
