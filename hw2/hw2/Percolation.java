package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int N;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private int countOpen;
    private int head;
    private int tail;


    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must larger than 0");
        }
        this.grid = new boolean[N][N];
        this.N = N;
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(N * N + 2);
        this.countOpen = 0;
        head = N * N;
        tail = N * N + 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("row and col must between 0 and N - 1");
        }
        if (isOpen(row, col)) {
            return weightedQuickUnionUF.connected(linerConvert(row, col), head);
        }
        return false;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("row and col must between 0 and N - 1");
        }
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = true;
        countOpen++;

        if (row == 0) {
            weightedQuickUnionUF.union(linerConvert(row, col), head);
        } else if (row == N - 1) {
            weightedQuickUnionUF.union(linerConvert(row, col), tail);
        } else {
            if (isOpen(row - 1, col)) {
                weightedQuickUnionUF.union(linerConvert(row, col), linerConvert(row - 1, col));
            }
            if (row + 1 < N && isOpen(row + 1, col)) {
                weightedQuickUnionUF.union(linerConvert(row, col), linerConvert(row + 1, col));
            }
            if ((col - 1) >= 0 && isOpen(row, col - 1)) {
                weightedQuickUnionUF.union(linerConvert(row, col), linerConvert(row, col - 1));
            }
            if ((col + 1) < N && isOpen(row, col + 1)) {
                weightedQuickUnionUF.union(linerConvert(row, col + 1), linerConvert(row, col));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("row and col must between 0 and N - 1");
        }
        return grid[row][col];
    }

    // number of open sites
    public int numberOfOpenSites() {
        return countOpen;
    }

    // does the system percolate?
    public boolean percolates() {

        return weightedQuickUnionUF.connected(head, tail);
    }

    private void isValid(int lenth) {
        if (lenth >= N || lenth < 0) {
            throw new IllegalArgumentException("row or col must >= 0 or < N");
        }
    }

    private int linerConvert(int row, int col) {
        return row * N + col;
    }


    public static void main(String[] args) {

    }

}
