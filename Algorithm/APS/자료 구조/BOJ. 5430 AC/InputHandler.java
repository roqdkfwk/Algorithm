package BOJ_5430;

import java.io.*;

public class InputHandler {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public int readTestCases() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    public char[] readOperators() throws IOException {
        return br.readLine().toCharArray();
    }

    public String[] readArray() throws IOException {
        br.readLine();
        String input = br.readLine();

        if (input.equals("[]")) {
            return new String[0];
        } else {
            return input.substring(1, input.length() - 1).split(",");
        }
    }
}

