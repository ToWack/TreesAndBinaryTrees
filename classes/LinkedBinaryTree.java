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
 * @author Renato Oppliger, Tom Wacker
 * @version 1.0
 */
public class LinkedBinaryTree<E> implements BinaryTree<E> {
	
	/** holds the number of nodes */
	private int size;
	/** holds the root node */
	private BinaryTreeNode<E> root;
	/** holds the list for saving children of type Positon<E> */
	private List<Position<E>> childrenList;
	/** holds the list for saving elements of type <E> */
	private List<E> elementList;
	/** holds the list for saving elements of type Position<E> */
	private List<Position<E>> positionList;
	/** holds the list for saving the descendants of type Position<E> */
	private List<Position<E>> descendantList;
	/** holds the list for saving the descendants of type Position<E> */
	private List<Position<E>> ancestorList;
	/** holds the output of this class */
	private String treeString;
	
	/**
	 * constructor to set default values
	 */
	public LinkedBinaryTree() {
		this.size = 0;
		this.root = null;
		this.childrenList = new ArrayList<Position<E>>();
		this.elementList = new ArrayList<E>();
		this.positionList = new ArrayList<Position<E>>();
		this.descendantList = new ArrayList<Position<E>>();
		this.ancestorList = new ArrayList<Position<E>>();
		this.treeString = "";
	}
	
	/**
	 * returns the number of nodes
	 * 
	 * @return number of nodes
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * indicates whether the tree is empty
	 * 
	 * @return boolean to check if tree is empty
	 */
	@Override
	public boolean isEmpty() {
		if(root != null)
			return false;
		return true;
	}
	
	/**
	 * returns the number of ancestors
	 * 
	 * @param p position of node
	 * @return number of ancestors
	 */
	@Override
	public int depth(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		if(isRoot(p))
			return 0;
		return depth(parent(p)) + 1;
	}
	
	/**
	 * returns the maximum depth
	 * 
	 * @return maximum depth
	 */
	@Override
	public int height() throws EmptyTreeException {
		if(isEmpty())
			throw new EmptyTreeException();
		else
			return heightOf(root);
	}
	
	/**
	 * helping method for height()
	 * 
	 * @param node node of tree
	 * @return depth of node
	 */
	private int heightOf(Position<E> p) {
		if(isExternal(p))
			return 0;
		else {
			int h = 0;
			for(Position<E> child : children(p)) {
				h = Math.max(h,heightOf(child));
			}
			return h + 1;
		}
	}
	
	/**
	 * indicates whether p is a root
	 * 
	 * @param p position of node
	 * @return boolean to check if p is root
	 */
	@Override
	public boolean isRoot(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		else {
			BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
			return node.parent == null;
		}
	}
	
	/**
	 * indicates whether p is an internal node
	 * 
	 * @param p position of node
	 * @return boolean to check if p is an internal node
	 */
	@Override
	public boolean isInternal(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		else
			return (hasLeft(p) || hasRight(p));
	}

	/**
	 * indicates whether p is an external node
	 * 
	 * @param p position of node
	 * @return boolean to check if p is an external node
	 */
	@Override
	public boolean isExternal(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		else
			return !(hasLeft(p) || hasRight(p));
	}
	
	/**
	 * adds a root which stores e to an empty tree
	 * 
	 * @param e value to add
	 * @return position of root
	 */
	@Override
	public Position<E> addRoot(E e) throws UnemptyTreeException {
		if(!isEmpty())
			throw new UnemptyTreeException();
		else {
			root = new BinaryTreeNode<E>(e);
			size = 1;
			return root;
		}
	}
	
	/**
	 * inserts a new child for p which stores e
	 * 
	 * @param p position of node
	 * @param e value to add
	 * @return position of added child
	 */
	@Override
	public Position<E> insertChild(Position<E> p, E e) throws InvalidPositionException {
		if(p == null || isEmpty() || (hasLeft(p) && hasRight(p)))
			throw new InvalidPositionException();
		if(isExternal(p) || !hasLeft(p))
			return insertLeft(p, e);
		else if(!hasRight(p))
			return insertRight(p, e);
		else
			throw new InvalidPositionException();
	}
	
	/**
	 * inserts new children for p
	 * 
	 * @param p position of node
	 * @param e1 value to add to left child
	 * @param e2 value to add to right child
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
	 * @param p position of node
	 * @param e value to add to left child
	 * @return position of added left child
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
	 * @param p position of node
	 * @param e element to add to right child
	 * @return position of added right child
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
	 * @param p position of node
	 * @return position of left child of p
	 */
	@Override
	public Position<E> leftChild(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		else {
			BinaryTreeNode<E> parentNode = (BinaryTreeNode<E>) p;
			return parentNode.leftChild;
		}
	}

	/**
	 * returns the right child of p (if it exists) 
	 * 
	 * @param p position of node
	 * @return position of right child of p
	 */
	@Override
	public Position<E> rightChild(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		else {
			BinaryTreeNode<E> parentNode = (BinaryTreeNode<E>) p;
			return parentNode.rightChild;
		}
	}
	
	/**
	 * checks whether p has a left child 
	 * 
	 * @param p position of node
	 * @return boolean to check if p has a left child
	 */
	@Override
	public boolean hasLeft(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		if(leftChild(p) == null)
			return false;
		return true;
	}

	/**
	 * checks whether p has a right child 
	 * 
	 * @param p position of node
	 * @return boolean to check if p has a right child
	 */
	@Override
	public boolean hasRight(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		if(rightChild(p) == null)
			return false;
		return true;
	}
	
