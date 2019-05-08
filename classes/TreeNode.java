package project4.classes;

import java.util.ArrayList;

import project4.interfaces.Position;

/**
 * application to set node of a tree
 * 
 * @author Renato Oppliger, Tom Wacker
 * @version 1.0
 */
public class TreeNode<E> implements Position<E> {
	
	/** holds the parent node */
	TreeNode<E> parent;
	/** holds the list of children */
	ArrayList<Position<E>> childrenList;
	/** holds the element to store */ 
	E element;
	
	/**
	 * constructor to set default values
	 * 
	 * @param e element
	 */
	public TreeNode(E e) {
		this.parent = null;
		this.childrenList = new ArrayList<>();
		this.element = e;
	}

	/**
	 * returns the element
	 * 
	 * @return element
	 */
	@Override
	public E element() {
		return element;
	}

}
