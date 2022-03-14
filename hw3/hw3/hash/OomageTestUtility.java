package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] numOfElements = new int[M];
        for (Oomage o :
                oomages) {
            numOfElements[(o.hashCode() & 0x7FFFFFFF) % M]++;
        }
        for (int i :
                numOfElements) {
            if (i < oomages.size() / 50 || i > oomages.size() / 2.5) {
                return false;
            }
        }
        return true;
    }
}
