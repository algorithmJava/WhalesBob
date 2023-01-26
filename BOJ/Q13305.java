import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Integer> distanceBetweenCity = makeList(br);
        List<Integer> cityFuelCost = makeList(br);

        System.out.println(minimumCost(distanceBetweenCity,cityFuelCost));
    }
    static List<Integer> makeList(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        List<Integer> list = new ArrayList<>();
        while(st.hasMoreTokens()){
            list.add(Integer.parseInt(st.nextToken()));
        }
        return list;
    }
    static long minimumCost(List<Integer> distance, List<Integer> cost){
        Stack<Integer> stack = new Stack<>();
        long total = 0;

        for(int i = 0; i < cost.size() -1; i++){
            if(stack.isEmpty()){
                stack.push(cost.get(i));
            }else{
                if(stack.peek() > cost.get(i)){
                    stack.push(cost.get(i));
                }
            }

            total += ((long) stack.peek() * distance.get(i));
        }
        return total;
    }
}