	/**
	 * stores e at node p
	 * 
	 * @param p position of node
	 * @param e value to add
	 * @return replaced element
	 */
	@Override
	public E replaceElement(Position<E> p, E e) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		else {
			BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
			return node.element = e;
		}
	}

	/**
	 * swaps the elements stored at p and q 
	 * 
	 * @param p position of first node
	 * @param q position of second node
	 */
	@Override
	public void swapElements(Position<E> p, Position<E> q) throws InvalidPositionException {
		if(p == null || isRoot(p))
			throw new InvalidPositionException();
		BinaryTreeNode<E> firstNode = (BinaryTreeNode<E>) p;
		BinaryTreeNode<E> secondNode = (BinaryTreeNode<E>) q;
		// save element of p in temp
		E temp = firstNode.element;
		// save element of q in p
		firstNode.element = secondNode.element;
		// save temp in q
		secondNode.element = temp;
	}

	/**
	 * returns a list of all elements of the tree
	 * 
	 * @return list of elements
	 */
	@Override
	public List<E> elements() {
		elementList.removeAll(elementList);
		if(isEmpty())
			return elementList;
		elementList.add(root.element);
		elementsList(root);
		return elementList;
	}
	
	/**
	 * helping method for elements()
	 * 
	 * @param list of elements
	 * @return list of elements
	 */
	public List<E> elementsList(Position<E> p) {
		for(Position<E> child : children(p)) {
			elementList.add(child.element());
			// if child has also children
			if(isInternal(child))
				elementsList(child);
		}
		return elementList;
	}

	/**
	 * returns a list of all positions of the tree
	 * 
	 * @return list of positions
	 */
 	@Override
	public List<Position<E>> positions() {
 		positionList.removeAll(positionList);
		if(isEmpty())
			return positionList;
		positionList.add(root);
		positionsList(root);
		return positionList;
	}
	
	/**
	 * helping method for positions()
	 * 
	 * @param list of positions
	 * @return list of positions
	 */
	public List<Position<E>> positionsList(Position<E> p) {
		for(Position<E> child : children(p)) {
			positionList.add(child);
			// if child has also children
			if(isInternal(child))
				positionsList(child);
		}
		return positionList;
	}

	/**
	 * returns the root node (if it exists)
	 * 
	 * @return position of root
	 */
	@Override
	public Position<E> root() throws EmptyTreeException {
		if(isEmpty())
			throw new EmptyTreeException();
		else
			return root;
	}

	/**
	 * returns the parent of node p (if it exists)
	 * 
	 * @param p position of node
	 * @return position of parent of p
	 */
	@Override
	public Position<E> parent(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		else {
			BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
			return node.parent;
		}
	}

	/**
	 * returns a list of all children of p
	 * 
	 * @param p position of node
	 * @return list of all children of p
	 */
	@Override
	public List<Position<E>> children(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		else {
			BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
			childrenList.removeAll(childrenList);
			// add left child first
			if(node.leftChild != null)
				childrenList.add(node.leftChild);
			if(node.rightChild != null)
				childrenList.add(node.rightChild);
			return childrenList;
		}
	}

	/**
	 * returns a list of all descendants of p
	 * 
	 * @param p position
	 * @return list of all descendants of p
	 */
	@Override
	public List<Position<E>> descendants(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		// remove all positions from list
		descendantList.removeAll(descendantList);
		return descendantsList(p);
	}
	
	/**
	 * helping method for descendants()
	 * 
	 * @param p position
	 * @return list of all descendants
	 */
	public List<Position<E>> descendantsList(Position<E> p) {
		if(isInternal(p)) {
			// add child to list and check if the child has also children
			for(Position<E> child : children(p)) {
				descendantList.add(child);
				// if child has also children
				if(isInternal(child))
					descendantsList(child);
			}
		}
		return descendantList;
	}
	
	/**
	 * returns a list of all ancestors of p
	 *
	 * @param p position of node
	 * @return list of all ancestors of p
	 */
	@Override
	public List<Position<E>> ancestors(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		ancestorList.removeAll(ancestorList);
		return ancestorsList(p);
	}

	/**
	 * helping method for ancestors()
	 * 
	 * @param p position of node
	 * @return list of ancestors
	 */
	public List<Position<E>> ancestorsList(Position<E> p) {
		if(isRoot(p))
			return ancestorList;
		ancestorList.add(parent(p));
		ancestorsList(parent(p));
		return ancestorList;
	}

	/**
	 * returns the sibling of p (if it exists)
	 * 
	 * @param p position of node
	 * @return position of sibling of p
	 */
	@Override
	public Position<E> sibling(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty() || !(hasLeft(p) || !hasRight(p)))
			throw new InvalidPositionException();
		else {
			BinaryTreeNode<E> childNode = (BinaryTreeNode<E>) p;
			BinaryTreeNode<E> parentNode = childNode.parent;
			if(parentNode.leftChild != childNode)
				return parentNode.leftChild;
			else
				return parentNode.rightChild;
			
		}
	}
	
	/**
	 * returns a String that shows the building of the tree
	 */
	@Override
	public String toString() {
		/*
		treeString = "";
		if(isEmpty())
			return treeString;
		return toStringList(root);
		*/
		return toString (root);
	}
	
	
	public String toString(Position<E> p) {
		/*
		String children = new String();
		String parent = p.element().toString();
		boolean isFirst = true;
		
		// check if p has minimally one child
		if(isInternal(p)) {
			// loop through children list
			for(Position<E> child : children(p)) {
				if(isFirst)
					isFirst = false;
				// set space if there is more than one children
				else
					children += " ";
				// check for children of child
				children += toStringList(child);
			}
			return parent + " (" + children + ")";
		} else {
			if(isFirst)
				return parent + children;
			return parent + children + " ";
		}
		*/
		String result = "";
	    if (root == null)
	        return "";
	    result += toString(root.leftChild);
	    result += toString(root.rightChild);
	    result += root.element.toString();
	    return result;
	}
}
