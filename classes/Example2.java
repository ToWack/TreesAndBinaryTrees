package project4.classes;

import project4.interfaces.Position;

public class Example2 {

	public static void main(String[] args) {
		LinkedTree<String> tree = new LinkedTree<>();
		// ceo
		Position<String> ceo = tree.addRoot("Guido Geschäftsführer");
		// departments
		Position<String> accounts = tree.insertChild(ceo, "Buchhaltung");
		Position<String> purchasing = tree.insertChild(ceo, "Einkauf");
		Position<String> hr = tree.insertChild(ceo, "Human Resources");
		Position<String> it = tree.insertChild(ceo, "IT");
		Position<String> logistics = tree.insertChild(ceo, "Logistik");
		Position<String> marketing = tree.insertChild(ceo, "Marketing");
		Position<String> sales = tree.insertChild(ceo, "Verkauf");
		// accounts
		tree.insertChild(accounts, "Thomas Tobler");
		tree.insertChild(accounts, "Anna Ananas");
		// purchasing
		Position<String> delivery = tree.insertChild(purchasing, "Lieferanten");
		Position<String> groups = tree.insertChild(purchasing, "Produktklassen");
		
		tree.insertChild(delivery, "Hanna Hansen");
		
		Position<String> bureau = tree.insertChild(groups, "Büromaterial");
		Position<String> electronics = tree.insertChild(groups, "Elektronik");
		Position<String> food = tree.insertChild(groups, "Nahrungsmittel");
		
		tree.insertChild(bureau, "Renate Regenwasser");
		
		tree.insertChild(electronics, "Petra Prestige");
		
		tree.insertChild(food, "Katja Kriechmayer");
		// hr
		tree.insertChild(hr, "Sandra Stulzer");
		// it
		tree.insertChild(it, "Horst Huber");
		tree.insertChild(it, "Daniel Danzig");
		// logistics
		Position<String> commission = tree.insertChild(logistics, "Kommissionieren");
		Position<String> transport = tree.insertChild(logistics, "Spedition");
		Position<String> quality = tree.insertChild(logistics, "Qualitätskontrolle");
		Position<String> arrival = tree.insertChild(logistics, "Wareneingang");
		Position<String> outgoing = tree.insertChild(logistics, "Wareneingang");
		
		tree.insertChild(commission, "Peter Parker");
		tree.insertChild(commission, "Rudolf Ranzig");
		
		tree.insertChild(transport, "Gaetan Gubler");
		
		tree.insertChild(quality, "Gustav Gans");
		
		tree.insertChild(arrival, "Beatrice Bodenmann");
		
		tree.insertChild(outgoing, "Caesar Credo");
		// marketing
		tree.insertChild(marketing, "Otto Opel");
		tree.insertChild(marketing, "Stefan Stegmaier");
		// sales
		Position<String> northeast = tree.insertChild(sales, "Nordwestschweiz");
		Position<String> middle = tree.insertChild(sales, "Mittelland");
		Position<String> east = tree.insertChild(sales, "Ostschweiz");
		Position<String> tessin = tree.insertChild(sales, "Deutschsprachig");
		Position<String> west = tree.insertChild(sales, "Westschweiz");
		Position<String> central = tree.insertChild(sales, "Zentralschweiz");
		Position<String> zuerich = tree.insertChild(sales, "Zürich");
		
		tree.insertChild(northeast, "Susi Schindler");
		
		tree.insertChild(middle, "Paul Peyer");
		
		tree.insertChild(east, "Gerhard Günstig");
		tree.insertChild(east, "Tatjana Tetris");
		
		tree.insertChild(tessin, "Guillermo Gonzalez");
		
		tree.insertChild(west, "Philip Promo");
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
