import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 3,1,4,3,2 의 경우가 있다고 하자.
        // 1,2,3,4,5 순으로 줄을 선다면, 1번 사람은 3분만에, 2번 사람은 4분만에, 3번 사람은 8분
        // 이렇게 가게 된다.

        // 필요한 시간의 합을 최소로 만드는 방식을 고안해 보자.
        // 그냥 이거 SJT 하면 되는거아니냐고? ㅋㅋㅋㅋㅋㅋㅋ

        int[] times = makeTimeList(br,n);
        Arrays.sort(times);

        int[] realGettingMoneyTime = new int[n];
        int sum = 0;
        for(int i = 0; i < n; i++){
            sum += times[i];
            realGettingMoneyTime[i] = sum;
        }

        sum = 0;
        for(int i = 0; i < n; i++){
            sum += realGettingMoneyTime[i];
        }
        System.out.println(sum);
    }
    static int[] makeTimeList(BufferedReader br, int size) throws IOException{
        int[] timeList = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < size; i++){
            timeList[i] = Integer.parseInt(st.nextToken());
        }
        return timeList;
    }
}
