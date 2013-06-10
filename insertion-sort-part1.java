/*
	 Given a sorted list with an unsorted number V in the right-most cell, can you write some simple code to insert V into the array so it remains sorted?

	 Print the array every time a value is shifted in the array until the array is fully sorted. The goal of this challenge is to follow the correct order of insertion sort.

	 Input Format
	 Two lines of input:
	 s - the size of the array
	 ar - the sorted array of integers

	 Output Format
	 On each line, output the entire array every time an item is shifted in it.

	 Sample Input

	 5
	 2 4 6 8 3
	 Sample Output

	 2 4 6 8 8
	 2 4 6 6 8
	 2 4 4 6 8
	 2 3 4 6 8
	 */


import java.util.*;
public class Solution {

		static void insertionSort(int[] ar) {
				int v = ar[ar.length - 1];
				int checkLimit = ar.length - 1;
				while (ar[checkLimit] >= v && ar[checkLimit - 1] >= v) {
						ar[checkLimit] = ar[checkLimit - 1];
						checkLimit--;
						printArray(ar);
						if (checkLimit == 0)
								break;
				}
				ar[checkLimit] = v;
				printArray(ar);
		}

		static void printArray(int[] ar) {
				for(int n: ar)
						System.out.print(n+" ");
				System.out.println("");
		}

		public static void main(String[] args) {
				Scanner in = new Scanner(System.in);
				int n = in.nextInt();
				int[] ar = new int[n];
				for(int i=0;i<n;i++)
						ar[i]=in.nextInt();
				insertionSort(ar);
		}
}

