package class10;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
	// 图中所以的点
	public HashMap<Integer, Node> nodes;
	// 图中所有的边
	public HashSet<Edge> edges;
	
	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}
