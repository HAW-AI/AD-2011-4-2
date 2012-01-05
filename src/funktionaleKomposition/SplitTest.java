
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class SplitTest {

	@Test
	public void test() {
		String[] strArray=SplitImpl.at(",").trim().notEmpty().from(" neue , ,funktionale , welt, ");
		assertEquals("[neue, funktionale, welt]",Arrays.toString(strArray));
		strArray=SplitImpl.at(",").trim().from(" neue , ,funktionale , welt, ");
		assertEquals("[neue, , funktionale, welt, ]",Arrays.toString(strArray));
		strArray=SplitImpl.at(",").from(" neue , ,funktionale , welt, ");
		assertEquals("[ neue ,  , funktionale ,  welt,  ]",Arrays.toString(strArray));
		
		strArray=SplitImpl.at("\n").from(" neue , , \n funktionale , welt, ");
		System.out.println(" neue , , \n funktionale , welt, ");
		System.out.println(Arrays.toString(strArray));

		strArray=SplitImpl.at("||").trim().notEmpty().from(" neue   funktionale welt ");
		System.out.println(" neue , , funktionale , welt, ");
		System.out.println(Arrays.toString(strArray));
	}

}
