import java.math.*;
import java.util.*;
import java.io.*;
  
public class Solution { 
  
    static BufferedReader in;
    static String file = "c:/users/rutter/projects/kickstart/in";

    public static void main(String[] args) throws Exception
    {
        int test = 0,   // 0 for local testing, 1 for std input
            _k = Integer.valueOf("1");
        if(test > 0) in = new BufferedReader(new InputStreamReader(System.in));
        else in = new BufferedReader(new FileReader(file));
        if(test < 0) {String[] str = in.readLine().split(" ");}
        Solution sol = new Solution();
        sol.LuckyDip();
    }

    void LuckyDip() throws Exception
    {
        /*
        Task
        Given a number. Either keep adding 1 or subtracting 1 to get an all even digit number
        */  
        int t = Integer.valueOf(in.readLine());
        for(int i = 1; i <= t; i++)
        {
            String[] split = in.readLine().split("\\s+");
            int n = Integer.valueOf(split[0]),
                k = Integer.valueOf(split[1]);
            int[] ary = new int[n];
            split = in.readLine().split("\\s+");
            for(int j = 0; j < n; j++) ary[j] = Integer.valueOf(split[j]);
            System.out.printf("Case #%d: %f\n", i, draw(ary, k));
        }
    }

    double draw(int[] ary, int k) 
    {
        // k is the replace draws
        Arrays.sort(ary);
        int n = ary.length;
        double expectation = 0., sumd = 0.;
        while(k-- >= 0) {
            sumd = 0;
            int lo = 0, hi = n - 1;
            while(lo < hi) {
                int m = (lo + hi) / 2;
                if(ary[m] < expectation) lo = m + 1;
                else hi = m;
            }
            sumd += expectation * lo;
            for(int i = lo; i < n; i++) sumd += 1. * ary[i];
            // for(int i = 0; i < n; i++) {
            //     sumd += Math.max(1. * ary[i], expectation);
            // }
            expectation = sumd / n;
        }
        return expectation;
    }

    void EvenDigits() throws Exception
    {

		/*
		Task
		Given a number. Either keep adding 1 or subtracting 1 to get an all even digit number
		*/  
		int t = Integer.valueOf(in.readLine());
		for(int i = 1; i <= t; i++)
		{
			String num = in.readLine();
			long val = Long.valueOf(num);
			long prev = getP(val), next = getN(val),
			    s1 = val - prev, s2 = next - val;
            // System.out.printf("%d %d\n", prev, next);
			System.out.printf("Case #%d: %d\n", i, Math.min(s1, s2));
		}
    }
    
    static long getN(long v) {
        long[] val = new long[20];
        int idx = 0;
        while(v > 0) {
            val[idx++] = v % 10;
            v /= 10;
        }
        for(int i = idx - 1; i >= 0; i--) {
            if(val[i] % 2 != 0) {
                for(int j = 0; j < i; j++) val[j] = 0;
                while(val[i] % 2 != 0) {
                    val[i]++;
                    val[i + 1] += val[i] / 10;
                    val[i] %= 10;
                    i++;
                    // System.out.println(Arrays.toString(val));
                }
            }
        }
        long res = 0;
        for(int i = 19; i >= 0; i--) res = res * 10 + val[i];
        return res;
    }
    
    static long getP(long v){
        long[] val = new long[20];
        int idx = 0;
        while(v > 0) {
            val[idx++] = v % 10;
            v /= 10;
        }
        // System.out.println(idx);
        // System.out.println(Arrays.toString(val));
        for(int i = idx - 1; i >= 0; i--) {
            if(val[i] % 2 != 0) {
                val[i] -= 1;
                for(int j = 0; j < i; j++) val[j] = 8;
                break;
            }
        }
        long res = 0;
        for(int i = idx - 1; i >= 0; i--) res = res * 10 + val[i];
        return res;
    }

    
}