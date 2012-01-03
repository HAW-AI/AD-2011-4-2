package hyperGraph.interfaces;

public interface HyperGraph {

	public HyperGraph addEdge();

	public HyperGraph addVertex();

	public HyperGraph removeEdge();

	public HyperGraph removeVertex();

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
		public HyperGraph addEdge() {
			return new NotAHyperGraph();
		}

		@Override
		public HyperGraph addVertex() {
			return new NotAHyperGraph();
		}

		@Override
		public HyperGraph removeEdge() {
			return new NotAHyperGraph();
		}

		@Override
		public HyperGraph removeVertex() {
			return new NotAHyperGraph();
		}

	}

}
