/**
 * 
 *
 */
package hyperGraph;

import java.io.File;
import java.util.*;
import hyperGraph.interfaces.*;

/**
 * @author Tobias Meurer
 * @author Stephan Berngruber
 * 
 */
public final class HGController {

	public static void main(String[] args) throws InterruptedException {
		new HGController().go(args);
	}

	private void go(String[] args) throws InterruptedException {
		if (args.length >= 1 && new File(args[0]).canRead()) {
			HGRawData rawData = HGParser.parse(args[0]);
			createHyperGraph(rawData);
		} else {
			HGRawData rawData = HGParser.parse("HyperGraphFiles/someGraph.hg");
			createHyperGraph(rawData);
		}
	}

	/*
	 * creates a HyperGraph from of the given rawdata in the parameters
	 */
	private void createHyperGraph(HGRawData rawData) {
		/*
		 * create a Graph with one fake vertex. The fake vertex is needed to be
		 * able to add the edges - otherwise we wouldn't be able to add edges,
		 * 'cause they can't be added without a connection to a vertex
		 */
		HyperGraph hyperGraph = Values.emptyHyperGraph().addVertexViaIndexes();
		int width = rawData.getWidth();
		int[] values = rawData.getValues();
		int[] subValues = new int[width];
		// adding the needed amount of edges
		for (int i = 0; i < rawData.getWidth(); i++) {
			hyperGraph = hyperGraph.addEdge(0);
		}

		for (int i = 0; i < rawData.getHeight(); i++) {
			System.arraycopy(values, i * width, subValues, 0, width);
			System.out.println(Arrays.toString(subValues));

			hyperGraph = hyperGraph.addVertex(subValues);
		}
		// remove the fake vertex
		hyperGraph = hyperGraph.removeVertex(0);
		System.out.println(hyperGraph);

	}

	private HGController() {
	}

}
