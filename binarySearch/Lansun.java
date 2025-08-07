package binarySearch;

import java.io.*;
import java.util.StringTokenizer;

public class Lansun {

    static class Main {
        int k;
        int n;
        int[] kLengthArray;
        public static void main(String[] args) throws IOException {
            new Main().logic();
        }

        private void logic() throws IOException {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            getInput();

            bw.write(String.valueOf(mainLogic()));
            bw.flush();
        }

        private int mainLogic() {
            double hi = Math.pow(2,31) - 1;
            double low = 0;

            while (low + 1 <= hi) {
                double mid = (low + hi) / 2;

                if(check(mid)) low = mid;
                else hi = mid;
            }

            return (int) hi;
        }


        private boolean check(double mid){
            int sum = 0;
            for(int kLength : kLengthArray){
                sum += (int) (kLength / mid);
            }
            return sum >= n;
        }

        private void getInput() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            kLengthArray = new int[k];

            for (int i = 0; i < k; i++) {
                kLengthArray[i] = Integer.parseInt(br.readLine());
            }
        }
    }
}
