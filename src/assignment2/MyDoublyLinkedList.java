package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> extends MyLinkedList<E>{
	private DNode head;
	private DNode tail;

	public void add(E e){ //Can add null elements, maybe double check
		DNode myNode = new DNode();
		myNode.element = e;
		myNode.next = null;

		if (this.isEmpty()){
			myNode.prev = null;
			head = myNode;
		}
		else{
			tail.next = myNode;
			myNode.prev = tail;
		}
		tail = myNode;
		size++;
	}

	public E remove(){
		if (this.isEmpty())
			throw new NoSuchElementException();
		if (this.size == 1){
			E e = head.element; //head.prev and next are already null
			head = null;
			tail = null;
			size--;
			return e;
		}else {
			E e = tail.element;
			tail = tail.prev;
			tail.next.prev = null;
			tail.next = null;
			size--;
			return e;
		}
	}

	public void clear(){ //should test
		Iterator<E> iter = this.iterator();
		while (iter.hasNext()){
			iter.next();
			iter.remove();
		}
		head=null;
		tail=null;
		size=0;
	}

	public void addFirst(E e){
		DNode myNode = new DNode();
		myNode.element = e;
		myNode.prev = null;
		if (this.isEmpty()){
			myNode.next = null;
			tail = myNode;
		}
		else{
			head.prev = myNode;
			myNode.next = head;
		}
		head = myNode;
		size++;
	}

	public void addLast(E e){
		this.add(e);
	}
	
	public E removeFirst(){
		if (this.isEmpty()){
			throw new NoSuchElementException();
		}
		if(this.getSize()==1){
			E e = head.element;
			head.next=null;
			head.prev = null;
			head = null;
			tail = null;
			size--;
			return e;
		}
		else{
			E e = head.element;
			head = head.next;
			head.prev.next = null;
			head.prev = null;
			size--;

			return e;

		}
	}

	public E removeLast(){
		return this.remove();
	}

	public E peekFirst(){
		if (this.isEmpty()){
			throw new NoSuchElementException();
		}
		else {
			return head.element;
		}
	}

	public E peekLast(){
		if (this.isEmpty()){
			throw new NoSuchElementException();
		}
		else
			return tail.element;
	}

	public boolean equals(Object o){
		if (o==this)
			return true;
		if (!(o instanceof MyDoublyLinkedList<?>))
			return false;
		MyDoublyLinkedList<E> comp = (MyDoublyLinkedList<E>) o;
		if (this.getSize()!=comp.getSize())
			return false;

		Iterator<E> iter1 = this.iterator();
		Iterator<E> iter2 = comp.iterator();

		while(iter1.hasNext()){
			E element1 = iter1.next();
			E element2 = iter2.next();
			if(!element1.equals(element2))
				return false;
		}
		return true;
	}

	public Iterator<E> iterator() {
		return new DLLIterator();
	}

	private class DNode {
		private E element;
		private DNode next;
		private DNode prev;
	}

	private class DLLIterator implements Iterator<E> {
		DNode curr;

		public DLLIterator() {
			this.curr = head;
		}

		public boolean hasNext() {
			return this.curr != null;
		}

		public E next() {
			if (!this.hasNext())
				throw new NoSuchElementException();

			E element = this.curr.element;
			this.curr = this.curr.next;
			return element;
		}
	}
}
