package project4.classes;

import java.util.ArrayList;
import java.util.List;

import project4.exceptions.EmptyTreeException;
import project4.exceptions.InvalidPositionException;
import project4.exceptions.UnemptyTreeException;
import project4.interfaces.BinaryTree;
import project4.interfaces.Position;

/**
 * application to fiddle with binary trees
 * 
 * @author Renato Oppliger, Thomas Wacker
 */
public class LinkedBinaryTree<E> implements BinaryTree<E> {
	
	/** holds the number of nodes */
	private int size;
	/** holds the root node */
	private BinaryTreeNode<E> root;
	private List<E> elementList;
	private List<Position<E>> positionList;
	private List<Position<E>> childrenList;
	private List<Position<E>> descendantList;
	private List<Position<E>> ancestorList;
	
	/**
	 * constructor to set default values
	 */
	public LinkedBinaryTree() {
		this.size = 0;
		this.root = null;
		this.elementList = new ArrayList<E>();
		this.positionList = new ArrayList<Position<E>>();
		this.childrenList = new ArrayList<Position<E>>();
		this.descendantList = new ArrayList<Position<E>>();
		this.ancestorList = new ArrayList<Position<E>>();
	}
	
	/*
	 * returns the number of nodes
	 */
	@Override
	public int size() {
		if(isEmpty())
			return 0;
		return size;
	}
	
	/*
	 * indicates whether the tree is empty
	 */
	@Override
	public boolean isEmpty() {
		if(root == null)
			return true;
		return false;
	}
	
	/**
	 * returns the number of ancestors
	 * 
	 * @param p position
	 */
	@Override
	public int depth(Position<E> p) throws InvalidPositionException {
		if(isRoot(p))
			return 0;
		return depth(parent(p)) + 1;
	}
	
	/**
	 * returns the maximum depth
	 */
	@Override
	public int height() throws EmptyTreeException {
		return heightOf(root);
	}
	
	/**
	 * helping method for height()
	 * 
	 * @param node BinaryTreeNode<E>
	 */
	private int heightOf(BinaryTreeNode<E> node) {
		if(isExternal(node))
			return 0;
		else {
			return Math.max(heightOf(node.leftChild), heightOf(node.rightChild)) + 1;
		}
	}
	
	/**
	 * indicates whether p is a root
	 * 
	 * @param p position
	 */
	@Override
	public boolean isRoot(Position<E> p) throws InvalidPositionException {
		if(p != null) {
			BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
			return node.parent == null;
		} else {
			throw new InvalidPositionException();
		}
	}
	
	/**
	 * indicates whether p is an internal node
	 * 
	 * @param p position
	 */
	@Override
	public boolean isInternal(Position<E> p) throws InvalidPositionException {
		if(p != null)
			return (hasLeft(p) || hasRight(p));
		else
			throw new InvalidPositionException();
	}

	/**
	 * indicates whether p is an external node
	 * 
	 * @param p position
	 */
	@Override
	public boolean isExternal(Position<E> p) throws InvalidPositionException {
		if(p != null)
			return !(hasLeft(p) || hasRight(p));
		else
			throw new InvalidPositionException();
	}
	
	/**
	 * adds a root which stores e to an empty tree
	 * 
	 * @param e element to add
	 */
	@Override
	public Position<E> addRoot(E e) throws UnemptyTreeException {
		if(isEmpty()) {
			root = new BinaryTreeNode<E>(e);
			size = 1;
			return root;
		} else 
			throw new UnemptyTreeException();
	}
	
	/**
	 * inserts a new child for p which stores e
	 * 
	 * @param p position
	 * @param e element to add
	 */
	@Override
	public Position<E> insertChild(Position<E> p, E e) throws InvalidPositionException {
		if(p == null || isEmpty() || (hasLeft(p) && hasRight(p)))
			throw new InvalidPositionException();
		BinaryTreeNode<E> parent = (BinaryTreeNode<E>) p;
		if(parent.leftChild == null && parent.rightChild == null) {
			parent.leftChild = new BinaryTreeNode<>(e);
			parent.leftChild.parent = parent;
			size++;
			return parent.leftChild;
		} else if(parent.leftChild != null && parent.rightChild == null) {
			parent.rightChild = new BinaryTreeNode<>(e);
			parent.rightChild.parent = parent;
			size++;
			return parent.rightChild;
		} else
			throw new InvalidPositionException();
	}
	
