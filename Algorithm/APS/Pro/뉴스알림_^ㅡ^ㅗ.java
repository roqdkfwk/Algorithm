import java.io.*;
import java.util.*;

class UserSolution {

    class News implements Comparable<News> {
        int ID, TIME;

        public News (int ID, int TIME) {
            this.ID = ID;
            this.TIME = TIME;
        }

        @Override
        public int compareTo(News N) {
            return this.TIME == N.TIME ? N.ID - this.ID : N.TIME - this.TIME;
        }
    }	// News

    class User {
        int ID, TIME;

        public User(int ID, int TIME) {
            this.ID = ID;
            this.TIME = TIME;
        }
    }	// User

    int N, K;
    Map<Integer, List<User>> CU;			// Channel - User
    Map<Integer, List<News>> CN;			// Channel - News
    Map<Integer, List<Integer>> NC;			// News - Channel
    Map<Integer, PriorityQueue<News>> UN;	// User - News

    // N : 뉴스 알림을 받는 유저의 수 (1 ≤ N ≤ 500)
    // K : 뉴스 알림을 보내는 뉴스 채널의 수 (1 ≤ K ≤ 500)
    void init(int N, int K)
    {
        this.N = N;
        this.K = K;

        CU = new HashMap<>();
        CN = new HashMap<>();
        NC = new HashMap<>();
        UN = new HashMap<>();
    }

    // mTime         : 현재 시각 (1 ≤ mTime ≤ 1,000,000,000)
    // mUID          : 유저의 ID (1 ≤ mUID ≤ 1,000,000,000)
    // mNum          : 유저가 등록하는 뉴스 채널의 수 (1 ≤ mNum ≤ 30)
    // mChannelIDs[] : 유저가 등록하는 뉴스 채널의 ID (1 ≤ mChannelIDs[] 의 값 ≤ 1,000,000,000)
    // registerUser(시각, 유저, 개수, 채널)
    // mTime 시각에 유저에게 보내지는 뉴스 알림이 있는 경우 먼저 알림을 보낸 후, mUID 유저를 뉴스 채널에 등록한다.
    void registerUser(int mTime, int mUID, int mNum, int mChannelIDs[])
    {
        User user = new User(mUID, mTime);
        for (int i = 0; i < mNum; i++) {
            int key = mChannelIDs[i];
            if (CU.get(key) == null) {
                CU.put(key, new ArrayList<>());
            }

            CU.get(key).add(user);
        }
    }

    // mTime   : 현재 시각 (1 ≤ mTime ≤ 1,000,000,000)
    // mNewsID : 뉴스의 ID (1 ≤ mNewsID ≤ 1,000,000,000)
    // mDelay  : 뉴스의 delay 시간 (1 ≤ mDelay ≤ 10,000)
    // mChannelID : 뉴스를 제공받는 뉴스 채널의 ID (1 ≤ mChannelID ≤ 1,000,000,000)
    // offerNews(시각, 뉴스, 딜레이, 채널)
    int offerNews(int mTime, int mNewsID, int mDelay, int mChannelID)
    {
        News news = new News(mNewsID, mTime + mDelay);	// new News(뉴스 ID, 뉴스가 User에게 제공되는 시각)
        Integer key = mChannelID;
        if (CN.get(key) == null) CN.put(key, new LinkedList<>());
        CN.get(key).add(news);

        if (NC.get(mNewsID) == null) NC.put(mNewsID, new LinkedList<>());
        NC.get(mNewsID).add(key);		// News - Channel은 cancelNews에서 해당 뉴스를 제공받는 채널을 찾을 때 씀

        if (CU.get(key) != null) {			// 해당 채널에서 알림을 받는 유저들이 있는 경우
            for (User user : CU.get(key)) {	// 해당 채널에서 알림을 받는 유저들을 찾아서
                if (UN.get(user.ID) == null) UN.put(user.ID, new PriorityQueue<>());
                UN.get(user.ID).add(news);	// 해당 유저들에게 뉴스를 제공함
//				System.out.println("mTime : " + news.TIME + " userID : " + user.ID + " newsID : " + news.ID);
            }
        }

//        System.out.println("OFFER : " + CU.get(key).size());
//        System.out.println("OFFER :: mTime : " + mTime + " " + CU.get(key).size());
        return CU.get(key).size();
    }

    // mTime   : 현재 시각 (1 ≤ mTime ≤ 1,000,000,000)
    // mNewsID : 취소되는 뉴스 ID (1≤ mNewsID ≤ 1,000,000,000)
    void cancelNews(int mTime, int mNewsID)
    {
        List<Integer> channelIDs = NC.get(mNewsID);	// 취소되는 뉴스가 제공되는 채널의 ID를 가져옴
        for (Integer chID : channelIDs) {
            Iterator<News> iter = CN.get(chID).iterator();
            while (iter.hasNext()) {				// 채널을 돌면서
                News news = iter.next();
                if (news.ID == mNewsID) {	// 채널에 제공되는 뉴스를 제거
                    iter.remove();
                    break;
                }
            }

            if (CU.get(chID) != null) {
                List<User> users = CU.get(chID);		// 해당 채널에서 알림을 받는 유저 리스트
                for (User us : users) {
                    iter = UN.get(us.ID).iterator();
                    while (iter.hasNext()) {			// 유저를 순회하면서
                        News news = iter.next();
                        if (news.ID == mNewsID) {// 유저에게 제공되는 해당 뉴스에 대한 알림을 제거
//							System.out.println("cancelTime : " + mTime + " userID : " + us.ID + " newsID : " + news.ID);
                            iter.remove();
                            break;
                        }
                    }
//					System.out.println("mTime : " + mTime +  " userID : " + us.ID + " size : " + UN.get(us.ID).size());
                }
            }
        }
    }


