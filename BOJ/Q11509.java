import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q11509 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(countSet(br,n));
    }
    static int countSet(BufferedReader br, int n) throws IOException{
        List<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < n; i++){
            int input = Integer.parseInt(st.nextToken());
            if(list.contains(input)){
                int index = list.indexOf(input);
                list.set(index, input-1);
            }else{
                list.add(input-1);
            }
        }
        return list.size();
    }
}
