package hyperGraph.implementations;

import java.util.*;

import hyperGraph.Values;
import hyperGraph.interfaces.*;

final public class HyperGraphImpl implements HyperGraph {

	// public static void main(String[] args){
	// System.out.println(nAHG);
	//
	// }

	private final Matrix inzidenzMatrix;
	final public static HyperGraph emptyHyperGraph = new HyperGraphImpl(
			Values.matrix(0, 0, new int[0]));

	private HyperGraphImpl(Matrix inzidenzMatrix) {
		this.inzidenzMatrix = inzidenzMatrix;
	}

	/**
	 * @author Tobias Meurer
	 * @author Stephan Berngruber
	 * 
	 * @param indexNumbersOfVertices
	 * 
	 *            in args you pass the index numbers of the vertices that are
	 *            connected to the new edge (indexes starting with zero)
	 * 
	 * 
	 */
	@Override
	public HyperGraph addEdge(int... indexNumbersOfConnectedVertices) {
		if (indexNumbersOfConnectedVertices.length == 0) {
			return HyperGraph.nAHG;
		}

		// turn the args ary into a List for easier access
		List<Integer> argsList = new ArrayList<Integer>();
		for (int i = 0; i < indexNumbersOfConnectedVertices.length; i++) {
			argsList.add(indexNumbersOfConnectedVertices[i]);
		}

		// values will contain the values that must be passed as param when
		// addColumn is bla on the matrix
		int[] values = new int[inzidenzMatrix.height()];

		for (int i = 0; i < values.length; i++) {
			if (argsList.contains(i)) {
				values[i] = i;
			} else {
				values[i] = 0;
			}

		}
		Matrix newInzidenzMatrix = inzidenzMatrix.addColumn(values);
		HyperGraph newHyperGraph;
		if (newInzidenzMatrix instanceof NotAMatrix) {
			newHyperGraph = HyperGraph.nAHG;
		} else {
			newHyperGraph = new HyperGraphImpl(newInzidenzMatrix);
		}
		return newHyperGraph;
	}

	@Override
	public HyperGraph addVertex() {
		int[] values = new int[inzidenzMatrix.width()];
		for (int i = 0; i < values.length; i++) {
			values[i] = 0;
		}
		Matrix newInzidenzMatrix = inzidenzMatrix.addRow(values);
		HyperGraph newHyperGraph;
		if (newInzidenzMatrix instanceof NotAMatrix) {
			newHyperGraph = HyperGraph.nAHG;
		} else {
			newHyperGraph = new HyperGraphImpl(newInzidenzMatrix);
		}
		return newHyperGraph;
	}

	/**
	 * @author Tobias Meurer
	 * @author Stephan Berngruber
	 * 
	 * @param indexNumbersOfEdges
	 * 
	 *            in args you pass the index numbers of the edges that are
	 *            connected to the new vertex (indexes starting with zero)
	 * 
	 * 
	 */
	@Override
	public HyperGraph addVertex(int... indexNumbersOfConnectedEdges) {
		// turn the args ary into a List for easier access
		List<Integer> argsList = new ArrayList<Integer>();
		for (int i = 0; i < indexNumbersOfConnectedEdges.length; i++) {
			argsList.add(indexNumbersOfConnectedEdges[i]);
		}

		// values will contain the values that must be passed as param when
		// addColumn is bla on the matrix
		int[] values = new int[inzidenzMatrix.width()];

		for (int i = 0; i < values.length; i++) {
			if (argsList.contains(i)) {
				values[i] = i;
			} else {
				values[i] = 0;
			}

		}

		return new HyperGraphImpl(inzidenzMatrix.addColumn(values));
	}

	@Override
	public HyperGraph removeEdge(int indexOfEdge) {
		return new HyperGraphImpl(inzidenzMatrix.removeColumn(indexOfEdge));
	}

	@Override
	public HyperGraph removeVertex(int indexOfVertex) {
		return new HyperGraphImpl(inzidenzMatrix.removeRow(indexOfVertex));
	}

	@Override
	public String toString() {
		return "HyperGraph: " + inzidenzMatrix.toString();
	}

}
