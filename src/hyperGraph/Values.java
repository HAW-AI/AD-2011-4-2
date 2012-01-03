package hyperGraph;

import hyperGraph.implementations.*;
import hyperGraph.interfaces.*;

final public class Values {

	private Values() {
	}
	
	public static Matrix matrix(int height, int width, int[] values){
		return MatrixImpl.create(height, width, values);
	}
	
	public static HyperGraph emptyHyperGraph(){
		return HyperGraphImpl.emptyHyperGraph;		
	}

}
