import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Q2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] array = makeArray(br,n);
        System.out.println(solution(array));
    }
    static int[] makeArray(BufferedReader br, int size) throws IOException{
        int[] array = new int[size+1];
        array[0] = 0;
        for(int i = 1; i <= size; i++){
            array[i] = Integer.parseInt(br.readLine());
        }
        return array;
    }
    static long solution(int[] array){
        long[][] dp = new long[2][array.length];

        dp[0][0] = dp[1][0] = array[0];
        dp[0][1] = dp[1][1] = array[1];
        if(array.length == 2){
            return array[1];
        }

        dp[0][2] = array[1] + array[2];
        dp[1][2] = array[2];

        for(int i = 3; i < array.length; i++){
            if(array[i] == 0){
                dp[0][i] = dp[1][i] = Math.max(dp[0][i-1], dp[1][i-1]);
            }else{
                dp[0][i] = dp[1][i-1] + array[i];
                dp[1][i] = Math.max(dp[0][i-1],getMaximum(List.of(dp[0][i-2],dp[0][i-3],dp[1][i-2])) + array[i]);
            }
        }
        return Math.max(dp[0][array.length-1], dp[1][array.length-1]);
    }
    static long getMaximum(List<Long> list){
        long max = -1;
        for (Long value : list) {
            if (max < value) {
                max = value;
            }
        }
        return max;
    }
}
