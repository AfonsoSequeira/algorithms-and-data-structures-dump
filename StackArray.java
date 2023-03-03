import java.util.Iterator;

public class StackArray<Item> implements Iterable<Item> {
    private Item[] stack;
    private int N;
    private int C;

    public void printArray(){
        System.out.println("Printing out array:");
        for (int i = 0; i < C; i++){
            System.out.println(stack[i]);
        }
    }

    @SuppressWarnings("unchecked")
    public StackArray(){
        C = 1;
        stack = (Item[]) new Object[C];
        N = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity){
        Item[] newStack = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++){
            newStack[i] = stack[i];
        }
        stack = newStack;
    }

    public void push(Item s){
        stack[N++] = s;
        if (N == C){
            resize(C * 2);
            C = C * 2;
        }
    }

    public Item pop(){
        Item poppedItem = stack[--N];
        stack[N] = null;
        if (N > 0 && N == C/4){
            resize(C/2);
            C = C/2;
        }
        return poppedItem;
    }

    public boolean isEmpty(){
        return stack[0] == null; 
    }
    
    public int size(){
        int sz = 0;
        while(stack[sz] != null){
            sz ++;
        }
        return sz;
    }

    public Iterator<Item> iterator() { return new ReverseArrayIterator();}

    private class ReverseArrayIterator implements Iterator<Item>
    {
        private int i = N;

        public boolean hasNext() {return i > 0;}

        public void remove() {/*not implemented*/}
        
        public Item next(){
            return stack[--i];
        }

    }

    public static void main(String[] args){
        StackArray<String> stack = new StackArray<String>();
        stack.push("Hello");
        stack.printArray();
        stack.push("Hello1");
        stack.printArray();
        stack.push("Hello2");
        stack.printArray();
        stack.push("Hello3");
        stack.printArray();
        stack.push("Hello4");
        stack.printArray();
        stack.pop();
        stack.printArray();
        stack.pop();
        stack.printArray();
        stack.pop();
        stack.printArray();
        stack.pop();
        stack.printArray();

    }
}
