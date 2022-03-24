package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private Maze maze;
    private int s;
    private int t;
    private boolean targetFound = false;

    private static final int INFINITY = 9999;


    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /**
     * Conducts a breadth first search of the maze starting at the source.
     */
    private void bfs(int s) {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i < maze.V(); i++) {
            distTo[i] = INFINITY;
        }

        marked[s] = true;
        announce();
        q.add(s);

        while (!q.isEmpty()) {
            int v = q.remove();
            if (v == t){
                return;
            }
            for (int w :
                    maze.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.add(w);
                    announce();
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs(s);
    }
}

