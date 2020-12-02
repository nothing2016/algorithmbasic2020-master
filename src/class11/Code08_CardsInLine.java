package class11;

/**
 * 范围上的尝试模型
 *
 * 题目：给定一个整型数组arr，代表数字不同的纸牌排成一条线
 * 玩家A和B依次从左或右拿走一张牌，A和B都是聪明绝顶，规定A先拿，B后拿
 * 请返回赢家的分数
 */
public class Code08_CardsInLine {

	/**
	 * 返回先手和后手的分数，较大的就是赢家的分数
	 * @param arr
	 * @return
	 */
	public static int win1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return Math.max(
				f(arr, 0, arr.length - 1),
				s(arr, 0, arr.length - 1)
				);
	}

	// L....R
	// F  S  L+1..R
	         // L..R-1

	/**
	 * f： first 表示第一个拿牌的人（先手），在L到R范围上能获得的最大分数
	 * @param arr
	 * @param L
	 * @param R
	 * @return
	 */
	public static int f(int[] arr, int L, int R) {
		// 如果只有一张牌，直接拿走
		if (L == R) {
			return arr[L];
		}
		// 从左边或右边那一张牌，但拿完后，剩下的过程自己就变成后手了
		// 所以要选择最大的值
		return Math.max(
				arr[L] + s(arr, L + 1, R),
				arr[R] + s(arr, L, R - 1)
				);
	}

	// arr[L..R]

	/**
	 * s： second 表示第二个拿牌的人（后手），能获得的最大分数
	 * @param arr
	 * @param L
	 * @param R
	 * @return
	 */
	public static int s(int[] arr, int L, int R) {
		// 如果是后手，只有一张牌，肯定被先手拿走了，自己肯定什么都没有拿到，所以是0
		if (L == R) {
			return 0;
		}
		// 如果自己是后手，那么先手肯定将先手自己的牌拿走后，剩下最不利的给后手
		// 所以自己变成先手时，肯定拿到最小的牌
		// 注意： 这都是从先手的角度考虑的问题
		return Math.min(
				f(arr, L + 1, R), // arr[i]
				f(arr, L, R - 1)  // arr[j]
				);
	}

	public static int win2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[][] f = new int[N][N];
		int[][] s = new int[N][N];
		for(int i = 0; i < N;i++) {
			f[i][i] = arr[i];
		}
		// s[i][i] = 0;
		for(int i = 1; i < N;i++) {
			int L =0;
			int R =i;
			while(L < N && R < N) {
				
				f[L][R] = Math.max(
						arr[L] + s[L + 1][ R],
						arr[R] + s[L][R - 1]
						); 
				s[L][R] = Math.min(
						f[L + 1][R], // arr[i]
						f[L][R - 1]  // arr[j]
						); 
				
				L++;
				R++;
				
			}
		}
		return Math.max(f[0][N-1], s[0][N-1]);
	}

	public static void main(String[] args) {
		int[] arr = { 4,7,9,5,19,29,80,4 };
		// A 4 9
		// B 7 5
		System.out.println(win1(arr));
		System.out.println(win2(arr));

	}

}
