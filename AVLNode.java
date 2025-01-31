package sayingsDatabase;

/**
 * The AVLNode class provides efficient storage and 
 * retrieval of Hawaiian sayings. 
 * 
 * @author Binh Tran and Ellie Ishii
 *
 */
public class AVLNode {
  private Saying saying;  // the saying stored in this node
  private AVLNode left;   // reference to the left child in the AVL tree
  private AVLNode right;  // reference to the right child in the AVL tree
  private int height;     // height of the node to maintain the balance in the AVL tree

  // constructor that initializes a new AVLNode with the saying
  public AVLNode(Saying saying) {
      this.saying = saying; // store the saying in the node
      this.height = 1;      // new node is initially added at leaf
  }

  // getters for the saying stored in the node
  public Saying getSaying() {
      return saying;
  }

  // getter for the left child node
  public AVLNode getLeft() {
      return left;
  }

  // setter for the left child node
  public void setLeft(AVLNode left) {
      this.left = left;
  }

  // getter for the right child node
  public AVLNode getRight() {
      return right;
  }

  // setter for the right child node
  public void setRight(AVLNode right) {
      this.right = right;
  }

  // getter for the heigh of the node
  public int getHeight() {
      return height;
  }

  // setter for the height of the node
  public void setHeight(int height) {
      this.height = height; // update the height of the node, which is used for balancing
  }
  
}
