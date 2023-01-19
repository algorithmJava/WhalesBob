import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[] towerArray = makeArray(br,size);
        int[] solution = makeSolution(towerArray);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < solution.length; i++){
            bw.write(Integer.toString(solution[i]));
            if(i < solution.length -1){
                bw.write(" ");
            }
        }
        bw.flush();
        bw.close();
    }
    static int[] makeArray(BufferedReader br, int size) throws IOException{
        int[] array = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < array.length; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }
        return array;
    }
    static int[] makeSolution(int[] array){
        int[] towerLog = new int[array.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for(int i = 1; i < array.length; i++){
            while(!stack.empty()){
                int compareIndex = stack.pop();
                if(array[i] < array[compareIndex]){
                    towerLog[i] = compareIndex+1;
                    stack.push(compareIndex);
                    break;
                }
            }
            stack.push(i);
        }
        return towerLog;
    }
}
