package book.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存溢出例子
 * <p>
 * 2014年9月24日
 * 
 * @author enilu(82552623@qq.com)
 * 
 */
public class OOM {
	public static void main(String[] args) {
		List<Object> list = new ArrayList<Object>();
		while (true) {
			list.add(new Object());
		}
	}
}
