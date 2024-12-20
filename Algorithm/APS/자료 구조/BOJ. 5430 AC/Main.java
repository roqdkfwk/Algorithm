package BOJ_5430;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputHandler inputHandler = new InputHandler();
        OutputHandler outputHandler = new OutputHandler();
        Solution solution = new Solution();

        int T = inputHandler.readTestCases();
        for (int t = 0; t < T; t++) {
            char[] operator = inputHandler.readOperators();
            String[] array = inputHandler.readArray();
            int countR = solution.operate(operator, array);
            outputHandler.printResult(countR, array);
        }
    }
}

