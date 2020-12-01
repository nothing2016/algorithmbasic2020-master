package class11;

/**
 * 题目： 规定1和a对应，2和b对应，3和c对应
 * 那么111 就可以转成 aaa ak ka
 * 给定一个只有数字组成的数字，能转成多少种结果
 */
public class Code06_ConvertToLetterString {

	public static int number(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		return process(str.toCharArray(), 0);
	}

	// str[0...i-1]已经转化完了，固定了
	// i之前的位置，如何转化已经做过决定了, 不用再关心
	// i... 有多少种转化的结果
	public static int process(char[] str, int i) {
		if (i == str.length) { // base case
			return 1;// 如果已经到了末尾，证明这一种转化是成功的，就返回1
		}
		// 如果是0，比如01，对不起，转不了，0没有对应的值
		if (str[i] == '0') {
			return 0;
		}
		// 如果i是1，那么i一定可以转成a,而且i和i+1也能转（因为最大为19，小于26，肯定能转）
		if (str[i] == '1') {
			int res = process(str, i + 1);
			if (i + 1 < str.length) {
				res += process(str, i + 2);
			}
			return res;
		}
		// 2开头的情况
		if (str[i] == '2') {
			int res = process(str, i + 1);
			// 必须是20 到 26之间才能转
			if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
				res += process(str, i + 2); // (i和i+1)作为单独的部分，后续有多少种方法
			}
			return res;
		}
		// 否则是3开头的，3开头一定只能是3转成c,然后i+1之后的继续转了，因为30以上一定大于26
		return process(str, i + 1);
	}
	
	public static int dpWays2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[] dp = new int[N+1];
		dp[N] = 1;
		for(int i = N-1; i >= 0; i--) {
			if (str[i] == '0') {
				dp[i] = 0;
			}
			if (str[i] == '1') {
				dp[i] = dp[i + 1];
				if (i + 1 < str.length) {
					dp[i] += dp[i + 2];
				}
			}
			if (str[i] == '2') {
				dp[i] = dp[i + 1];
				if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
					dp[i] += dp[i + 2]; // (i和i+1)作为单独的部分，后续有多少种方法
				}
			}
		}
		return dp[0];
	}
	

	public static int dpWays(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[] dp = new int[N + 1];
		dp[N] = 1;
		for (int i = N - 1; i >= 0; i--) {
			if (str[i] == '0') {
				dp[i] = 0;
			} else if (str[i] == '1') {
				dp[i] = dp[i + 1];
				if (i + 1 < N) {
					dp[i] += dp[i + 2];
				}
			} else if (str[i] == '2') {
				dp[i] = dp[i + 1]; 
				if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
					dp[i] += dp[i + 2];
				}
			} else {
				dp[i] = dp[i + 1];
			}
		}
		return dp[0];
	}

	public static void main(String[] args) {
		System.out.println(number("11111"));
		System.out.println(dpWays2("11111"));
	}

}
