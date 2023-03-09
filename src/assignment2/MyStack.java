package assignment2;

import javax.naming.NoPermissionException;
import java.util.NoSuchElementException;

public class MyStack<E> {
    private MyDoublyLinkedList<E> dll;

    public MyStack(){
        dll = new MyDoublyLinkedList<E>();
    }
    public void push(E e){
        dll.addFirst(e);
    }
    public E pop(){
        return dll.removeFirst();
    }
    public E peek(){
        return dll.peekFirst();
    }
    public boolean isEmpty(){
        return dll.isEmpty();
    }
    public void clear(){
        dll.clear();
    }
    public int getSize(){
        return dll.getSize();
    }
}
