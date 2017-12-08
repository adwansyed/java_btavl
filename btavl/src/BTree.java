class BTree {
    public BNode root;

    public BTree() {
        root = null;
    }

    public void insert(int value, int index) {
        if (root == null) {
            root = new BNode(value, index);
        } else {
            insert(root, value, index);
        }
    }

    public void insert(BNode node, int value, int index) {
        if (value <= node.value) {
            if (node.left == null) {
                node.left = new BNode(value, index);
            } else {
                insert(node.left, value, index);
            }
        } else {
            if (node.right == null) {
                node.right = new BNode(value, index);
            } else {
                insert(node.right, value, index);
            }
        }

    }

    public void insertAVL(int value, int index) {

        // IMPLEMENT THIS METHOD
        // IT WILL INSERT A NODE AND IF THE INSERTION
        // VIOLATES THE AVL CONDITION, THE METHOD WILL
        // BALANCE THE TREE
        if (root == null) {
            root = new BNode(value, index);
        }

        BNode currentNode = root;
        while (true) {
            if (currentNode.value == value)
                return;

            BNode parent = currentNode;

            boolean goLeft = currentNode.value > value;
            currentNode = goLeft ? currentNode.left : currentNode.right;

            if (currentNode == null) {
                if (goLeft) {
                    parent.left = new BNode(value, index, parent);
                } else {
                    parent.right = new BNode(value, index, parent);
                }
                rebalance(parent);
                break;
            }
        }

    }

    private void rebalance(BNode node) {
        setBalance(node);

        if (node.balance == -2) {
            if (height(node.left.left) >= height(node.left.right))
                node = rotateRight(node);
            else
                node = rotateLeftThenRight(node);

        } else if (node.balance == 2) {
            if (height(node.right.right) >= height(node.right.left))
                node = rotateLeft(node);
            else
                node = rotateRightThenLeft(node);
        }

        if (node.parent != null) {
            rebalance(node.parent);
        } else {
            root = node;
        }
    }

    private BNode rotateLeft(BNode node) {

        BNode rightNode = node.right;
        rightNode.parent = node.parent;

        node.right = rightNode.left;

        if (node.right != null)
            node.right.parent = node;

        rightNode.left = node;
        node.parent = rightNode;

        if (rightNode.parent != null) {
            if (rightNode.parent.right == node) {
                rightNode.parent.right = rightNode;
            } else {
                rightNode.parent.left = rightNode;
            }
        }

        setBalance(node, rightNode);

        return rightNode;
    }

    private BNode rotateRight(BNode node) {

        BNode leftNode = node.left;
        leftNode.parent = node.parent;

        node.left = leftNode.right;

        if (node.left != null)
            node.left.parent = node;

        leftNode.right = node;
        node.parent = leftNode;

        if (leftNode.parent != null) {
            if (leftNode.parent.right == node) {
                leftNode.parent.right = leftNode;
            } else {
                leftNode.parent.left = leftNode;
            }
        }

        setBalance(node, leftNode);

        return leftNode;
    }

    private BNode rotateLeftThenRight(BNode n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }

    private BNode rotateRightThenLeft(BNode n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }

    private int height(BNode n) {
        if (n == null)
            return -1;
        return n.height;
    }

    private void setBalance(BNode... nodes) {
        for (BNode n : nodes) {
            reheight(n);
            n.balance = height(n.right) - height(n.left);
        }
    }

    private void reheight(BNode node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    public void show() {
        show(root);
    }

    public void show(BNode node) {
        if (node != null) {
           /* System.out.println("[(" + (node.left != null ? node.left.value : "null") + "),("
                    + node.value + "),("
                    + (node.right != null ? node.right.value : "null") + ")]");*/
            show(node.left);
            System.out.println(node);
            show(node.right);
        }
    }
}