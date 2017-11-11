package ru.job4j.bst;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Custom binary search tree.
 *
 * @param <E> the type parameter
 */
public class CustomBinarySearchTree<E extends Comparable<E>> {
    /**
     * The Root of the Tree.
     */
    private Node<E> root;


    /**
     * The type Node.
     *
     * @param <E> the type parameter
     */
    class Node<E> {

        /**
         * The Value of the Node.
         */
        private E value;

        /**
         * The left Node.
         */
        private Node<E> leftNode;

        /**
         * The right Node.
         */
        private Node<E> rightNode;

        /**
         * Instantiates a new Node.
         *
         * @param value the value
         */
        Node(E value) {
            this.value = value;
        }

        /**
         * Gets value.
         *
         * @return the value
         */
        public E getValue() {
            return value;
        }
    }

    /**
     * Add child value to the specific parent node.
     * @param parentNode where to add a child.
     * @param child child value
     * @return added child node
     */
    private Node<E> add(Node<E> parentNode, E child) {
        if (parentNode == null) {
            parentNode = new Node<E>(child);
            if (this.root == null) {
                this.root = parentNode;
            }

        } else if (child.compareTo(parentNode.value) <= 0) {
            parentNode.leftNode = add(parentNode.leftNode, child);

        } else if (child.compareTo(parentNode.value) > 0) {
            parentNode.rightNode = add(parentNode.rightNode, child);

        } else {
            throw new UnsupportedOperationException();
        }

        return parentNode;
    }

    /**
     * Add node.
     *
     * @param child the child
     * @return the node
     */
    public Node<E> add(E child) {

        return add(root, child);
    }

    /**
     * Returns toString() for the result of toList() convert.
     * @return String.
     */
    @Override
    public String toString() {
        return this.toList().toString();
    }

    /**
     * To list list.
     *
     * @return the list
     */
    public List<E> toList() {
        List<E> list = new ArrayList<>();
        collectNodes(list, root);
        return list;
    }

    /**
     * Recursive method for collecting all values from the Tree to the list.
     * @param list where values is collecting
     * @param parentNode from which collecting starts
     */
    private void collectNodes(List<E> list, Node<E> parentNode) {
        list.add(parentNode.getValue());
        if (parentNode.leftNode != null) {
            collectNodes(list, parentNode.leftNode);
        }
        if (parentNode.rightNode != null) {
            collectNodes(list, parentNode.rightNode);
        }
    }



}
