package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private Percolation percolation;
    private int T;
    private double[] x;
    private int N;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N or T must be positive");
        }
        percolation = pf.make(N);
        this.T = T;
        this.N = N;
        x = new double[T];

        for (int i = 0; i < T; i++) {
            for (int j = 0; j < N * N; j++) {
                if (percolation.percolates()) {
                    break;
                }
                percolation.open(StdRandom.uniform(0, N - 1), StdRandom.uniform(0, N - 1));
            }
            x[i] = percolation.numberOfOpenSites() / (N * N * 1.0f);
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(x);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(x);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double mean = mean();
        double sigma = stddev();
        return mean - 1.96 * sigma / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double mean = mean();
        double sigma = stddev();
        return mean + 1.96 * sigma / Math.sqrt(T);
    }

}
