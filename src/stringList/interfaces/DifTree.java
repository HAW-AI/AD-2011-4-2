/**
 * 
 */
package stringList.interfaces;

/**
 * @author Kai Bielenberg (kai.bielenberg@haw-hamburg.de)
 *
 */
public interface DifTree {

    Object get(int index);

    boolean isLeaf();

    void addHead(Object o);
    void addTail(Object o);
    
    void delHead();
    void delTail();
    /**
     * @param indexElem
     * @param index
     */

    /**
     * @return
     */
    int getIndex();
    void setIndex(int index);
    Object getElem();
    DifTree getLeft();
    DifTree getRight();
   
}
