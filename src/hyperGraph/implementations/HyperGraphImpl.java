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
	 * @param args
	 * 
	 *            in args you pass the index numbers of the vertices that are 
	 *            connected to the new edge (indexes starting with zero)
	 * 
	 * 
	 */
	@Override
	public HyperGraph addEdge(int... args) {

		// turn the args ary into a List for easier access
		List<Integer> argsList = new ArrayList<Integer>();
		for (int i = 0; i < args.length; i++) {
			argsList.add(args[i]);
		}

		// values will contain the values that must be passed as param when addColumn is bla on the matrix,
		// therefore the length of values is the actual matrix-size +1
		int[] values = new int[inzidenzMatrix.height() + 1];

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
	public HyperGraph addVertex() {
		
		return null;
	}

	@Override
	public HyperGraph addVertex(int... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HyperGraph removeEdge() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HyperGraph removeVertex() {
		// TODO Auto-generated method stub
		return null;
	}

}
