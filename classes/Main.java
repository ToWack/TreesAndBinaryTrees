package project4.classes;

/**
 * application to test the LinkedBinaryTree and LinkedTree classes
 * 
 * @author Renato Oppliger, Thomas Wacker
 */
public class Main {

	/**
	 * main method
	 * 
	 * @param args unused.
	 */
	public static void main(String[] args) {
		LinkedBinaryTree<Integer> binaryTree = new LinkedBinaryTree<>();
		System.out.println(binaryTree.isEmpty());
		binaryTree.addRoot(1);
		System.out.println(binaryTree.isEmpty());
	}

}
