package redBlackTree;

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

enum Color { RED, BLACK }

class Node<K extends Comparable<? super K>,V>
{
    public K key;
    public V value;
    public Node<K,V> left;
    public Node<K,V> right;
    public Node<K,V> parent;
    public Color color;

    public Node(K key, V value, Color nodeColor, Node<K,V> left, Node<K,V> right) {
        this.key = key;
        this.value = value;
        this.color = nodeColor;
        this.left = left;
        this.right = right;
        if (left  != null)  left.parent = this;
        if (right != null) right.parent = this;
        this.parent = null;
    }

    public Node<K,V> grandparent() {
        assert parent != null; // Not the root node
        assert parent.parent != null; // Not child of root
        return parent.parent;
    }

    public Node<K,V> sibling() {
        assert parent != null; // Root node has no sibling
        if (this == parent.left)
            return parent.right;
        else
            return parent.left;
    }

    public Node<K,V> uncle() {
        assert parent != null; // Root node has no uncle
        assert parent.parent != null; // Children of root have no uncle
        return parent.sibling();
    }

}
