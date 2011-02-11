
public class SimpleQueue {
  private static final int STANDARD_CAPACITY = 16;
  
  private Object[] elements;
  private int capacity;
  private int length;
  private int start;
  private int end;
  
  public SimpleQueue() {
    capacity = STANDARD_CAPACITY;
    elements = new Object[capacity];
    length = 0;
    start = 0;
    end = 0;
  }
  
  public void enqueue(Object obj) throws FullQueueException {
    if (length == capacity) {
      throw new FullQueueException();
    }
    
    elements[end] = obj;
    end = (end + 1) % capacity;
    ++length;
  }
  
  public Object dequeue() throws EmptyQueueException {
    if (length == 0) {
      throw new EmptyQueueException();
    }
    
    Object front = elements[start];
    start = (start + 1) % capacity;
    --length;
    return front;
  }
  
  public static Object giveAnObject(SimpleQueue queue) {
    Object toStore = new Object();
    Object toReturn = null;

    try {
      queue.enqueue(toStore);
      queue.enqueue(new Object());
      queue.enqueue(new Object());
      
      toReturn = queue.dequeue();
    } catch (FullQueueException e) {
      System.err.println("Tried to add too many!");
    } catch (EmptyQueueException e) {
      System.err.println("Tried to remove a non-existant element");
    }
    
    return toReturn;
  }
  
  public static void doSomething(SimpleQueue queue) {
    Object objectToDoSomethingWith = giveAnObject(queue);
  }
  
  public static void main(String[] args) {
    SimpleQueue queue = new SimpleQueue();
    doSomething(queue);
    /* Work on other stuff */
  }
}
