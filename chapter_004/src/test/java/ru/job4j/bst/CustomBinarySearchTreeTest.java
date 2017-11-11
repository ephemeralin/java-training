package ru.job4j.bst;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The Custom binary search tree test.
 */
public class CustomBinarySearchTreeTest {
    /**
     * When create BST then has proper order.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenCreateBSTthenHasProperOrder() throws Exception {
        CustomBinarySearchTree<Integer> binarySearchTree = new CustomBinarySearchTree<Integer>() {
            {
                add(8);
                add(3);
                add(10);
                add(1);
                add(6);
                add(14);
                add(4);
                add(7);
                add(13);
            }
        };
        assertThat(binarySearchTree.toString(), is("[8, 3, 1, 6, 4, 7, 10, 14, 13]"));
    }

}