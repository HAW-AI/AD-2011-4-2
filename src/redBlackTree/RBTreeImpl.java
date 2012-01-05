package redBlackTree;

import redBlackTree.interfaces.Node;
import redBlackTree.interfaces.RBTree;

/* Copyright (c) 2012 the authors listed at the following URL, and/or
the authors of referenced articles or incorporated external code:
http://en.literateprograms.org/Red-black_tree_(Java)?action=history&offset=20100112141306

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Retrieved from: http://en.literateprograms.org/Red-black_tree_(Java)?oldid=16622
*/

public class RBTreeImpl<K extends Comparable<? super K>,V> implements RBTree<K, V>
{
    private static final int INDENT_STEP = 4;

    public NodeImpl<K,V> root;

    /**
     * creates an empty red-black tree
     */
    public RBTreeImpl() {
        root = null;
        verifyProperties();
    }

    /* (non-Javadoc)
	 * @see redBlackTree.RBTree#verifyProperties()
	 */
    @Override
	public void verifyProperties() {
        if (VERIFY_RBTREE) {
            verifyProperty1(root);
            verifyProperty2(root);
            // Property 3 is implicit
            verifyProperty4(root);
            verifyProperty5(root);
        }
    }

    /**
     * verifies property 1:
     * each node is either red or black
     * -> fails if color reference is null
     */
    private static void verifyProperty1(NodeImpl<?,?> n) {
        assert nodeColor(n) == Color.RED || nodeColor(n) == Color.BLACK;
        if (n == null) return;
        verifyProperty1(n.left);
        verifyProperty1(n.right);
    }

    /**
     * verifies property 2:
     * the root node is black
     */
    private static void verifyProperty2(NodeImpl<?,?> root) {
        assert nodeColor(root) == Color.BLACK;
    }

    /**
     * helper function to imply property 3:
     * all leaves are black and contain no data
     * (leaves are always null)
     */
    private static Color nodeColor(NodeImpl<?,?> n) {
        return n == null ? Color.BLACK : n.color;
    }

    /**
     * verifies property 4:
     * every red node has two children, and both are black
     * (or equivalently, the parent of every red node is black)
     */
    private static void verifyProperty4(NodeImpl<?,?> n) {
        if (nodeColor(n) == Color.RED) {
            assert nodeColor(n.left)   == Color.BLACK;
            assert nodeColor(n.right)  == Color.BLACK;
            assert nodeColor(n.parent) == Color.BLACK;
        }
        if (n == null) return;
        verifyProperty4(n.left);
        verifyProperty4(n.right);
    }

    /**
     * verifies property 5:
     * all paths from any given node to its leaf nodes contain the same number of black nodes
     */
    private static void verifyProperty5(NodeImpl<?,?> root) {
        verifyProperty5Helper(root, 0, -1);
    }

    private static int verifyProperty5Helper(NodeImpl<?,?> n, int blackCount, int pathBlackCount) {
        if (nodeColor(n) == Color.BLACK) {
            blackCount++;
        }
        if (n == null) {
            if (pathBlackCount == -1) {
                pathBlackCount = blackCount;
            } else {
                assert blackCount == pathBlackCount;
            }
            return pathBlackCount;
        }
        pathBlackCount = verifyProperty5Helper(n.left,  blackCount, pathBlackCount);
        pathBlackCount = verifyProperty5Helper(n.right, blackCount, pathBlackCount);
        return pathBlackCount;
    }

    /* (non-Javadoc)
	 * @see redBlackTree.RBTree#lookup(K)
	 */
    @Override
	public V lookup(K key) {
        NodeImpl<K,V> n = lookupNode(key);
        return n == null ? null : n.value;
    }
    
    /**
     * helper function
     * searches for a given key and returns its node
     */
    private Node<K, V> lookupNode(K key) {
        NodeImpl<K,V> n = root;
        while (n != null) {
            int compResult = key.compareTo(n.key);
            if (compResult == 0) {
                return n;
            } else if (compResult < 0) {
                n = n.left;
            } else {
                assert compResult > 0;
                n = n.right;
            }
        }
        return n;
    }
    
    /**
     * rotates a (sub)tree to keep the tree balanced after insertion/deletion
     */
    private void rotateLeft(NodeImpl<K,V> n) {
        NodeImpl<K,V> r = n.right;
        replaceNode(n, r);
        n.right = r.left;
        if (r.left != null) {
            r.left.parent = n;
        }
        r.left = n;
        n.parent = r;
    }

    private void rotateRight(NodeImpl<K,V> n) {
        NodeImpl<K,V> l = n.left;
        replaceNode(n, l);
        n.left = l.right;
        if (l.right != null) {
            l.right.parent = n;
        }
        l.right = n;
        n.parent = l;
    }

    /**
     * cuts a node away from its parent, substituting a new node or null in its place
     */
    private void replaceNode(NodeImpl<K,V> oldn, NodeImpl<K,V> newn) {
        if (oldn.parent == null) {
            root = newn;
        } else {
            if (oldn == oldn.parent.left)
                oldn.parent.left = newn;
            else
                oldn.parent.right = newn;
        }
        if (newn != null) {
            newn.parent = oldn.parent;
        }
    }

