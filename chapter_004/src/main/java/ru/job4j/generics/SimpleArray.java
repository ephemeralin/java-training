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
     * Check if index is correct.
     * @param position
     * @return
     */
    private boolean checkIndex(int position) {
        if (position > lastIndex) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Add.
     *
     * @param value the value
     */
    public void add(E value) {
        if (objects.length == lastIndex + 1) {
            throw new IndexOutOfBoundsException();
        }
        this.objects[lastIndex++] = value;
    }

    /**
     * Get item by index.
     *
     * @param position the position
     * @return the item
     */
    public E get(int position) {
        if (!checkIndex(position)) {
            throw new IndexOutOfBoundsException();
        }
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
        if (!checkIndex(position)) {
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
