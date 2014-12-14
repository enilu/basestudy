package basestudy.creazyit;

public class LinkedList<T> implements List<T> {
	private static int DEFAULT_SIZE = 16;
	private int size = 0;
	private Node header;
	private Node tail;

	private class Node {
		private T data;
		private Node next;

		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

	}

	public boolean emtpy() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void add(T data) {
		try {
			insert(data, size);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void insert(T data, int index) throws Exception {
		if (index < 0 || index > size) {
			throw new Exception("数组越界异常");
		}
		if (size == 0) {
			header = new Node(data, null);
			tail = header;
		} else if (index == size) {

			Node newNode = new Node(data, null);
			tail.setNext(newNode);

			tail = newNode;
		} else {
			Node newNode = new Node(data, null);
			Node pre = getNodeByIndex(index - 1);
			Node next = getNodeByIndex(index);
			pre.setNext(newNode);
			newNode.setNext(next);
		}
		size++;
	}

	public T remove(int index) throws Exception {
		if (index < 0 || index > size) {
			throw new Exception("数组越界异常");
		}

		if (index == 0) {
			Node next = header.getNext();
			header.next = null;
			header = next;
			size--;
			return header.data;

		} else {
			Node pre = getNodeByIndex(index - 1);
			Node del = getNodeByIndex(index);
			pre.setNext(del.getNext());
			del.setNext(null);
			size--;
			return del.data;

		}

	}

	private Node getNodeByIndex(int index) throws Exception {
		if (index < 0 || index > size) {
			throw new Exception("数组越界异常");
		}
		if (index == 0) {
			return header;
		}
		Node current = header;
		for (int i = 1; i < size; i++) {
			current = current.next;
			if (i == index) {
				break;
			}
		}
		return current;
	}

	public T get(int index) throws Exception {
		return getNodeByIndex(index).data;
	}

	public int remove(T data) {
		if (data == null) {
			return -1;
		}
		Node del = header;
		for (int i = 0; i < size; i++) {
			if (data.equals(del.data)) {
				try {
					Node pre = getNodeByIndex(i - 1);
					pre.setNext(del.getNext());
					del.setData(null);
					del.setNext(null);
					size--;
					return i;
				} catch (Exception e) {
					return -1;
				}
			}
			del = del.next;
		}
		return -1;
	}

	public int getIndex(T data) {
		if (data == null) {
			return -1;
		}
		Node current = header;
		for (int i = 0; i < size; i++) {
			if (data.equals(current.data)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public String toString() {
		if (size == 0) {
			return "[]";
		}
		StringBuilder builder = new StringBuilder("[");
		Node current = header;
		for (int i = 0; i < size; i++) {
			builder.append(current.data.toString()).append(",");
			current = current.next;
		}
		builder.append("]");
		return builder.toString();
	}

}
