import java.util.Arrays;

class StreamProcessor {
    private int index;
    private BTree storage;

    // YOU MIGHT HAVE TO MODIF THIS CLASS BY ADDING NEW METHODS AND MEMBERS
    private BTree storageAtQuery;

    public StreamProcessor() {
        this.index = 0;
        this.storage = new BTree();
        this.storageAtQuery = new BTree();
    }

    public void consume(int v) {
        storage.insert(v, index);
        storageAtQuery.insert(index, v);
        index++;
    }

    private BNode searchPrincipalNode(int value, BNode node) {
        if (node != null) {
            if (node.value == value) {
                return node;
            } else {
                BNode foundNode = searchPrincipalNode(value, node.left);
                if (foundNode == null) {
                    foundNode = searchPrincipalNode(value, node.right);
                }
                return foundNode;
            }
        } else {
            return null;
        }
    }

    public int[] search(int v) {
        return searchInTree(v, storage);
    }

    private int[] searchInTree(int v, BTree currentStorage) {
        BNode[] nodes = searchForNodes(v, currentStorage);
        if (nodes.length == 0) {
            return new int[]{-1};
        } else {
            int[] searchResult = new int[nodes.length];
            for (int index = 0; index < nodes.length; index++) {
                searchResult[index] = nodes[index].index;
            }
            return searchResult;
        }
    }

    private BNode[] searchForNodes(int v, BTree currentStorage) {
        BNode node = searchPrincipalNode(v, currentStorage.root);
        if (node == null) {
            return new BNode[0];
        }
        BNode[] otherNodes = otherNodes(v, node.left);
        BNode[] nodes = Arrays.copyOf(otherNodes, otherNodes.length + 1);
        nodes[otherNodes.length] = node;
        return nodes;
    }

    private BNode[] otherNodes(int v, BNode node) {
        if (node != null && node.value == v) {
            BNode[] otherNodes = otherNodes(v, node.left);
            BNode[] newArray = Arrays.copyOf(otherNodes, otherNodes.length + 1);
            newArray[otherNodes.length] = node;
            return newArray;
        }
        return new BNode[0];
    }

    public int at(int i) {
        int[] index = searchInTree(i, storageAtQuery);
        if (index.length > 0) {
            return index[0];
        }
        return -1;
    }

    public void show() {
        storage.show();
    }
}