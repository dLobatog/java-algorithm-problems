/*
 * In this problem at each moment you have a set of intervals.
 * You can move from interval (a, b) from our set to interval (c, d) from our set if and only if
 * c < a < d or c < b < d.
 * Also there is a path from interval I1 from our set to interval I2 from
 * our set if there is a sequence of successive moves starting from I1 so that we can reach I2.
 *
 * Your program should handle the queries of the following two types:
 *
 * "1 x y" (x < y) — add the new interval (x, y) to the set of intervals.
 * The length of the new interval is guaranteed to be strictly greater than all the previous intervals.
 * "2 a b" (a ≠ b) — answer the question: is there a path from a-th (one-based) added interval to b-th (one-based) added interval?
 *
 * Answer all the queries. Note, that initially you have an empty set of intervals.
 *
 * Input
 * The first line of the input contains integer n denoting the number of queries, (1 ≤ n ≤ 100). Each of the following lines contains a query as described above. All numbers in the input are integers and don't exceed 109 by their absolute value.
 * It's guaranteed that all queries are correct.
 *
 * Output
 * For each query of the second type print "YES" or "NO" on a separate line depending on the answer.
 *
 */

import java.util.Scanner;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author Daniel Lobato Garcia (blog.daniellobato.me)
 */
public class Main {
		public static void main(String[] args) {
				InputStream inputStream = System.in;
				OutputStream outputStream = System.out;
				Scanner in = new Scanner(inputStream);
				PrintWriter out = new PrintWriter(outputStream);
				TaskB solver = new TaskB();
				solver.solve(1, in, out);
				out.close();
		}
}

class TaskB {

		public class Interval {
				private int x;

				public int getX() {
						return x;
				}

				public void setX(int x) {
						this.x = x;
				}

				public int getY() {
						return y;
				}

				public void setY(int y) {
						this.y = y;
				}

				public Interval(int x, int y) {
						this.x = x;
						this.y = y;
				}

				private int y;

		}

		private ArrayList<Interval> intervals;
		private boolean[] visited;

		public void solve(int testNumber, Scanner in, PrintWriter out) {
				intervals = new ArrayList<Interval>();
				int totalQueries = in.nextInt();

				for (int i = 0; i < totalQueries; i++) {
						int queryType = in.nextInt();
						int x = in.nextInt();
						int y = in.nextInt();

						if (queryType == 1)
								intervals.add(new Interval(x, y));

						if (queryType == 2) {
								visited = new boolean[intervals.size()];
								/*
									 If the interval Y is hit by a dfs starting from X,
									 there's a path between both
									 */
								dfs(x - 1);


								if (visited[y - 1]) {
										out.println("YES");
								} else {
										out.println("NO");
								}
						}
				}
		}

		public void dfs(int fromIndex) {
				visited[fromIndex] = true;
				Interval from = intervals.get(fromIndex);
				int a = from.getX();
				int b = from.getY();

				for (int i = 0; i < intervals.size(); i++) {
						if (visited[i])
								continue;

						Interval to = intervals.get(i);
						int c = to.getX();
						int d = to.getY();

						if ((c < a && a < d) || (c < b && b < d)) {
								dfs(i);
						}
				}
		}
}


