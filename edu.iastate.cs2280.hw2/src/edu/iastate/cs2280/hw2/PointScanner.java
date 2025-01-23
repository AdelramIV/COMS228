package edu.iastate.cs2280.hw2;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 
 * @author  Garrett Brix
 *
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
		
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		sortingAlgorithm = algo;
		points = new Point[pts.length];
		for (int i = pts.length - 1; i >= 0; --i) {
		    Point p = pts[i];
		    points[i] = new Point(p);
		}
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		int count = 0;
		int yOrX = 0;
		int createPnt = 0;
		int x = 0, y = 0;
		sortingAlgorithm = algo;
		
		 try {
		        Scanner inFS = new Scanner(new FileInputStream(inputFileName));

		        while (inFS.hasNext()) {
		            if (inFS.hasNextInt()) {
		                inFS.nextInt();  // Read the integer
		                count++;
		            } 
		            else {
		                inFS.next();  // Skip non-integer values
		            }
		        }
		        inFS.close();

		        // Check if count is even (because we need pairs of integers for (x, y) coordinates)
		        if (count % 2 != 0) {
		            throw new InputMismatchException("File contains an odd number of integers: " + count);
		        }

		        // Initialize points array: count / 2 because we need pairs of integers for each Point
		        points = new Point[count / 2];
		        int index = 0;
		        // Second pass: read points and populate the array
		        
		        inFS = new Scanner(new FileInputStream(inputFileName));
		        while (inFS.hasNext()) {
		            if (inFS.hasNextInt()) {
		            	switch(yOrX) {
		            	case 0:
		            		x = inFS.nextInt();
		            		yOrX++;
		            		createPnt++;
		            		break;
		            	case 1:
		            		y = inFS.nextInt();
		            		yOrX--;
		            		if(createPnt == 1) {
		            			points[index] = new Point(x, y);
		            			index++;
		            			createPnt--;
		            		}
		            		break;
		            	default:
		            		break;
		            	}
		            } 
		            else {
		                inFS.next();  // Skip non-integer values
		            }

		        }

		    } 
		 	catch (FileNotFoundException e) {
		        System.out.println("File not found: " + e.getMessage());
		        throw e;
		    } 
		 	catch (InputMismatchException e) {
		        System.out.println("Error: " + e.getMessage());
		        throw e;
		    }
	}

	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @return
	 */
	public void scan()
	{
		
		AbstractSorter aSorter;
		//references aSorter with specified type of sorter
		switch (sortingAlgorithm) {
        case SelectionSort:
            aSorter = new SelectionSorter(points);
            break;
        case InsertionSort:
            aSorter = new InsertionSorter(points);
            break;
        case MergeSort:
            aSorter = new MergeSorter(points);
            break;
        case QuickSort:
            aSorter = new QuickSorter(points);
            break;
        default:
            throw new IllegalArgumentException("Invalid sorting algorithm: " + sortingAlgorithm);
		}
		
		//Sort X and find median X coord
		long startTime = System.nanoTime();
		aSorter.setComparator(0);
		aSorter.sort();
		int mX = aSorter.getMedian().getX();
		//Sort Y and find Median Y coord
		aSorter.setComparator(1);
		aSorter.sort();
		int mY = aSorter.getMedian().getY();
		long endTime = System.nanoTime();
		scanTime = endTime - startTime;
		//Create median point
		medianCoordinatePoint = new Point(mX, mY);
		
		//TODO sum up the times spent on the two sorting rounds and set the instance variable scanTime. 
		
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		String formattedOutput = String.format("%-15s %5d %10d", sortingAlgorithm, points.length, scanTime);
		System.out.println(formattedOutput);
		return null; 
	}
	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		System.out.println("MCP: ("+medianCoordinatePoint.getX()+", "+medianCoordinatePoint.getY()+")");
		return null; 
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
	    PrintWriter writer = new PrintWriter(new FileOutputStream("OutputTest.txt", true));
	    
	    writer.println("MCP: (" + medianCoordinatePoint.getX() + ", " + medianCoordinatePoint.getY() + ")");
	    writer.println("-------");
	    writer.close();
	}	

	

		
}
