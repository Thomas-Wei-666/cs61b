package test;

import UF.WeiUF;
import org.junit.Assert;
import org.junit.Test;

public class TestUF {
    public static final int N = 10;


    @Test
    public void TestWeiUF() {
        WeiUF weiUF = new WeiUF(N);
        weiUF.union(1, N - 1);
        weiUF.union(8, N - 1);
        weiUF.union(3, 6);

        Assert.assertEquals(false, weiUF.connected(2, 5));
        Assert.assertEquals(3, weiUF.sizeOf(N - 1));
        Assert.assertEquals(2,weiUF.sizeOf(3));

    }
}
