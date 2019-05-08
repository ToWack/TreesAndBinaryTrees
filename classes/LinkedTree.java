package project4.classes;

import java.util.ArrayList;
import java.util.List;

import project4.exceptions.EmptyTreeException;
import project4.exceptions.InvalidPositionException;
import project4.exceptions.UnemptyTreeException;
import project4.interfaces.Position;
import project4.interfaces.Tree;

/**
 * application to fiddle with general trees
 * 
 * @author Renato Oppliger, Tom Wacker
 * @version 1.0
 */
public class LinkedTree<E> implements Tree<E> {

	/** holds the number of nodes */
	private int size;
	/** holds the root node */
	private TreeNode<E> root;
	
	/**
	 * constructor to set default values
	 */
	public LinkedTree() {
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
	 * indicates whether the tree is empty
	 * 
	 * @return boolean to check if tree is empty
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
	 * returns the maximum depth of tree
	 * 
	 * @throws EmptyTreeException
	 * @return maximum depth of tree
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
	 * @return max depth of tree
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
		TreeNode<E> node = (TreeNode<E>) p;
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
		TreeNode<E> parentNode = (TreeNode<E>)p;
		return (parentNode.childrenList.isEmpty()) ? false : true;
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
			root = new TreeNode<E>(e);
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
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		TreeNode<E> parentNode = (TreeNode<E>)p;
		TreeNode<E> childNode = new TreeNode<E>(e);
		childNode.parent = parentNode;
		parentNode.childrenList.add(childNode);
		size++;
		return childNode;
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
			TreeNode<E> node = (TreeNode<E>) p;
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
		TreeNode<E> firstNode = (TreeNode<E>) p;
		TreeNode<E> secondNode = (TreeNode<E>) q;
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
		ArrayList<E> list = new ArrayList<>(this.size());
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
	 * returns a list of all positions of the tree
	 * 
	 * @return list of positions
	 */
	@Override
	public List<Position<E>> positions() {
		ArrayList<Position<E>> list = new ArrayList<>(this.size());
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
			TreeNode<E> node = (TreeNode<E>) p;
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
		TreeNode<E> parentNode = (TreeNode<E>)p;
		return parentNode.childrenList;
	}

	/**
	 * returns a list of all descendants of a node p
	 * 
	 * @param p position of node
	 * @throws InvalidPositionException
	 * @return list of descendants
	 */
	@Override
	public List<Position<E>> descendants(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		
		ArrayList<Position<E>> list = new ArrayList<>(this.size());
		return descendantsList(p, list);
	}
	
	/**
	 * helping method for descendants(Position<E> p)
	 * 
	 * @param p position of node
	 * @param list ArrayList to fill with descendants
	 * @return list of all descendants
	 */
	public List<Position<E>> descendantsList(Position<E> p, ArrayList<Position<E>> list) {
		if(isInternal(p)) {
			// add child to list and check if the child has also children
			for(Position<E> child : children(p)) {
				list.add(child);
				// call recursive method, check for internal is above
				descendantsList(child, list);
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
		return ancestorsList(p, list);
	}

	/**
	 * helping method for ancestors(Position<E> p)
	 * 
	 * @param p position of node
	 * @param list ArrayList to fill with ancestors
	 * @return list of ancestors
	 */
	public List<Position<E>> ancestorsList(Position<E> p, ArrayList<Position<E>> list) {
		if(isRoot(p))
			return list;
		list.add(parent(p));
		ancestorsList(parent(p), list);
		return list;
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
				// set space if there is more than one children
				else
					children += " ";
				// check for children of child
				children += stringOf(child);
			}
			return parent + " (" + children + ")";
		} else {
			return parent + children;
		}
	}

}
