package SWEA_23011;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.StringTokenizer;

class Solution
{
    private static final int INIT 			= 100;
    private static final int DROP_BLOCKS 	= 200;
    private static final int CHANGE_BLOCKS 	= 300;
    private static final int GET_RESULT	 	= 400;

    private static UserSolution usersolution = new UserSolution();

    private static boolean run(BufferedReader br) throws Exception
    {
        int Q, W, H;

        int mPlayer, mCol;

        int ret = -1, ans;
        int[] mBlockCnt = new int[2];
        int r1, r2;

        Q = Integer.parseInt(br.readLine());

        boolean okay = false;

        for (int q = 0; q < Q; ++q)
        {
            int cmd;
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            System.out.println("몇번째? : " + q);

            cmd = Integer.parseInt(st.nextToken());
            switch(cmd)
            {
                case INIT:
                    W = Integer.parseInt(st.nextToken());
                    H = Integer.parseInt(st.nextToken());
                    System.out.println("INIT - W - H : " + W + " " + H);
                    usersolution.init(W, H);
                    okay = true;
                    break;
                case DROP_BLOCKS:
                    mPlayer = Integer.parseInt(st.nextToken());
                    mCol = Integer.parseInt(st.nextToken());

                    System.out.println("DROP - mPlayer - mCol : " + mPlayer + " " + mCol);
                    System.out.println(okay);

                    if (okay)
                        ret = usersolution.dropBlocks(mPlayer, mCol);
                    ans = Integer.parseInt(st.nextToken());;
                    if (ret != ans)
                        okay = false;
                    break;
                case CHANGE_BLOCKS:
                    mPlayer = Integer.parseInt(st.nextToken());
                    mCol = Integer.parseInt(st.nextToken());

                    System.out.println("CHANGE - mPlayer - mCol : " + mPlayer + " " + mCol);

                    if (okay)
                        ret = usersolution.changeBlocks(mPlayer, mCol);
                    ans = Integer.parseInt(st.nextToken());;
                    if (ret != ans)
                        okay = false;
                    break;
                case GET_RESULT:
                    System.out.println("RESULT");
                    if (okay)
                        ret = usersolution.getResult(mBlockCnt);
                    ans =  Integer.parseInt(st.nextToken());
                    r1 =  Integer.parseInt(st.nextToken());
                    r2 =  Integer.parseInt(st.nextToken());
                    if (ans != ret || mBlockCnt[0] != r1 || mBlockCnt[1] != r2)
                        okay = false;
                    break;
                default:
                    okay = false;
                    break;
            }
        }

        return okay;
    }

    public static void main(String[] args) throws Exception
    {

        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(br.readLine(), " ");

        int TC = Integer.parseInt(line.nextToken());
        int MARK = Integer.parseInt(line.nextToken());

        for (int testcase = 1; testcase <= TC; ++testcase)
        {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        br.close();

    }
}