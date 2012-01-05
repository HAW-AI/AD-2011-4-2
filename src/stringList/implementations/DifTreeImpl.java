/**
 * 
 */
package stringList.implementations;

import java.util.*;

import stringList.interfaces.DifTree;

/**
 * @author Kai Bielenberg (kai.bielenberg@haw-hamburg.de)
 * 
 */
public class DifTreeImpl implements DifTree {

    private Object elem;
    private int index;
    private DifTree left = null;
    private DifTree right = null;

    /*
     * (non-Javadoc)
     * 
     * @see stringList.interfaces.difTree#create(java.lang.Object[])
     */

    private DifTreeImpl(Object o, boolean isLeft) {
        // Konstruktor for a leav
        // used in the adding methods.
        this.elem = o;
        if (isLeft) {
            this.index = -1;
        } else {
            this.index = 1;
        }
    }

    private DifTreeImpl(List<Object> elems, boolean isLeft) {
        //Teilung der Liste in einen Differenzbaum
        if (elems == null)
            throw new IllegalArgumentException("Elems == null");
        int size = elems.size();
        this.index = ((int) Math.ceil((double) size / 2));
        // Wenn Size > 3, geht alles ganz normal. Das mittlere Element
        // wird das Elem des Objektes und die linken und rechten Teile
        
        if (size >= 3) {
            this.elem = elems.get(index - 1);
            this.left = create(elems.subList(0, index - 1), true);
            this.right = create(elems.subList(index, size), false);
        // Haben wir durch mehrere Aufteilungen eine Liste die nur noch 2 Elemente enthällt,
        // müssen wir unterscheiden ob diese ein Left oder Right Leav ist, da sich sonst
        // der Index anders verhällt.
        } else if (size == 2 && isLeft) {
            this.elem = elems.get(index);
            this.left = create(elems.subList(0, index), true);
        } else if (size == 2 && !isLeft) {
            this.elem = elems.get(index - 1);
            this.right = create(elems.subList(index, size), false);
        // Bei Listen der Größe 1 ist das Element ein Leav und wir nicht weiter geteilt.
        } else if (size == 1) {
            this.elem = elems.get(0);
        }
        // Bei einem Left Branch des Trees wird der Index negativ gesetzt, da er vom
        // Hauptindex abgezogen werden muss. Siehe Differenz Tree in der Aufhabe.
        if (isLeft) {
            index *= -1;
        }
    }

    private DifTree create(List<Object> elems, boolean isLeft) {
        if (elems == null)
            return new NaDifTree();
        return new DifTreeImpl(elems, isLeft);
    }

    public static DifTree create(List<Object> elems) {
        if (elems == null)
            return new NaDifTree();
        return new DifTreeImpl(elems, false);
    }

    public static DifTree create(Object o, boolean isLeft) {
        return new DifTreeImpl(o, isLeft);
    }
    
