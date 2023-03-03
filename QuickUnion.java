public class QuickUnion {
    private int[] id;

    public QuickUnion(int n){
        id = new int[n];
        for (int i = 0; i < n; i++){
            id[i] = i;
        }
    }

    public int root(int n){
        while (id[n] != n){n = id[n];}
        return n;
    }

    public boolean find(int p, int q){
        if (p < 0 || p >= id.length || q < 0 || q >= id.length){
            throw new IllegalArgumentException("Indeces must be within range of id array.");
        }
        else{
            return root(p) == root(q);
        }
    }

    public void union(int m, int n){
        if (m < 0 || m >= id.length || n < 0 || n >= id.length){
            throw new IllegalArgumentException("Indeces must be within range of id array.");
        }
        else {
            int p = root(m);
            int q = root(n);
            id[p] = q;
        }
    }

    public void printArray(){
        String s = "";
        for(int i=0; i<id.length ; i++){ 
            s = s + ", " + id[i];    
        }
        System.out.println(s);
    } 

    public static void main(String[] args) {  
        QuickUnion x = new QuickUnion(5);
        x.union(0,1);
        x.printArray();
        x.union(1,2);
        x.printArray();
        x.union(1,4);
        x.printArray();
    }
}