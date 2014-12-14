package basestudy.creazyit;

/**
 * @author enilu
 *
 */
public class SequenceListTest {
	public static void main(String[] args) throws Exception {
		List<String> list = new LinkedList<String>();
		System.out.println(list.emtpy());
		for (int i = 0; i < 20; i++) {
			if (i == 15) {
				System.out.println(list.size());
			}
			list.add("str" + i);
		}
		String str1 = list.get(0);

		System.out.println(list.size());
		list.remove(0);
		list.remove("str3");
		System.out.println(list.size());
		System.out.println(list.emtpy());
		list.insert("str22", 5);
		System.out.println(list.size());
	}
}
