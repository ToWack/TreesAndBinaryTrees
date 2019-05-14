package project4.classes;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import project4.exceptions.InvalidPositionException;
import project4.interfaces.Position;

/**
 * little game to guess an element in a tree within 3 guesses
 * 
 * @author Renato Oppliger, Tom Wacker
 * @version 1.0
 */
public class ProperBinaryTreeGameExample {

	/** available tries to guess an existing number */
	private static final int AVAILABLE_TRIES = 11;
	/** depth of tree to build */
	private static final int TREE_DEPTH = 5;
	/** minimum value for random int generator */
	private static final int MIN_VALUE = 1;
	/** max value for random int generator */
	private static final int MAX_VALUE = 100;
	/** creating binary tree object */
	private static LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
    /** creating scanner object for user inputs */
	private static Scanner scan = new Scanner(System.in);
	/** creating random object for generating random numbers */
	private static Random random = new Random();
	/** creating a counter with initial value 0 */
	private static int counter = 0;
	/** boolean to check if game is finished */
	private static boolean isFinished = false;
	
	/**
	 * creates the binary tree
	 */
	public static void generateBinaryTree() {
		// create root node
		Position<Integer> root = tree.addRoot(generateRandomInt());
		// add children in relation of given depth
		addChild(TREE_DEPTH, root);
	}
	
	/**
	 * generates a random integer between MIN_VALUE and MAX_VALUE
	 * 
	 * @return random integer
	 */
	public static int generateRandomInt() {
	    return random.nextInt((MAX_VALUE - MIN_VALUE) + 1) + MIN_VALUE;
	}
	
	/**
	 * add randomly children to tree in relation of depth
	 * 
	 * @param depth depth of tree
	 * @param p position of node
	 */
	public static void addChild(int depth, Position<Integer> p) {
		depth--;	    
	    if(depth >= 0) {
	    	Position<Integer> leftChild = tree.insertLeft(p, generateRandomInt());
		    Position<Integer> rightChild = tree.insertRight(p, generateRandomInt());
	        addChild(depth, leftChild);
	        addChild(depth, rightChild);
	    }
	}
	
	/**
	 * 
	 * @param node node of binary tree
	 * @param key key to search in nodes
	 * @return boolean if key exists in node
	 */
	public static boolean isNodeExisting(BinaryTreeNode<Integer> node, int key) {
		// if node doesn't exist
		if(node == null)
			return false;
		// if key is found in a node
		if(node.element == (Integer)key)
			return true;
					
		// check left and right child of node
		boolean leftChild = isNodeExisting(node.leftChild, key);
		boolean rightChild = isNodeExisting(node.rightChild, key);
		
		return leftChild || rightChild;
	}
	
	/**
	 * main method
	 * 
	 * @param args unused.
	 */
	public static void main(String[] args) {
		// creates the tree
		generateBinaryTree();
		
		// show information
		System.out.println("Guess an existing number in the binary tree!");
		System.out.println("Number of nodes: " + tree.size());
		System.out.println("Available tries: " + AVAILABLE_TRIES + "\n");
		
		do {
			try {
				System.out.println("Enter an Integer you think exists in the binary tree:");
				int choice = scan.nextInt();
				BinaryTreeNode<Integer> root = (BinaryTreeNode<Integer>)tree.root();
				if(!isNodeExisting(root, choice)) {
					counter++;
					System.out.println("Tries available: " + (AVAILABLE_TRIES - counter));
					if(counter == AVAILABLE_TRIES) {
						System.out.println("YOU LOST!");
						System.out.println(tree);
					}
				} else {
					isFinished = true;
					System.out.println("YOU WON WITH " + (counter + 1) + " TRY/TRIES.");
					System.out.println(tree);
				}
			} catch(InvalidPositionException ex) {
				System.out.println("Error");
			} catch(InputMismatchException ex) {
				System.out.println("Error: Input mismatch. Make sure you entered the correct data type.");
			} catch(RuntimeException ex) {
				System.out.println("Error: Unexpected error occured. " + ex.getMessage());
	        } catch(Exception ex) {
	        	System.out.println(ex.getMessage());
	        }
		} while(counter < AVAILABLE_TRIES && !isFinished);
	}

}
