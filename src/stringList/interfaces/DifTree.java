/**
 * 
 */
package stringList.interfaces;

/**
 * @author Kai Bielenberg (kai.bielenberg@haw-hamburg.de)
 *
 */
public interface DifTree {

    
    /**
     * 
     * @param index
     * @return the found Objekt at the Index or Null if the Index is wrong.
     */
    Object get(int index);

    /**
     * 
     * @return true if the Tree is a Leaf. Left and Right are null!
     */
    boolean isLeaf();

    /**
     * Adds an Element to the Front of the Tree/List
     * @param o
     */
    boolean addHead(Object o);
    /**
     * Adds an Element to the Back of the Tree/List
     * @param o
     */
    boolean addTail(Object o);
    
    /**
     * del Head or Tail elements.
     */
    boolean delHead();
    boolean delTail();
    

    /**
     * @return current Index of the Node
     */
    int getIndex();
    
    /**
     * Sets Index of current Node. Sometimes needed when deleting Elems which have
     * an Element in Right or Left which is not null
     * @param index
     */
    void setIndex(int index);
    
    /**
     * 
     * @return Elem from the current Node
     */
    Object getElem();
    
    /**
     * 
     * @return left side of Node
     */
    DifTree getLeft();
    /**
     * 
     * @return right side of Node
     */
    DifTree getRight();
   

}
