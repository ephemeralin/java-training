package ru.job4j.textfilesearch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The type Search engine.
 */
@ThreadSafe
public class SearchEngine {
    /**
     * The Lock.
     */
    private final Object lock = new Object();
    /**
     * Root path.
     */
    private final String root;
    /**
     * Text string to search.
     */
    private final String text;
    /**
     * List of available extensions for searching.
     */
    private final List<String> extensions;

    /**
     * List of found paths.
     */
    @GuardedBy("lock")
    private final List<String> foundPaths;

    /**
     * The Executor.
     */
    private final ExecutorService executor;

    /**
     * Instantiates a new Search engine.
     *
     * @param root       the root
     * @param text       the text
     * @param extensions the extensions
     */
    public SearchEngine(String root, String text, List<String> extensions) {
        this.root = root;
        this.text = text;
        this.extensions = extensions;
        this.foundPaths = new ArrayList<>();
        this.executor = Executors.newWorkStealingPool();
    }

    /**
     * Gets found paths.
     *
     * @return the found paths
     */
    public List<String> getFoundPaths() {
        return foundPaths;
    }

    /**
     * Start search.
     *
     * @throws IOException the io exception
     */
    public void startSearch() throws IOException {
        List<File> threadFoldersList = new ArrayList<>();
        File rootDir = new File(root);
        threadFoldersList.add(rootDir);

        collectFoldersForThreads(rootDir, threadFoldersList);

        Iterator<File> it = threadFoldersList.iterator();
        while (it.hasNext()) {
            this.executor.execute(new SearchThread(it.next().getPath()));
        }

        this.executor.shutdown();

        executor.shutdown();
        try {
            if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    /**
     * Collect Directories for creating Threads.
     * @param dir from where starts searching process
     * @param threadFoldersList found dirs will be added here
     */
    private void collectFoldersForThreads(File dir, List<File> threadFoldersList) {
        File[] files = dir.listFiles((dir1, name) -> {
                if (new File(String.format("%s/%s", dir.getPath(), name)).isDirectory()) {
                    return true;
                }
                return false;
            });
        for (File file : files) {
            threadFoldersList.add(file);
            collectFoldersForThreads(file, threadFoldersList);
        }
    }

    /**
     * Just add found path to the list.
     * @param path the path
     */
    private void addFoundPath(String path) {
        synchronized (lock) {
            foundPaths.add(path);
        }
    }

    /**
     * Search in file boolean.
     *
     * @param pathString the path string
     * @return the boolean
     * @throws IOException the io exception
     */
    public boolean searchInFile(String pathString) throws IOException {
        boolean isContain = false;

        Path path = Paths.get(pathString);

        try (Scanner scanner = new Scanner(path, StandardCharsets.UTF_8.name())) {
            while (scanner.hasNextLine()) {
                if (scanner.nextLine().contains(this.text)) {
                    isContain = true;
                    break;
                }
            }
        }

        return isContain;
    }

    /**
     * The type Search thread.
     */
    class SearchThread implements Runnable {
        /**
         * Path.
         */
        private final String path;

        /**
         * Instantiates a new Search thread.
         *
         * @param path the path
         */
        SearchThread(String path) {
            this.path = path;
        }

        /**
         * Searh method.
         */
        private void search() {
            File dir = new File(path);
            File[] files = dir.listFiles((dir1, name) -> {
                boolean isAccepted = false;
                for (String ext : extensions) {
                    if (name.toLowerCase().endsWith(ext)) {
                        isAccepted = true;
                        break;
                    }
                }
                return isAccepted;
            });

            for (File file : files) {
                try {
                    if (searchInFile(file.getPath())) {
                        addFoundPath(file.getPath());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            search();
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        ArrayList<String> extencions = new ArrayList<String>() {
            {
                add(".txt");
                add(".xml");
                add(".html");
            }
        };
        SearchEngine searchEngine = new SearchEngine("/Users/ephemeralin/tmp", "java", extencions);
        searchEngine.startSearch();

        for (String path : searchEngine.getFoundPaths()) {
            System.out.println(path);
        }
    }
}
