package project4.classes;

import java.util.InputMismatchException;
import java.util.Scanner;

import project4.exceptions.EmptyTreeException;
import project4.exceptions.InvalidPositionException;
import project4.exceptions.UnemptyTreeException;
import project4.interfaces.Position;

/**
 * application to test the binary tree implementation with a scanner
 * 
 * @author Renato Oppliger, Tom Wacker
 * @version 1.0
 */
public class BinaryTreeScannerExample {
	
	/** creating binary tree object */
    private static LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
    /** creating scanner object for user inputs */
	private static Scanner scan = new Scanner(System.in);
    /** creating and initializing root */
    private static Position<Integer> root = null;

    /**
     * prints the positions of the tree
     */
	public static void printPositions() {
		/** String that is printed out at the end */
		String s = "";
		/** counter for positions */
		int counter = 0;
    	System.out.println("Your current positions:");
    	
    	if(tree.positions().isEmpty()) {
    		System.out.println("Empty list");
    	} else {
	    	/** go through positions and create a String */
	    	for(Position<Integer> p : tree.positions()) {
	    		BinaryTreeNode<Integer> node = (BinaryTreeNode<Integer>)p;
	    		
	    		/** check if position is root */
	    		if(tree.isRoot(p))
	    			s = "Root (";
	    		else
	    			s = "Node (";
	    		
	    		/** create main part of the String */
	    		s += counter + "): " + p.element() + ", depth: " + tree.depth(p);
	    		
	    		/** check if it is possible to add a child */
				if(node.leftChild != null && node.rightChild != null)
					s += " (full)";
				
				counter++;
				System.out.println(s);
	    	}
    	}
		System.out.println("\n");
	}
	
