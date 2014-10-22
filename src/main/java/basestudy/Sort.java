package basestudy;

import java.util.Date;

/**
 * 冒泡排序
 * <p>
 * 2014年8月22日
 * 
 * @author enilu(82552623@qq.com)
 * 
 */
public class Sort {

	public static void main(String[] args) {
		int count = 10;
		int[] arr = new int[count];
		System.out.println("随机数:");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * count);
			System.out.print(arr[i] + "    ");
		}
		long start = new Date().getTime();
		// bubbleSort(arr);
		// selectionSort(arr);
		// insertSort(arr);
		// arr = mergeSort(arr);
		arr = countingSort(arr);
		long end = new Date().getTime();
		System.out.println("\r\n花费时间" + (end - start) + "毫秒");
		System.out.println("排序完毕：");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "    ");
		}

	}

	private static int[] randixSort(int[] arr) {

		return null;
	}

	/**
	 * 计数排序
	 * 
	 * @param arr
	 * @return
	 */
	private static int[] countingSort(int[] arr) {
		int result[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int count = 0;
			int current = arr[i];
			for (int j = 0; j < arr.length; j++) {
				if (arr[j] < current) {
					count++;
				}
			}
			result[count] = current;
		}
		return result;
	}

	/**
	 * 归并排序????
	 * 
	 * @param arr
	 */
	private static int[] mergeSort(int[] arr) {
		return mergesort(arr, 0, arr.length - 1, new int[arr.length]);

	}

	private static int[] mergesort(int[] num, int s, int t, int[] num1) {

		int m;
		int[] num2 = new int[t + 1];
		if (s == t)
			num1[s] = num[s];

		else {
			m = (s + t) / 2;
			mergesort(num, s, m, num2);// 左半部分递归调用
			mergesort(num, m + 1, t, num2);// 右半部分递归调用
			merg(num2, s, m, t, num1);// 由num2去归并，返回的值放到num1中,num1赋新值，其实就是更新num2,然后让num2再去归并，返回新的num1

		}

		return num1;
	}

	// 有序表的合并

	private static void merg(int[] num, int l, int m, int n, int[] num1) {
		int i, j, k;
		i = l;
		j = m + 1;
		k = l;
		while (i <= m && j <= n) {
			if (num[i] < num[j])
				num1[k++] = num[i++];
			else {
				num1[k++] = num[j++];
			}
		}

		while (i <= m) {
			num1[k++] = num[i++];
		}
		while (j <= n) {
			num1[k++] = num[j++];
		}

	}

	/**
	 * 插入排序
	 * 
	 * @param arr
	 */
	private static void insertSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int current = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > current) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = current;
		}
	}

	/**
	 * 冒泡排序
	 * 
	 * @param arr
	 */
	private static void bubbleSort(int[] arr) {
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (int i = 0; i < arr.length - 1; i++) {
				int left = arr[i];
				int right = arr[i + 1];
				if (left > right) {
					arr[i] = right;
					arr[i + 1] = left;
					swapped = true;
				}
			}

		}

	}

	/**
	 * 选择排序
	 * 
	 * @param arr
	 */
	private static void selectionSort(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			int min = arr[i];
			int minIndex = i;
			for (int j = i; j < arr.length; j++) {
				if (arr[j] < min) {
					min = arr[j];
					minIndex = j;
				}
			}
			if (minIndex != i) {
				int temp = arr[i];
				arr[i] = arr[minIndex];
				arr[minIndex] = temp;
			}
		}
	}
}
