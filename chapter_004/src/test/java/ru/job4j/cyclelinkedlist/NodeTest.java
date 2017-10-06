package ru.job4j.cyclelinkedlist;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * The type Node test.
 */
public class NodeTest {
    /**
     * Has cycle.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenStartFromFirstThenHasCycle() throws Exception {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(first);

        assertThat(Node.hasCycle(first), is(true));
    }

    /**
     * Has cycle.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenStartFromThirdThenHasCycle() throws Exception {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(first);

        assertThat(Node.hasCycle(third), is(true));
    }

    /**
     * Has cycle.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenInternalLoopThenHasCycle() throws Exception {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(two);

        assertThat(Node.hasCycle(first), is(true));
    }

    /**
     * Has not cycle.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenHasNotLoopThenHasNotCycle() throws Exception {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.setNext(two);
        two.setNext(third);
        third.setNext(four);

        assertThat(Node.hasCycle(first), is(false));
    }
}