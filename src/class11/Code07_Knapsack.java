package class11;

/**
 * 背包问题
 */
public class Code07_Knapsack {

	public static int getMaxValue(int[] w, int[] v, int bag) {
		return process(w, v, 0, 0, bag);
	}

	// 不变 ： w[]  v[]  bag
	// index... 最大价值
	// 0..index-1上做了货物的选择，使得你已经达到的重量是多少alreadyW
	// 如果返回-1，认为没有方案
	// 如果不返回-1，认为返回的值是真实价值
	public static int process(int[] w, int[] v, int index, int alreadyW, int bag) {
		if (alreadyW > bag) {
			return -1;
		}
		// 到这里证明重量没超

		// 如果没有货了
		if (index == w.length) {
			return 0;
		}
		int p1 = process(w, v, index + 1, alreadyW, bag);
		int p2next = process(w, v, index + 1, alreadyW + w[index], bag);
		int p2 = -1;
		if (p2next != -1) {
			p2 = v[index] + p2next;
		}
		return Math.max(p1, p2);

	}

	public static int maxValue(int[] w, int[] v, int bag) {
		return process(w, v, 0, bag);
	}

	// 只剩下rest的空间了，
	// index...货物自由选择，但是剩余空间不要小于0
	// 返回 index...货物能够获得的最大价值
	// 经典的从左往右的尝试模型，以index作为[index,N-1]选择的开头
	public static int process(int[] w, int[] v, int index, int rest) {
		// 没有空间了
		if (rest < 0) { // base case 1
			return -1;
		}
		// rest >=0
		// 没有货了
		if (index == w.length) { // base case 2
			return 0;
		}
		// 有货也有空间
		// 1.不要当前的这个index的货
		int p1 = process(w, v, index + 1, rest);
		int p2 = -1;
		// 2.要当前这个index的货
		int p2Next = process(w, v, index + 1, rest - w[index]);
		if(p2Next!=-1) {
			p2 = v[index] + p2Next;
		}
		return Math.max(p1, p2);
	}

	/**
	 * 直接根据暴力的尝试改的
	 * @param w
	 * @param v
	 * @param bag
	 * @return
	 */
	public static int dpWay(int[] w, int[] v, int bag) {
		int N = w.length;
		int[][] dp = new int[N + 1][bag + 1];
		// 当index == w.length ,dp[w.length][i]都是0，所以不需要初始化了
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 1; rest <= bag; rest++) {
				dp[index][rest] = dp[index + 1][rest];
				// 这里如果不这样写，就会掉进一个大坑中，因为必须要给付初始值dp[index][rest] = dp[index + 1][rest];
				if (rest >= w[index]) {
					dp[index][rest] = Math.max(dp[index + 1][rest], v[index] + dp[index + 1][rest - w[index]]);
				}
			}
		}
		return dp[0][bag];
	}


	/**
	 * dpWay直接根据暴力的尝试改的，但是不能明显的看出最后一行的填值
	 * 所以改成了更容易理解的代码
	 * @param w
	 * @param v
	 * @param bag
	 * @return
	 */
	public static int dpWay2(int[] w, int[] v, int bag) {
		int N = w.length;
		// dp[i][j] 表示[i,n-1]的物品中，容量为j时的最大价值
		int[][] dp = new int[N][bag + 1];
		for(int j =0;j<=bag;j++){
			// 对于最后一行，只有容量j能装下w[N-1]时，才有价值
			dp[N-1][j] = j >= w[N-1] ? v[N-1]: 0;
		}
		// 第一列都是0，因为j==0的空间，什么都装不下
		for(int i = N-2;i>=0;i--){
			for(int j = 1;j<=bag;j++){
				// 不要
				dp[i][j] = dp[i+1][j];
				// 要的情况，但是要保证空间够用
				if(j - w[i] >= 0){
					dp[i][j] = Math.max(dp[i][j],v[i] + dp[i+1][j-w[i]]);
				}
			}
		}
		return dp[0][bag];
	}

	/**
	 * 如果是完全背包问题，也就是每个物品都是无限的，那如何求解呢？
	 * 完全背包问题可以转换成背包问题，因为bag是有限的，所以每个物品的个数也是有限的
	 * 注：此解法没有经过测试，不能保证其正确性，只提供思路的参考
	 * @param w
	 * @param v
	 * @param bag
	 * @return
	 */
	public static int dpWay3(int[] w, int[] v, int bag) {
		int N = w.length;
		// dp[i][j] 表示[i,n-1]的物品中，容量为j时的最大价值
		int[][] dp = new int[N][bag + 1];
		for(int j =0;j<=bag;j++){
			// 对于最后一行，只有容量j能装下w[N-1]时，才有价值,如果能装，就看看最多能装几个
			dp[N-1][j] = j >= w[N-1] ? v[N-1] * (j/w[N-1]): 0;
		}
		// 第一列都是0，因为j==0的空间，什么都装不下
		for(int i = N-2;i>=0;i--){
			for(int j = 1;j<=bag;j++){
				// 在第i个物品中，选择的个数为[0,j/w[i]
				// 选0个
				dp[i][j] = dp[i+1][j];
				// 选择[1,j/w[i]个的情况，但是要保证空间够用
				for(int k = 1;k <= j/w[i];k++){
					if(j - k* w[i] >= 0){
						dp[i][j] = Math.max(dp[i][j],k * v[i] + dp[i+1][j-k*w[i]]);
					}
				}
			}
		}
		return dp[0][bag];
	}




	public static void main(String[] args) {
		int[] weights = { 3, 2, 4, 7 };
		int[] values = { 5, 6, 3, 19 };
		int bag = 56;
		System.out.println(maxValue(weights, values, bag));
		System.out.println(dpWay(weights, values, bag));
		System.out.println(dpWay2(weights, values, bag));
		System.out.println(dpWay3(weights, values, bag));
	}

}
