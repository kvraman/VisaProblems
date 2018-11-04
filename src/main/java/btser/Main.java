/**
 * 
 */
package btser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author venkat.kalyan
 *
 */
public class Main {

	/**
	 * 
	 */
	public Main() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Simple main file to prove correctness of code
		// Decided to use a main file rather than a unit test
		// But this can as easily be written as an regression unit
		// test

		// Construct a test tree
		BTNode<Integer> one = new BTNode<Integer>(null, null, 1);
		BTNode<Integer> six = new BTNode<Integer>(null, null, 6);
		BTNode<Integer> four = new BTNode<Integer>(one, six, 4);
		BTNode<Integer> eighteen = new BTNode<Integer>(null, null, 18);
		BTNode<Integer> twenty = new BTNode<Integer>(eighteen, null, 20);
		BTNode<Integer> fifteen = new BTNode<Integer>(null, twenty, 15);
		BTNode<Integer> ten = new BTNode<Integer>(four, fifteen, 10);

		// Print test tree
		ten.print();
		System.out.println("\n---------------------------");

		// Decide the output file
		String tmpDir = System.getProperty("java.io.tmpdir");
		Path path = Paths.get(tmpDir, "output.txt");
		File file = path.toFile();

		try {
			// Serialize the tree to the output file
			ten.serialize(file);

			// Deserialize the tree
			// When deserializing, I need to know the type
			// to interpret the file contents
			// Alternatively, I could have added metadata to the
			// file to identify data types etc.
			BTNode<Integer> head = BTNode.deserializeIntTree(file);

			// Print to check if it is the same tree we get
			head.print();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
