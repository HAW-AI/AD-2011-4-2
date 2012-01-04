/**
 * 
 */
package stringList.interfaces;

/**
 * @author Kai Bielenberg (kai.bielenberg@haw-hamburg.de)
 *
 */
public interface IndexedList {
    IndexedList create(Object[] o);
    
    void pushHead(Object elem);
    void pushTail(Object elem);
    Object popHead();
    Object popTail();
    Object get(int index);
}
