/**
 * 
 *
 */
package hyperGraph;

import hyperGraph.interfaces.*;

/**
 * @author Tobias Meurer
 * @author Stephan Berngruber
 * 
 */
public class CopyOfMain {
	public static void main(String[] args) {
		HyperGraph hyp1 = Values.emptyHyperGraph();
		HyperGraph hyp2 = hyp1.addEdge();
		System.out.println(hyp2);
		// hyp1.addEdge(1);
		hyp2 = hyp1.addVertex();
		System.out.println(hyp2);
		hyp1 = hyp2.addVertex();
		System.out.println(hyp1);
		hyp2 = hyp1.addVertex();
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
//		hyp1 = hyp2.addEdge(1,2,4);
//		System.out.println(hyp1);
		hyp1 = hyp2.addVertex();
		System.out.println(hyp1);
		hyp2 = hyp1.addVertex(1,3);
		System.out.println(hyp2);
//		hyp2 = hyp1.addVertex(1,3,4);
//		System.out.println(hyp2);
		hyp1 = hyp2.removeVertex(4);
		System.out.println(hyp1);
		

	}
}
