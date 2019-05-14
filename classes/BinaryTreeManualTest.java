package project4.classes;

import project4.interfaces.Position;

/**
 * application to test the LinkedBinaryTree class
 * 
 * @author Renato Oppliger, Thomas Wacker
 */
public class BinaryTreeManualTest {

	/**
	 * main method
	 * 
	 * @param args unused.
	 */
	public static void main(String[] args) {
		/** test a LinkedBinaryTree 
		 * 
		 * 					55
		 * 			4				3
		 * 		72		89		21 		36
		 * 			 10    81
		 * 
		 * root 55, with children 4 and 3
		 * node 4 with children 72 and 89
		 * node 89 with children 10 and 81
		 * node 3 with children 21 and 36 
		 * 
		 * */
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
		System.out.println("BinaryTree created!\n");
		
		System.out.println("isEmpty(): expected: true - result: " + tree.isEmpty());
		System.out.println("size(): expected: 0 - result: " + tree.size());
		
		Position<Integer> root = tree.addRoot(0);
		System.out.println("\nAdded root with element 0!\n");
		
		System.out.println("isRoot(root): expected: true - result: " + tree.isRoot(root));
		System.out.println("root(): expected: 0 - result: " + tree.root().element());
		
		tree.replaceElement(root, 55);
		System.out.println("\nReplaced element 0 in root with 55!\n");
		
		System.out.println("root(): expected: 55 - result: " + tree.root().element());
		System.out.println("isInternal(root): expected: false - result: " + tree.isInternal(root));
		System.out.println("isExternal(root): expected: true - result: " + tree.isExternal(root));
		System.out.println("height(): expected: 0 - result: " + tree.height());
		System.out.println("depth(root): expected: 0 - result: " + tree.depth(root));
		System.out.println("hasLeft(root): expected: false - result: " + tree.hasLeft(root));
		System.out.println("hasLeft(root): expected: false - result: " + tree.hasRight(root));
		
		Position<Integer> child1 = tree.insertLeft(root, 3);
		Position<Integer> child2 = tree.insertRight(root, 4);
		System.out.println("\nAdded a left and a right child to root node with elements 3 and 4!\n");
		
		System.out.println("size(): expected: 3 - result: " + tree.size());
		System.out.println("isRoot(child2): expected: false - result: " + tree.isInternal(child2));
		System.out.println("isInternal(root): expected: true - result: " + tree.isInternal(root));
		System.out.println("isExternal(root): expected: false - result: " + tree.isExternal(root));
		System.out.println("height(): expected: 1 - result: " + tree.height());
		System.out.println("depth(child1): expected: 1 - result: " + tree.depth(child1));
		System.out.println("leftChild(root): expected: 3 - result: " + tree.leftChild(root).element());
		System.out.println("rightChild(root): expected: 4 - result: " + tree.rightChild(root).element());
		System.out.println("sibling(child1): expected: 4 - result: " + tree.sibling(child1).element());
		
		tree.swapElements(child1, child2);
		System.out.println("\nSwapped elements of nodes child1 and child2!\n");
		
		System.out.println("leftChild(root): expected: 4 - result: " + tree.leftChild(root).element());
		System.out.println("rightChild(root): expected: 3 - result: " + tree.rightChild(root).element());
		System.out.println("hasLeft(root): expected: true - result: " + tree.hasLeft(root));
		System.out.println("hasLeft(root): expected: true - result: " + tree.hasRight(root));
		System.out.println("sibling(child1): expected: 3 - result: " + tree.sibling(child1).element());
		System.out.println("parent(child2): expected: 55 - result: " + tree.parent(child2).element());
		System.out.print("children(root): expected: 4 3 - result: ");
		for(Position<Integer> child : tree.children(root)) {
			System.out.print(child.element() + " ");
		}
		System.out.println();
		System.out.print("ancestors(child1): expected: 55 - result: ");
		for(Position<Integer> ancestor : tree.ancestors(child1)) {
			System.out.print(ancestor.element() + " ");
		}
		System.out.println();
		System.out.print("descendants(root): expected: 4 3 - result: ");
		for(Position<Integer> descendant : tree.descendants(root)) {
			System.out.print(descendant.element() + " ");
		}
		System.out.println();
		System.out.println("elements(): expected: [55, 4, 3] - result: " + tree.elements());
		System.out.print("positions(): expected: 55 4 3 - result: ");
		for(Position<Integer> position : tree.positions()) {
			System.out.print(position.element() + " ");
		}
		System.out.println();
		System.out.println("toString(): expected: 55 (4 3) - result: " + tree.toString());
		
		tree.insertChild(child1, 72);
		Position<Integer> child3 = tree.insertChild(child1, 89);
		System.out.println("\nAdded two children to node child1 with elements 72 and 89!\n");
		
		System.out.println("size(): expected: 5 - result: " + tree.size());
		System.out.println("isRoot(child3): expected: false - result: " + tree.isRoot(child3));
		System.out.println("isEmpty(): expected: false - result: " + tree.isEmpty());
		System.out.println("isInternal(child3): expected: false - result: " + tree.isInternal(child3));
		System.out.println("isExternal(child3): expected: true - result: " + tree.isExternal(child3));
		System.out.println("height(): expected: 2 - result: " + tree.height());
		System.out.println("depth(child3): expected: 2 - result: " + tree.depth(child3));
		System.out.println("parent(child3): expected: 4 - result: " + tree.parent(child3).element());
		System.out.print("children(child1): expected: 72 89 - result: ");
		for(Position<Integer> child : tree.children(child1)) {
			System.out.print(child.element() + " ");
		}
		System.out.println();
		System.out.print("ancestors(child3): expected: 4 55 - result: ");
		for(Position<Integer> ancestor : tree.ancestors(child3)) {
			System.out.print(ancestor.element() + " ");
		}
		System.out.println();
		System.out.print("descendants(root): expected: 4 72 89 3 - result: ");
		for(Position<Integer> descendant : tree.descendants(root)) {
			System.out.print(descendant.element() + " ");
		}
		System.out.println();
		System.out.println("elements(): expected: [55, 4, 72, 89, 3] - result: " + tree.elements());
		System.out.print("positions(): expected: 55 4 72 89 3 - result: ");
		for(Position<Integer> position : tree.positions()) {
			System.out.print(position.element() + " ");
		}
		System.out.println();
		System.out.println("toString(): expected: 55 (4 (72 89) 3) - result: " + tree.toString());
		
		tree.insertChildren(child2, 21, 36);
		tree.insertChildren(child3, 10, 81);
		System.out.println("\nAdded children to node child2 and child3 with elements 21, 36 and 10, 81!\n");
		
		System.out.println("size(): expected: 9 - result: " + tree.size());
		System.out.println("isEmpty(): expected: false - result: " + tree.isEmpty());
		System.out.println("height(): expected: 3 - result: " + tree.height());
		System.out.print("descendants(root): expected: 4 72 89 10 81 3 21 36 - result: ");
		for(Position<Integer> descendant : tree.descendants(root)) {
			System.out.print(descendant.element() + " ");
		}
		System.out.println();
		System.out.println("elements(): expected: [55, 4, 72, 89, 10, 81, 3, 21, 36] - result: " + tree.elements());
		System.out.print("positions(): expected: 55 4 72 89 10 81 3 21 36 - result: ");
		for(Position<Integer> position : tree.positions()) {
			System.out.print(position.element() + " ");
		}
		System.out.println();
		System.out.println("toString(): expected: 55 (4 (72 89 (10 81)) 3 (21 36)) - result: " + tree.toString());
		
		System.out.println("\nTest the traversals of binary tree!\n");
		System.out.print("Preorder: expected: 55 4 72 89 10 81 3 21 36 - result: ");
		tree.preOrder(root);
		System.out.print("\nPostorder: expected: 72 10 81 89 4 21 36 3 55 - result: ");
		tree.postOrder(root);
		System.out.print("\nPostorder: expected: 72 4 10 89 81 55 21 3 36 - result: ");
		tree.inOrder(root);
	}

}
