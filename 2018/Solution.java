import java.math.*;
import java.util.*;
import java.io.*;
  
public class Solution { 
  

    public static void main(String[] args) throws Exception
    {
        Solution sol = new Solution();
        sol.r_a_EvenDigits();
    }

    void r_a_EvenDigits() throws Exception
    {
        BufferedReader in;
        int test = 0,   // 0 for std input, 1 for local testing
            _k = Integer.valueOf("1");
        if(test > 0) in = new BufferedReader(new InputStreamReader(System.in));
        else in = new BufferedReader(new FileReader(file));
        if(test < 0) {String[] str = in.readLine().split(" ");}

        /**********************************************************/


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

    static String file = "c:/users/lifeiteng/projects/kickstart/in";
}