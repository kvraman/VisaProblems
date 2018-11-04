/**
 * 
 */
package btser;

import java.io.File;
import java.io.IOException;

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
		BTNode<Integer> one = new BTNode<Integer>(null, null, 1);
		BTNode<Integer> six = new BTNode<Integer>(null, null, 6);
		BTNode<Integer> four = new BTNode<Integer>(one, six, 4);
		BTNode<Integer> eighteen = new BTNode<Integer>(null, null, 18);
		BTNode<Integer> twenty = new BTNode<Integer>(eighteen, null, 20);
		BTNode<Integer> fifteen = new BTNode<Integer>(null, twenty, 15);
		BTNode<Integer> ten = new BTNode<Integer>(four, fifteen, 10);

		ten.print();
		System.out.println("---------------------------");
		File file = new File("d:/output.txt");

		try {
			ten.serialize(file);
			BTNode<Integer> head = BTNode.deserializeIntTree(file);
			head.print();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
