import java.util.Iterator;

public class StackLinkedList<Item> implements Iterable<Item> {
    private Node first = null;

    private class Node {
        Item item;
        Node next;
    }

    public void push(Item item){

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    } //insert a new item onto stack

    public Item pop() {
        Item str = first.item;
        first = first.next;
        return str;
    } //remove and return the string most recently added

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        Node node = first;
        int counter = 1;
        while (node.next != null){
            node = node.next;
            counter ++;
        }
        return counter;
    }

    public Iterator<Item> iterator() { return new ListIterator();}

    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext() {return current != null;}

        public void remove() {/*not implemented*/}
        
        public Item next(){
            Node oldCurrent = current;
            current = oldCurrent.next;
            return oldCurrent.item;
        }

    }


    public static void main(String[] args){
        StackLinkedList<String> ls = new StackLinkedList<String>();
        ls.push("Hi");
        ls.push("Hi2");
        ls.push("Hi3");
        ls.push("Hi4");
        System.out.println(ls.pop());
        System.out.println(ls.size());
        return;
    }
}
