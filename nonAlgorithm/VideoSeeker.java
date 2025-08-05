package nonAlgorithm;
import java.lang.*;

public class VideoSeeker {
    static class Solution {
        int pos;
        int video_len;
        public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {

            this.video_len = toTime(video_len);
            this.pos = toTime(pos);

            skip(op_start, op_end);

            for(String command : commands){
                if(command.charAt(0) == 'n')
                    next();
                else
                    prev();
                skip(op_start, op_end);
            }

            return toString(this.pos);
        }

        private void next(){
            this.pos += 10;
            if(this.pos > this.video_len)
                this.pos = this.video_len;
        }

        private void prev(){
            this.pos -= 10;
            if(this.pos <= 0)
                this.pos = 0;
        }

        private void skip(String op_start, String op_end){
            if(this.pos >= toTime(op_start) && this.pos < toTime(op_end) )
                this.pos = toTime(op_end);
        }

        private int toTime(String time){
            String[] split = time.split(":");

            return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
        }

        private String toString(int time){
            int m = time / 60;
            int s = time % 60;

            return String.format("%02d:%02d",m,s);
        }
    }
}
