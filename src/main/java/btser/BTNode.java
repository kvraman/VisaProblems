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

	public int height() {
		return Math.max(left != null ? left.height() : 0, right != null ? right.height() : 0) + 1;
	}

	public void print() {
		this.print(this, this.height(), 0);
	}

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

	private void print(BTNode<T> node, int height, int level) {
		LinkedList<BTNode<T>> queue = new LinkedList<BTNode<T>>();
		queue.push(node);
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

	public void serialize(File file) throws IOException {
		Writer writer = new FileWriter(file);
		this.serialize(writer, this);
		writer.close();
	}

	private void serialize(Writer writer, BTNode<T> node) throws IOException {
		if (node == null) {
			writer.write("null ");
			return;
		}
		writer.write(node.getValue().toString() + " ");
		this.serialize(writer, node.getLeft());
		this.serialize(writer, node.getRight());
	}

	public static BTNode<Integer> deserializeIntTree(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		if (scanner.hasNext()) {
			return deserializeIntTree(scanner);
		}
		scanner.close();
		return null;
	}

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
