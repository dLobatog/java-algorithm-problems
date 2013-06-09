/* Suppose that A is a list of n numbers (A1, A2, A3, … , An) and
 * B (B1, B2, B3, .. ,Bn ) is a permutation of these numbers, we say B is K-Manipulative if and only if:

 M(B) = min( B1 xor B2, B2 xor B3, B3 xor B4, … , Bn-1 xor Bn, Bn xor B1 ) is not less than 2^K.
 You are given A. Find the largest K such that there exists a K-manipulative permutation B.

Input:

The first line is an integer N. The second line is the N integers A1 to An.

Output:
The largest possible K, or -1 if there is no solution.

Constraints:
N <= 100. Ai > 0 and can be represented in 32 bits. */


/*
 * If the result needs to be so that any permutation is less than 2^K, then the K
 * last bits can always be discarded, since 2^K, is 1 followed by k 0s in binary
 *
 * Then we can always look for the permutation where all elements are positioned so that
 * you never XOR a number with itself (that would equal 0, which is less than 2^K).
 *
 * To ensure the aforementioned condition, one can count the number of elements and make sure
 * there are no more than n/2 repetitions of the same number, because if there are n repetitions,
 * no permutation exists so that adjacent elements are never repeated.
 *
 * Solution time  is
 *          space is 2 * N  due to a temporary array
 *
 */


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {


		public static void main(String[] args) {
				Scanner in = new Scanner(System.in);
				int totalA   = in.nextInt();
				int[] A      = new int[totalA];

				for(int i = 0; i < totalA; i++)
						A[i] = in.nextInt();

				Arrays.sort(A);
				Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();

				for (int k = 31; k > 0; k--) {

						for (int i = 0; i < totalA; i++) {
								int repeatedTimes = occurrences.containsKey(A[i] >> k) ? (occurrences.get(A[i] >> k) + 1) : 1;
								occurrences.put(A[i] >> k, repeatedTimes);
						}

						if (largestElement(occurrences) <= (totalA / 2)) {
								System.out.println(k);
								return;
						}

						for (Map.Entry<Integer, Integer> entry : occurrences.entrySet())
								occurrences.put(entry.getKey(), 0);
				}
		}

		public static int largestElement(Map<Integer, Integer> map) {
				int max = 0;

				for (Map.Entry<Integer, Integer> entry : map.entrySet())
						if (max == 0 || entry.getValue() > max)
								max = entry.getValue();

				return max;
		}
}
