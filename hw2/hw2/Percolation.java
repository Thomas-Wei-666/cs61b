package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int N;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private int countOpen;
    private boolean firstOpenFlag = true;


    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N<=0){
            throw new IllegalArgumentException("N must larger than 0");
        }
        this.grid = new boolean[N][N];
        this.N = N;
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(N * N + 1);
        this.countOpen = 0;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("row and col must between 0 and N - 1");
        }
        if (isOpen(row, col)) {
            return weightedQuickUnionUF.connected(linerConvert(row, col), N * N);
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
        if (firstOpenFlag) {
            weightedQuickUnionUF.union(linerConvert(row, col), N * N);
            grid[row][col] = true;
            firstOpenFlag = false;
            countOpen++;
        } else {
            grid[row][col] = true;
            modifyFull(row, col);
            countOpen++;
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
        int topLoc = findFullInRowX(0);
        int bottomLoc = findFullInRowX(N - 1);
        return topLoc >= 0 && bottomLoc >= 0;
    }

    private void isValid(int lenth) {
        if (lenth >= N || lenth < 0) {
            throw new IllegalArgumentException("row or col must >= 0 or < N");
        }
    }

    private int linerConvert(int row, int col) {
        return row * N + col;
    }

    private int findFullInRowX(int rowX) {
        isValid(rowX);
        int res = -1;
        int i = 0;
        for (; i < N; i++) {
            if (isFull(rowX, i)) {
                res = i;
                break;
            }
        }
        if (res == -1) {
            return -1;
        } else {
            return linerConvert(rowX, res);
        }
    }

    private int surroundCheck(int row, int col) {
        if (row - 1 >= 0) {
            if (isFull(row - 1, col)) {
                return linerConvert(row - 1, col);
            }
        }
        if (col - 1 >= 0) {
            if (isFull(row, col - 1)) {
                return linerConvert(row, col - 1);
            }
        }
        if (row + 1 < N) {
            if (isFull(row + 1, col)) {
                return linerConvert(row + 1, col);
            }
        }
        if (col + 1 < N) {
            if (isFull(row, col + 1)) {
                return linerConvert(row, col + 1);
            }
        }
        return -1;
    }

    private void modifyFull(int row, int col) {
        if (surroundCheck(row, col) == -1) {
            return;
        } else {
            weightedQuickUnionUF.union(linerConvert(row, col), N * N);
            if (row - 1 >= 0) {
                if (isOpen(row - 1, col) && !isFull(row - 1, col)) {
                    modifyFull(row - 1, col);
                }
            }
            if (row + 1 < N) {
                if (isOpen(row + 1, col) && !isFull(row + 1, col)) {
                    modifyFull(row + 1, col);
                }
            }
            if (col - 1 >= 0) {
                if (isOpen(row, col - 1) && !isFull(row, col - 1)) {
                    modifyFull(row, col - 1);
                }
            }
            if (col + 1 < N) {
                if (isOpen(row, col + 1) && !isFull(row, col + 1)) {
                    modifyFull(row, col + 1);
                }
            }
        }

    }

    public static void main(String[] args) {

    }

}
