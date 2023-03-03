public class WeightedQuickUnion {
    private int[] id;
    private int[] sz;

    public WeightedQuickUnion(int n){
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++){
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int root(int n){
        while (id[n] != n){
            id[n] = id[id[n]];//path compression
            n = id[n];
        }
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
            if (sz[p] > sz[q]){
                id[q] = p;
                sz[p] += sz[q];
            }
            else {
                id[p] = q;
                sz[q] += sz[p];
            }
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
        WeightedQuickUnion x = new WeightedQuickUnion(5);
        x.union(0,1);
        x.printArray();
        x.union(1,2);
        x.printArray();
        x.union(1,4);
        x.printArray();
        x.union(3,1);
        x.printArray();
    }
}