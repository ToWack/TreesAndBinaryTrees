package project4.classes;

import project4.interfaces.Position;

/**
 * application that creates a company hierarchy
 * 
 * @author Renato Oppliger, Tom Wacker
 * @version 1.0
 */
public class CompanyHierarchyExample {

	/**
	 * main method
	 * 
	 * @param args unused.
	 */
	public static void main(String[] args) {
		LinkedTree<String> tree = new LinkedTree<>();
		// ceo
		Position<String> ceo = tree.addRoot("Guido Geschüftsführer");
		// departments
		Position<String> accounts = tree.insertChild(ceo, "Buchhaltung");
		Position<String> it = tree.insertChild(ceo, "IT");
		Position<String> logistics = tree.insertChild(ceo, "Logistik");
		Position<String> sales = tree.insertChild(ceo, "Verkauf");
		
		// accounts
		tree.insertChild(accounts, "Tom Tobler");
		tree.insertChild(accounts, "Anna Ananas");
		
		// it
		tree.insertChild(it, "Horst Huber");
		tree.insertChild(it, "Daniel Donner");
		
		// logistics
		Position<String> commission = tree.insertChild(logistics, "Kommissionieren");
		Position<String> transport = tree.insertChild(logistics, "Spedition");
		Position<String> quality = tree.insertChild(logistics, "Qualitätskontrolle");
		Position<String> arrival = tree.insertChild(logistics, "Wareneingang");
		Position<String> outgoing = tree.insertChild(logistics, "Wareneingang");
		
		tree.insertChild(commission, "Peter Pech");
		tree.insertChild(commission, "Rudolf Radler");
		
		tree.insertChild(transport, "Eva Eisen");
		
		tree.insertChild(quality, "Gustav Getriebe");
		
		tree.insertChild(arrival, "Beatrice Bodenmann");
		
		tree.insertChild(outgoing, "Caesar Credo");
		
		// sales
		Position<String> northwest = tree.insertChild(sales, "Nordwestschweiz");
		Position<String> middle = tree.insertChild(sales, "Mittelland");
		Position<String> east = tree.insertChild(sales, "Ostschweiz");
		Position<String> tessin = tree.insertChild(sales, "Tessin");
		Position<String> west = tree.insertChild(sales, "Westschweiz");
		Position<String> central = tree.insertChild(sales, "Zentralschweiz");
		Position<String> zuerich = tree.insertChild(sales, "Zürich");
		
		tree.insertChild(northwest, "Susi Schindler");
		
		tree.insertChild(middle, "Paul Peier");
		
		tree.insertChild(east, "Gerhard Günstig");
		tree.insertChild(east, "Tatjana Tetris");
		
		tree.insertChild(tessin, "Guillermo Gonzalez");
		
		tree.insertChild(west, "Claudio Cool");
		tree.insertChild(west, "Ramona Rassig");
		
		tree.insertChild(central, "Günter Glanz");
		
		tree.insertChild(zuerich, "Arno Aare");
		tree.insertChild(zuerich, "Rolf Renner");
		tree.insertChild(zuerich, "Vanessa Vordermann");
		
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.toString());
	}

}
