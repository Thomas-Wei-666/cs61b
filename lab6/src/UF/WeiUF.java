package UF;

import UF.UnionFind;

public class WeiUF implements UnionFind {
    private int[] parent;
    private int[] size;

    public WeiUF(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

    }


    @Override
    public void validate(int v1) {
        if (v1 < 0 || v1 > parent.length) {
            throw new RuntimeException("v1 isn't valid");
        }
    }

    @Override
    public int sizeOf(int v1) {
        validate(v1);

        return size[find(v1)];
    }

    @Override
    public int parent(int v1) {
        validate(v1);
        return parent[v1];
    }

    @Override
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);

        return find(v1) == find(v2);
    }

    @Override
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int p1 = find(v1), p2 = find(v2);
        if (size[p1] > size[p2]) {
            parent[p2] = p1;
            size[p1] += size[p2];
        } else if (size[p2] >= size[p1]) {
            parent[p1] = p2;
            size[p2] += size[p1];
        }
    }

    @Override
    public int find(int v1) {
        validate(v1);
        while (v1 != parent(v1)) {
            v1 = parent(v1);
        }
        return v1;
    }
}
