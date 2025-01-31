package sayingsDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * The AVLTree class efficiently stores and manages Hawaiian sayings.
 * It makes sure that the tree remains balanced during insertions,
 * searching for members, finding first and last sayings, and finding the
 * predecessors, as well as the successors.
 * 
 * @author Binh Tran and Ellie Ishii
 *
 */
public class AVLTree {
    // Inner class representing a node in the AVL tree
    class AVLNode {
        Saying saying; // The saying stored in this node
        AVLNode left, right; // Pointers to left and right child nodes
        int height; // Height of the node for balancing

        // Constructor to create a new AVL node
        public AVLNode(Saying saying) {
            this.saying = saying; // store the saying in the node
            this.height = 1; // New node is initially added at leaf
        }
    }

    private AVLNode root; // Root node of the AVL tree

    // Get the height of the tree
    private int height(AVLNode node) {
        return (node == null) ? 0 : node.height; // Return height or 0 if node is null
    }

    // perform a right rotation on the subtree rooted at y
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left; // Set x, which is the left child of y
        AVLNode T2 = x.right; // Store T2, which is right subtree of x

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights of y and x
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // perform a left rotation on the subtree rooted at x
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right; // Set y, which is the right child of x
        AVLNode T2 = y.left; // Store T2, which is the left subtree of y

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights of x and y 
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get balance factor of node N
    private int getBalance(AVLNode N) {
        return (N == null) ? 0 : height(N.left) - height(N.right); // Calculate balance factor
    }

    // Compare two Hawaiian sayings based on their Hawaiian words
    private int compareHawaiianWords(Saying saying1, Saying saying2) {
        return saying1.getHawaiianWords().compareTo(saying2.getHawaiianWords()); // Compare using natural order
    }

    // perform in-order traversal of the AVL tree and return a list of sayings
    public List<Saying> inOrderTraversal() {
        List<Saying> result = new ArrayList<>(); // store the result of traversal
        inOrderTraversalHelper(root, result); // start from the root
        return result;
    }

    // Helper method for in-order traversal
    private void inOrderTraversalHelper(AVLNode node, List<Saying> result) {
        if (node != null) {
            // Traverse the left subtree
            inOrderTraversalHelper(node.left, result);
            // Visit the current node
            result.add(node.saying);
            // Traverse the right subtree
            inOrderTraversalHelper(node.right, result);
        }
    }

    // Insert a new saying into the AVL tree
    public void insert(Saying saying) {
        root = insert(root, saying); // insert and update root after insertion
    }

