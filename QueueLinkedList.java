public class QueueLinkedList<Item>  {
    Node first;
    Node last;

    private class Node{
        Item item;
        Node  next;
    }

    public QueueLinkedList(){
        last = null;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            last = null;
        }
        else {
            oldLast.next = last;
        }
    }//insert a new string at the end of the queue

    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {last = null;}
        return item;
    } //remove and return the item at the from of the queue
    
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

    
}