    public static DifTree create(Object... o) {
        if (o == null) return new NaDifTree();
        return new DifTreeImpl(Arrays.asList(o), false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see stringList.interfaces.DifTree#isLeaf(int)
     */
    @Override
    public boolean isLeaf() {
        return (left == null && right == null);
    }

    public DifTree getRight() {
        return this.right;
    }

    public DifTree getLeft() {
        return this.left;
    }

    public int getIndex() {
        return this.index;
    }

    public Object getElem() {
        return this.elem;
    }

    /*
     * (non-Javadoc)
     * 
     * @see stringList.interfaces.DifTree#setIndex()
     */
    @Override
    public void setIndex(int index) {
        this.index = index;

    }
    /*
     * (non-Javadoc)
     * 
     * @see stringList.interfaces.DifTree#get(int)
     */
    @Override
    public Object get(int indexElem) {
        // If Object not found, null is returned
        Object result = null;
        
        // Index found, result = found elem
        if (this.index == indexElem) {
            result = this.elem;
            
        // IndexElem bigger than actual Index,
        // get_ for right side is called. Recursive Search with accumulating Index.
        // Index needs to accumulated because of the Differenz Tree.
        // Only the first Index equals the Index in the former List.
        } else if (this.index < indexElem) {
            if (right != null) {
                result = ((DifTreeImpl) this.right).get_(indexElem, this.index);
            }
        // Same for Left side if indexElem is smaller than aktual Index.
        } else if (this.index > indexElem) {
            if (left != null) {
                result = ((DifTreeImpl) this.left).get_(indexElem, this.index);
            }
        } else {
            System.out.println("404 not found");
            return null;
        }
        return result;
    }

    private Object get_(int indexElem, int accu) {
        //Recursive search with accumulating Index.
        Object result = null;
        accu += this.index;
        // Index found
        if (indexElem == accu) {
            result = this.elem;
        // IndexElem bigger -> search in right side
        } else if (indexElem > accu) {
            if (right != null) {
                result = ((DifTreeImpl) right).get_(indexElem, accu);
            }
       // IndexElem smaller -> search in left side
        } else if (this.index < indexElem) {
            if (left != null) {
                result = ((DifTreeImpl) left).get_(indexElem, accu);
            }
        } else {
            System.out.println("404 not found");
            return null;
        }

        return result;
    }

    public String toString() {
        String l = "";
        String r = "";
        if (left != null)
            l = left.toString();
        if (right != null)
            r = right.toString();
        return ("[" + l + "(" + elem + "/" + index + ")" + r + "]");
    }

    /*
     * (non-Javadoc)
     * 
     * @see stringList.interfaces.DifTree#addHead(java.lang.Object)
     */
    @Override
    public boolean addHead(Object o) {
       boolean result = false;
       result = this.addHead_(o);
       System.out.println(result);
       if (result) this.setIndex(this.getIndex() +1);
        
       return result;
    }
    
    private boolean addHead_(Object o) {
        boolean result = false;
        if (left == null) {
            left = DifTreeImpl.create(o, true);
            result = true;
        } else {
            result = ((DifTreeImpl) left).addHead_(o);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see stringList.interfaces.DifTree#addTail(java.lang.Object)
     */
    @Override
    public boolean addTail(Object o) {
        boolean result = false;
        //Same as addHead, but we only search right.
        if (right == null) {
            right = DifTreeImpl.create(o, false);
            result = true;
        } else {
            return right.addTail(o);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see stringList.interfaces.DifTree#delHead()
     */
    @Override
    public boolean delHead() {
        boolean result = false;
        result = this.delHead_();
        if (result) this.setIndex(this.getIndex() -1 );
        return result;
    }
    
    private boolean delHead_() {
        boolean result = false;
        if (!isLeaf()) {
            //The head is the item witch is on the leftest side of the tree.
            //therefor we search this item.
            // It is found if the item has no more Left Side set. Then the 
            // left side from the Parent of this Item is set to null
            // This Equals deleting the Head Item of the List.
            if (left != null) {
                if (left.isLeaf()) {
                    left = null;
                    result = true;
                //If the Item which is to be deleted is no leav, we have to do a 
                // little reconstruction in the Tree. 
                // The deleted Item has an Item on its rigth side. Which now 
                // needs to be reindexed and then put one level upwards.
                } else if (left.getLeft() == null) {// and right.getLeft != null
                    int tempIndex = left.getIndex();
                    left = left.getRight();
                    left.setIndex(left.getIndex() + tempIndex);
                    result = true;
                } else {
                   result = ((DifTreeImpl) left).delHead_();
                }
            } else if (left == null) {
                this.setIndex(this.getIndex() + right.getIndex());
                this.elem = right.getElem();
                DifTree tempRight = right.getRight();
                this.left = right.getLeft();
                this.right = tempRight;
                result = true;
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see stringList.interfaces.DifTree#delTail()
     */
    @Override
    public boolean delTail() {
        boolean result = false;
        //same logic as delHead
        if (!isLeaf()) {

            if (right != null) {
                if (right.isLeaf()) {
                    right = null;
                    result = true;
                } else if (right.getRight() == null) {// and right.getLeft !=
                                                      // null
                    int tempIndex = right.getIndex();
                    right = right.getLeft();
                    right.setIndex(right.getIndex() + tempIndex);
                    result = true;
                } else {
                    result = right.delTail();
                }
            } else if (right == null) {
                this.setIndex(this.getIndex() + left.getIndex());
                this.elem = left.getElem();
                DifTree tempLeft = left.getLeft();
                this.right = left.getRight();
                this.left = tempLeft;
                result = true;
            }

        }
        return result;

    }


}
