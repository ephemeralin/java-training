package ru.job4j.customtree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Tree.
 *
 * @param <E> the type parameter
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E>, Iterable<E> {

    /**
     * The Root of the Tree.
     */
    private Node<E> root;

    /**
     * The length of the Tree.
     */
    private int length;

    /**
     * The type Node.
     *
     * @param <E> the type parameter
     */
    class Node<E> {

        /**
         * The Childen.
         */
        private List<Node<E>> childen;
        /**
         * The Value.
         */
        private E value;

        /**
         * Instantiates a new Node.
         *
         * @param value the value
         */
        Node(E value) {
            this.value = value;
            this.childen = new ArrayList<>();
        }

        /**
         * Gets childen.
         *
         * @return the childen
         */
        public List<Node<E>> getChilden() {
            return childen;
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
     * Instantiates a new Tree.
     *
     * @param value the value
     */
    public Tree(E value) {
        this.root = new Node<E>(value);
        this.length = 0;
    }

    /**
     * Gets length.
     *
     * @return the length
     */
    public int getLength() {
        return length;
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
        List<Node<E>> childrenList = getChildrenForNode(parentNode);
        for (Node<E> childNode : childrenList) {
            collectNodes(list, childNode);
        }
    }

    @Override
    public boolean add(E parent, E child) {
        boolean added = false;

        Node<E> parentNode = findParent(root, parent);
        Node<E> childNode = new Node<>(child);

        if (parentNode != null) {
            List<E> allValues = toList();
            if (!allValues.contains(child)) {
                parentNode.getChilden().add(childNode);
                added = true;
                length++;
            }
        }

        return added;
    }

    /**
     * Gets children.
     *
     * @param parentValue the parent value
     * @return the children
     */
    public List<Node<E>> getChildren(E parentValue) {
        Node<E> parentNode = findParent(root, parentValue);
        if (parentNode == null) {
            throw new NoSuchElementException();
        }
        return getChildrenForNode(parentNode);
    }

    /**
     * Gets all child nodes from the parent node.
     * @param node parent
     * @return list of child nodes
     */
    private List<Node<E>> getChildrenForNode(Node<E> node) {
        ArrayList<Node<E>> list = new ArrayList<>();
        for (Node<E> child : node.getChilden()) {
            list.add(child);
        }
        return list;
    }

    /**
     * Find parent node by value.
     * @param currentParent from which search starts
     * @param findingParentValue parent value for search
     * @return parent node
     */
    private Node<E> findParent(Node<E> currentParent, E findingParentValue) {
        Node<E> foundParent = null;

        if (currentParent.getValue().compareTo(findingParentValue) == 0) {
            foundParent = currentParent;
        } else {
            for (Node<E> node : currentParent.getChilden()) {
                foundParent = findParent(node, findingParentValue);
                if (foundParent != null) {
                    break;
                }
            }
        }

        return foundParent;
    }

    /**
     * Returns toString() for the result of toList() convert.
     * @return String.
     */
    @Override
    public String toString() {
        return this.toList().toString();
    }

    @Override
    public Iterator<E> iterator() {
        List<E> elements = toList();
        return elements.iterator();
    }


}