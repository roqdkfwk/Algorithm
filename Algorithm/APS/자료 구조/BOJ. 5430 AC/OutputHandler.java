package BOJ_5430;

public class OutputHandler {

    public void printResult(int countR, String[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        int size = array.length;
        if (countR == -1) {
            System.out.println("error");
        } else if (size == 0) {
            System.out.println("[]");
        } else if (countR % 2 == 0) {
            for (int i = 0; i < size; i++) sb.append(array[i]).append(",");

            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            System.out.println(sb);
        } else {
            for (int i = size - 1; i >= 0; i--) sb.append(array[i]).append(",");

            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            System.out.println(sb);
        }
    }
}

