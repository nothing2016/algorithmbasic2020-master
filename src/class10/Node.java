package class10;

import java.util.ArrayList;

// 点结构的描述  A  0
public class Node {
	public int value;
	public int in;
	public int out;

	// 出度的节点
	public ArrayList<Node> nexts;
	// 出度的边
	public ArrayList<Edge> edges;

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}
