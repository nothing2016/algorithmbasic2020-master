package class11;

import java.util.Stack;

/**
 * 题目：如果将一个栈的内容逆序，但不能申请额外的数据结构空间
 * 只可以使用少数几个变量
 */
public class Code04_ReverseStackUsingRecursive {

	/**
	 *
	 * @param stack
	 */
	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		int i = f(stack);
		reverse(stack);
		// 这一行一定是原来栈顶的值先被压到栈中的，这里有深度遍历的思维
		// 只有深度到底了，然后才会return, 因为f(stack)函数是将一个栈的栈底值返回，栈上面的值往下沉
		// 这样return的时候，一定是原来栈顶的元素最后return
		stack.push(i);
	}

	/**
	 * 将一个栈的栈底值返回，栈上面的值往下沉
	 * @param stack
	 * @return
	 */
	public static int f(Stack<Integer> stack) {
		int result = stack.pop();
		if (stack.isEmpty()) {
			return result;
		} else {
			int last = f(stack);
			stack.push(result);
			return last;
		}
	}

	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}

}
