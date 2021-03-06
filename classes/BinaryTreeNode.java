package project4.classes;

import project4.interfaces.Position;

/**
 * application to set node of a binary tree
 * 
 * @author Renato Oppliger, Tom Wacker
 * @version 1.0
 */
public class BinaryTreeNode<E> implements Position<E> {
	
	/** holds the parent node */
	BinaryTreeNode<E> parent;
	/** holds the left child node */
	BinaryTreeNode<E> leftChild;
	/** holds the right child node */
	BinaryTreeNode<E> rightChild;
	/** holds the element to store */ 
	E element;
	
	
	/**
	 * constructor to set default values
	 * 
	 * @param e element
	 */
	public BinaryTreeNode(E e) {
		this.parent = null;
		this.leftChild = null;
		this.rightChild = null;
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
