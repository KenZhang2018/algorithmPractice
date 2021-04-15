package trainningcamp002.class06;

public class BinarySearchTree {

    public Node root;
    public int size;

    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new Node(value, parent, left, right);
    }

    public Node search(int element) {
        Node node = root;
        while (node != null && node.value != null) {
            if (element == node.value){
                break;
            } else if (element < node.value) {
                node = node.left;
            } else  {
                node = node.right;
            }
        }
        return node;
    }

    public Node insert(int element) {
        if (root == null) {
            root = createNode(element, null, null, null);
            size++;
            return root;
        }
        Node insertParentNode = null;
        Node searchTmpNode = root;
        while (searchTmpNode != null && searchTmpNode.value != null) {
            insertParentNode = searchTmpNode;
            if (element < searchTmpNode.value) {
                searchTmpNode = root.left;
            } else {
                searchTmpNode = root.right;
            }
        }
        Node newNode = createNode(element, null, null, null);
        if (insertParentNode.value == newNode.value) {
            insertParentNode.left = newNode;
        } else {
            insertParentNode.right = newNode;
        }
        newNode.parent = insertParentNode;
        size++;
        return newNode;
    }

    public Node delete(int element) {
        Node deleteNode = search(element);
        if (deleteNode != null) {
            return deleteNode(deleteNode);
        }
        return null;
    }

    protected Node deleteNode(Node deleteNode) {
        Node nodeToReturn = null;
        if (deleteNode != null) {
            if (deleteNode.isLeaf()) {
                nodeToReturn = transplant(deleteNode, null);
            } else if (deleteNode.left != null && deleteNode.right == null) {
                nodeToReturn = transplant(deleteNode, deleteNode.left);
            } else if (deleteNode.left == null && deleteNode.right != null) {
                nodeToReturn = transplant(deleteNode, deleteNode.right);
            } else if (deleteNode.left != null && deleteNode.right != null) {
                Node mostLeft = getMinimum(deleteNode);
                if (mostLeft != deleteNode.right) {
                    transplant(mostLeft, mostLeft.right);

                    deleteNode.right.parent = mostLeft;
                    mostLeft.right = deleteNode.right;
                }

                deleteNode.left.parent = mostLeft;
                mostLeft.left = deleteNode.left;

                nodeToReturn = transplant(deleteNode, mostLeft);

            }
            size--;
        }
        return nodeToReturn;
    }

    private Node getMinimum(Node deleteNode) {
        Node mostLeft = deleteNode.right;
        while (mostLeft.left != null) {
            mostLeft = mostLeft.left;
        }
        return mostLeft;
    }

    /**
     * Put one node from tree (newNode) to the place of another (nodeToReplace).
     *
     * @param nodeToReplace
     *            Node which is replaced by newNode and removed from tree.
     * @param newNode
     *            New node.
     *
     * @return New replaced node.
     */
    private Node transplant(Node nodeToReplace, Node newNode) {
        if (nodeToReplace.parent == null) {
            root = newNode;
        } else if (nodeToReplace.parent.left == nodeToReplace) {
            nodeToReplace.parent.left = newNode;
        } else if (nodeToReplace.parent.right == nodeToReplace) {
            nodeToReplace.parent.right = newNode;
        }
        if (newNode != null) {
            newNode.parent = nodeToReplace.parent;

        }
        return newNode;
    }


    public class Node {
        public Integer value;
        public Node parent;
        public Node left;
        public Node right;

        public Node(int value, Node parent, Node left, Node right) {
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

    }

}
