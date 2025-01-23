package edu.iastate.cs2280.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author Garrett Brix
 *
 */

/**
 * 
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
	
	// Other private instance variables if you need ... 
		
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		super();
		points = new Point[pts.length];
		for (int i = pts.length - 1; i >= 0; --i) {
		    Point p = pts[i];
		    points[i] = new Point(p);
		}
		algorithm = "QuickSort";
	}
		

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 */
	@Override 
	public void sort()
	{
		 // Start the quicksort on the entire array of points
        quickSortRec(0, points.length - 1);
        
        //Test Output
        /*for(Point pnt : points) {
			System.out.print("("+ pnt.getX()+", "+pnt.getY()+")");
		}
		System.out.println();*/
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		if (first < last) {
            // Partition the array and get the pivot index
            int pivotIndex = partition(first, last);

            // Recursively sort elements before and after partition
            quickSortRec(first, pivotIndex - 1);
            quickSortRec(pivotIndex + 1, last);
        }
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
		 Point pivot = points[last];
	        int i = first - 1;

	        for (int j = first; j < last; j++) {
	            if (pointComparator.compare(points[j], pivot) <= 0) {
	                i++;
	                swap(i, j);
	            }
	        }

	        swap(i + 1, last);

	        return i + 1; 
	}	
		

}
