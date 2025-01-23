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
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		/*points = new Point[pts.length];
		for (int i = pts.length - 1; i >= 0; --i) {
		    Point p = pts[i];
		    points[i] = new Point(p);
		}*/
		algorithm = "SelectionSort";
	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		int i, j;
		for (i = 0; i < points.length - 1; ++i){
			int min = i;
			for (j = i+1; j < points.length; ++j){
				if (pointComparator.compare(points[j], points[min]) < 0){
					min = j;
				}
			}
		 swap(i, min);
		 }
		
		//Test Output
		/*for(Point pnt : points) {
			System.out.print("("+ pnt.getX()+", "+pnt.getY()+")");
		}
		System.out.println();*/
	}
}
