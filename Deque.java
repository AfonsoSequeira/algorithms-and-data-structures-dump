import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last; 

    //private class node
    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return first == null | last == null;
    }

    // return the number of items on the deque
    public int size(){
        int counter = 0;
        Node c = first;
        while (c != null){
            c = c.next;
            counter ++;
        }
        return counter;
    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null){throw new IllegalArgumentException();}
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        if (isEmpty()){
            last = first;
        }
    }

    // add the item to the back
    public void addLast(Item item){
        if (item == null){throw new IllegalArgumentException();}
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldLast;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
        }
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty()){
            throw new java.util.NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        if (first != null){
            first.previous = null;
        }

        if (isEmpty()){
            last = first;
        }
    
        return item; 
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (isEmpty()){
            throw new java.util.NoSuchElementException();
        }
        Item item = last.item;
        last = last.previous;
        if (last != null){
            last.next = null;
        }

        if (isEmpty()){
            first = last;
        }
    
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() { return new ListIterator();}

    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext() {return current != null;}

        public void remove() {throw new UnsupportedOperationException();}
        
        public Item next(){
            if (hasNext() == false){
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    @SuppressWarnings ("unused")
    private void printDeque(){
        for (Item item : this ){
            System.out.println(item);
        };
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.removeLast(); 
        deque.isEmpty();
        deque.isEmpty(); 
        deque.isEmpty();    
        deque.isEmpty(); 
        deque.isEmpty();
        deque.addLast(8);
    }

}
