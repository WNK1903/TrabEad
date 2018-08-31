package bstPackage;
public class Node<E> {
  protected E element;
  protected Node<E> next;

  public Node(E e) {
    element = e;
    next = null;
  }

  public E getElement() {
    return element; 
  }

  public Node<E> getNext() { 
    return next;
  }

  public void setElement(E e) { 
    element = e; 
  }

  public void setNext(Node<E> n) {
    next = n; 
  }
}