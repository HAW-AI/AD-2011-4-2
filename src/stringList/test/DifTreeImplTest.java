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
        List<Object> l = new ArrayList<Object>();
        l.add("a");
        l.add("b");
        l.add("c");
        DifTree test = DifTreeImpl.create(l);
        test.addHead("#");
        test.addTail("d");
        System.out.println(test);
        assertTrue(true);
        
    }
    
    @Test
    public void testDel() {
        System.out.println("___ TEST DEL ____");
        List<Object> l = new ArrayList<Object>();
        l.add("a");
        l.add("b");
        l.add("c");
        l.add("d");
        l.add("e");
        l.add("f");
        l.add("g");
        
        DifTree test = DifTreeImpl.create(l);
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
        test.delTail();
        test.delTail();
        assertTrue(true);
        }
}
