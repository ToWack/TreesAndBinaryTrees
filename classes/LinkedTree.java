package project4.classes;

import java.util.List;

import project4.exceptions.EmptyTreeException;
import project4.exceptions.InvalidPositionException;
import project4.exceptions.UnemptyTreeException;
import project4.interfaces.Position;
import project4.interfaces.Tree;

public class LinkedTree<E> implements Tree<Integer> {

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int height() throws EmptyTreeException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Integer> elements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Position<Integer>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<Integer> root() throws EmptyTreeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<Integer> parent(Position<Integer> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Position<Integer>> children(Position<Integer> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Position<Integer>> descendants(Position<Integer> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Position<Integer>> ancestors(Position<Integer> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRoot(Position<Integer> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInternal(Position<Integer> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isExternal(Position<Integer> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int depth(Position<Integer> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Position<Integer> addRoot(Integer e) throws UnemptyTreeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<Integer> insertChild(Position<Integer> p, Integer e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer replaceElement(Position<Integer> p, Integer e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void swapElements(Position<Integer> p, Position<Integer> q) throws InvalidPositionException {
		// TODO Auto-generated method stub

	}

}
