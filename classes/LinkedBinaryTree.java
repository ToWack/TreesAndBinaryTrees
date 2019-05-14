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
	
	/**
	 * constructor to set default values
	 */
	public LinkedBinaryTree() {
		this.size = 0;
		this.root = null;
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
	 * indicates whether the binary tree is empty
	 * 
	 * @return boolean to check if binary tree is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * returns the number of ancestors
	 * 
	 * @param p position of node
	 * @throws InvalidPositionException
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
	 * returns the maximum depth of binary tree
	 * 
	 * @throws EmptyTreeException
	 * @return max depth of binary tree
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
	 * @param p position of node
	 * @return max depth of binary tree
	 */
	private int heightOf(Position<E> p) {
		if(isExternal(p))
			return 0;
		else {
			int h = 0;
			for(Position<E> childPosition : children(p)) {
				h = Math.max(h, heightOf(childPosition));
			}
			return h + 1;
		}
	}
	
	/**
	 * indicates whether position is a root
	 * 
	 * @param p position of node
	 * @throws InvalidPositionException
	 * @return boolean to check if position is a root
	 */
	@Override
	public boolean isRoot(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		return node.parent == null;
	}
	
	/**
	 * indicates whether position is an internal node
	 * 
	 * @param p position of node
	 * @throws InvalidPositionException
	 * @return boolean to check if position is an internal node
	 */
	@Override
	public boolean isInternal(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		else
			return (hasLeft(p) || hasRight(p));
	}

	/**
	 * indicates whether position is an external node
	 * 
	 * @param p position of node
	 * @throws InvalidPositionException
	 * @return boolean to check if position is an external node
	 */
	@Override
	public boolean isExternal(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		return (isInternal(p) == false);
	}
	
	/**
	 * adds a root which stores e to an empty tree
	 * 
	 * @param e element to add
	 * @throws UnemptyTreeException
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
	 * inserts a new child which stores e
	 * 
	 * @param p position of node
	 * @param e element to add
	 * @throws InvalidPositionException
	 * @return position of added child
	 */
	@Override
	public Position<E> insertChild(Position<E> p, E e) throws InvalidPositionException {
		if(isExternal(p) || !hasLeft(p))
			return insertLeft(p, e);
		else if(!hasRight(p))
			return insertRight(p, e);
		else
			throw new InvalidPositionException();
	}
	
	/**
	 * inserts new children left and right to a node
	 * 
	 * @param p position of node
	 * @param e1 element to add to left child
	 * @param e2 element to add to right child
	 * @throws InvalidPositionException
	 */
	@Override
	public void insertChildren(Position<E> p, E e1, E e2) throws InvalidPositionException {
		if(p == null || isInternal(p) || isEmpty())
			throw new InvalidPositionException();
		else {
			/**
			 * theoretically you could check if one child is not set
			 * then you can fill the other one
			 * e1 value for a left child, e2 value for a right child
			 * but in our view this is not the sense of this method because of the insertChild method
			 */
			insertLeft(p, e1);
			insertRight(p, e2);
		}
	}
	
	/**
	 * inserts new left child which stores e
	 * 
	 * @param p position of node
	 * @param e element to add to left child
	 * @throws InvalidPositionException
	 * @return position of added left child
	 */
	@Override
	public Position<E> insertLeft(Position<E> p, E e) throws InvalidPositionException {
		// no check if p is null or tree is empty because this will be done in the method below
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
	 * inserts new right child which stores e
	 * 
	 * @param p position of node
	 * @param e element to add to right child
	 * @throws InvalidPositionException
	 * @return position of added right child
	 */
	@Override
	public Position<E> insertRight(Position<E> p, E e) throws InvalidPositionException {
		// no check if p is null or tree is empty because this will be done in the method below
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
	 * returns the position of left child
	 * 
	 * @param p position of node
	 * @throws InvalidPositionException
	 * @return position of left child
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
	 * returns the position of right child 
	 * 
	 * @param p position of node
	 * @throws InvalidPositionException
	 * @return position of right child
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
	 * checks whether node has a left child 
	 * 
	 * @param p position of node
	 * @throws InvalidPositionException
	 * @return boolean to check if left child exists
	 */
	@Override
	public boolean hasLeft(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		return (leftChild(p) != null);
	}

	/**
	 * checks whether node has a right child 
	 * 
	 * @param p position of node
	 * @throws InvalidPositionException
	 * @return boolean to check if right child exists
	 */
	@Override
	public boolean hasRight(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		return (rightChild(p) != null);
	}
	
	/**
	 * replace stored element of a node
	 * 
	 * @param p position of node
	 * @param e element to add
	 * @throws InvalidPositionException
	 * @return replaced element
	 */
	@Override
	public E replaceElement(Position<E> p, E e) throws InvalidPositionException {
		if(p == null || isEmpty() || !this.positions().contains(p))
			throw new InvalidPositionException();
		else {
			BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
			return node.element = e;
		}
	}

	/**
	 * swaps the elements of two nodes p and q 
	 * 
	 * @param p position of first node
	 * @param q position of second node
	 * @throws InvalidPositionException
	 */
	@Override
	public void swapElements(Position<E> p, Position<E> q) throws InvalidPositionException {
		if(p == null || q == null || isEmpty() || !(this.positions().contains(p) && this.positions().contains(q)))
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
	 * returns a list of all elements of the binary tree
	 * 
	 * @return list of elements
	 */
	@Override
	public List<E> elements() {
		ArrayList<E> list = new ArrayList<>(size());
		if(isEmpty())
			return list;
		list.add(root.element);
		elementsOf(root, list);
		return list;
	}
	
	/**
	 * helping method for elements()
	 * 
	 * @param p position of node
	 * @param list ArrayList to fill with elements
	 * @return list of elements
	 */
	public List<E> elementsOf(Position<E> p, ArrayList<E> list) {
		for(Position<E> child : children(p)) {
			list.add(child.element());
			// if child has also children
			if(isInternal(child))
				elementsOf(child, list);
		}
		return list;
	}

	/**
	 * returns a list of all positions of the binary tree
	 * 
	 * @return list of positions
	 */
 	@Override
	public List<Position<E>> positions() {
 		ArrayList<Position<E>> list = new ArrayList<>(size());
		if(isEmpty())
			return list;
		list.add(root);
		positionsOf(root, list);
		return list;
	}
	
	/**
	 * helping method for positions()
	 * 
	 * @param p position of node
	 * @param list ArrayList to fill with positions
	 * @return list of positions
	 */
	public List<Position<E>> positionsOf(Position<E> p, ArrayList<Position<E>> list) {
		for(Position<E> child : children(p)) {
			list.add(child);
			// if child has also children
			if(isInternal(child))
				positionsOf(child, list);
		}
		return list;
	}

	/**
	 * returns the root node
	 * 
	 * @throws EmptyTreeException
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
	 * returns the parent of a node p
	 * 
	 * @param p position of node
	 * @throws InvalidPositionException
	 * @return position of parent
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
	 * returns a list of all children of a node p
	 * 
	 * @param p position of node
	 * @throws InvalidPositionException
	 * @return list of children
	 */
	@Override
	public List<Position<E>> children(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		else {
			ArrayList<Position<E>> list = new ArrayList<>(size());
			// add left child first
			if(hasLeft(p))
				list.add(leftChild(p));
			if(hasRight(p))
				list.add(rightChild(p));
			return list;
		}
	}

	/**
	 * returns a list of all descendants of a node p
	 * 
	 * @param p position
	 * @throws InvalidPositionException
	 * @return list of descendants
	 */
	@Override
	public List<Position<E>> descendants(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		
		ArrayList<Position<E>> list = new ArrayList<>(size());
		return descendantsOf(p, list);
	}
	
	/**
	 * helping method for descendants(Position<E> p)
	 * 
	 * @param p position of node
	 * @param list ArrayList to fill with descendants
	 * @return list of all descendants
	 */
	public List<Position<E>> descendantsOf(Position<E> p, ArrayList<Position<E>> list) {
		if(isInternal(p)) {
			// add child to list and check if the child has also children
			for(Position<E> child : children(p)) {
				list.add(child);
				// call recursive method, check for internal is above
				descendantsOf(child, list);
			}
		}
		return list;
	}
	
	/**
	 * returns a list of all ancestors of a node p
	 *
	 * @param p position of node
	 * @throws InvalidPositionException
	 * @return list of ancestors
	 */
	@Override
	public List<Position<E>> ancestors(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		
		ArrayList<Position<E>> list = new ArrayList<>(this.size());
		return ancestorsOf(p, list);
	}

	/**
	 * helping method for ancestors(Position<E> p)
	 * 
	 * @param p position of node
	 * @param list ArrayList to fill with ancestors
	 * @return list of ancestors
	 */
	public List<Position<E>> ancestorsOf(Position<E> p, ArrayList<Position<E>> list) {
		if(isRoot(p))
			return list;
		list.add(parent(p));
		ancestorsOf(parent(p), list);
		return list;
	}

	/**
	 * returns the sibling of a node
	 * 
	 * @param p position of node
	 * @throws InvalidPositionException
	 * @return position of sibling
	 */
	@Override
	public Position<E> sibling(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty() || !(hasLeft(parent(p)) && hasRight(parent(p))))
			throw new InvalidPositionException();
		else {
			BinaryTreeNode<E> childNode = (BinaryTreeNode<E>) p;
			BinaryTreeNode<E> parentNode = childNode.parent;
			
			return (parentNode.leftChild != childNode) ? parentNode.leftChild : parentNode.rightChild;
		}
	}
	
	/**
	 * returns a String that shows the building of the tree
	 * 
	 * @return building of the tree in form of a String
	 */
	@Override
	public String toString() {
		if(isEmpty())
			return "";
		return stringOf(root);
	}
	
	/**
	 * helping method of toString()
	 * 
	 * @param p position of node
	 * @return building of the tree in form of a String
	 */
	public String stringOf(Position<E> p) {
		String children = "";
		String parent = p.element().toString();
		boolean isFirst = true;
		
		// check if p has minimally one child
		if(isInternal(p)) {
			// loop through children list
			for(Position<E> child : children(p)) {
				if(isFirst)
					isFirst = false;
				else
					// set space if there is more than one children
					children += " ";
				// check for children of child
				children += stringOf(child);
			}
			return parent + " (" + children + ")";
		} else {
			return parent;
		}
	}
	
	/** ------------------------------ */
	/** additional implemented methods */
	/** ------------------------------ */
	
	/**
	 * prints out the preOrder traversal
	 * 
	 * @param p position of node
	 */
	public void preOrder(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		BinaryTreeNode<E> node = (BinaryTreeNode<E>)p;
		System.out.print(node.element + " ");
		for(Position<E> child : children(p))
			preOrder(child);
	}
	
	/**
	 * prints out the postOrder traversal
	 * 
	 * @param p position of node
	 */
	public void postOrder(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		BinaryTreeNode<E> node = (BinaryTreeNode<E>)p;
		for(Position<E> child : children(p))
			postOrder(child);
		System.out.print(node.element + " ");
	}
	
	/**
	 * prints out the inOrder traversal
	 * 
	 * @param p position of node
	 */
	public void inOrder(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		BinaryTreeNode<E> node = (BinaryTreeNode<E>)p;
		if(hasLeft(p))
			inOrder(leftChild(p));
		System.out.print(node.element + " ");
		if(hasRight(p))
			inOrder(rightChild(p));
	}
	
}
