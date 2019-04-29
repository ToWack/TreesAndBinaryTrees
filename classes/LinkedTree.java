package project4.classes;

import java.util.ArrayList;
import java.util.List;

import project4.exceptions.EmptyTreeException;
import project4.exceptions.InvalidPositionException;
import project4.exceptions.UnemptyTreeException;
import project4.interfaces.Position;
import project4.interfaces.Tree;

public class LinkedTree<E> implements Tree<E> {

	/** holds the number of nodes */
	private int size;
	/** holds the root node */
	private TreeNode<E> root;
	private List<Position<E>> childrenList;
	/** holds the list for saving elements of type <E> */
	private List<E> elementList;
	/** holds the list for saving elements of type Position<E> */
	private List<Position<E>> positionList;
	/** holds the list for saving the descendants of type Position<E> */
	private List<Position<E>> descendantList;
	/** holds the list for saving the descendants of type Position<E> */
	private List<Position<E>> ancestorList;
	
	public LinkedTree() {
		this.size = 0;
		this.root = null;
		this.childrenList = new ArrayList<Position<E>>();
		this.elementList = new ArrayList<E>();
		this.positionList = new ArrayList<Position<E>>();
		this.descendantList = new ArrayList<Position<E>>();
		this.ancestorList = new ArrayList<Position<E>>();
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if(root != null)
			return false;
		return true;
	}
	
	@Override
	public int depth(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		if(isRoot(p))
			return 0;
		return depth(parent(p)) + 1;
	}

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
	 * @param node BinaryTreeNode<E>
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
	
	@Override
	public boolean isRoot(Position<E> p) throws InvalidPositionException {
		if(p != null && !isEmpty()) {
			TreeNode<E> node = (TreeNode<E>) p;
			return node.parent == null;
		} else {
			throw new InvalidPositionException();
		}
	}
	
	@Override
	public boolean isInternal(Position<E> p) throws InvalidPositionException {
		if(p != null && !isEmpty())
			//return (hasLeft(p) || hasRight(p));
			return false;
		else
			throw new InvalidPositionException();
	}

	@Override
	public boolean isExternal(Position<E> p) throws InvalidPositionException {
		if(p != null && !isEmpty())
			//return !(hasLeft(p) || hasRight(p));
			return false;
		else
			throw new InvalidPositionException();
	}
	
	@Override
	public Position<E> addRoot(E e) throws UnemptyTreeException {
		if(isEmpty()) {
			root = new TreeNode<E>(e);
			size = 1;
			return root;
		} else 
			throw new UnemptyTreeException();
	}

	@Override
	public Position<E> insertChild(Position<E> p, E e) throws InvalidPositionException {
		/**if(p == null || isEmpty() || (hasLeft(p) && hasRight(p)))
			throw new InvalidPositionException();
		if(isExternal(p) || !hasLeft(p))
			return insertLeft(p, e);
		else if(!hasRight(p))
			return insertRight(p, e);
		else
			throw new InvalidPositionException();**/
		return null;
	}
	
	@Override
	public E replaceElement(Position<E> p, E e) throws InvalidPositionException {
		if(p != null && !isEmpty()) {
			TreeNode<E> node = (TreeNode<E>) p;
			return node.element = e;
		} else
			throw new InvalidPositionException();
	}

	@Override
	public void swapElements(Position<E> p, Position<E> q) throws InvalidPositionException {
		if(p == null || isRoot(p))
			throw new InvalidPositionException();
		
		TreeNode<E> pNode = (TreeNode<E>) p;
		TreeNode<E> qNode = (TreeNode<E>) q;
		// save element of p in temp
		E temp = pNode.element;
		// save element of q in p
		pNode.element = qNode.element;
		// save temp in q
		qNode.element = temp;
	}

	@Override
	public List<E> elements() {
		elementList.removeAll(elementList);
		if(isEmpty())
			return elementList;
		elementList.add(root.element);
		elementsList(root);
		return elementList;
	}
	
	public List<E> elementsList(Position<E> p) {
		for(Position<E> child : children(p)) {
			elementList.add(child.element());
			// if child has also children
			if(isInternal(child))
				elementsList(child);
		}
		return elementList;
	}

	@Override
	public List<Position<E>> positions() {
		positionList.removeAll(positionList);
		if(isEmpty())
			return positionList;
		positionList.add(root);
		positionsList(root);
		return positionList;
	}
	
	public List<Position<E>> positionsList(Position<E> p) {
		for(Position<E> child : children(p)) {
			positionList.add(child);
			// if child has also children
			if(isInternal(child))
				positionsList(child);
		}
		return positionList;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(isEmpty())
			throw new EmptyTreeException();
		else
			return root;
	}

	@Override
	public Position<E> parent(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		else {
			BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
			return node.parent;
		}
	}

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

	@Override
	public List<Position<E>> descendants(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		// remove all positions from list
		descendantList.removeAll(descendantList);
		return descendantsList(p);
	}
	
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

	@Override
	public List<Position<E>> ancestors(Position<E> p) throws InvalidPositionException {
		if(p == null || isEmpty())
			throw new InvalidPositionException();
		ancestorList.removeAll(ancestorList);
		return ancestorsList(p);
	}

	public List<Position<E>> ancestorsList(Position<E> p) {
		if(isRoot(p))
			return ancestorList;
		ancestorList.add(parent(p));
		ancestorsList(parent(p));
		return ancestorList;
	}
	
	@Override
	public String toString() {
		return null;
	}

}
