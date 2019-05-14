package project4.classes;

import project4.interfaces.BinaryTree;
import project4.interfaces.Position;

import java.util.Scanner;

/**
 * application to get a morse code by letter or a letter by morse code
 * 
 * @author Renato Oppliger, Tom Wacker
 * @version 1.0
 */
public class MorseCodeExample {

	/** creating binary tree object */
	private static BinaryTree<String> alphabet = new LinkedBinaryTree<>();
	/** creating scanner object for user inputs */
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * creates the binary tree
	 */
	public static void createMorseTree() {
		alphabet.addRoot("");
		Position<String> e = alphabet.insertChild(alphabet.root(), "E");
		Position<String> t = alphabet.insertChild(alphabet.root(), "T");
		Position<String> i = alphabet.insertChild(e, "I");
		Position<String> a = alphabet.insertChild(e, "A");
		Position<String> n = alphabet.insertChild(t, "N");
		Position<String> m = alphabet.insertChild(t, "M");
		Position<String> s = alphabet.insertChild(i, "S");
		Position<String> u = alphabet.insertChild(i, "U");
		Position<String> w = alphabet.insertChild(a, "W");
		Position<String> d = alphabet.insertChild(n, "D");
		Position<String> k = alphabet.insertChild(n, "K");
		Position<String> g = alphabet.insertChild(m, "G");
		alphabet.insertChild(a, "R");
		alphabet.insertChild(m, "O");
		alphabet.insertChildren(s, "H", "V");
		alphabet.insertChild(u, "F");
		alphabet.insertChildren(w, "P", "J");
		alphabet.insertChildren(d, "B", "X");
		alphabet.insertChildren(k, "C", "Y");
		alphabet.insertChildren(g, "Z", "Q");
	}

	/**
	 * returns letter by morse code
	 * 
	 * @param code entered morse code by user
	 * @return letter by morse code
	 */
	public static String searchLetter(String code) {
		Position<String> pos = alphabet.root();
		for (int i = 0; i < code.length(); i++) {
			// get left child when it is a dot
			if (code.charAt(i) == '.')
				pos = alphabet.leftChild(pos);
			// get right child when it is a minus
			else if (code.charAt(i) == '-')
				pos = alphabet.rightChild(pos);
		}
		return pos.element();
	}

	/**
	 * returns morse code by letter
	 * 
	 * @param code entered letter by user
	 * @return morse code by letter
	 */
	public static String searchCode(String code) {
		String morseCode = "";
		Position<String> pos = getPosition(code);
		Position<String> parentPos = alphabet.parent(pos);
		
		// morse code is built up from below
		while(!(alphabet.isRoot(pos))) {
			// check left child
			if (alphabet.leftChild(parentPos).equals(pos))
				morseCode = "." + morseCode;
			// check right child
			else if (alphabet.rightChild(parentPos).equals(pos))
				morseCode = "-" + morseCode;
			
			pos = parentPos;
			parentPos = alphabet.parent(parentPos);
		}
		return morseCode;	
	}
	
	/**
	 * returns the position of a letter
	 * 
	 * @param code entered letter by user
	 * @return position of letter
	 */
	public static Position<String> getPosition(String code){
		Position<String> pos = null;
		for (int i = 0; i < alphabet.positions().size(); i++) {
			if (alphabet.positions().get(i).element().equals(code)) {
				pos = alphabet.positions().get(i);
			}
		}
		return pos;
	}
	
	/**
	 * main method
	 * 
	 * @param args unused.
	 */
	public static void main(String[] args) {
		boolean isFalse = false;
		createMorseTree();
		
		try {
			System.out.println("Enter your code: ");
			// get user input
			String code = scanner.nextLine().toUpperCase();
			
			// check if morse code is accepted
			for(int i = 0; i < code.length(); i++) {
				if(!(code.charAt(i) == '.') && !(code.charAt(i) == '-'))
					isFalse = true;
			}
			// if morse code is accepted
			if (!isFalse)
				System.out.println(searchLetter(code));
			// check if tree contains input
			else if (alphabet.elements().contains(code))
				System.out.println(searchCode(code));
			// error
			else
				System.out.println("The code you entered is not a letter or a morse code");
		} catch(RuntimeException ex) {
			System.out.println("Error: Unexpected error occured. " + ex.getMessage());
        }
	}
}