    /* (non-Javadoc)
	 * @see redBlackTree.RBTree#insert(K, V)
	 */
    @Override
	public void insert(K key, V value) {
        NodeImpl<K,V> insertedNode = new NodeImpl<K,V>(key, value, Color.RED, null, null);
        if (root == null) {
            root = insertedNode;
        } else {
            NodeImpl<K,V> n = root;
            while (true) {
                int compResult = key.compareTo(n.key);
                if (compResult == 0) {
                    n.value = value;
                    return;
                } else if (compResult < 0) {
                    if (n.left == null) {
                        n.left = insertedNode;
                        break;
                    } else {
                        n = n.left;
                    }
                } else {
                    assert compResult > 0;
                    if (n.right == null) {
                        n.right = insertedNode;
                        break;
                    } else {
                        n = n.right;
                    }
                }
            }
            insertedNode.parent = n;
        }
        insertCase1(insertedNode);
        verifyProperties();
    }

    /**
     * insertion case 1:
     * the new node is now the root node of the tree
     * -> the new node is colored black
     */
    private void insertCase1(NodeImpl<K,V> n) {
        if (n.parent == null)
            n.color = Color.BLACK;
        else
            insertCase2(n);
    }

    /**
     * insertion case 2:
     * the new node has a black parent
     * -> all properties are still satisfied
     */
    private void insertCase2(NodeImpl<K,V> n) {
        if (nodeColor(n.parent) == Color.BLACK)
            return; // Tree is still valid
        else
            insertCase3(n);
    }

    /**
     * insertion case 3:
     * the uncle node of the new node is red
     * -> uncle and parent are colored black
     * -> grandparent is colored red
     * => grandparent may now violate the properties
     * 		-> execute the insertion cases for the grandparent
     */
    void insertCase3(NodeImpl<K,V> n) {
        if (nodeColor(n.uncle()) == Color.RED) {
            n.parent.color = Color.BLACK;
            n.uncle().color = Color.BLACK;
            n.grandparent().color = Color.RED;
            insertCase1(n.grandparent());
        } else {
            insertCase4(n);
        }
    }

    /**
     * insertion case 4:
     * a) the new node is the right child of its parent and the parent is the left child of the grandparent
     * -> rotate left about the parent
     * b) the new node is the left child of its parent and the parent is the right child of the grandparent
     * -> rotate right about the parent
     */
    void insertCase4(NodeImpl<K,V> n) {
        if (n == n.parent.right && n.parent == n.grandparent().left) {
            rotateLeft(n.parent);
            n = n.left;
        } else if (n == n.parent.left && n.parent == n.grandparent().right) {
            rotateRight(n.parent);
            n = n.right;
        }
        insertCase5(n);
    }

    /**
     * insertion case 5:
     * a) new node is the left child of its parent and the parent is the left child of the grandparent
     * -> rotate right about the grandparent
     * b) new node is the right child of its parent and the parent is the right child of the grandparent
     * -> rotate left about the grandparent
     */
    void insertCase5(NodeImpl<K,V> n) {
        n.parent.color = Color.BLACK;
        n.grandparent().color = Color.RED;
        if (n == n.parent.left && n.parent == n.grandparent().left) {
            rotateRight(n.grandparent());
        } else {
            assert n == n.parent.right && n.parent == n.grandparent().right;
            rotateLeft(n.grandparent());
        }
    }

    /* (non-Javadoc)
	 * @see redBlackTree.RBTree#delete(K)
	 */
    @Override
	public void delete(K key) {
        NodeImpl<K,V> n = lookupNode(key);
        if (n == null)
            return;  // Key not found, do nothing
        
        //if the node has two non-leaf children, the value from the in-order predecessor
        //(the rightmost element in the left subtree) is copied into the node to be
        // deleted and the predecessor node is deleted
        if (n.left != null && n.right != null) {
            NodeImpl<K,V> pred = maximumNode(n.left);
            n.key   = pred.key;
            n.value = pred.value;
            n = pred;
        }

        //if there is at most one child, the node is replaced by the child (or null)
        assert n.left == null || n.right == null;
        NodeImpl<K,V> child = (n.right == null) ? n.left : n.right;
        if (nodeColor(n) == Color.BLACK) {
            n.color = nodeColor(child);
            //if the color of the node to be deleted is black and the child can't be changed from red to black
            //there would be one less black node on every path through the child node
            //-> adjustment of the tree
            deleteCase1(n); 
        }
        replaceNode(n, child);
        
        if (nodeColor(root) == Color.RED) {
            root.color = Color.BLACK;
        }

        verifyProperties();
    }

    /**
     * returns the maximum node of a (sub)tree
     */
    private static <K extends Comparable<? super K>,V> Node<K, V> maximumNode(NodeImpl<K,V> n) {
        assert n != null;
        while (n.right != null) {
            n = n.right;
        }
        return n;
    }

