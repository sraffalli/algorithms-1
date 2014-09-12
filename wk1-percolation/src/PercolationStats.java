public class PercolationStats {

    public static final double RATIO = 1.96;
    private final double[] results;
    private final double nbXps;

    /**
     * Perform T independent computational experiments on an N-by-N grid.
     */
    public PercolationStats(int n, int t) {
        results = new double[t];
        nbXps = t;

        double nbCases = n * n;

        for (int k = 0; k < t; k++) {
            Percolation p = new Percolation(n);
            double x = 0.0;
            while (!p.percolates()) {
                int i = StdRandom.uniform(1, n + 1);
                int j = StdRandom.uniform(1, n + 1);

                if (p.isFull(i, j)) {
                    p.open(i, j);
                    x++;
                }
            }
            results[k] = x / nbCases;
        }
    }

    public static void main(String[] args) {

        if (args.length != 2) {
            throw new IllegalArgumentException("You must specify 2 arguments !");
        }

        int n = Integer.valueOf(args[0]);
        int t = Integer.valueOf(args[1]);

        PercolationStats s = new PercolationStats(n, t);

        StdOut.println("mean                    = " + s.mean());
        StdOut.println("stddev                  = " + s.stddev());
        StdOut.println("95% confidence interval = " + s.confidenceLo() + ", " + s.confidenceHi());

    }  // test client, described below

    /**
     * Sample mean of percolation threshold.
     */
    public double mean() {
        double mean = 0.0;
        for (double result : results) {
            mean += result;
        }
        return mean / nbXps;
    }

    /**
     * Sample standard deviation of percolation threshold.
     */
    public double stddev() {
        double mean = mean();
        double stddev = 0.0;

        for (double result : results) {
            double x = result - mean;
            stddev += x * x;
        }
        return Math.sqrt(stddev / (nbXps - 1.0));
    }

    /**
     * Returns lower bound of the 95% confidence interval.
     */
    public double confidenceLo() {
        return mean() - (RATIO * stddev()) / Math.sqrt(nbXps);
    }

    /**
     * Returns upper bound of the 95% confidence interval.
     */
    public double confidenceHi() {
        return mean() + (RATIO * stddev()) / Math.sqrt(nbXps);
    }
}