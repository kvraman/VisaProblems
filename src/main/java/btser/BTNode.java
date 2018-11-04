package btser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Scanner;

public class BTNode<T> {
	private BTNode<T> left;
	private BTNode<T> right;
	private T value;

	public BTNode(BTNode<T> left, BTNode<T> right, T value) {
		this.left = left;
		this.right = right;
		this.value = value;
	}

	// Getters and setters explivitly defined
	// Not using lombok
	public BTNode<T> getLeft() {
		return left;
	}

	public void setLeft(BTNode<T> left) {
		this.left = left;
	}

	public BTNode<T> getRight() {
		return right;
	}

	public void setRight(BTNode<T> right) {
		this.right = right;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * @return the height of the tree
	 */
	public int computeHeight() {
		return Math.max(left != null ? left.computeHeight() : 0, right != null ? right.computeHeight() : 0) + 1;
	}

	/**
	 * @param node
	 *            node to print
	 * @param height
	 *            height of the entire tree
	 * @param level
	 *            level this node is at
	 * @param nthNodeAtLevel
	 *            the nth node at this level
	 * @param queue
	 *            queue to output the data to. Used for level order printing types
	 */
	private void printNode(BTNode<T> node, int height, int level, int nthNodeAtLevel, LinkedList<BTNode<T>> queue) {
		int gap = (int) Math.pow(2, height - level);
		int offset = (int) Math.pow(2, (height - level - 1)) - 1;
		int tabsToPrint = nthNodeAtLevel == 0 ? offset : gap;

		for (int i = 0; i < tabsToPrint; i++)
			System.out.print("\t");

		System.out.print(null == node ? "n" : node.getValue());
		if (level < (height - 1)) {
			queue.add(node == null ? null : node.getLeft());
			queue.add(node == null ? null : node.getRight());
		}
	}

	/**
	 * @param node
	 *            node to print. Typically the root node
	 * @param height
	 *            height of the tree
	 * @param level
	 *            level to print from
	 */
	public void print() {
		LinkedList<BTNode<T>> queue = new LinkedList<BTNode<T>>();
		queue.push(this);
		int level = 0;
		int height = this.computeHeight();
		int nNodesAtLevel = (int) Math.pow(2, level);
		int nthNodeAtLevel = 0;
		while (!queue.isEmpty()) {
			BTNode<T> top = queue.pollFirst();
			this.printNode(top, height, level, nthNodeAtLevel, queue);
			nNodesAtLevel--;
			nthNodeAtLevel++;
			if (nNodesAtLevel == 0) {
				level++;
				System.out.println();
				nNodesAtLevel = (int) Math.pow(2, level);
				nthNodeAtLevel = 0;
			}
			if (level > height)
				return;
		}
	}

	/**
	 * @param file
	 *            output file to which we serialize the binary tree
	 * @throws IOException
	 */
	public void serialize(File file) throws IOException {
		Writer writer = new FileWriter(file);
		this.serialize(writer, this);
		writer.close();
	}

	/**
	 * For serialization I am using pre order traversal.
	 * 
	 * @param writer
	 *            based of the file we need to write to
	 * @param node
	 *            node to be written
	 * @throws IOException
	 */
	private void serialize(Writer writer, BTNode<T> node) throws IOException {
		// For leaf nodes, write null to the file
		if (node == null) {
			writer.write("null ");
			return;
		}
		// print the value with a space at the end to separate them
		writer.write(node.getValue().toString() + " ");

		// serialize left and right nodes
		this.serialize(writer, node.getLeft());
		this.serialize(writer, node.getRight());
	}

	/**
	 * For deserialization, I need to assume that the tree is an int tree
	 * 
	 * @param file
	 *            - to deserialize
	 * @return
	 * @throws FileNotFoundException
	 */
	public static BTNode<Integer> deserializeIntTree(File file) throws FileNotFoundException {
		// Use a scanner to scane the file
		Scanner scanner = new Scanner(file);
		if (scanner.hasNext()) {
			return deserializeIntTree(scanner);
		}
		scanner.close();
		return null;
	}

	/**
	 * When deserializing, if a null is found, then we will assume that node is
	 * null. If it is not null, we will parse it as an integer
	 * 
	 * @param scanner
	 * @return
	 */
	private static BTNode<Integer> deserializeIntTree(Scanner scanner) {
		String text = scanner.next();
		Integer value = text.equalsIgnoreCase("null") ? null : Integer.parseInt(text);
		BTNode<Integer> node = value == null ? null : new BTNode<Integer>(null, null, value);
		if (node != null) {
			node.setLeft(deserializeIntTree(scanner));
			node.setRight(deserializeIntTree(scanner));
		}
		return node;
	}
}
