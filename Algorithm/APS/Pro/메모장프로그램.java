import java.util.*;
import java.io.*;

class UserSolution
{
    int H, W;
    int r_cursor, c_cursor;
    int length;
    Map<Character, Integer>[] map;
    Deque<Character>[] dq;
    char[] mStr;

    void init(int H, int W, char mStr[])
    {
        this.H = H;
        this.W = W;
        this.mStr = mStr;
        this.length = 0;

        map = new HashMap[H];
        dq = new ArrayDeque[H];
        for (int i = 0; i < H; i++) {
            map[i] = new HashMap<>();
            dq[i] = new ArrayDeque<>();
        }

        int index = 0;
        while (mStr[index] != '\0') {
            this.length++;
            char ch = mStr[index];
            if (map[index / W].get(ch) != null) {
                map[index / W].put(ch, map[index / W].get(ch) + 1);
            } else {
                map[index / W].put(ch, 1);
            }

            dq[index / W].addLast(ch);

            index++;
        }

        r_cursor = c_cursor = 1;
    }

    void insert(char mChar)
    {
        int row = r_cursor - 1;
        length++;

        Stack<Character> tmp = new Stack<>();
        int size = dq[row].size();
        for (int i = c_cursor; i <= size; i++) {
            tmp.add(dq[row].pollLast());
        }

        dq[row].addLast(mChar);
        if (map[row].get(mChar) != null) {
            map[row].put(mChar, map[row].get(mChar) + 1);
        } else {
            map[row].put(mChar, 1);
        }

        while (!tmp.isEmpty()) {
            if (dq[row] != null) {
                dq[row].add(tmp.pop());
            } else {
                dq[row] = new ArrayDeque<>();
                dq[row].add(tmp.pop());
            }
        }

        while (dq[row].size() > W) {
            char ch = dq[row].pollLast();
            map[row].put(ch, map[row].get(ch) - 1);

            dq[row + 1].addFirst(ch);
            if (map[row + 1].get(ch) != null) {
                map[row + 1].put(ch, map[row + 1].get(ch) + 1);
            } else {
                map[row + 1].put(ch, 1);
            }

            row++;
        }

        if (c_cursor == W) {
            r_cursor++;
            c_cursor = 1;
        } else {
            c_cursor++;
        }
    }

    char moveCursor(int mRow, int mCol)
    {
        int height;
        if (length % W == 0) height = length / W;
        else height = (length / W) + 1;

        if (mCol > dq[mRow - 1].size()) {
            r_cursor = height;
            c_cursor = dq[r_cursor - 1].size() + 1;
            return '$';
        } else {
            r_cursor = mRow;
            c_cursor = mCol;

            Stack<Character> tmp = new Stack<>();
            for (int i = 0; i < c_cursor; i++) {
                tmp.add(dq[mRow - 1].pollFirst());
            }

            char ch = tmp.peek();
            for (int i = 0; i < mCol; i++) {
                dq[mRow - 1].addFirst(tmp.pop());
            }

            return ch;
        }
    }

    int countCharacter(char mChar)
    {
        int count = 0;
        for (int i = r_cursor; i < H; i++) {
            if (map[i].get(mChar) != null) {
                count += map[i].get(mChar);
            }
        }

        Stack<Character> tmp = new Stack<>();
        int size = dq[r_cursor - 1].size();
        for (int i = c_cursor; i <= size; i++) {
            if (dq[r_cursor - 1].getLast() == mChar) {
                count ++;
            }

            tmp.add(dq[r_cursor - 1].pollLast());
        }

        while (!tmp.isEmpty()) {
            dq[r_cursor - 1].addLast(tmp.pop());
        }

        return count;
    }
}

class Solution
{
    private final static int CMD_INIT       = 100;
    private final static int CMD_INSERT     = 200;
    private final static int CMD_MOVECURSOR = 300;
    private final static int CMD_COUNT      = 400;

    private final static UserSolution usersolution = new UserSolution();

    private static void String2Char(char[] buf, String str, int maxLen)
    {
        for (int k = 0; k < str.length(); k++)
            buf[k] = str.charAt(k);

        for (int k = str.length(); k <= maxLen; k++)
            buf[k] = '\0';
    }

    private static char[] mStr = new char[90001];

    private static boolean run(BufferedReader br) throws Exception
    {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int queryCnt = Integer.parseInt(st.nextToken());
        boolean correct = false;

        for (int q = 0; q < queryCnt; q++)
        {
            st = new StringTokenizer(br.readLine(), " ");

            int cmd = Integer.parseInt(st.nextToken());

            if (cmd == CMD_INIT)
            {
                int H = Integer.parseInt(st.nextToken());
                int W = Integer.parseInt(st.nextToken());

                String2Char(mStr, st.nextToken(), 90000);

                usersolution.init(H, W, mStr);
                correct = true;
            }
            else if (cmd == CMD_INSERT)
            {
                char mChar = st.nextToken().charAt(0);

                usersolution.insert(mChar);
            }
            else if (cmd == CMD_MOVECURSOR)
            {
                int mRow = Integer.parseInt(st.nextToken());
                int mCol = Integer.parseInt(st.nextToken());

                char ret = usersolution.moveCursor(mRow, mCol);

                char ans = st.nextToken().charAt(0);
                if (ret != ans)
                {
                    correct = false;
                }
            }
            else if (cmd == CMD_COUNT)
            {
                char mChar = st.nextToken().charAt(0);

                int ret = usersolution.countCharacter(mChar);

                int ans = Integer.parseInt(st.nextToken());
                if (ret != ans)
                {
                    correct = false;
                }
            }
        }
        return correct;
    }

    public static void main(String[] args) throws Exception
    {
        int TC, MARK;

        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

        for (int testcase = 1; testcase <= TC; ++testcase)
        {
            int score = run(br) ? MARK : 0;

            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }
}