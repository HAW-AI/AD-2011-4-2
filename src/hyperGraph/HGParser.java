package hyperGraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Integer;
import java.lang.reflect.Array;

/**
 * gestohlen von Ben Rexin <benjamin.rexin@haw-hamburg.de> 
 * Patrick Detlefsen <patrick.detlefsen@haw-hamburg.de>
 * 
 * verändert/angepasst von
 * 
 * @author Tobias Meurer
 * @author Stephan Berngruber
 * 
 * 
 */
public class HGParser {
	private static final String ELEMENT_DELIMITER = " ";

	public static HGRawData parse(String file) {
		return parse_array(content(file));
	}

	private static HGRawData parse_array(String[] content) {
		final String[] header = content[0].split(ELEMENT_DELIMITER);
		int height = Integer.parseInt(header[0]);
		int width = Integer.parseInt(header[1]);
		// body contains strings without the head
		String[] body = body(content);
		// List<Integer> values = new ArrayList<Integer>();
		// for (int line = 0; line < Array.getLength(body); line++) {
		// String[] elements = body[line].split(ELEMENT_DELIMITER);
		// for (int e = 0; e < Array.getLength(elements); e++) {
		// values.add(Integer.parseInt(elements[e]));
		// }
		// }
		int[] values = new int[height * width];
		for (int line = 0; line < Array.getLength(body); line++) {
			String[] elements = body[line].split(ELEMENT_DELIMITER);
			for (int e = 0; e < Array.getLength(elements); e++) {
				values[width * line + e] = Integer.parseInt(elements[e]);
			}
		}
		return HGRawData.creator(width, height, values);
	}

	/**
	 * return the rest of the strings, without header
	 * 
	 * @param file
	 */
	private static String[] body(String[] content) {
		return Arrays.copyOfRange(content, 1, content.length);
	}

	// private static List<String> body(List<String> content) {
	// List<String> result = content;
	// result.remove(0);
	// return result;
	// }

	/**
	 * returns the file content as String[] filled with the single lines at each
	 * index
	 * 
	 * @param file
	 * @return
	 */
	private static String[] content(String filename) {
		final BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(new File(filename)));
		} catch (FileNotFoundException e) {
			System.err.println("Catched: FILE NOT FOUND");
			return new String[] { "" };

		}
		List<String> lines = new ArrayList<String>();
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			// too bad, leave line as it is
		}

		return lines.toArray(new String[0]);
	}

}
