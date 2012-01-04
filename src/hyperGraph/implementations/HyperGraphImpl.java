package hyperGraph.implementations;

import java.util.*;

import hyperGraph.Values;
import hyperGraph.interfaces.*;

final public class HyperGraphImpl implements HyperGraph {

	private final Matrix incidenceMatrix;
	final public static HyperGraph emptyHyperGraph = new HyperGraphImpl(
			Values.matrix(0, 0, new int[0]));

	private HyperGraphImpl(Matrix incidenceMatrix) {
		this.incidenceMatrix = incidenceMatrix;
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
		int heightOfIncidenceMatrix = incidenceMatrix.height();

		if (indexNumbersOfConnectedVertices.length == 0) {
			return HyperGraph.nAHG;
		}

		List<Integer> argsList = new ArrayList<Integer>();
		int maxInt = 0;
		/*
		 * turn the args ary into a List for easier access and save the biggest
		 * Integer in maxInt for precondition Check
		 */
		for (int i = 0; i < indexNumbersOfConnectedVertices.length; i++) {
			int nextInt = indexNumbersOfConnectedVertices[i];
			argsList.add(nextInt);
			if (nextInt > maxInt) {
				maxInt = nextInt;
			}
		}

		/*
		 * throw an exception if a Integer of the args... parameter is equal or
		 * lager then the width of the matrix, because then a vertex with that
		 * index doesn't exist
		 */
		if (heightOfIncidenceMatrix <= maxInt) {
			throw new IndexOutOfBoundsException(
					"At least one index of the edges, in the parameter, doesn't exist in the actual graph!");
		}

		/*
		 * values will contain the values that must be passed as param when
		 * addColumn is called on the matrix
		 */
		int[] values = new int[heightOfIncidenceMatrix];

		for (int i = 0; i < values.length; i++) {
			if (argsList.contains(i)) {
				values[i] = 1;
			} else {
				values[i] = 0;
			}

		}
		Matrix newIncidenceMatrix = incidenceMatrix.addColumn(values);
		HyperGraph newHyperGraph = getNewHyperGraph(newIncidenceMatrix);
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

		int widthOfIncidenceMatrix = incidenceMatrix.width();

		List<Integer> argsList = new ArrayList<Integer>();
		int maxInt = 0;
		/*
		 * turn the args ary into a List for easier access and save the biggest
		 * Integer in maxInt for precondition Check
		 */
		for (int i = 0; i < indexNumbersOfConnectedEdges.length; i++) {
			int nextInt = indexNumbersOfConnectedEdges[i];
			argsList.add(nextInt);
			if (nextInt > maxInt) {
				maxInt = nextInt;
			}
		}
		/*
		 * throw an exception if a Integer of the args... parameter is equal or
		 * lager then the width of the matrix, because then an edge with that
		 * index doesn't exist
		 */
		if (widthOfIncidenceMatrix <= maxInt) {
			throw new IndexOutOfBoundsException(
					"At least one index of the edges, in the parameter, doesn't exist in the actual graph!");
		}

		/*
		 * values will contain the values that must be passed as param when
		 * addColumn() is called on the matrix
		 */
		int[] values = new int[widthOfIncidenceMatrix];

		/*
		 * the matrix expects just 0 (if there is no connection to the Edge) and
		 * 1 (if there is a connection ) in the parameter of addRow() (see
		 * matrix), so we have to fill values with 0 and only with 1 for the
		 * given indexes
		 */
		for (int i = 0; i < values.length; i++) {
			if (argsList.contains(i)) {
				values[i] = 1;
			} else {
				values[i] = 0;
			}

		}

		// get the new Matrix by calling addRow()
		Matrix newIncidenceMatrix = incidenceMatrix.addRow(values);
		HyperGraph newHyperGraph = getNewHyperGraph(newIncidenceMatrix);
		return newHyperGraph;
	}

	/**
	 * Helper Methode, for
	 * 
	 * @param IncidenceMatrix
	 * @return
	 */
	private HyperGraph getNewHyperGraph(Matrix IncidenceMatrix) {
		HyperGraph newHyperGraph;
		// catch a NotaMatrix if necessary
		if (IncidenceMatrix instanceof NotAMatrix) {
			newHyperGraph = HyperGraph.nAHG;
		} else {
			newHyperGraph = new HyperGraphImpl(IncidenceMatrix);
		}
		return newHyperGraph;
	}

	@Override
	public HyperGraph removeEdge(int indexOfEdge) {
		return new HyperGraphImpl(incidenceMatrix.removeColumn(indexOfEdge));
	}

	@Override
	public HyperGraph removeVertex(int indexOfVertex) {
		return new HyperGraphImpl(incidenceMatrix.removeRow(indexOfVertex));
	}

	@Override
	public String toString() {
		return "HyperGraph: " + incidenceMatrix.toString();
	}

}
