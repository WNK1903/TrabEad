package bstPackage;


public interface Queue<E> {
  /** Returns the number of elements in the queue.*/
  public int numElements();  
  /** Returns whether the queue is empty. */
  public boolean isEmpty();
  /** Returns whether the queue is full. */
  public boolean isFull();
  /** Inspects the element at the front of the queue.*/
  public E front() throws UnderflowException; 
  /** Inspects the element at the back of the queue.*/
  public E back() throws UnderflowException;
  /** Inserts an element at the rear of the queue. */
  public void enqueue (E element) throws OverflowException; 
  /** Removes the element at the front of the queue.*/
  public E dequeue() throws UnderflowException;  
}