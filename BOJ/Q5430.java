import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.NoSuchElementException;

public class Q5430 {
    static boolean frontRear = true;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());

        for(int i = 0; i < cases; i++){
            String function = br.readLine();
            int arraySize = Integer.parseInt(br.readLine());
            ArrayDeque<Integer> deque = getDeque(br);
            try{
                resultOfFunction(function, deque);
            }catch(NoSuchElementException ex){
                System.out.println("error");
            }
            frontRear = true;
        }
    }
    static ArrayDeque<Integer> getDeque(BufferedReader br) throws IOException{
        StringBuilder input = new StringBuilder(br.readLine());
        input.delete(0,1); input.delete(input.length()-1, input.length());

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        String[] getAsArray = input.toString().split(",");
        for (String s : getAsArray) {
            if(s.equals("")){
                return deque;
            }
            deque.addLast(Integer.parseInt(s));
        }
        return deque;
    }
    static void resultOfFunction(String function, ArrayDeque<Integer> deque) throws NoSuchElementException {
        for(int i = 0; i < function.length(); i++){
            if(function.charAt(i) == 'R'){
                frontRear = !frontRear;
            }

            if(function.charAt(i) == 'D'){
                if(frontRear){
                    deque.removeFirst();
                }else{
                    deque.removeLast();
                }
            }
        }
        printInDeque(deque);
    }
    static void printInDeque(ArrayDeque<Integer> deque){
        StringBuilder newBuilder = new StringBuilder();
        newBuilder.append('[');
        while(!deque.isEmpty()){
            if(frontRear){
                newBuilder.append(deque.removeFirst());
            }else{
                newBuilder.append(deque.removeLast());
            }
            if(deque.size() >= 1){
                newBuilder.append(",");
            }
        }
        newBuilder.append(']');
        System.out.println(newBuilder);
    }
}