	/**
	 * inserts new children for p
	 * 
	 * @param p position
	 * @param e1 element to add
	 * @param e2 element to add
	 */
	@Override
	public void insertChildren(Position<E> p, E e1, E e2) throws InvalidPositionException {
		if(p == null || isInternal(p) || isEmpty())
			throw new InvalidPositionException();
		else {
			insertLeft(p, e1);
			insertRight(p, e2);
		}
	}
	
	/**
	 * inserts new left child for p which stores e
	 * 
	 * @param p position
	 * @param e element to add
	 */
	@Override
	public Position<E> insertLeft(Position<E> p, E e) throws InvalidPositionException {
		if(hasLeft(p))
			throw new InvalidPositionException();
		else {
			BinaryTreeNode<E> parentNode = (BinaryTreeNode<E>) p;
			parentNode.leftChild = new BinaryTreeNode<E>(e);
			parentNode.leftChild.parent = parentNode;
			size++;
			return parentNode.leftChild;
		}
	}
	
	/**
	 * inserts new right child for p which stores e
	 * 
	 * @param p position
	 * @param e element to add
	 */
	@Override
	public Position<E> insertRight(Position<E> p, E e) throws InvalidPositionException {
		if(hasRight(p))
			throw new InvalidPositionException();
		else {
			BinaryTreeNode<E> parentNode = (BinaryTreeNode<E>) p;
			parentNode.rightChild = new BinaryTreeNode<E>(e);
			parentNode.rightChild.parent = parentNode;
			size++;
			return parentNode.rightChild;
		}
	}
	
	/**
	 * returns the left child of p (if it exists) 
	 * 
	 * @param p position
	 */
	@Override
	public Position<E> leftChild(Position<E> p) throws InvalidPositionException {
		if(hasLeft(p) && p != null) {
			BinaryTreeNode<E> parentNode = (BinaryTreeNode<E>) p;
			return parentNode.leftChild;
		} else
			throw new InvalidPositionException();
	}

	/**
	 * returns the right child of p (if it exists) 
	 * 
	 * @param p position
	 */
	@Override
	public Position<E> rightChild(Position<E> p) throws InvalidPositionException {
		if(hasRight(p) && p != null) {
			BinaryTreeNode<E> parentNode = (BinaryTreeNode<E>) p;
			return parentNode.rightChild;
		} else
			throw new InvalidPositionException();
	}
	
	/**
	 * checks whether p has a left child 
	 * 
	 * @param p position
	 */
	@Override
	public boolean hasLeft(Position<E> p) throws InvalidPositionException {
		if(leftChild(p) == null)
			return false;
		return true;
	}

	/**
	 * checks whether p has a right child 
	 * 
	 * @param p position
	 */
	@Override
	public boolean hasRight(Position<E> p) throws InvalidPositionException {
		if(rightChild(p) == null)
			return false;
		return true;
	}
	
	/**
	 * stores e at node p
	 * 
	 * @param p position
	 * @param e element to add
	 */
	@Override
	public E replaceElement(Position<E> p, E e) throws InvalidPositionException {
		if(p != null) {
			BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
			return node.element = e;
		} else
			throw new InvalidPositionException();
	}

	/**
	 * swaps the elements stored at p and q 
	 * 
	 * @param p position
	 * @param q position
	 */
	@Override
	public void swapElements(Position<E> p, Position<E> q) throws InvalidPositionException {
		if(p == null || isRoot(p))
			throw new InvalidPositionException();
		
		BinaryTreeNode<E> pNode = (BinaryTreeNode<E>) p;
		BinaryTreeNode<E> qNode = (BinaryTreeNode<E>) q;
		// save element of p in temp
		E temp = pNode.element;
		// save element of q in p
		pNode.element = qNode.element;
		// save temp in q
		qNode.element = temp;
	}