    // helper method to insert a saying into the tree
    private AVLNode insert(AVLNode node, Saying saying) {
        // Perform normal BST insertion
        if (node == null)
            return new AVLNode(saying); // Create new node if position is empty

        // Compare Hawaiian sayings for AVL order
        int cmp = compareHawaiianWords(saying, node.saying);
        if (cmp < 0) {
            node.left = insert(node.left, saying); // Insert into left subtree
        } else if (cmp > 0) {
            node.right = insert(node.right, saying); // Insert into right subtree
        } else {
            throw new IllegalArgumentException("Saying already exists in the database."); // Handle duplicates
        }

        // Update the height of the current node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // check the balance of the current node
        int balance = getBalance(node);

        // If this node becomes unbalanced, there are 4 cases
        // CASE 1: Left Left Case
        if (balance > 1 && compareHawaiianWords(saying, node.left.saying) < 0) {
            return rightRotate(node);
        }

        // CASE 2: Right Right Case
        if (balance < -1 && compareHawaiianWords(saying, node.right.saying) > 0) {
            return leftRotate(node);
        }

        // CASE 3: Left Right Case
        if (balance > 1 && compareHawaiianWords(saying, node.left.saying) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // CASE 4: Right Left Case
        if (balance < -1 && compareHawaiianWords(saying, node.right.saying) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return the updated node 
        return node;
    }

    // Get the first saying (minimum key) in the tree
    public Saying first() {
        return first(root); // find the first node
    }

    // helper method to find the first saying
    private Saying first(AVLNode node) {
        if (node == null)
            return null; // If tree is empty
        while (node.left != null) {
            node = node.left; // Go to the leftmost node
        }
        return node.saying; // Return the smallest saying
    }

    // Get the last saying (maximum key) in the tree
    public Saying last() {
        return last(root); // find the last node
    }

    // helper method to find the last saying in the tree
    private Saying last(AVLNode node) {
        if (node == null)
            return null; // If tree is empty
        while (node.right != null) {
            node = node.right; // Go to the rightmost node
        }
        return node.saying; // Return the largest saying
    }

    // Check if a saying exists in the tree
    public boolean member(Saying saying) {
        return member(root, saying); // search for the saying
    }

    // helper method to check if a saying is a member in the tree
    private boolean member(AVLNode node, Saying saying) {
        if (node == null)
            return false; // return false if node is null

        int cmp = compareHawaiianWords(saying, node.saying);

        if (cmp < 0) {
            return member(node.left, saying); // Search in the left subtree
        } else if (cmp > 0) {
            return member(node.right, saying); // Search in the right subtree
        } else {
            return true; // Found the saying
        }
    }

    // Get the predecessor of a given saying
    public Saying predecessor(Saying saying) {
        return predecessor(root, saying); // find the predecessor
    }

    // helper method to find the predecessor of a saying
    private Saying predecessor(AVLNode node, Saying saying) {
        // Find the node and its predecessor
        if (node == null)
            return null; // return null if tree is empty
        int cmp = compareHawaiianWords(saying, node.saying);
        if (cmp < 0) {
            return predecessor(node.left, saying); // search in left subtree
        } else {
            Saying rightPred = predecessor(node.right, saying); // search right
            if (rightPred != null) {
                return rightPred; // return if found
            }
            return node.saying; // return current node's saying
        }
    }

    // Get the successor of a given saying
    public Saying successor(Saying saying) {
        return successor(root, saying); // find the successor
    }

    // helper method to find the successor of a saying
    private Saying successor(AVLNode node, Saying saying) {
        // Find the node and its successor
        if (node == null)
            return null; // return null if the tree is empty
        int cmp = compareHawaiianWords(saying, node.saying);
        if (cmp > 0) {
            return successor(node.right, saying); // search in the right subtree
        } else {
            Saying leftSucc = successor(node.left, saying); // search left
            if (leftSucc != null) {
                return leftSucc; // return if found
            }
            return node.saying; // return current node's saying
        }
    }

    // Find sayings containing a specific Hawaiian word
    public List<Saying> MeHua(String word) {
        List<Saying> results = new ArrayList<>(); // List to store results
        MeHua(root, word, results); // Collect sayings
        return results; // Return the collected sayings
    }

    // Helper method for MeHua to find sayings containing a specific Hawaiian word
    private void MeHua(AVLNode node, String word, List<Saying> results) {
        if (node == null)
            return; // Base case: if node is null

        // Check if the saying contains the specified word
        if (node.saying.getHawaiianWords().contains(word)) {
            results.add(node.saying); // Add to results
        }

        // Recursively search in both left and right subtrees
        MeHua(node.left, word, results);
        MeHua(node.right, word, results);
    }

    // Find sayings containing a specific English word
    public List<Saying> Sayings(String word) {
        List<Saying> results = new ArrayList<>(); // List to store results
        Sayings(root, word, results); // Collect sayings
        return results; // Return the collected sayings
    }

    // Helper method for Sayings to find sayings containing a specific English word
    private void Sayings(AVLNode node, String word, List<Saying> results) {
        if (node == null)
            return; // Base case: if node is null

        // Check if the English translation contains the specified word
        if (node.saying.getEnglishTranslation().contains(word)) {
            results.add(node.saying); // Add to results
        }

        // Traverse left and right subtrees
        Sayings(node.left, word, results);
        Sayings(node.right, word, results);
    }
}
