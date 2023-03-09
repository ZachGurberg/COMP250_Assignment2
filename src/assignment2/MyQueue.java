package assignment2;

public class MyQueue<E> {

    private MyDoublyLinkedList<E> dll;
    public MyQueue(){
        dll = new MyDoublyLinkedList<E>();
    }
    public void enqueue(E e){
        dll.addLast(e);
    }
    public E dequeue(){
        return dll.removeFirst();
    }
    public boolean isEmpty(){
        return dll.isEmpty();
    }
    public void clear(){
        dll.clear();
    }
    public boolean equals(Object o){
        if (!(o instanceof MyQueue<?>)){
            return false;}
        else{
            MyQueue<E> comp = (MyQueue<E>)o;
            return (this.dll.equals(comp.dll));
        }
    }


}
