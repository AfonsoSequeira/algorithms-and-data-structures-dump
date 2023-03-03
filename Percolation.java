import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[] grid;
    private int dim;
    private int nOpen;
    private WeightedQuickUnionUF uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if (n <= 0){
            throw new IllegalArgumentException("Indeces must be within range of id array.");
        }
        else {
            dim = n;
            nOpen = 0;
            grid = new int[n*n];
            //initialize quick union object
            uf =  new WeightedQuickUnionUF(n*n + 2);
            for (int i = 0; i < (n*n) ; i++){
                grid[i] = 0;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        int n = (row - 1) * dim + (col - 1);
        if (row < 1 || row > dim || col < 1 || col > dim){
            throw new IllegalArgumentException("Indeces must be within range of id array.");
        }
        else {
            if (grid[n] == 0) {nOpen +=1;}
            grid[n] = 1;

            if (n < dim) {
                uf.union(n, grid.length); 
            }

            if (n > (dim)*(dim - 1) - 1){
                uf.union(n, grid.length + 1); 
            }

            try {
                if (isOpen(row, col + 1) ){
                    uf.union(n, n + 1); 
                }
            }
            catch (IllegalArgumentException e) {
            }

            try {
                if (isOpen(row, col - 1) ){uf.union(n, n - 1);}
            }
            catch (IllegalArgumentException e) {
            }

            try {
                if (isOpen(row + 1, col) ){uf.union(n, n + dim);}

            }
            catch (IllegalArgumentException e) {
            }

            try {
                if (isOpen(row - 1, col) ){uf.union(n, n - dim);}

            }
            catch (IllegalArgumentException e) {
            }

        }
    }
    
    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        int n = (row - 1) * dim + (col - 1);
        if (row < 1 || row > dim || col < 1 || col > dim){
            throw new IllegalArgumentException("Indeces must be within range of id array.");
        }
        else {
            return grid[n] == 1;
        }
    }

    // is the site (row, col) full? is site connected to site of first row, which are all connected.
    public boolean isFull(int row, int col){
        int n = (row - 1) * dim + (col - 1);
        if (row < 1 || row > dim || col < 1 || col > dim){
            throw new IllegalArgumentException("Indeces must be within range of id array.");
        }
        else {
            return uf.find(n) ==  uf.find(dim * dim) && isOpen(row, col);
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return nOpen;
    }

    // does the system percolate?
    public boolean percolates(){
        return uf.find(dim * dim) == uf.find(dim * dim + 1);
    }

    // test client (optional)
    public static void main(String[] args){
        //isOpen, check!
        //numberOfOpen sites, check!
        //percolates, check!

        Percolation test = new Percolation(3);

        test.open(2,3);
        test.open(2,3);
        test.open(2,3);
        System.out.println(test.numberOfOpenSites());


        //test.open(3,3);
        //System.out.println(test.isOpen(1, 1));
        //System.out.println(test.isOpen(3, 3));
        //System.out.println(test.isOpen(1, 2));
        //System.out.println(test.numberOfOpenSites());

    }
}