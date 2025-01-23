package edu.iastate.cs2280.hw2;

/**
 *  
 * @author Garrett Brix
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters 
{
	
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		Scanner scnr = new Scanner(System.in);
		int i = 1;
		Random generator = new Random();
		System.out.println("Performances of Four Sorting Algorithms in Point Scanning\r\n"
				+ "keys: 1 (random integers) 2 (file input) 3 (exit)");
		
		PointScanner[] scanners = new PointScanner[4]; 
		int key = 0;
		while(key != 3) {
			System.out.println();
			System.out.print("Trial " + i + ": ");
			key = scnr.nextInt();
			if(key == 3) {
				break;
			}
			switch(key) {
			case(1):
				System.out.print("Enter number of random points: ");
				int numPts = scnr.nextInt();
				
				scanners[0] = new PointScanner(generateRandomPoints(numPts, generator), Algorithm.SelectionSort);
				scanners[1] = new PointScanner(generateRandomPoints(numPts, generator), Algorithm.InsertionSort);
				scanners[2] = new PointScanner(generateRandomPoints(numPts, generator), Algorithm.MergeSort);
				scanners[3] = new PointScanner(generateRandomPoints(numPts, generator), Algorithm.QuickSort);
				break;
			case(2):
				System.out.println("Enter file name: ");
				String fileName = scnr.next();
				scanners[0] = new PointScanner(fileName, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(fileName, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(fileName, Algorithm.MergeSort);
				scanners[3] = new PointScanner(fileName, Algorithm.QuickSort);
				break;
			default:
				System.out.println("Invalid key input");
				i--;
				break;
			}
				
				System.out.println();
				System.out.println("Algorithm         Size   Time");
				System.out.println("----------------------------------");
				scanners[0].scan();
				scanners[0].stats();
				scanners[0].writeMCPToFile();
				
				scanners[1].scan();
				scanners[1].stats();
				scanners[1].writeMCPToFile();
				
				scanners[2].scan();
				scanners[2].stats();
				scanners[2].writeMCPToFile();
				
				scanners[3].scan();
				scanners[3].stats();
				scanners[3].writeMCPToFile();
				
				System.out.println("----------------------------------");
				for(PointScanner scn : scanners) {
					//Print out all the points?
				}
			i++;
		}
		System.out.println("Goodbye");
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	private static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{
		Point[] pts = new Point[numPts];
		for(int i = 0; i < numPts; i++) {
			int x = rand.nextInt(101) - 50;
			int y = rand.nextInt(101) - 50;
			pts[i] = new Point(x, y);
		}
		return pts; 
		
	}
	
}
