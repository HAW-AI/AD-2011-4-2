package hyperGraph.interfaces;

/**
 * @author Tobias Meurer
 * @author Stephan Berngruber
 * 
 */

public interface HyperGraph {

	public HyperGraph addEdge(int... indexNumbersOfConnectedVertices);

	public HyperGraph addVertexViaIndexes(int... indexNumbersOfConnectedEdges);

	public HyperGraph addVertex(int[] vertexValues);

	public HyperGraph removeEdge(int indexOfEdge);

	public HyperGraph removeVertex(int indexOfVertex);

	//public String getEdgeToString(int index);

	//public int[] getEdgeAsArray(int index);

	/**
	 * @author Tobias Meurer
	 * @author Stephan Berngruber
	 * 
	 *         Class NotAHyperGraph: inner class only to produce a single
	 *         NotAHyperGraph-object Every Method of this class returns when
	 *         called this NotAHyperGraph-object
	 * 
	 */

	final public HyperGraph nAHG = new NotAHyperGraph();

	final public class NotAHyperGraph implements HyperGraph {

		private NotAHyperGraph() {
		};

		@Override
		public HyperGraph addEdge(int... indexNumbersOfConnectedVertices) {
			return new NotAHyperGraph();
		}

		@Override
		public HyperGraph addVertexViaIndexes(
				int... indexNumbersOfConnectedEdges) {
			return new NotAHyperGraph();
		}

		@Override
		public HyperGraph addVertex(int[] vertexValues) {
			return new NotAHyperGraph();
		}

		@Override
		public HyperGraph removeEdge(int indexOfEdge) {
			return new NotAHyperGraph();
		}

		@Override
		public HyperGraph removeVertex(int indexOfVertex) {
			return new NotAHyperGraph();
		}

		@Override
		public String toString() {
			return "Not a HyperGraph";
		}

		// @Override
		// public String getEdgeToString(int index) {
		// return "Not a HyperGraph";
		// }
		//
		// @Override
		// public int[] getEdgeAsArray(int index) {
		// return new int[0];
		// }

	}

}
