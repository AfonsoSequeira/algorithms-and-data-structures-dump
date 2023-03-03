import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args){
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> myQueue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            myQueue.enqueue(StdIn.readString());
        }
        String toPrint;
        for (int i = 0; i < k ; i++){
            toPrint = myQueue.dequeue();
            StdOut.print(toPrint);
        }
    } 
}
