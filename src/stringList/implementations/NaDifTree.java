/**
 * 
 */
package stringList.implementations;

import stringList.interfaces.DifTree;

/**
 * @author Kai Bielenberg (kai.bielenberg@haw-hamburg.de)
 *
 */
public class NaDifTree implements DifTree {

    
    public NaDifTree(){}
    /* (non-Javadoc)
     * @see stringList.interfaces.DifTree#get(int)
     */
    @Override
    public Object get(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see stringList.interfaces.DifTree#isLeaf()
     */
    @Override
    public boolean isLeaf() {
        // TODO Auto-generated method stub
        return false;
    }
    /* (non-Javadoc)
     * @see stringList.interfaces.DifTree#addHead(java.lang.Object)
     */
    @Override
    public boolean addHead(Object o) {
        
        return false;
    }
    /* (non-Javadoc)
     * @see stringList.interfaces.DifTree#addTail(java.lang.Object)
     */
    @Override
    public boolean addTail(Object o) {
        return false;
        
    }
    /* (non-Javadoc)
     * @see stringList.interfaces.DifTree#delHead()
     */
    @Override
    public boolean delHead() {
        return false;
        
    }
    /* (non-Javadoc)
     * @see stringList.interfaces.DifTree#delTail()
     */
    @Override
    public boolean delTail() {
       return false;
        
    }
    /* (non-Javadoc)
     * @see stringList.interfaces.DifTree#getIndex()
     */
    @Override
    public int getIndex() {
        // TODO Auto-generated method stub
        return 0;
    }
    /* (non-Javadoc)
     * @see stringList.interfaces.DifTree#setIndex(int)
     */
    @Override
    public void setIndex(int index) {
        // TODO Auto-generated method stub
        
    }
    /* (non-Javadoc)
     * @see stringList.interfaces.DifTree#getElem()
     */
    @Override
    public Object getElem() {
        // TODO Auto-generated method stub
        return null;
    }
    /* (non-Javadoc)
     * @see stringList.interfaces.DifTree#getLeft()
     */
    @Override
    public DifTree getLeft() {
        // TODO Auto-generated method stub
        return null;
    }
    /* (non-Javadoc)
     * @see stringList.interfaces.DifTree#getRight()
     */
    @Override
    public DifTree getRight() {
        // TODO Auto-generated method stub
        return null;
    }

}
