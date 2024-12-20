package BOJ_5430;
import java.util.*;

public class Solution {

    public int operate(char[] operators, String[] array) {
        ArrayDeque<String> dq = new ArrayDeque<>(Arrays.asList(array));
        int countR = 0;

        for (char op : operators) {
            if (op == 'R') countR++;

            if (op == 'D') {
                if (dq.isEmpty())
                    return -1;

                if (countR % 2 == 0)
                    dq.pollFirst();
                else
                    dq.pollLast();
            }
        }

        return countR;
    }
}

