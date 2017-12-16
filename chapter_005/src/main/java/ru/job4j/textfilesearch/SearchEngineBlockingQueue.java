package ru.job4j.textfilesearch;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type Search engine.
 */
public class SearchEngineBlockingQueue {
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
    private final BlockingQueue<String> foundPathsQueue;

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
    public SearchEngineBlockingQueue(String root, String text, List<String> extensions) {
        this.root = root;
        this.text = text;
        this.extensions = extensions;
        this.foundPathsQueue = new ArrayBlockingQueue<>(10);
        this.executor = Executors.newCachedThreadPool();
    }

    /**
     * Gets found paths.
     *
     * @return the found paths
     */
    public BlockingQueue<String> getFoundPathsQueue() {
        return foundPathsQueue;
    }

    /**
     * Start search.
     */
    public void startSearchEngine() {
        List<File> threadFoldersList = new ArrayList<>();
        File rootDir = new File(root);
        threadFoldersList.add(rootDir);

        collectFoldersForThreads(rootDir, threadFoldersList);

        Iterator<File> it = threadFoldersList.iterator();

        while (it.hasNext()) {
            new SearchThread(it.next().getPath(), getFoundPathsQueue()).run();
        }
        new Printer(getFoundPathsQueue()).run();
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
         * Blocking queue for results.
         */
        private final BlockingQueue<String> queue;

        /**
         * Path.
         */
        private final String path;

        /**
         * Instantiates a new Search thread.
         *
         * @param path  the path
         * @param queue the queue
         */
        SearchThread(String path, BlockingQueue<String> queue) {
            this.path = path;
            this.queue = queue;
        }

        /**
         * Searh method.
         *
         * @return list of paths
         */
        private List<String> search() {
            List<String> banchOfPaths = new ArrayList<>();
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
                        banchOfPaths.add(file.getPath());

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return banchOfPaths;
        }

        @Override
        public void run() {
            try {
                Iterator<String> iterator = search().iterator();
                while (iterator.hasNext()) {
                    queue.put(iterator.next());
                }
            } catch (InterruptedException ex) {

            }
        }
    }


    /**
     * The type Printer.
     */
    class Printer implements Runnable {
        /**
         * Blocking queue.
         */
        private final BlockingQueue<String> queue;

        /**
         * Instantiates a new Printer.
         *
         * @param q the q
         */
        Printer(BlockingQueue<String> q) {
            queue = q;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    print(queue.take());
                }
            } catch (InterruptedException ex) {

            }
        }

        /**
         * Print.
         *
         * @param path the path
         */
        void print(String path) {
            System.out.println(path);
        }

    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        ArrayList<String> extensions = new ArrayList<String>() {
            {
                add(".txt");
                add(".xml");
                add(".html");
            }
        };

        SearchEngineBlockingQueue searchEngine = new SearchEngineBlockingQueue("/Users/ephemeralin/tmp", "java", extensions);

        searchEngine.startSearchEngine();
    }
}
