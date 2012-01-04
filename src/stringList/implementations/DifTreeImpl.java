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
        // Konstruktor für ein Blatt
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
        } else if (size == 2 && isLeft) {
            this.elem = elems.get(index);
            this.left = create(elems.subList(0, index), true);
        } else if (size == 2 && !isLeft) {
            this.elem = elems.get(index - 1);
            this.right = create(elems.subList(index, size), false);
        } else if (size == 1) {
            this.elem = elems.get(0);
        }
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
     * @see stringList.interfaces.DifTree#get(int)
     */
    @Override
    public Object get(int indexElem) {
        Object result = null;
        if (this.index == indexElem) {
            result = this.elem;
        } else if (this.index < indexElem) {
            if (right != null) {
                result = ((DifTreeImpl) this.right).get_(indexElem, this.index);
            }
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
        Object result = null;
        accu += this.index;
        if (indexElem == accu) {
            result = this.elem;
        } else if (indexElem > accu) {
            if (right != null) {
                result = ((DifTreeImpl) right).get_(indexElem, accu);
            }
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
        return ("[ " + l + " (" + elem + " / " + index + ") " + r + " ]");
    }

    /*
     * (non-Javadoc)
     * 
     * @see stringList.interfaces.DifTree#addHead(java.lang.Object)
     */
    @Override
    public void addHead(Object o) {
        if (left == null) {

            left = DifTreeImpl.create(o, true);
        } else {
            left.addHead(o);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see stringList.interfaces.DifTree#addTail(java.lang.Object)
     */
    @Override
    public void addTail(Object o) {
        if (right == null) {

            right = DifTreeImpl.create(o, false);
        } else {
            right.addTail(o);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see stringList.interfaces.DifTree#delHead()
     */
    @Override
    public void delHead() {
        if (!isLeaf()) {

            if (left != null) {
                if (left.isLeaf()) {
                    left = null;
                } else if (left.getLeft() == null) {// and right.getLeft != null
                    int tempIndex = left.getIndex();
                    left = left.getRight();
                    left.setIndex(left.getIndex() + tempIndex);
                } else {
                    left.delHead();
                }
            } else if (left == null) {
                this.setIndex(this.getIndex() + right.getIndex());
                this.elem = right.getElem();
                DifTree tempRight = right.getRight();
                this.left = right.getLeft();
                this.right = tempRight;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see stringList.interfaces.DifTree#delTail()
     */
    @Override
    public void delTail() {
        if (!isLeaf()) {

            if (right != null) {
                if (right.isLeaf()) {
                    right = null;
                } else if (right.getRight() == null) {// and right.getLeft !=
                                                      // null
                    int tempIndex = right.getIndex();
                    right = right.getLeft();
                    right.setIndex(right.getIndex() + tempIndex);
                } else {
                    right.delTail();
                }
            } else if (right == null) {
                this.setIndex(this.getIndex() + left.getIndex());
                this.elem = left.getElem();
                DifTree tempLeft = left.getLeft();
                this.right = left.getRight();
                this.left = tempLeft;
            }

        }

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

}