	/**
	 * main method
	 * 
	 * @param args unused.
	 */
	public static void main(String[] args) {
        /** creating char to check if user wants to use another operation */
        char ch;
        /** two Integers for getting the position */
        int pos1;
        int pos2;
        /** two Integers where the value to save is stored */
        int element1;
        int element2;
        
        /**  print out binary tree operations  */
        do {
		    System.out.println("For using an operation, enter the number.\n");
		    System.out.println("1:  add root node");
		    System.out.println("2:  insert child");
		    System.out.println("3:  insert children");
		    System.out.println("4:  insert left node");
		    System.out.println("5:  insert right node");
		    System.out.println("6:  replace element");
		    System.out.println("7:  swap elements");
		    System.out.println("8:  get root position");
		    System.out.println("9:  get left child position");
		    System.out.println("10: get right child position");
		    System.out.println("11: get parent of a position");
		    System.out.println("12: get children of a position");
		    System.out.println("13: get descendants of a position");
		    System.out.println("14: get ancestors of a position");
		    System.out.println("15: get sibling of a position");
		    System.out.println("16: get elements of the tree");
		    System.out.println("17: get positions of the tree");
		    System.out.println("18: check if position is root ");
		    System.out.println("19: check if position has a left child");
		    System.out.println("20: check if position has a right child");
		    System.out.println("21: check if position is internal");
		    System.out.println("22: check if position is external");
		    System.out.println("23: check if tree is empty");
		    System.out.println("24: get number of nodes");
		    System.out.println("25: get depth");
		    System.out.println("26: get height");
		    System.out.println("27: get String in brackets");
		    System.out.println("28: get preOrder String");
		    System.out.println("29: get postOrder String");
		    System.out.println("30: get inOrder String\n");
 
            /** number of chosen tree operation */
            int choice = scan.nextInt();
            try {
	            switch (choice) {
	            	/** add root */
		            case 1:
		                System.out.println("Enter an integer you want to save in the root node:");
		                root = tree.addRoot(scan.nextInt());
		                System.out.println("Root node added.");
		            	break;
		            /** insert child */    
		            case 2:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the node where you want to add a child:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Enter an integer you want to save in the child:");
		            	element1 = scan.nextInt();
		                tree.insertChild(tree.positions().get(pos1), element1);
		                System.out.println("Child added.");
		                break;
		            /** insert children */
		            case 3:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the node where you want to add both children:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Enter an integer you want to save in the first child:");
		            	element1 = scan.nextInt();
		            	System.out.println("Enter an integer you want to save in the second child:");
		            	element2 = scan.nextInt();
		                tree.insertChildren(tree.positions().get(pos1), element1, element2);
		                System.out.println("Children added.");
		                break;
		            /** insert left child */
		            case 4:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the node where you want to add a left child:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Enter an integer you want to save in the left child:");
		            	element1 = scan.nextInt();
		                tree.insertLeft(tree.positions().get(pos1), element1);
		                System.out.println("Left child added.");
		                break;
		            /** insert right child */
		            case 5:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the node where you want to add a right child:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Enter an integer you want to save in the right child:");
		            	element1 = scan.nextInt();
		                tree.insertRight(tree.positions().get(pos1), element1);
		                System.out.println("Right child added.");
		                break;
		            /** replace element */
		            case 6:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the node where you want to replace an element:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Enter a new integer to save:");
		            	element1 = scan.nextInt();
		                tree.replaceElement(tree.positions().get(pos1), element1);
		                System.out.println("Element replaced.");
		                break;
		            /** swap elements */
		            case 7:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the first node:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Enter a number from brackets to choose the second node:");
		            	pos2 = scan.nextInt();
		                tree.swapElements(tree.positions().get(pos1), tree.positions().get(pos2));
		                System.out.println("Elements swapped.");
		                break;
		            /** get root */
		            case 8:
		            	System.out.println("Root: " + tree.root());
		                break;
		            /** get left child */
		            case 9:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the node from which you want the left child:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Left child: " + tree.leftChild(tree.positions().get(pos1)));
		                break;
		            /** get right child */
		            case 10:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the node from which you want the right child:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Right child: " + tree.rightChild(tree.positions().get(pos1)));
		                break;
		            /** get parent */
		            case 11:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the node from which you want the parent:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Parent: " + tree.parent(tree.positions().get(pos1)));
		                break;
		            /** get children */
		            case 12:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the node from which you want the children:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Children: " + tree.children(tree.positions().get(pos1)));
		                break;
		            /** get descendants */
		            case 13:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the node from which you want the descendants:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Descendants: " + tree.descendants(tree.positions().get(pos1)));
		                break;
		            /** get ancestors */
		            case 14:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the node from which you want ancestors:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Ancestors: " + tree.ancestors(tree.positions().get(pos1)));
		                break;
		            /** get sibling */
		            case 15:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the node from which you want the sibling:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Sibling: " + tree.sibling(tree.positions().get(pos1)));
		                break;
		            /** get elements */
		            case 16:
		            	System.out.println("Elements: " + tree.elements());
		                break;
		            /** get positions */
		            case 17:
		            	System.out.println("Positions: " + tree.positions());
		                break;
		            /** check if it is root */
		            case 18:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the node to check if it is root:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Is root: " + tree.isRoot(tree.positions().get(pos1)));
		                break;
		            /** check if it has a left child */
		            case 19:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the node to check if it has a left child:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Has left child: " + tree.hasLeft(tree.positions().get(pos1)));
		                break;
		            /** check if it has a right child */
		            case 20:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the node to check if it has a right child:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Has right child: " + tree.hasRight(tree.positions().get(pos1)));
		                break;
		            /** check if it is internal */
		            case 21:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the node to check if it is internal:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Is internal: " + tree.isInternal(tree.positions().get(pos1)));
		                break;
		            /** check if it is external */
		            case 22:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the node to check if it is external:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Is external: " + tree.isExternal(tree.positions().get(pos1)));
		                break;
		            /** check if it is empty */
		            case 23:
		                System.out.println("Is empty: " + tree.isEmpty());
		                break;
		            /** get number of nodes */
		            case 24:
		            	System.out.println("Number of nodes: " + tree.size());
		                break;
		            /** get depth */
		            case 25:
		            	printPositions();
		            	System.out.println("Enter a number from brackets to choose the node to get the number of ancestors:");
		            	pos1 = scan.nextInt();
		            	System.out.println("Depth: " + tree.depth(tree.positions().get(pos1)));
		                break;
		            /** get height */
		            case 26:
		            	System.out.println("Maximum depth: " + tree.height());
		                break;
		            /** get String in brackets */
		            case 27:
		            	System.out.println(tree.toString());
		                break;
		            /** get preOrder String */
		            case 28:
		            	tree.preOrder(root);
		            	break;
		            /** get postOrder String */
		            case 29:
		            	tree.postOrder(root);
		            	break;
		            /** get inOrder String */
		            case 30:
		            	tree.inOrder(root);
		            	break;
		            /** default action */
		            default: 
		                System.out.println("No such operation found.");
		                break;   
	            }
            } catch(EmptyTreeException ex) {
				System.out.println("Error: Tree is empty. Operation could not be done.");
			} catch(InvalidPositionException ex) {
				System.out.println("Error: Invalid position. Operation could not be done.");
			} catch(UnemptyTreeException ex) {
				System.out.println("Error: Tree is not empty. Operation could not be done.");
			} catch(InputMismatchException ex) {
				System.out.println("Error: Input mismatch. Make sure you entered the correct data type.");
				scan.next();
			} catch(IndexOutOfBoundsException ex) {
				System.out.println("Error: Index out of bounds. Operation could not be done.");
			} catch(RuntimeException ex) {
				System.out.println("Error: Unexpected error occured. " + ex.getMessage());
	        }
 			/** ask user if he wants to quit */
            System.out.println("\n\nDo you want to continue (Type y for continue)?");
            /** save his answer */
            ch = scan.next().charAt(0);
        } while (ch == 'Y'|| ch == 'y');     
	}
}
