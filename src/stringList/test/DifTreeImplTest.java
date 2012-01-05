/**
 * 
 */
package stringList.test;

import static org.junit.Assert.*;

import java.util.*;



import org.junit.Test;

import stringList.implementations.DifTreeImpl;
import stringList.interfaces.DifTree;

/**
 * @author Kai Bielenberg (kai.bielenberg@haw-hamburg.de)
 *
 */
public class DifTreeImplTest {

    /**
     * Test method for {@link stringList.implementations.DifTreeImpl#create(java.util.List)}.
     */
    @Test
    public void testCreate() {
    
        List<Object> l = new ArrayList<Object>();
        l.add("a");
        l.add("b");
        l.add("c");
        l.add("d");
        l.add("e");
        
        DifTree test = DifTreeImpl.create(l);
        System.out.println(test);
        assertTrue(true);
    }

    /**
     * Test method for {@link stringList.implementations.DifTreeImpl#get(int)}.
     */
    @Test
    public void testGet() {
        System.out.println("\n___ TEST Get____\n");
        List<Object> l = new ArrayList<Object>();
        l.add("a");
        l.add("b");
        l.add("c");
        l.add("d");
        l.add("e");
        
        DifTree test = DifTreeImpl.create(l);
        assertEquals("a", test.get(1));
        assertEquals("b", test.get(2));
        assertEquals("c", test.get(3));
        assertEquals("d", test.get(4));
        assertEquals("e", test.get(5));
        assertEquals(null, test.get(6));
        assertEquals(null, test.get(0));
        assertEquals(null, test.get(-1));
    }
    @Test
    public void testAdd() {
        System.out.println("\n___ TEST SimpleAdd____\n");
        List<Object> l = new ArrayList<Object>();
        l.add("a");
        l.add("b");
        l.add("c");
        DifTree test = DifTreeImpl.create(l);
        System.out.println("Before:");
        System.out.println(test);
        System.out.println("\n Items to be Added:\n Head: # and Tail: d\n");
        test.addHead("#");
        test.addTail("d");
        System.out.println("Result:");
        System.out.println(test);
        assertEquals("#", test.get(1));
        assertTrue(true);
        
    }
    
    @Test
    public void testDelTail() {
        System.out.println("\n___ TEST DEL TAIL____\n");
        List<Object> l = new ArrayList<Object>();
        l.add("a");
        l.add("b");
        l.add("c");
        l.add("d");
        l.add("e");
        l.add("f");
        l.add("g");
        
        DifTree test = DifTreeImpl.create(l);
        System.out.println("Del everything from the Tail");
        System.out.println(test);
        test.delTail();
        System.out.println(test);
        test.delTail();
        System.out.println(test);
        test.delTail();
        System.out.println(test);
        test.delTail();
        System.out.println(test);
        test.delTail();
        System.out.println(test);
        test.delTail();
        System.out.println(test);
        test.delTail();
        System.out.println(test);
        assertTrue(true);
        }
    
    @Test
    public void testDelHead() {
        System.out.println("\n___ TEST DEL HEAD ____\n");
        List<Object> l = new ArrayList<Object>();
        l.add("a");
        l.add("b");
        l.add("c");
        l.add("d");
        l.add("e");
        l.add("f");
        l.add("g");
        
        DifTree test = DifTreeImpl.create(l);
        System.out.println("Del everything from the head");
        System.out.println(test);
        test.delHead();
        System.out.println(test);
        test.delHead();
        System.out.println(test);
        test.delHead();
        System.out.println(test);
        test.delHead();
        System.out.println(test);
        test.delHead();
        System.out.println(test);
        test.delHead();
        System.out.println(test);
        assertTrue(true);
        }
    
    @Test
    public void testDelBoth() {
        System.out.println("___ VARIOUS ADD AND DEL TEST____\n");
        DifTree test = DifTreeImpl.create("a","b", "c", "d", 4, 3,2,1);
        System.out.println("Bevore:");
        System.out.println(test);
        System.out.println("Items Added: (Head: x), (Tail: blub)");
        test.addHead("x");
        test.addTail("blub");
        System.out.println("Various deletes");
        System.out.println(test);
        test.delHead();
        System.out.println(test);
        test.delTail();
        System.out.println(test);
        test.delTail();
        System.out.println(test);
        test.delTail();
        System.out.println(test);
        test.delHead();
        System.out.println(test);
        test.delHead();
        System.out.println(test);
        assertTrue(true);
    }
        
}
