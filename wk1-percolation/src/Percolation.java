public class Percolation {

    private final WeightedQuickUnionUF uf;
    private final boolean[][] system;
    private final int bottomNode;
    private final int topNode;
    private final int size;
    private boolean hasOpen = false;

    /**
     * Create N-by-N grid, with all sites blocked.
     */
    public Percolation(int n) {
        system = new boolean[n][n];
        size = n;
        bottomNode = n * n + 1;
        topNode = n * n;
        uf = new WeightedQuickUnionUF(bottomNode + 1);

        // link the top with the first line and the bottom with the last line
        for (int i = 0, j = n * n - 1; i < n; i++, j--) {
            uf.union(topNode, i);
            uf.union(bottomNode, j);
        }
    }

    /**
     * Open site (row i, column j) if it is not already.
     */
    public void open(int i, int j) {
        checkIndex(i, j);

        if (!isOpen(i, j)) {
            hasOpen = true;
            system[i - 1][j - 1] = true;

            int currentNode = node(i, j);

            if (i >= 2 && isOpen(i - 1, j)) {
                uf.union(currentNode, node(i - 1, j));
            }
            if (j >= 2 && isOpen(i, j - 1)) {
                uf.union(currentNode, node(i, j - 1));
            }
            if (i < size && isOpen(i + 1, j)) {
                uf.union(currentNode, node(i + 1, j));
            }
            if (j < size && isOpen(i, j + 1)) {
                uf.union(currentNode, node(i, j + 1));
            }
        }

    }

    /**
     * Is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {
        checkIndex(i, j);

        return system[i - 1][j - 1];
    }

    /**
     * Is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        checkIndex(i, j);

        if (!isOpen(i, j)) {
            return false;
        }
        return uf.connected(topNode, node(i, j));
    }

    /**
     * Does the system percolate?
     */
    public boolean percolates() {
        return hasOpen && uf.connected(topNode, bottomNode);
    }

    private int node(int i, int j) {
        return size * (i - 1) + j - 1;
    }

    private void checkIndex(int i, int j) {
        if (i < 1 || i > size) {
            throw new IndexOutOfBoundsException("The index should be between 1 and " + size + " instead of equals to " + i);
        }
        if (j < 1 || j > size) {
            throw new IndexOutOfBoundsException("The index should be between 1 and " + size + " instead of equals to " + i);
        }
    }
}