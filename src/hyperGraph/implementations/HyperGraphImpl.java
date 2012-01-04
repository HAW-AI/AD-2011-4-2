package hyperGraph.implementations;

import java.util.*;
import hyperGraph.Values;
import hyperGraph.interfaces.*;

/**
 * @author Tobias Meurer
 * @author Stephan Berngruber
 * 
 */

final public class HyperGraphImpl implements HyperGraph {

	private final InzidenzMatrix incidenceMatrix;
	final public static HyperGraph emptyHyperGraph = new HyperGraphImpl(
			Values.matrix(0, 0, new int[0]));

	// Constructing impossible !
	private HyperGraphImpl(InzidenzMatrix incidenceMatrix) {
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

		/*
		 * it's not allowed to add a edge without a connection to at least one
		 * vertex
		 */
		if (indexNumbersOfConnectedVertices.length == 0) {
			return HyperGraph.nAHG;
		}

		List<Integer> argsList = new ArrayList<Integer>();
		// maxInt (for precondition check): save the biggest Integer (see below)
		int maxInt = -1;
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
		if (incidenceMatrix.height() <= maxInt) {
			throw new IndexOutOfBoundsException(
					"At least one vertex with an index given in the methodeargument, doesn't exist in the actual graph!");
		}

		/*
		 * values will contain the values that must be passed as param when
		 * addColumn is called on the matrix
		 */
		int[] values = new int[incidenceMatrix.height()];

		for (int i = 0; i < values.length; i++) {
			if (argsList.contains(i)) {
				values[i] = 1;
			} else {
				values[i] = 0;
			}

		}
		InzidenzMatrix newIncidenceMatrix = incidenceMatrix.addColumn(values);
		HyperGraph newHyperGraph = getNewHyperGraph(newIncidenceMatrix);
		return newHyperGraph;
	}

	/**
	 * @author Tobias Meurer
	 * @author Stephan Berngruber
	 * 
	 * @param vertexValues
	 * 
	 *            in vertexValues you pass 0 if not connected to the edge with
	 *            the index == the index of the array vertexValues, else 1
	 * 
	 * 
	 */
	@Override
	public HyperGraph addVertex(int[] vertexValues) {

		// get the new Matrix by calling addRow()
		InzidenzMatrix newIncidenceMatrix = incidenceMatrix.addRow(vertexValues);
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
	 *            connected to the new vertex (indexes starting with zero) if no
	 *            args passed, just an vertex will be added
	 * 
	 * 
	 */
	@Override
	public HyperGraph addVertexViaIndexes(int... indexNumbersOfConnectedEdges) {
		List<Integer> argsList = new ArrayList<Integer>();
		// maxInt (for preconditioncheck): save the biggest Integer (see below)
		int maxInt = -1;
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
		if (incidenceMatrix.width() <= maxInt) {
			throw new IndexOutOfBoundsException(
					"At least one edge with an index given in the methodeargument, doesn't exist in the actual graph!");
		}

		/*
		 * values will contain the values that must be passed as param when
		 * addColumn() is called on the matrix
		 */
		int[] values = new int[incidenceMatrix.width()];

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
		InzidenzMatrix newIncidenceMatrix = incidenceMatrix.addRow(values);
		HyperGraph newHyperGraph = getNewHyperGraph(newIncidenceMatrix);
		return newHyperGraph;
	}

	/**
	 * Helper Methode, inclusive catching a NotaMatrix Object
	 * 
	 * @param IncidenceMatrix
	 * @return
	 */
	private HyperGraph getNewHyperGraph(InzidenzMatrix IncidenceMatrix) {
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
		return "HyperGraph:\n" + incidenceMatrix.toString();
	}

}
