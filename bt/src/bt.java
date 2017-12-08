import java.util.Arrays;

public class bt {

    public static final int START_SEARCH_INPUT_INDEX = 3;

    public static void main(String[] args) {
        System.out.println(Arrays.asList(args));
        int seed = Integer.parseInt(args[0]);
        int max = Integer.parseInt(args[1]);
        int streamLength = Integer.parseInt(args[2]);
        int length = (args.length - START_SEARCH_INPUT_INDEX) / 2;
        int[] searchQueries = new int[length];
        int[] atQueries = new int[length];
        populateSearchAndAtQueriesArray(args, searchQueries, atQueries);

        long startSetupTree = System.currentTimeMillis();
        StreamProcessor processor = setupTree(seed, max, streamLength);
        long treeSetup = System.currentTimeMillis() - startSetupTree;


        long startSearch = System.currentTimeMillis();
        applySearch(searchQueries, processor);
        long averageSearch = (System.currentTimeMillis() - startSearch) / searchQueries.length;

        long startAt = System.currentTimeMillis();
        applyAt(atQueries, processor);
        long averageAt = (System.currentTimeMillis() - startAt) / atQueries.length;


        System.out.println("time(tree setup) = " + treeSetup + " ms");
        System.out.println("time(average applySearch) = " + averageSearch + " ms");
        System.out.println("time(average at) = " + averageAt + " ms");
    }

    private static void applyAt(int[] atQueries, StreamProcessor processor) {
        for (int index = 0; index < atQueries.length; index++) {
            int indexQuery = atQueries[index];
            System.out.println("at(" + indexQuery + ") = " + processor.at(indexQuery));
        }
    }

    private static void applySearch(int[] searchQueries, StreamProcessor processor) {
        for (int index = 0; index < searchQueries.length; index++) {
            int val = searchQueries[index];
            System.out.println("search(" + val + ") = [" + show(processor.search(val)) + "]");
        }
    }

    private static String show(int[] array) {
        String output = "";
        for (int index = 0; index < array.length; index++) {
            output += array[index];
            if (index < array.length - 1)
                output += ",";
        }
        return output;
    }

    private static StreamProcessor setupTree(int seed, int max, int streamLength) {
        Stream stream = new Stream(max, seed);
        StreamProcessor processor = new StreamProcessor();
        for (int index = 0; index < streamLength; index++) {
            int generated = stream.getNext();
            processor.consume(generated);
        }
        return processor;
    }

    private static void populateSearchAndAtQueriesArray(String[] args, int[] searchQueries, int[] atQueries) {
        for (int index = START_SEARCH_INPUT_INDEX; index < args.length; index++) {
            int startAtInputIndex = (args.length + START_SEARCH_INPUT_INDEX) / 2;
            if (index < startAtInputIndex) {
                int searchQueriesIndex = index - START_SEARCH_INPUT_INDEX;
                searchQueries[searchQueriesIndex] = Integer.parseInt(args[index]);
            } else {
                int atQueriesIndex = index - startAtInputIndex;
                atQueries[atQueriesIndex] = Integer.parseInt(args[index]);
            }
        }
    }


}
