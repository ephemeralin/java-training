package ru.job4j.customTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    Node<E> root;

    class Node<E> {
        List<Node<E>> childen;
        E value;

        public Node(E value) {
            this.value = value;
        }
    }

    public Tree(E value) {
        this.root = new Node<E>(value);
    }

    @Override
    public boolean add(E parent, E child) {
        Node<E> node = findParent(root, parent);
        return (node != null);
    }

    public List<Node<E>> getChildren(E parentValue) {
        Node<E> parentNode = findParent(root, parentValue);
        if (parentNode == null) {
            throw new NoSuchElementException();
        }
        ArrayList<Node<E>> list = new ArrayList<>();
        for (Node<E> child : parentNode.childen) {
            list.add(child);
        }
        return list;
    }

    private Node<E> findParent(Node<E> currentParent, E findingParentValue) {
        Node<E> foundParent = null;
        if (currentParent.value.compareTo(findingParentValue) == 0) {
            foundParent = currentParent;
        } else {
            for (Node<E> node : currentParent.childen) {
                foundParent = findParent(node, findingParentValue);
                if (foundParent != null) {
                    break;
                }
            }
        }
        return foundParent;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}