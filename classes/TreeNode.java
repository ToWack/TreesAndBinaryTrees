package project4.classes;

import project4.interfaces.Position;

public class TreeNode<E> implements Position<E> {
	
	/** holds the parent node */
	TreeNode<E> parent;
	/** holds the left child node */
	TreeNode<E> leftChild;
	/** holds the right child node */
	TreeNode<E> rightChild;
	/** holds the element to store */ 
	E element;
	
	public TreeNode(E e) {
		this.parent = null;
		this.leftChild = null;
		this.rightChild = null;
		this.element = e;
	}

	@Override
	public E element() {
		return element;
	}

}