	/**
	 * returns a list of all elements
	 */
	@Override
	public List<E> elements() {
		elementList.removeAll(elementList);
		elementList.add(root.element);
		children(root);
		elementsOf(elementList);
		return elementList;
	}
	
	/**
	 * helping method for elements()
	 * 
	 * @param list element list
	 */
	public List<E> elementsOf(List<E> list) {
		for(Position<E> p : childrenList) {
			BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
			list.add(node.element);
			children(node);
			elementsOf(list);
		}
		return list;
	}

	/**
	 * returns a list of all positions
	 */
	@Override
	public List<Position<E>> positions() {
		positionList.removeAll(positionList);
		positionList.add(root);
		children(root);
		positionsOf(positionList);
		return positionList;
	}
	
	/**
	 * helping method for positions()
	 * 
	 * @param list element list
	 */
	public List<Position<E>> positionsOf(List<Position<E>> list) {
		for(Position<E> p : childrenList) {
			BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
			list.add(node);
			children(node);
			positionsOf(list);
		}
		return list;
	}

	/**
	 * returns the root node (if it exists)
	 */
	@Override
	public Position<E> root() throws EmptyTreeException {
		if(!isEmpty())
			return root;
		else
			throw new EmptyTreeException();
	}

	/**
	 * returns the parent of node p (if it exists)
	 * 
	 * @param p position
	 */
	@Override
	public Position<E> parent(Position<E> p) throws InvalidPositionException {
		if(p != null) {
			BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
			return node.parent;
		} else
			throw new InvalidPositionException();
	}

	/**
	 * returns a list of all children of p
	 * 
	 * @param p position
	 */
	@Override
	public List<Position<E>> children(Position<E> p) throws InvalidPositionException {
		if(p != null) {
			BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
			childrenList.removeAll(childrenList);
			if(node.leftChild != null)
				childrenList.add(node.leftChild);
			if(node.rightChild != null)
				childrenList.add(node.rightChild);
			return childrenList;
		} else
			throw new InvalidPositionException();
	}

	/**
	 * returns a list of all descendants of p
	 * 
	 * @param p position
	 */
	@Override
	public List<Position<E>> descendants(Position<E> p) throws InvalidPositionException {
		if(p != null) {
			descendantList.removeAll(descendantList);
			if(isExternal(p))
				return descendantList;
			return descendantsOf(p);
		} else
			throw new InvalidPositionException();
	}
	
	/**
	 * helping method for descendants()
	 * 
	 * @param p position
	 */
	public List<Position<E>> descendantsOf(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		children(node);
		
		if(isInternal(p)) {
			for(Position<E> child : childrenList) {
				descendantList.add(child);
				
				if(isInternal(child))
					descendantsOf(child);
			}
		}
		return descendantList;
	}

	
	/**
	 * returns a list of all ancestors of p
	 *
	 * @param p position
	 */
	@Override
	public List<Position<E>> ancestors(Position<E> p) throws InvalidPositionException {
		if(p != null) {
			ancestorList.removeAll(ancestorList);
			return ancestorsOf(p, ancestorList);
		} else
			throw new InvalidPositionException();
	}
	
	/**
	 * helping method for ancestors()
	 * 
	 * @param p position
	 */
	public List<Position<E>> ancestorsOf(Position<E> p, List<Position<E>> list) {
		if(isRoot(p))
			return ancestorList;
		list.add(parent(p));
		ancestorsOf(parent(p), list);
		return list;
	}

	/**
	 * returns the sibling of p (if it exists)
	 * 
	 * @param p position
	 */
	@Override
	public Position<E> sibling(Position<E> p) throws InvalidPositionException {
		if(p != null) {
			BinaryTreeNode<E> childNode = (BinaryTreeNode<E>) p;
			BinaryTreeNode<E> parentNode = childNode.parent;
			if(parentNode.leftChild != childNode && hasRight(parentNode))
				return parentNode.rightChild;
			else if(parentNode.rightChild != childNode && hasLeft(parentNode))
				return parentNode.leftChild;
			else
				throw new InvalidPositionException();
		} else
			throw new InvalidPositionException();
	}
	
}
