package BOJ_20040;

import java.util.*;
public class Solution {

    List<int[]> edges;
    int[] parent;
    int answer;
    public Solution(List<int[]> edges, int V) {
        this.edges = edges;
        this.parent = new int[V];
        for (int i = 0; i < V; i++)
            parent[i] = i;

        int count = 0;
        for (int[] edge : edges) {
            count++;

            int v1 = findParent(edge[0]);
            int v2 = findParent(edge[1]);

            if (v1 == v2) {
                answer = count;
                return;
            }

            union(v1, v2);
        }
    }

    public int findParent(int v) {
        if (parent[v] != v)
            parent[v] = parent[findParent(v)];

        return parent[v];
    }

    public void union(int v1, int v2) {
        if (v1 > v2)
            parent[v1] = v2;
        else
            parent[v2] = v1;
    }

    public int getResult() {
        return answer;
    }
}
