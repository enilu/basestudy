package basestudy.creazyit;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
	private static int DEFAULT_SIZE = 16;
	// 线性表长度
	private int size = 0;
	// 线性表最大容量
	private int capacity;
	private Object[] datas;

	public ArrayList() {
		capacity = DEFAULT_SIZE;
		datas = new Object[DEFAULT_SIZE];
	}

	public ArrayList(T data) {
		capacity = DEFAULT_SIZE;
		datas = new Object[DEFAULT_SIZE];
		add(data);
	}

	public boolean emtpy() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	private void expend() {
		int minCapacity = size + 1;
		if (minCapacity > capacity) {
			while (minCapacity > capacity) {
				capacity <<= 1;
			}
			datas = Arrays.copyOf(datas, capacity);
		}
	}

	public void insert(T data, int index) throws Exception {
		if (index < 0 || index > size) {
			throw new Exception("数组越界异常");
		}
		expend();
		System.arraycopy(datas, index, datas, index + 1, size - index);
		datas[index] = data;
		size++;
	}

	public int remove(T data) {

		for (int i = 0; i < size; i++) {
			if (data.equals(datas[i])) {
				datas[i] = null;
				size--;
				System.arraycopy(datas, i + 1, datas, i, size - i);
				return i;
			}
		}
		return -1;
	}

	public T remove(int index) throws Exception {
		if (index < 0 || index > size - 1) {
			throw new Exception("数组越界异常");
		}
		Object rec = datas[index];
		datas[index] = null;
		System.arraycopy(datas, index + 1, datas, index, size - index);
		size--;
		return (T) rec;
	}

	public T get(int index) throws Exception {
		if (index < 0 || index > size - 1) {
			throw new Exception("数组越界异常");
		}
		return (T) datas[index];
	}

	public int getIndex(T data) {
		for (int i = 0; i < size; i++) {
			if (data.equals(datas[i])) {
				return i;
			}
		}
		return -1;
	}

	public void add(T data) {
		try {
			insert(data, size);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		for (int i = 0; i < size; i++) {
			builder.append(datas[i].toString()).append(",");
		}
		builder.append("]");
		return builder.toString();
	}

}