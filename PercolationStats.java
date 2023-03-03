import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int nSize;
    private int tTrials;
    private double[] x;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if (n < 1 || trials < 1){
            throw new IllegalArgumentException();
        }
        nSize = n;
        tTrials = trials;
        x = new double[trials];
        double sz = nSize * nSize;
        double res;

        for (int t = 0; t < tTrials; t++){
            res = percolationExperiment(nSize);
            x[t] = res/sz;
        }        
    }

    //experiment function 
    private int percolationExperiment(int n){
        Percolation experiment = new Percolation(n);
        boolean success = false;
        int row;
        int col;

        while (success == false){       
            row = StdRandom.uniformInt(1, n + 1);
            col = StdRandom.uniformInt(1, n + 1);

            experiment.open(row, col);
            success = experiment.percolates();
        }
        return experiment.numberOfOpenSites();
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(x);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(x);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        double sigma  = 1.96 * this.stddev() / Math.sqrt(tTrials);
        return this.mean() - sigma; 
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        double sigma  = 1.96 * this.stddev() / Math.sqrt(tTrials);
        return this.mean() + sigma; 
    }

   // test client (see below)
   public static void main(String[] args){
    int nSize = Integer.parseInt(args[0]);
    int nTrials = Integer.parseInt(args[1]);

    PercolationStats experiments = new PercolationStats(nSize, nTrials);
    double mean = experiments.mean();
    double std = experiments.stddev();
    double lCo = experiments.confidenceLo();
    double hCo = experiments.confidenceHi();

    String s1 = String.format("mean = %f", mean);
    String s2 = String.format("stddev = %f", std);

    System.out.println(s1);
    System.out.println(s2);
    System.out.println("95% confidence interval = ["+ lCo  + "," + hCo + "]");
   }

}
