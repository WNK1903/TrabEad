package bstPackage;

public interface List<E> {
	public int numElements();
	public boolean isEmpty();
	public boolean isFull();
	public void insert(E element, int pos);
	public E remove(int pos);
	public E get(int pos);
	public int search(E element);
}

