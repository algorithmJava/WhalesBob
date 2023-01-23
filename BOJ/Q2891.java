import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2981 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = makeArray(br,n);
        Arrays.sort(numbers);
        List<Integer> divideList = new ArrayList<>();

        for(int divide = 2; divide <= numbers[0]; divide++){
            int mod = numbers[0] % divide, index = 1;
            for(; index < numbers.length; index++ ){
                if(numbers[index] % divide != mod){
                    break;
                }
            }
            if(index == numbers.length){
                divideList.add(divide);
            }
        }

        int diff = numbers[1] - numbers[0];
        Set<Integer> divideSet = new HashSet<>();
        for(int i = 1; i <= Math.sqrt(diff); i++){
            if(diff % i == 0){
                divideSet.add(i);
                divideSet.add(diff/i);
            }
        }
        divideSet.remove(1);

        for(Integer div : divideSet){
            int index = 2;
            for(; index < numbers.length; index++){
                if(numbers[index] % div != numbers[0]){
                    break;
                }
            }
            if(index == numbers.length && !divideList.contains(div)){
                divideList.add(div);
            }
        }
        Collections.sort(divideList);
        StringBuilder builder = new StringBuilder();
        for(int i = 0 ; i < divideList.size(); i++){
            builder.append(divideList.get(i));
            if(i < divideList.size()-1){
                builder.append(" ");
            }
        }
        System.out.println(builder);
    }
    static int[] makeArray(BufferedReader br, int size) throws IOException{
        int[] array = new int[size];
        for(int i = 0; i < size; i++){
            array[i] = Integer.parseInt(br.readLine());
        }
        return array;
    }
}
