package project4.classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import project4.interfaces.BinaryTree;
import project4.interfaces.Position;

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
		BinaryTree<Integer> tree = new LinkedBinaryTree<>();
		Position<Integer> root = tree.addRoot(0);
		Position<Integer> child1 = tree.insertLeft(root, 1);
		Position<Integer> child2 = tree.insertRight(root, 2);
		
		Position<Integer> child3 = tree.insertLeft(child1, 3);
		Position<Integer> child4 = tree.insertRight(child1, 4);
		
		Position<Integer> child5 = tree.insertLeft(child3, 5);
		Position<Integer> child6 = tree.insertRight(child3, 6);
		
		Position<Integer> child7 = tree.insertLeft(child5, 7);
		Position<Integer> child8 = tree.insertRight(child5, 8);
		//System.out.println("size(): " + tree.size());
		//System.out.println("depth(p): " + tree.depth(child3));
		//System.out.println(tree.height());
		/*System.out.println(tree.children(root));
		List<Position<Integer>> list = new ArrayList<>();
		list.add(child2);
		list.add(child1);
		System.out.println(list);
		System.out.println(tree.children(root).equals(list));*/
		//System.out.println(tree.sibling(child8)==child7);
		//System.out.println(tree.ancestors(child5));
		//System.out.println(tree.descendants(child5));
		//System.out.println(tree.elements().size());
		//System.out.println(tree.positions());
		System.out.println(tree.toString());
	}

}
