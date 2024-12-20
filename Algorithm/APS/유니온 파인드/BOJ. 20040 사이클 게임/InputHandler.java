package BOJ_20040;

import java.util.*;
import java.io.*;
public class InputHandler {

    int N, M;
    List<int[]> edges;
    public InputHandler() throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new int[] {
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
            });
        }
    }

    public List<int[]> getEdges() {
        return edges;
    }

    public int getVertex() {
        return N;
    }
}
