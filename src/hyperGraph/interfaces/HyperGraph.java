package hyperGraph.interfaces;

public interface HyperGraph {

	public HyperGraph addEdge(int... indexNumbersOfConnectedVertices);

	public HyperGraph addVertex(int... indexNumbersOfConnectedEdges);

	public HyperGraph removeEdge(int indexOfEdge);

	public HyperGraph removeVertex(int indexOfVertex);

	/**
	 * @author Tobias Meurer
	 * @author Stephan Berngruber
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
		public HyperGraph addVertex(int... indexNumbersOfConnectedEdges) {
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
			// TODO Auto-generated method stub
			return "Not a HyperGraph";
		}

	}

}
