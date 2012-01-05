/**
 * 
 */
package redBlackTree;

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

public class main {

	public static void main(String[] args) {
        RBTree<Integer, Integer> t = new RBTreeImpl<Integer,Integer>();
        t.print();

        java.util.Random gen = new java.util.Random();

        for (int i = 0; i < 5000; i++) {
            int x = gen.nextInt(10000);
            int y = gen.nextInt(10000);

            t.print();
            System.out.println("Inserting " + x + " -> " + y);
            System.out.println();

            t.insert(x, y);
            assert t.lookup(x).equals(y);
        }
        for (int i = 0; i < 60000; i++) {
            int x = gen.nextInt(10000);

            t.print();
            System.out.println("Deleting key " + x);
            System.out.println();

            t.delete(x);
        }
    }
	
}
