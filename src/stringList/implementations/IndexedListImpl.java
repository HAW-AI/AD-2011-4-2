/**
 * 
 */
package stringList.implementations;

import java.util.*;

import stringList.interfaces.DifTree;
import stringList.interfaces.IndexedList;

/**
 * @author Kai Bielenberg (kai.bielenberg@haw-hamburg.de)
 *
 */
public class IndexedListImpl implements IndexedList {

    private DifTree tree;

    private IndexedListImpl(Object[] o){
        this.tree = DifTreeImpl.create(Arrays.asList(o));
    }
    
    /* (non-Javadoc)
     * @see stringList.interfaces.IndexedList#create(java.lang.Object[])
     */
    @Override
    public IndexedList create(Object[] o) {
        return new IndexedListImpl(o);
    }

    /* (non-Javadoc)
     * @see stringList.interfaces.IndexedList#pushHead(java.lang.Object)
     */
    @Override
    public void pushHead(Object elem) {
        
    }

    /* (non-Javadoc)
     * @see stringList.interfaces.IndexedList#pushTail(java.lang.Object)
     */
    @Override
    public void pushTail(Object elem) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see stringList.interfaces.IndexedList#popHead()
     */
    @Override
    public Object popHead() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see stringList.interfaces.IndexedList#popTail()
     */
    @Override
    public Object popTail() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see stringList.interfaces.IndexedList#get(int)
     */
    @Override
    public Object get(int index) {
        // TODO Auto-generated method stub
        return null;
    }

}
