import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int tail;
    private int head;
    private int capacity;

    // construct an empty randomized queue
    @SuppressWarnings("unchecked")
    public RandomizedQueue(){
        capacity = 1;
        queue = (Item[]) new Object[capacity];
        head = 0;
        tail = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return head == tail;
    }

    // return the number of items on the randomized queue
    public int size(){
        return tail - head;
    }

    // private method to resize the array
    @SuppressWarnings("unchecked")
    private void upSize(int capacity){
        Item[] newQueue = (Item[]) new Object[capacity];
        for (int i = head; i < tail; i++){
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }

    @SuppressWarnings("unchecked")
    private void downSize(int capacity){
        Item[] newQueue = (Item[]) new Object[capacity];
        for (int i = 0; i < capacity; i++){
            newQueue[i] = queue[i + head];
        }
        queue = newQueue;
        tail = tail - head;
        head = 0;
    }

    // add the item
    public void enqueue(Item item){
        if (item == null){throw new IllegalArgumentException();}
        queue[tail] = item;
        tail += 1;
        if ( tail == capacity) {
            upSize(capacity * 2);
            capacity = capacity * 2;
        }
    }

    // remove and return a random item
    public Item dequeue(){
        if (isEmpty()){
            throw new java.util.NoSuchElementException();
        }
        int i = StdRandom.uniformInt(head, tail);
        Item item = queue[i];
        queue[i] = queue[head];
        queue[head] = null;
        head += 1;
        if ( size() > 0 && size() == capacity/4 ){
            downSize(capacity/2);
            capacity = capacity/2;
        } 

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (isEmpty()){
            throw new java.util.NoSuchElementException();
        }
        int i = StdRandom.uniformInt(head, tail);
        return queue[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() { return new RandomArrayIterator();}

    private class RandomArrayIterator implements Iterator<Item>
    {
        private Item[] randomArray; 
        private int i;
        private int sz = size();

        @SuppressWarnings("unchecked")
        public RandomArrayIterator(){
            randomArray = (Item[]) new Object[sz];
            for (int i = head; i < tail; i++){
                randomArray[i - head] = queue[i];
            }
            i = 0;
        }

        public boolean hasNext() {return i < sz;}

        public void remove() {throw new UnsupportedOperationException();}
        
        public Item next(){
            if (hasNext() == false){
                throw new NoSuchElementException();
            }
            int roll = StdRandom.uniformInt(i, sz);
            Item oldI = randomArray[i];
            randomArray[i] = randomArray[roll];
            randomArray[roll] = oldI;
            return randomArray[i++];
        }

    }

    @SuppressWarnings ("unused")
    private void printOrderedQueue(){
        for (int i = 0; i < capacity; i++){
            System.out.println(queue[i]);
        }; 
    }

    @SuppressWarnings ("unused")
    private void printRandomQueue(){
        for (Item item : this ){
            System.out.println(item);
        };
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(41);
        queue.dequeue();    
        queue.size();      
        queue.enqueue(729);
        queue.isEmpty();    
        StdOut.print(queue.dequeue());     
    }
    
}
