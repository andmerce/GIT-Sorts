import java.util.*;
import java.io.*;
import edu.princeton.cs.algs4.StdIn;
// Template for Sort classes 10/26/2015
public class Sort {

	
	
	//Compares two items
	private static boolean less(Comparable v, Comparable w){
		
		//Compares 2 objects and returns a negative int, zero, or
		//positive int to indicate that the object is less than,
		//equal to, or greater than the existing object.
		//ex: if v is greater than w return positive; 
		//    if v is less than w return negative;
		return v.compareTo(w) < 0;
	}
	
	//exchanges two items (exchanges int i for j)
	private static void exch(Comparable [] a, int i, int j){
		//temporary buffer
		Comparable t = a[i]; 
		
		a[i] = a[j]; 
		a[j] = t;
	}
	
	//show what's in the array
	private static void show(Comparable [] a){
		for(int i = 0; i < a.length; i++)
		{
			System.out.println(a[i] + " ");
		}
		System.out.println();
	}
	
	public static boolean isSorted(Comparable[] a){
		for(int i = 1; i < a.length; i++)
		{
			//iterate through the list to see it's in order
			//doesn't work with duplicates
			if(less(a[i], a[i -1])) return false;
			
		}
		return true;
	}
	
	//running time is constant regardless of input 10/26/2015
	public static void selectionSort(Comparable[] a){
		//Sort a[] into increasing order
		int N = a.length;
		for(int i = 0; i < N; i++)
		{
			//Exchange a[i] with smallest entry in
			//a[i +1...N]
			int min = i;
			//make j ahead of i by 1 to iterate through array
			for(int j = i + 1; j < N; j++)
			{
				//if j is less than min, set
				//min to be the lowest number
				if(less(a[j], a[min]))
				{
					min = j;
				}			
			}
			//swap i and min's locations because min
			//is always the lowest number.
			exch(a, i, min);
		}
	}
	public static void insertionSort(Comparable[] a)
	{
		int N = a.length;
		for(int i = 1; i < N; i++)
		{
			//Insert a[i] among a[i-1], a[i-2], a[i-3]...
			for(int j = i; j > 0 && less(a[j], a[j-1]); j--)
				exch(a, j, j-1);
		}
	}
	
	public static void shellSort(Comparable[] a)
	{
		int N = a.length;
		int h = 1;
		while(h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364...
		while (h >= 1)
		{
			//h-sort the array
			for (int i = h; i < N; i++)
			{
				//INsert a[i] among a[i - h], a[i-2*h], a[i-3*h]...
				for(int j = i; j >= h && less(a[j], a[j-h]); j -= h)
				{
					exch(a, j, j-h);
				}
			}
			h = h/3;
		}
	}
	/*******************************************************
	 * MERGESORT!!!!!!!!
	 * 10/26/2015
	 * @param a
	 * @return
	 * *****************************************************
	 */
	//auxilary array for merges
	private static Comparable[] aux;
	public static void sort(Comparable[] a)
	{
		aux = new Comparable[a.length];//allocate space just once
		sort(a, 0, a.length -1);
	}
	/*
	 * To sort  subarray a[lo..hi] we divide it into two parts:
	 * a[lo..mid] and a[mid+1..hi], sort them independently(via recursive calls)
	 * and merge the resulting ordered subarrays to produce the result 10/26/2015
	 */
	private static void sort(Comparable[] a, int lo, int hi)
	{
		// this is the base case to get out of recursive call
		if(hi <= lo) return;
		//split the array in two
		int mid = lo + (hi - lo)/2;
		//recursively sort the left half
		sort(a, lo, mid);
		//recursively sort the right half
		sort(a, mid+1, hi);
		//merge both sorted halves
		merge(a, lo, mid, hi);
	}
	
	/*
	 * This method merges by first copying into the auxiliary array aux[] then
	 * merging back to a[]. In the merge (the second for loop), there are four
	 * conditions: 
	 * left half exhausted(take from the right), 
	 * right half exhausted(take from the left),
	 * current key on right less than current key on left(take from the right)
	 * and current keyon right greater than or equal to current key on left(take from the left
	 */
	private static void merge(Comparable[] a, int lo, int mid, int hi)
	{
		//Merge a[lo..mid] with a[mid+1..hi]
		int i = lo, j = mid+1;
		for (int k = lo; k <=hi; k++)
		{
			aux[k] = a[k];
		}
		// Merge back to a[lo..hi]
		for(int k = lo; k <= hi; k++)
		{
			//left half exhausted(take from the right)
			if(i>mid)                             a[k] = aux[j++];
			//right half exhausted(take from the left),
			else if(j>hi)                         a[k] = aux[i++];
			//current key on right less than current key on left(take from the right)
			else if(less(aux[j], aux[i]))         a[k] = aux[j++];
			//and current keyon right greater than or equal to current key on left(take from the left)
			else                                  a[k] = aux[i++];
		}
	}
	public static String [] returnStringArray(String a)
	{
		String input[] = new String[a.length()];
		for(int i = 0; i < a.length(); i++)
		{
			input[i] = input[i].valueOf(a.charAt(i));
		}
		
		return input;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		//BufferedReader StdIn = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter a bunch of strings!");
		String temp = in.nextLine();
		String a [] = returnStringArray(temp);
		selectionSort(a);
		assert isSorted(a);
		show(a);

	}

}
