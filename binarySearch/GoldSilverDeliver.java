package binarySearch;

public class GoldSilverDeliver {
    public static void main(String[] args) {
        long result = new GoldSilverDeliver().solution(90,500, new int[]{70, 70, 0},new int[]{0,0,500},new int[]{100,100,2}, new int[]{4,8,1});
        System.out.println(result);
    }

    int g,s;
    City[] cityArray;
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        this.g = a;
        this.s = b;
        init(g,s,w,t);

        long hi = (long)(Math.pow(10,9) * Math.pow(10,5) * 2 * 2);
        long low = 0;

        while(low+1 < hi){
            long mid = (hi+low)/2;

            if(check(mid)) hi = mid;
            else low = mid;
        }

        return hi;
    }

    boolean check(long mid){
        long total = 0L;
        long totalG = 0L;
        long totalS = 0L;

        for(City city : this.cityArray){
            long possibleRunningCnt = mid / (city.time * 2L);
            if(mid % (city.time * 2L) >= city.time)
                possibleRunningCnt++;

            long tmp = Math.min(city.weight * possibleRunningCnt, city.gold + city.silver);

            total += tmp;
            totalG += Math.min(tmp,city.gold);
            totalS += Math.min(tmp,city.silver);
        }
        return total >= g+s && totalG >= g && totalS >= s;
    }

    void init(int[] g, int[] s, int[] w, int[] t){
        this.cityArray = new City[g.length];

        for(int i=0; i<g.length;i++){
            this.cityArray[i] = new City(g[i], s[i], w[i], t[i]);
        }
    };

    static class City{
        int gold,silver,weight,time;
        City(int g, int s, int w, int t){
            this.gold = g;
            this.silver = s;
            this.weight = w;
            this.time = t;
        }
    }
}
