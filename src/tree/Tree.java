package tree;

/**
 *
 * @author Paweł
 */
public class Tree {

    private Node root = null;

    private class Node {

        int index;
        double value;

        Node left = null;
        Node right = null;

        public Node(int i, double v) {
            this.index = i;
            this.value = v;
        }

    }
    
    public void insert(int i, double v) {
        root = insert(root, i, v);
    }

    private Node insert(Node n, int i, double v) {

        if (n == null) {
            Node newNode = new Node(i, v);
            return newNode;
        } else if (n.index > i) {
            n.left = insert(n.left, i, v);
            return n;
        } else if (n.index < i) {
            n.right = insert(n.right, i, v);
            return n;
        } else {
            n.value = v;
            return n;
        }

    }

    public double get(int i) {

        Node n = get(root, i);

        if (n != null) {
            return n.value;
        } else {
            return 0.0;
        }

    }

    private Node get(Node n, int i) {

        if (n == null) {
            return null;
        } else if (i > n.index) {
            n = get(n.right, i);
            return n;
        } else if (i < n.index) {
            n = get(n.left, i);
            return n;
        } else {
            return n;
        }

    }

    public void printMe() {
        printTree(root, 0);
    }

    private void printTree(Node n, int d) {
        if (n != null) {
            if (n.left != null) {
                printTree(n.left, d + 1);
            }
            int i;
            for (i = 0; i < d; i++) {
                System.out.print("   ");
            }
            System.out.println("Indeks: " + n.index + " Wartość: " + n.value + " Głębokość: " + d);
            if (n.right != null) {
                printTree(n.right, d + 1);
            }

        }

    }

    public static void main(String[] args) {

        Tree myTree = new Tree();

        System.out.println("Tree Test:");

        myTree.insert(5, 5.5);
        myTree.insert(6, 6.6);
        myTree.insert(3, 3.3);
        myTree.insert(4, 4.4);
        myTree.insert(1, 1.1);
        myTree.insert(8, 8.8);
        myTree.insert(3, 3.9);
        myTree.insert(7, 7.7);
        myTree.insert(2, 2.2);

        myTree.printMe();

        double szukam = myTree.get(2);
        System.out.println("\nPoszukiwany element o indeksie 2: " + szukam);
        szukam = myTree.get(9);
        System.out.println("Poszukiwany element o indeksie 9: " + szukam);
        
    }

}
