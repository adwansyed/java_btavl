class BNode {
    public int value;
    public int index;
    public BNode left;
    public BNode right;
    public BNode parent;
    public int balance;
    public int height;

    public BNode() {
        left = right = null;
    }

    public BNode(int value, int index) {
        this.value = value;
        this.index = index;
        left = right = null;
    }

    public BNode(int value, int index, BNode parent) {
        this(value, index);
        this.parent = parent;
    }

    public String toString() {
        String s = "value = " + this.value + " index = " + this.index;
        if (isLeaf()) {
            s += " (Leaf)";
        }
        return s;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}
