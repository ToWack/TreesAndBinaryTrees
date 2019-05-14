package project4.classes;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import project4.interfaces.Position;

/**
 * application where user can choose music style and interprets from a tree
 * 
 * @author Renato Oppliger, Tom Wacker
 * @version 1.0
 */
public class JukeBoxExample {
	
	/** creating binary tree object */
	private static LinkedTree<String> tree = new LinkedTree<>();
    /** creating scanner object for user inputs */
	private static Scanner scan = new Scanner(System.in);
	/** creating a counter with initial value 1 */
	private static int counter = 1;
	/** creating file object */
	private static File file;
	/** creating input stream for audio */
	private static AudioInputStream stream;
	/** creating clip object */
	private static Clip clip;
  
	/**
	 * creates the tree
	 */
	private static void generateTree() {
		Position<String> root = tree.addRoot("Musikstil");
		// add styles
		Position<String> folk = tree.insertChild(root, "Volksmusik");
		Position<String> swiss = tree.insertChild(root, "Mundart");
		Position<String> metal = tree.insertChild(root, "Metal");
		// add interprets
		tree.insertChild(folk, "Helene Fischer");
		tree.insertChild(folk, "Vanessa Mai");
		tree.insertChild(swiss, "Lo & Leduc");
		tree.insertChild(swiss, "Gölä");
		tree.insertChild(metal, "Metallica");
		tree.insertChild(metal, "Machine Head");
	}
	
	/**
	 * main method
	 * 
	 * @param args unused.
	 */
	public static void main(String[] args) {
		try {
			/** local variables for user input */
			int choiceStyle, choiceInterpret = 0;
			
			/** creates the tree */
			generateTree();
			
			System.out.println("Choose a music style:\n");
			
			/**  print out music styles  */
			for(Position<String> p : tree.positions()) {
				/** get only children of root */
				if(tree.depth(p) == 1) {
					System.out.println(counter + ": " + p.element());
					counter++;
				}
			}
		    
        	/** user input to choose music style */
        	choiceStyle = scan.nextInt();
        	
        	/** let user choose an interpret if chosen music style exists */
        	if(choiceStyle <= tree.children(tree.root()).size() && choiceStyle >= 1) {
        		/** get chosen music style */
            	Position<String> chosenMusicStyle = tree.children(tree.root()).get(choiceStyle - 1);
            	
            	/** set counter to start over */
            	counter = 1;
           
            	System.out.println("\nChoose a number to play a song from one of the listed interprets:\n");
            	
            	/**  print out music interprets of chosen style */
            	for(Position<String> p : tree.children(chosenMusicStyle)) {
    				System.out.println(counter + ": " + p.element());
    				counter++;
        		}
            	
            	/** user input to choose interpret */
            	choiceInterpret = scan.nextInt();
        	}        
        
            switch (choiceStyle) {
            	/** Volksmusik */
	            case 1:
	            	switch(choiceInterpret) {
	            		/** Helene Fischer */
	            		case 1:
	    	            	file = new File("./Helene_Fischer.wav");
	    	            	break;
	    	            /** Vanessa Mai */
	            		case 2:
	            			file = new File("./Vanessa_Mai.wav");
	            			break;
	            		default:
	            			file = new File("./Helene_Fischer.wav");
	            			break;
	            	}
	            	break;
	            /** Mundart */    
	            case 2:
	            	switch(choiceInterpret) {
	            		/** Lo & Leduc */
	            		case 1:
	    	            	file = new File("./Lo_Leduc.wav");
	    	            	break;
	    	            /** Gölä */
	            		case 2:
	            			file = new File("./Goelae.wav");
	            			break;
	            		default:
	            			file = new File("./Lo_Leduc.wav");
	            			break;
	            	}
	            	break;
	            /** Metal */
	            case 3:
	            	switch(choiceInterpret) {
	            		/** Metallica */
	            		case 1:
	    	            	file = new File("./Metallica.wav");
	    	            	break;
	    	            /** Machine Head */
	            		case 2:
	            			file = new File("./Machine_Head.wav");
	            			break;
	            		default:
	            			file = new File("./Metallica.wav");
	            			break;
	            	}
	            	break;	           
	            default:
	            	file = new File("./Bird.wav");
	        	    break;
            }
            // add audio file to audio input stream
    	    stream = AudioSystem.getAudioInputStream(file);
    	    // get clip
    	    // special data line for audio
    	    // preload of data possible -> instead of real time
    	    // with preload length is known -> ex. start at any position, loops
    	    clip = AudioSystem.getClip();
    	    // open stream with clip
    	    clip.open(stream);
    	    // start playing
    	    clip.start();
    	    // add sleep before close - 20 seconds
    	    Thread.sleep(20000);
    	    // close stream and exit
    	    stream.close();
		} catch(IndexOutOfBoundsException ex) {
			System.out.println("Error: Index out of bounds.");
		} catch(InputMismatchException ex) {
			System.out.println("Error: Input mismatch. Make sure you entered the correct data type.");
		} catch(RuntimeException ex) {
			System.out.println("Error: Unexpected error occured. " + ex.getMessage());
        } catch(Exception ex) {
        	System.out.println(ex.getMessage());
        }		
	}
}