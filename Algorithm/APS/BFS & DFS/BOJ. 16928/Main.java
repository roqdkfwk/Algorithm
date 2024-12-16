package BOJ_16928;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        InputHandler inputHandler = new InputHandler();
        HashMap<Integer, Integer> hashmap = inputHandler.getHashmap();

        Solution solution = new Solution(hashmap);
        int answer = solution.getCount();

        OutputHandler outputHandler = new OutputHandler();
        outputHandler.printResult(answer);
    }
}
