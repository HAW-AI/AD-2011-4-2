package hyperGraph;

import hyperGraph.interfaces.HyperGraph;

public class Main2 {
	public static void main(String[] args) {
		HyperGraph hyp1 = Values.emptyHyperGraph();
		HyperGraph hyp2 = hyp1.addEdge();
		System.out.println(hyp2);
		// hyp1.addEdge(1);
		hyp2 = hyp1.addVertexViaIndexes();
		System.out.println(hyp2);
		hyp1 = hyp2.addVertexViaIndexes();
		System.out.println(hyp1);
		hyp2 = hyp1.addVertexViaIndexes();
		System.out.println(hyp2);
		hyp1 = hyp2.addEdge();
		System.out.println(hyp1);
		hyp1 = hyp2.addEdge(0, 1, 2);
		System.out.println(hyp1);
		hyp2 = hyp1.addEdge(0);
		System.out.println(hyp2);
		hyp1 = hyp2.addEdge(1);
		System.out.println(hyp1);
		hyp2 = hyp1.addEdge(2);
		System.out.println(hyp2);
		hyp1 = hyp2.addEdge();
		System.out.println(hyp1);
		// hyp1 = hyp2.addEdge(1,2,4);
		// System.out.println(hyp1);
		hyp1 = hyp2.addVertexViaIndexes();
		System.out.println(hyp1);
		hyp2 = hyp1.addVertexViaIndexes(1, 3);
		System.out.println(hyp2);
		// hyp2 = hyp1.addVertexViaIndexes(1,3,4);
		// System.out.println(hyp2);
		hyp1 = hyp2.removeVertex(5);
		System.out.println(hyp1);
		hyp2 = hyp1.removeEdge(5);
		System.out.println(hyp2);

	}
}
