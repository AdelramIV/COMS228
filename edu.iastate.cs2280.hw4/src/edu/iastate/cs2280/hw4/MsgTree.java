package edu.iastate.cs2280.hw4;

/**
 * @author Garrett Brix
 * 
 */
public class MsgTree {

	public char payloadChar;
	public MsgTree left;
	public MsgTree right;
	
	//Keeps track of root node
	public MsgTree root;
	
	/*Can use a static char idx to the tree string for recursive
	solution, but it is not strictly necessary*/
	private static int staticCharIdx = 0;
	
	
	//Constructor building the tree from a string
	public MsgTree(String encodingString){
		//Scan next character in encoding string
		char curChar = encodingString.charAt(staticCharIdx);
		//Update index to read next char of the string
		staticCharIdx++;
		//If curChar is a ^ (internal or root node), create left and right recursively
		if(curChar == '^') {
			this.payloadChar = curChar;
			this.left = new MsgTree(encodingString);
			this.right = new MsgTree(encodingString);
		}
		//if curChar is not ^, this is a leaf so set payload equal to current char
		if(curChar != '^') {
			this.payloadChar = curChar;
		}
		if(curChar == ' ') {
			this.payloadChar = curChar;
		}
		if(curChar == '\n') {
			this.payloadChar = curChar;
		}
	}

	//Constructor for a single node with null children
	public MsgTree(char payloadChar){
		this.payloadChar = payloadChar;
		root = this;
		left = null;
		right = null;
	}
	
	public static boolean isLeaf(MsgTree node) {
		//If left and right are null it is a leaf at the end of a tree
		if(node.left == null && node.right == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Get methods
	//Returns left node
	public MsgTree getLeft(MsgTree node) {
		return node.left;
	}
	
	//Returns right node
	public MsgTree getRight(MsgTree node) {
		return node.right;
	}
	
	//Returns data in given node
	public char getPayload(MsgTree node) {
		//Checks if given node is internal or root IE has no payload
		if(node.payloadChar == ' ') {//TODO Find a way to make this null instead?
			throw new NullPointerException();
		}
		else {
			return node.payloadChar;
		}
	}
	
	//Set methods
	public void setPayload(MsgTree node, char data) {
		node.payloadChar = data;
	}
	
	//method to print characters and their binary codes
	public static void printCodes(MsgTree root, String code){
		
		//Checks that the tree exists
		if(root == null) {
			return;
		}
		
		//Checks if a leaf IE not a ^
		if(root.payloadChar != '^') {
			System.out.println(root.payloadChar + "        " + code);
		}
		
		printCodes(root.left, code+'0');
		printCodes(root.right, code+'1');
	}
	
	//method to print the decoded message
	public void decode(MsgTree codes, String msg) {
		MsgTree root = codes;
		int msgIdx = 0;
		
		//Iterate through entire msg
		while(msgIdx < msg.length()) {
			//While not at a leaf, navigate the tree based on char in msg
			while(!isLeaf(codes)) {
				if(msg.charAt(msgIdx) == '1') {
					codes = codes.right;
				}
				else{
					codes = codes.left;
				}
				msgIdx++;
			}
			System.out.print(codes.payloadChar);
			codes = root;
		}
	}

	//Statistics method
	public void printStats(MsgTree root, String code, String msg) {
		int totalChar = 0;
		int msgIdx = 0;
		
		
		//Calculates total number of characters in a message
		MsgTree totalRoot = root;
		//Iterate through entire msg
		while(msgIdx < msg.length()) {
			//While not at a leaf, navigate the tree based on char in msg
			while(!isLeaf(totalRoot)) {
				if(msg.charAt(msgIdx) == '1') {
					totalRoot = totalRoot.right;
				}
				else{
					totalRoot = totalRoot.left;
				}
				msgIdx++;
			}
			totalChar++;
			totalRoot = root;
		}
		
		//Calculates Avg number of bits
		double avgBit = (double)msg.length() / (double)totalChar;
		
		//Calculates space savings
		double spaceSavings = ((1 - ((double) msg.length())/((double) msg.length() * 16))*100);
		
		//Output statistics
		System.out.println("Avg bits/char:     " + avgBit);
		System.out.println("Total Characters:  " + totalChar);
		System.out.println("Space Savings:     " + spaceSavings + "%");
	}
}
