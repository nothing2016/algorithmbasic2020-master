package class01;

/**
 * 求一个数，出现了奇数次
 * eor  ^ 异或操作 = 无进位相加
 * 题解：a ^ a = 0    a ^ b ^ b == b
 * 数组遍历一遍，那么剩下的就奇数次的数
 */
public class Code07_EvenTimesOddTimes {

	// arr中，只有一种数，出现奇数次，找出这个数
	public static void printOddTimesNum1(int[] arr) {
		int eor = 0;
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
		}
		System.out.println(eor);
	}

	// arr中，有两种数，出现奇数次，其他的都是偶数次，找出这两个数
	public static void printOddTimesNum2(int[] arr) {
		int eor = 0;
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
		}
		//结果肯定为： eor = a ^ b，因为偶数次的都异或为0了
		//
		// eor = a ^ b
		// eor != 0
		// eor必然有一个位置上是1
		// 0110010000
		// 0000010000
		int rightOne = eor & (~eor + 1); // 提取出最右的1
		// 得到的最右边的1会确定一种情况，a和b中，一定有一个数的这个位置为1，另一个为0
		int onlyOne = 0; // eor'
		for (int i = 0 ; i < arr.length;i++) {
			//  arr[1] =  111100011110000
			// rightOne=  000000000010000
			// 用所有最右1的这个数来异或的话，就会得到a和b其中的一个值，就是最右1的位置为1的数
			if ((arr[i] & rightOne) != 0) {
				onlyOne ^= arr[i];
			}
		}
		// eor ^ onlyOne 就会得到另一个数
		System.out.println(onlyOne + " " + (eor ^ onlyOne));
	}

	
	public static int bit1counts(int N) {
		int count = 0;
		
		//   011011010000
		//   000000010000     1
		
		//   011011000000
		// 
		
		
		
		while(N != 0) {
			int rightOne = N & ((~N) + 1);
			count++;
			N ^= rightOne;
			// N -= rightOne
		}
		
		
		return count;
		
	}
	
	
	public static void main(String[] args) {
		int a = 5;
		int b = 7;

		a = a ^ b;
		b = a ^ b;
		a = a ^ b;

		System.out.println(a);
		System.out.println(b);

		int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
		printOddTimesNum1(arr1);

		int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
		printOddTimesNum2(arr2);

	}

}
