package BOJ_20040;

import BOJ_16928.OutputHandler;

import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, NumberFormatException {
        // 1. 입력 값을 받는다.
        InputHandler inputHandler = new InputHandler();
        List<int[]> edges = inputHandler.getEdges();
        int V = inputHandler.getVertex();

        // 2. 문제 해결 로직을 수행
        Solution solution = new Solution(edges, V);
        int answer = solution.getResult();

        // 3. 정답을 출력
        OutputHandler outputHandler = new OutputHandler();
        outputHandler.printResult(answer);
    }
}
