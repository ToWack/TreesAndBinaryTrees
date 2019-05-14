package project4.classes;

import project4.interfaces.Position;

/**
 * application to test the LinkedTree class
 * 
 * @author Renato Oppliger, Thomas Wacker
 */
public class GeneralTreeManualTest {

	/**
	 * main method
	 * 
	 * @param args unused.
	 */
	public static void main(String[] args) {
		/** test a LinkedTree 
		 * 
		 * 							55
		 * 				4						3
		 * 	 72    81       89   92
		 * 		9 10 44
		 * 
		 * root 55, with children 4 and 3
		 * node 4 with children 72, 81, 89 and 92
		 * node 81 with children 9, 10 and 44
		 * 
		 * */
		LinkedTree<Integer> tree = new LinkedTree<>();
		System.out.println("Tree created!\n");
		
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
		
		Position<Integer> child1 = tree.insertChild(root, 3);
		Position<Integer> child2 = tree.insertChild(root, 4);
		System.out.println("\nAdded a left and a right child to root node with elements 3 and 4!\n");
		
		System.out.println("size(): expected: 3 - result: " + tree.size());
		System.out.println("isRoot(child2): expected: false - result: " + tree.isInternal(child2));
		System.out.println("isInternal(root): expected: true - result: " + tree.isInternal(root));
		System.out.println("isExternal(root): expected: false - result: " + tree.isExternal(root));
		System.out.println("height(): expected: 1 - result: " + tree.height());
		System.out.println("depth(child1): expected: 1 - result: " + tree.depth(child1));
		System.out.println("toString(): expected: 55 (3 4) - result: " + tree.toString());
		
		tree.swapElements(child1, child2);
		System.out.println("\nSwapped elements of nodes child1 and child2!\n");
		
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
		Position<Integer> child3 = tree.insertChild(child1, 81);
		tree.insertChild(child1, 89);
		tree.insertChild(child1, 92);
		System.out.println("\nAdded four children to node child1 with elements 72, 81, 89 and 92!\n");
		
		System.out.println("size(): expected: 7 - result: " + tree.size());
		System.out.println("isRoot(child3): expected: false - result: " + tree.isRoot(child3));
		System.out.println("isEmpty(): expected: false - result: " + tree.isEmpty());
		System.out.println("isInternal(child3): expected: false - result: " + tree.isInternal(child3));
		System.out.println("isExternal(child3): expected: true - result: " + tree.isExternal(child3));
		System.out.println("height(): expected: 2 - result: " + tree.height());
		System.out.println("depth(child3): expected: 2 - result: " + tree.depth(child3));
		System.out.println("parent(child3): expected: 4 - result: " + tree.parent(child3).element());
		System.out.print("children(child1): expected: 72 81 89 92 - result: ");
		for(Position<Integer> child : tree.children(child1)) {
			System.out.print(child.element() + " ");
		}
		System.out.println();
		System.out.print("ancestors(child3): expected: 4 55 - result: ");
		for(Position<Integer> ancestor : tree.ancestors(child3)) {
			System.out.print(ancestor.element() + " ");
		}
		System.out.println();
		System.out.print("descendants(root): expected: 4 72 81 89 92 3 - result: ");
		for(Position<Integer> descendant : tree.descendants(root)) {
			System.out.print(descendant.element() + " ");
		}
		System.out.println();
		System.out.println("elements(): expected: [55, 4, 72, 81, 89, 92, 3] - result: " + tree.elements());
		System.out.print("positions(): expected: 55 4 72 81 89 92 3 - result: ");
		for(Position<Integer> position : tree.positions()) {
			System.out.print(position.element() + " ");
		}
		System.out.println();
		System.out.println("toString(): expected: 55 (4 (72 81 89 92) 3) - result: " + tree.toString());
		
		tree.insertChild(child3, 9);
		tree.insertChild(child3, 10);
		Position<Integer> child4 = tree.insertChild(child3, 44);
		System.out.println("\nAdded three children to node child3 with elements 9, 10 and 44!\n");
		
		System.out.println("size(): expected: 10 - result: " + tree.size());
		System.out.println("height(): expected: 3 - result: " + tree.height());
		System.out.println("depth(child3): expected: 3 - result: " + tree.depth(child4));
		System.out.print("children(child3): expected: 9 10 44 - result: ");
		for(Position<Integer> child : tree.children(child3)) {
			System.out.print(child.element() + " ");
		}
		System.out.println();
		System.out.print("ancestors(child4): expected: 81 4 55 - result: ");
		for(Position<Integer> ancestor : tree.ancestors(child4)) {
			System.out.print(ancestor.element() + " ");
		}
		System.out.println();
		System.out.print("descendants(root): expected: 4 72 81 9 10 44 89 92 3 - result: ");
		for(Position<Integer> descendant : tree.descendants(root)) {
			System.out.print(descendant.element() + " ");
		}
		System.out.println();
		System.out.println("elements(): expected: [55, 4, 72, 81, 9, 10, 44, 89, 92, 3] - result: " + tree.elements());
		System.out.print("positions(): expected: 55 4 72 81 9 10 44 89 92 3 - result: ");
		for(Position<Integer> position : tree.positions()) {
			System.out.print(position.element() + " ");
		}
		System.out.println();
		System.out.println("toString(): expected: 55 (4 (72 81 (9 10 44) 89 92) 3) - result: " + tree.toString());
	}
}
