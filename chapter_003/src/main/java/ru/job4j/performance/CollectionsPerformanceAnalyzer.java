package ru.job4j.performance;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;
import java.util.Collection;

/**
 * Created by ephemeralin on 05.08.17.
 */
public class CollectionsPerformanceAnalyzer {

    /**
     * Number of elements in collection.
     */
    private int amount;
    /**
     * Array list.
     */
    private ArrayList<String> arrayList;
    /**
     * Linked list.
     */
    private LinkedList<String> linkedList;
    /**
     * Tree set.
     */
    private TreeSet<String> treeSet;


    /**
     * Instantiates a new Collections performance analyzer.
     *
     * @param amount the amount
     */
    public CollectionsPerformanceAnalyzer(int amount) {
        this.amount = amount;
        this.arrayList = new ArrayList<>();
        this.linkedList = new LinkedList<>();
        this.treeSet = new TreeSet<>();
    }

    /**
     * Add double.
     *
     * @param collection the collection
     * @param amount     the amount
     * @return the double
     */
    public double add(Collection<String> collection, int amount) {
        String randomString;
        long startTime = new Date().getTime();
        for (int i = 0; i < amount; i++) {
            randomString = generateString(100);
            collection.add(randomString);
        }
        long finishTime = new Date().getTime();
        return finishTime - startTime;
    }

    /**
     * Remove double.
     *
     * @param collection the collection
     * @param amount     the amount
     * @return the double
     */
    public double remove(Collection<String> collection, int amount) {
        long startTime = new Date().getTime();
        for (int i = 0; i < amount; i++) {
            collection.remove(0);
        }
        long finishTime = new Date().getTime();
        return finishTime - startTime;
    }

    /**
     * Poll first double.
     *
     * @param treeSet the tree set
     * @param amount  the amount
     * @return the double
     */
    public double pollFirst(TreeSet<String> treeSet, int amount) {
        System.out.println(treeSet.size());
        long startTime = new Date().getTime();
        for (int i = 0; i < amount; i++) {
            treeSet.pollFirst();
        }
        long finishTime = new Date().getTime();
        System.out.println(treeSet.size());
        return finishTime - startTime;
    }

    /**
     * Poll first double.
     *
     * @param linkedList the linked list
     * @param amount     the amount
     * @return the double
     */
    public double pollFirst(LinkedList<String> linkedList, int amount) {
        System.out.println(linkedList.size());
        long startTime = new Date().getTime();
        for (int i = 0; i < amount; i++) {
            linkedList.pollFirst();
        }
        long finishTime = new Date().getTime();
        System.out.println(linkedList.size());
        return finishTime - startTime;
    }

    /**
     * Generate random string.
     * @param length the length of the string
     * @return generated string
     */
    private String generateString(int length) {
        Random random = new Random();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }

    /**
     * Check add.
     */
    public void checkAdd() {
        System.out.println(String.format("ArrayList ADD check for %d strings: ~%s ms", this.amount, add(arrayList, this.amount) / 100));
        System.out.println(String.format("LinkedList ADD check for %d strings: ~%s ms", this.amount, add(linkedList, this.amount) / 100));
        System.out.println(String.format("TreeSet ADD check for %d strings: ~%s ms", this.amount, add(treeSet, this.amount) / 100));
    }

    /**
     * Check delete.
     */
    public void checkDelete() {
        LinkedList<String> linkedListCopy = (LinkedList) linkedList.clone();
        System.out.println(String.format("ArrayList REMOVE check for %d strings: ~%s ms", this.amount, remove(this.arrayList, this.amount / 1000) / 100));
        System.out.println(String.format("LinkedList REMOVE check for %d strings: ~%s ms", this.amount, remove(this.linkedList, this.amount / 1000) / 100));
        System.out.println(String.format("LinkedList POLLFIRST check for %d strings: ~%s ms", this.amount, pollFirst(linkedListCopy, this.amount / 1000) / 100));
        System.out.println(String.format("TreeSet POLLFIRST check for %d strings: ~%s ms", this.amount, pollFirst(this.treeSet, this.amount / 1000) / 100));
    }
}