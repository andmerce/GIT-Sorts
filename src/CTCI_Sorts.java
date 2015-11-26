import java.util.*;
/*
 * CRACKING THE CODING INTERVIEW SORTS
 * 11/2/2015
 * Runtime: 0(n log(n)) average and worst case.
 * Memory: depends
 */
public class CTCI_Sorts {
	
	
	public static void mergeSort(int[] a){
		
		int[] helper = new int[a.length];
		mergeSort(a, helper, 0, a.length - 1);
	}
	
	public static void mergeSort(int[] a, int[] helper, int low, int high){
		if(low < high){
			int middle = (low + high) / 2;
			//printIntArray(a);
			System.out.println("");
			System.out.println("MERGESORT!");
			mergeSort(a, helper, low, middle); //Sort left half
			mergeSort(a, helper, middle +1, high); //Sort right half
			merge(a, helper, low, middle, high); // Merge them
		}
	}
	public static void printIntArray(int[] a){
		
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i]);
		}
		
		
	}
	/* This is where the real sorting and merger happens
	 * 11/2/2015
	 */
	public static void merge(int[] a, int[] helper, int low, int middle, int high){
		//Copy both halves into a helper array
		for(int i =low; i <= high; i++){
			helper[i] = a[i];
		}
		
		int helperLeft = low;
		int helperRight = middle +1;
		int current = low;
		/* Iterate through helper array. Compare the left and right
		 * half, copying back the smaller element from the two halves
		 * into the original array.*/
		while(helperLeft <= middle && helperRight <= high){
			if (helper[helperLeft] <= helper[helperRight]){
				a[current] = helper[helperRight];
				helperLeft++;
							
			}else {// If right element is smaller than left element
				a[current] = helper[helperRight];
				helperRight++;
				
			}
			current++;
		}
		
		/* Copy the rest of the left side of the array into the
		 * target array */
		int remaining = middle - helperLeft;
		for(int i = 0; i <= remaining; i++){
			a[current + i] = helper[helperLeft +i];
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {1, 2, 3, 4, 5, 6};
		int[] b = {4, 2, 5, 7, 9, 0, 1};
		mergeSort(a);
		printIntArray(a);

	}

}
