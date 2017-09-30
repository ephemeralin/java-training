package ru.job4j.generics;

/**
 * The type Simple array.
 *
 * @param <E> the type parameter
 */
public class SimpleArray<E> {
    /**
     * Array of any type objects.
     */
    private Object[] objects;
    /**
     * Index of addition.
     */
    private int lastIndex = 0;

    /**
     * Instantiates a new Simple array.
     *
     * @param size the size
     */
    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    /**
     * Add.
     *
     * @param value the value
     */
    public void add(E value) {
        this.objects[lastIndex++] = value;
    }

    /**
     * Get e.
     *
     * @param position the position
     * @return the e
     */
    public E get(int position) {
        return (E) this.objects[position];
    }

    /**
     * Update boolean.
     *
     * @param value    the value
     * @param position the position
     * @return the boolean
     */
    public boolean update(E value, int position) {
        if (position > lastIndex) {
            return false;
        } else {
            this.objects[position] = value;
            return true;
        }
    }

    /**
     * Delete boolean.
     *
     * @param position the position
     * @return the boolean
     */
    public boolean delete(int position) {
        if (position > lastIndex) {
            return false;
        } else {
            objects[position] = null;
            return true;
        }
    }

    /**
     * Gets last index.
     *
     * @return the last index
     */
    public int getLastIndex() {
        return lastIndex;
    }
}
