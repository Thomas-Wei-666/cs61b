package UF;

public interface UnionFind {
    void validate(int v1);

    int sizeOf(int v1);

    int parent(int v1);

    boolean connected(int v1, int v2);

    void union(int v1, int v2);

    int find(int v1);
}
