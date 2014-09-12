import org.junit.Assert;
import org.junit.Test;

public class PercolationTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpen_5x5_throws_OutOfBounds1() {
        new Percolation(5).isOpen(-1, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpen_5x5_throws_OutOfBounds2() {
        new Percolation(5).isOpen(4, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpen_5x5_throws_OutOfBounds3() {
        new Percolation(5).isOpen(7, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpen_5x5_throws_OutOfBounds4() {
        new Percolation(5).isOpen(2, 7);
    }

    @Test
    public void isOpen_5x5() {
        final Percolation p = new Percolation(5);
        p.open(1, 1);
        p.open(1, 2);
        p.open(1, 3);
        p.open(1, 4);
        p.open(1, 5);
        Assert.assertTrue(p.isOpen(1, 1));
        Assert.assertTrue(p.isOpen(1, 2));
        Assert.assertTrue(p.isOpen(1, 3));
        Assert.assertTrue(p.isOpen(1, 4));
        Assert.assertTrue(p.isOpen(1, 5));

        for (int i = 2; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                Assert.assertFalse(p.isOpen(i, j));
            }
        }
    }

    @Test
    public void percolates_5x5_true() {
        final Percolation p = new Percolation(5);
        p.open(1, 1); // Open left-most edge of grid
        p.open(2, 1);
        p.open(3, 1);
        p.open(4, 1);
        p.open(5, 1);
        Assert.assertTrue(p.percolates());
    }


    @Test
    public void percolates_5x5_false_without_open() {
        final int n = 5;
        final Percolation p = new Percolation(n);
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                Assert.assertFalse(
                        p.isOpen(i, j));
                Assert.assertFalse(
                        p.isFull(i, j));
            }
        }
        Assert.assertFalse(p.percolates());
    }

    @Test
    public void percolates_5x5_false_with_some_open() {
        final Percolation p = new Percolation(5);
        p.open(1, 4);
        p.open(2, 3);
        Assert.assertFalse(p.percolates());
    }

    @Test
    public void percolates_1x1_false() {
        final Percolation p = new Percolation(1);
        Assert.assertFalse(p.percolates());
    }


    @Test
    public void isFull_5x5_true() {
        final Percolation p = new Percolation(5);
        p.open(1, 1);
        Assert.assertTrue(p.isFull(1, 1));
        p.open(2, 1);
        Assert.assertTrue(p.isFull(2, 1));
        p.open(1, 2);
        Assert.assertTrue(p.isFull(1, 2));
        p.open(2, 2);
        Assert.assertTrue(p.isFull(2, 2));
    }

    @Test
    public void isFull_5x5_false() {
        final Percolation p = new Percolation(5);
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                Assert.assertFalse(p.isFull(i, j));
            }
        }

        p.open(1, 1);
        Assert.assertTrue(p.isFull(1, 1));
    }


    @Test
    public void isFull_2x2_false() {
        final Percolation p = new Percolation(2);
        p.open(1, 1);
        p.open(2, 2);
        Assert.assertFalse(p.isFull(1, 2));
        Assert.assertFalse(p.isFull(2, 1));
        Assert.assertFalse(p.isFull(2, 2));
    }


    @Test
    public void isFull_1x1_false() {
        final Percolation p = new Percolation(1);
        Assert.assertFalse(p.isFull(1, 1));
    }


}