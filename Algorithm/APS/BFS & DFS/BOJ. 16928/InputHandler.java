package BOJ_16928;

import java.util.*;
import java.io.*;
public class InputHandler {

    static int N, M;
    static HashMap<Integer, Integer> hashmap;
    public InputHandler() throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        hashmap = new HashMap<>();
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            hashmap.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    public HashMap<Integer, Integer> getHashmap() {
        return hashmap;
    }
}
