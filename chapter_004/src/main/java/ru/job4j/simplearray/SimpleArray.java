package ru.job4j.simplearray;

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
     *
     */
    private int index = 0;

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
        this.objects[index++] = value;
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
        if (position > index) {
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
        if (position > index) {
            return false;
        } else {
            objects[position] = null;
            return true;
        }
    }

}