    /**
     * deletion case 1:
     * node is the root node
     * -> no properties are violated
     */
    private void deleteCase1(NodeImpl<K,V> n) {
        if (n.parent == null)
            return;
        else
            deleteCase2(n);
    }

    /**
     * deletion case 2:
     * the node has a red sibling
     * -> exchange the colors of the parent and the sibling
     * -> rotate about the parent
     */
    private void deleteCase2(NodeImpl<K,V> n) {
        if (nodeColor(n.sibling()) == Color.RED) {
            n.parent.color = Color.RED;
            n.sibling().color = Color.BLACK;
            if (n == n.parent.left)
                rotateLeft(n.parent);
            else
                rotateRight(n.parent);
        }
        deleteCase3(n);
    }

    /**
     * deletion case 3:
     * the node's parent, sibling and sibling's children are black
     * -> color sibling red
     * => one less black node on every path through the parent
     * 		-> run the deletion cases for the parent 
     */
    private void deleteCase3(NodeImpl<K,V> n) {
        if (nodeColor(n.parent) == Color.BLACK &&
            nodeColor(n.sibling()) == Color.BLACK &&
            nodeColor(n.sibling().left) == Color.BLACK &&
            nodeColor(n.sibling().right) == Color.BLACK)
        {
            n.sibling().color = Color.RED;
            deleteCase1(n.parent);
        }
        else
            deleteCase4(n);
    }

    /**
     * deletion case 4:
     * the nodes sibling and sibling's children are black, the parent is red
     * -> exchange the colors of the sibling and the parent
     */
    private void deleteCase4(NodeImpl<K,V> n) {
        if (nodeColor(n.parent) == Color.RED &&
            nodeColor(n.sibling()) == Color.BLACK &&
            nodeColor(n.sibling().left) == Color.BLACK &&
            nodeColor(n.sibling().right) == Color.BLACK)
        {
            n.sibling().color = Color.RED;
            n.parent.color = Color.BLACK;
        }
        else
            deleteCase5(n);
    }

    /**
     * deletion case 5:
     * a) the node's sibling is black, the sibling's left child is red,
     * 	  the sibling's right child is black, the node is the left child
     * 	  of its parent
     * 		-> exchange the colors of the sibling and its left child and
     * 		   rotate right about the sibling
     * b) the node's sibling is black, the sibling's left child is black,
     * 	  the sibling's right child is red, the node is the right child
     * 	  of its parent
     * 		-> exchange the colors of the sibling and its right child and
     * 		   rotate left about the sibling
     */
    private void deleteCase5(NodeImpl<K,V> n) {
        if (n == n.parent.left &&
            nodeColor(n.sibling()) == Color.BLACK &&
            nodeColor(n.sibling().left) == Color.RED &&
            nodeColor(n.sibling().right) == Color.BLACK)
        {
            n.sibling().color = Color.RED;
            n.sibling().left.color = Color.BLACK;
            rotateRight(n.sibling());
        }
        else if (n == n.parent.right &&
                 nodeColor(n.sibling()) == Color.BLACK &&
                 nodeColor(n.sibling().right) == Color.RED &&
                 nodeColor(n.sibling().left) == Color.BLACK)
        {
            n.sibling().color = Color.RED;
            n.sibling().right.color = Color.BLACK;
            rotateLeft(n.sibling());
        }
        deleteCase6(n);
    }

    /**
     * deletion case 6:
     * a) the node's sibling is black, the sibling's right child is red,
     * 	  the node is the left child of its parent
     * 	  	-> exchange the colors of the node's parent and sibling
     * 		-> color the sibling's right child black
     * 		-> rotate left about the parent
     * b) the node's sibling is black, the sibling's left child is red,
     * 	  the node is the right child of its parent
     * 	  	-> exchange the colors of the node's parent and sibling
     * 		-> color the sibling's left child black
     * 		-> rotate right about the parent
     */
    private void deleteCase6(NodeImpl<K,V> n) {
        n.sibling().color = nodeColor(n.parent);
        n.parent.color = Color.BLACK;
        if (n == n.parent.left) {
            assert nodeColor(n.sibling().right) == Color.RED;
            n.sibling().right.color = Color.BLACK;
            rotateLeft(n.parent);
        }
        else
        {
            assert nodeColor(n.sibling().left) == Color.RED;
            n.sibling().left.color = Color.BLACK;
            rotateRight(n.parent);
        }
    }

    /* (non-Javadoc)
	 * @see redBlackTree.RBTree#print()
	 */
    @Override
	public void print() {
        printHelper(root, 0);
    }

    private static void printHelper(NodeImpl<?,?> n, int indent) {
        if (n == null) {
            System.out.print("<empty tree>");
            return;
        }
        if (n.right != null) {
            printHelper(n.right, indent + INDENT_STEP);
        }
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        if (n.color == Color.BLACK)
            System.out.println(n.key);
        else
            System.out.println("<" + n.key + ">");
        if (n.left != null) {
            printHelper(n.left, indent + INDENT_STEP);
        }
    }
}