    // mTime   : 현재 시각 (1 ≤ mTime ≤ 1,000,000,000)
    // mUID    : 뉴스 알림을 확인하는 유저 ID (1 ≤ mUID ≤ 1,000,000,000)
    // mRetIDs[] : 받은 뉴스 ID를 최신 순서대로 저장하는 공간
    int checkUser(int mTime, int mUID, int mRetIDs[])
    {
        int count = 0;
        List<News> curr = new LinkedList<>();

        PriorityQueue<News> pq = UN.get(mUID);
//		while (!pq.isEmpty()) {
//			System.out.println("checkPQ : " + mUID + " " + pq.peek().ID + " " + pq.poll().TIME);
//		}

        Iterator<News> iter = pq.iterator();
        while (iter.hasNext()) {
            News news = iter.next();
//			System.out.println("checkPQ : " + mUID + " " + news.ID + " " + news.TIME);
            if (news.TIME <= mTime) {
//                System.out.println("ID : " + news.ID + " TIME : " + news.TIME);
                curr.add(news);
                count++;
                iter.remove();
            }
        }

//		while (!pq.isEmpty() && pq.peek().TIME <= mTime) {	// 뉴스 알림 시각이 현재 시각보다 이른 경우
//			News news = pq.poll();							// 유저가 받은 뉴스 알림을 삭제하고
//			System.out.println("newsID : " + news.ID);
//			curr.add(news.ID);
//			count++;										// 유저가 받은 뉴스 알림의 개수를 1만큼 증가
//		}

        Collections.sort(curr);
        for (int i = 0; i < 3 && i < curr.size(); i++) {
            mRetIDs[i] = curr.get(i).ID;
//            System.out.println(mRetIDs[i]);
        }

//        System.out.println("CHECK : " + count);
//        System.out.println("CHECK :: mTime : " + mTime + " mUID : " + mUID + " count : " + count);
        return count;
    }
}

class Solution {
    private static BufferedReader br;
    private static UserSolution usersolution = new UserSolution();

    private final static int INIT = 0;
    private final static int REGI = 1;
    private final static int OFFER = 2;
    private final static int CANCEL = 3;
    private final static int CHECK = 4;

    private static int gids[] = new int[30];
    private static int ansids[] = new int[3];
    private static int retids[] = new int[3];

    private static boolean run() throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N, K, cmd, ans, ret;
        int time, num, uid, gid, nid, delay;

        int Q = Integer.parseInt(st.nextToken());
        boolean ok = false;

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            cmd = Integer.parseInt(st.nextToken());

            if (cmd == INIT) {
                N = Integer.parseInt(st.nextToken());
                K = Integer.parseInt(st.nextToken());

                usersolution.init(N, K);
                ok = true;
            } else if (cmd == REGI) {
                time = Integer.parseInt(st.nextToken());
                uid = Integer.parseInt(st.nextToken());
                num = Integer.parseInt(st.nextToken());
                for (int m = 0; m < num; m++) {
                    gids[m] = Integer.parseInt(st.nextToken());
                }

                usersolution.registerUser(time, uid, num, gids);
            } else if (cmd == OFFER) {
                time = Integer.parseInt(st.nextToken());
                nid = Integer.parseInt(st.nextToken());
                delay = Integer.parseInt(st.nextToken());
                gid = Integer.parseInt(st.nextToken());
                ans = Integer.parseInt(st.nextToken());

                ret = usersolution.offerNews(time, nid, delay, gid);
                if (ans != ret) {
                    ok = false;
                }
            }
            else if (cmd == CANCEL) {
                time = Integer.parseInt(st.nextToken());
                nid = Integer.parseInt(st.nextToken());

                usersolution.cancelNews(time, nid);
            }
            else if (cmd == CHECK) {
                time = Integer.parseInt(st.nextToken());
                uid = Integer.parseInt(st.nextToken());

                ret = usersolution.checkUser(time, uid, retids);

                ans = Integer.parseInt(st.nextToken());
                num = ans;
                if (num > 3) num = 3;
                for (int m = 0; m < num; m++) {
                    ansids[m] = Integer.parseInt(st.nextToken());
                }
                if (ans != ret) {
                    ok = false;
                }
                else {
                    for (int m = 0; m < num; m++) {
                        if (ansids[m] != retids[m]) {
                            ok = false;
                        }
                    }
                }
            }
            else ok = false;
        }
        return ok;
    }

    public static void main(String[] args) throws Exception {
        int T, MARK;

        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");
        T = Integer.parseInt(stinit.nextToken());
        MARK = Integer.parseInt(stinit.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            int score = run() ? MARK : 0;
            System.out.println("#" + tc + " " + score);
        }

        br.close();
    }
}