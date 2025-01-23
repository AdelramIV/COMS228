package edu.iastate.cs2280.hw4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Garrett Brix
 * 
 */
public class TreeMain {

	public static void main(String[] args) throws FileNotFoundException {
		String encodingString = null;
		String codeString = null;
		
		Scanner inFS = new Scanner(new FileInputStream(args[0]));
		while (inFS.hasNext()) {
			//Works for something simple like cadbard but will need to be adjusted to handle multiple encoding lines
			encodingString = inFS.nextLine();
			String secondLine = inFS.nextLine();
			if(secondLine.contains("^")) {
				encodingString += '\n';
				encodingString += secondLine;
				codeString = inFS.nextLine();
			}
			else{
				codeString = secondLine;
			}
		}
		inFS.close();
		
		//Creates the tree
		MsgTree codeTree = new MsgTree(encodingString);
		
		//Printing the encoded message
		System.out.println("MESSAGE:");
		codeTree.decode(codeTree, codeString);
		System.out.println();

		//Printing all the characters in the code table
		System.out.println("character      code");
		System.out.println("-------------------------");
		codeTree.printCodes(codeTree, "");
		
		//Printing statistics
		System.out.println("\nSTATISTICS:");
		codeTree.printStats(codeTree, "", codeString);
	}

}
