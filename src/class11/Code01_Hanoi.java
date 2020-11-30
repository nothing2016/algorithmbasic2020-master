package class11;

import java.util.Stack;

public class Code01_Hanoi {

	public static void hanoi1(int n) {
		leftToRight(n);
	}

	// 请把1~N层圆盘 从左 -> 右
	public static void leftToRight(int n) {
		if (n == 1) {
			System.out.println("Move 1 from left to right");
			return;
		}
		leftToMid(n - 1);
		System.out.println("Move " + n + " from left to right");
		midToRight(n - 1);
	}

	// 请把1~N层圆盘 从左 -> 中
	public static void leftToMid(int n) {
		if (n == 1) {
			System.out.println("Move 1 from left to mid");
			return;
		}
		leftToRight(n - 1);
		System.out.println("Move " + n + " from left to mid");
		rightToMid(n - 1);
	}

	public static void rightToMid(int n) {
		if (n == 1) {
			System.out.println("Move 1 from right to mid");
			return;
		}
		rightToLeft(n - 1);
		System.out.println("Move " + n + " from right to mid");
		leftToMid(n - 1);
	}

	public static void midToRight(int n) {
		if (n == 1) {
			System.out.println("Move 1 from mid to right");
			return;
		}
		midToLeft(n - 1);
		System.out.println("Move " + n + " from mid to right");
		leftToRight(n - 1);
	}

	public static void midToLeft(int n) {
		if (n == 1) {
			System.out.println("Move 1 from mid to left");
			return;
		}
		midToRight(n - 1);
		System.out.println("Move " + n + " from mid to left");
		rightToLeft(n - 1);
	}

	public static void rightToLeft(int n) {
		if (n == 1) {
			System.out.println("Move 1 from right to left");
			return;
		}
		rightToMid(n - 1);
		System.out.println("Move " + n + " from right to left");
		midToLeft(n - 1);
	}

	public static void hanoi2(int n) {
		if (n > 0) {
			func(n, "left", "right", "mid");
		}
	}

	// 1~i 圆盘 目标是from -> to， other是另外一个

	/**
	 * 汉诺塔问题如何划分成子问题，1- N的圆盘中，N表示最大的圆盘，如果将1-N从圆盘 from柱子 移动到 to柱子 呢?
	 *
	 * 而且在整个过程中不能大的压小的
	 *
	 * 1.化解子问题，我们思考一下，N是最大的圆盘，也就是在1- (N-1)的所有圆盘在移动的过程中
	 * N永远在最底下，也就是说N永远都不会压到其他的小圆盘
	 *
	 * 2.这样我们就有了化解的思路
	 *    a. 将1- （n-1）先放到 other上，不管是如何做到的，反正假设一定能做到
	 *    b. 将N 放到 to 中，这一步肯定是最简单的
	 *    c. 将 1- （n-1）放回to， 放回的过程我们肯定是忽略N的，因为N最大，永远不会被比他大的压住
	 *       所以可以认为在移动 1- （n-1）的过程中，N是被忽略掉的
	 *
	 *  3. 这样我就知道移动 1 - （n-1）的过程和移动 1- n的过程是同一个递归，由此可解
	 *
	 *
	 * @param N  n个圆盘
	 * @param from 从柱子from开始移动
	 * @param to  这是目的地柱子
	 * @param other 这是一个用于缓存的柱子
	 */
	public static void func(int N, String from, String to, String other) {
		// 如果已经递归到了最上面最小的圆盘，直接移动即可，这也是递归的出口
		if (N == 1) { // base
			System.out.println("Move 1 from " + from + " to " + to);
		} else {
			// 将 1 - (n-1) 个圆盘先从from 移动到 other ,to 作为缓存区
			func(N - 1, from, other, to);
			// 将N移动到 to,N到达目的地后永远可以不再变化了，后面的交给 1- （N-1）
			System.out.println("Move " + N + " from " + from + " to " + to);
			// 将 1 - (n-1) 从other移动到to即可
			func(N - 1, other, to, from);
		}
	}

	public static class Record {
		public boolean finish1;
		public int base;
		public String from;
		public String to;
		public String other;

		public Record(boolean f1, int b, String f, String t, String o) {
			finish1 = false;
			base = b;
			from = f;
			to = t;
			other = o;
		}
	}

	public static void hanoi3(int N) {
		if (N < 1) {
			return;
		}
		Stack<Record> stack = new Stack<>();
		stack.add(new Record(false, N, "left", "right", "mid"));
		while (!stack.isEmpty()) {
			Record cur = stack.pop();
			if (cur.base == 1) {
				System.out.println("Move 1 from " + cur.from + " to " + cur.to);
				if (!stack.isEmpty()) {
					stack.peek().finish1 = true;
				}
			} else {
				if (!cur.finish1) {
					stack.push(cur);
					stack.push(new Record(false, cur.base - 1, cur.from, cur.other, cur.to));
				} else {
					System.out.println("Move " + cur.base + " from " + cur.from + " to " + cur.to);
					stack.push(new Record(false, cur.base - 1, cur.other, cur.to, cur.from));
				}
			}
		}
	}

	public static void main(String[] args) {
		int n = 3;
		hanoi1(n);
		System.out.println("============");
		hanoi2(n);
		System.out.println("============");
		hanoi3(n);
	}

}
