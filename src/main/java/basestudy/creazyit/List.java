package basestudy.creazyit;

public interface List<T> {

	public boolean emtpy();

	public int size();

	public void add(T data);

	public void insert(T data, int index) throws Exception;

	public int remove(T data);

	public T remove(int index) throws Exception;

	public T get(int index) throws Exception;

	public int getIndex(T data);

}