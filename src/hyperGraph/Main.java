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
public class Main {

	private Main() {
	}

	public static void main(String[] args) {

		Main main = new Main();
		main.go();

	}

	private void go() {
		HyperGraph hg = Values.emptyHyperGraph();
		hg.addVertex();
		hg.addEdge(0);
		
		
	}

}
