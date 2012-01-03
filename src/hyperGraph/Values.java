package hyperGraph;

import hyperGraph.implementations.*;
import hyperGraph.interfaces.*;

final public class Values {

	private Values() {
	}
	
	public static Matrix matrix(int width, int height, int[] values){
		return MatrixImpl.create(width, height, values);
	}
	
	public static HyperGraph emptyHyperGraph(){
		return HyperGraphImpl.emptyHyperGraph;		
	}

}
