package project4.classes;

import project4.interfaces.Position;

public class BinaryTreeNode<E> implements Position<E> {
	
	BinaryTreeNode<E> parent;
	BinaryTreeNode<E> leftChild;
	BinaryTreeNode<E> rightChild;
	protected E element;
	
	public BinaryTreeNode(E e) {
		this.parent = null;
		this.leftChild = null;
		this.rightChild = null;
		this.element = e;
	}

	@Override
	public E element() {
		return null;
	}

}
