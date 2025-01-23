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
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super();
		points = new Point[pts.length];
		for (int i = pts.length - 1; i >= 0; --i) {
		    Point p = pts[i];
		    points[i] = new Point(p);
		}
		algorithm = "MergeSort";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		mergeSortRec(points);
		
		//Test Output
		/*for(Point pnt : points) {
			System.out.print("("+ pnt.getX()+", "+pnt.getY()+")");
		}
		System.out.println();*/
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		if (pts.length < 2) {
            return;
        }

        int mid = pts.length / 2;
        Point[] left = new Point[mid];
        Point[] right = new Point[pts.length - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = pts[i];
        }
        for (int i = mid; i < pts.length; i++) {
            right[i - mid] = pts[i];
        }
        
        //Recursive call
        mergeSortRec(left);
        mergeSortRec(right);
        merge(pts, left, right);
	}

	//Helper function to re-merge halves
	private void merge(Point[] pts, Point[] left, Point[] right) {
		 int i = 0, j = 0, k = 0;
	        while (i < left.length && j < right.length) {
	            // Replace comparison based on the Point's sorting criteria (e.g., x-coordinates)
	            if (pointComparator.compare(left[i], right[j]) <= 0){
	                pts[k++] = left[i++];
	            } else {
	                pts[k++] = right[j++];
	            }
	        }
	        while (i < left.length) {
	            pts[k++] = left[i++];
	        }
	        while (j < right.length) {
	            pts[k++] = right[j++];
	        }
	}

}
