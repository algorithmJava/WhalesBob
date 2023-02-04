import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Q1132_2nd {
    static Set<Character> checkSet;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Map<Character, Long> map = makeCharIntMap(br,n);
        int[] numArr = IntStream.iterate(9,x->x-1).limit(map.size()).toArray();
        // 0을 곱하려면, 맨 마지막 숫자여야 한다.
        // 그러면, 맨 마지막 숫자이면서, map 의 크기는 10이어야 하고
        // 0이 아니어야 하는 문자들을 모두 set 에 담아 두자.
        // 만약에, map 의 크기가 10이면서, 마지막 숫자가 0이 아니어야 하면
        // swap 한다. 그렇게 for 문 돌아 보자.
        // swap 은 숫자만 돌린다.
        // 그렇게 돌아간 애와, get 한 애를 곱해서 더하면 되겟다.
        List<Character> listKeySet = new ArrayList<>(map.keySet());
        listKeySet.sort((value1,value2)-> (map.get(value2).compareTo(map.get(value1))));

        for(int i = listKeySet.size()-1; map.size() == 10 && i >= 0; i--){
            if(checkSet.contains(listKeySet.get(i))){
                swap(numArr, i,i-1);
            }else{
                break;
            }
        }

        long sum = 0;

        for(int i = 0; i < numArr.length; i++){
            sum += (map.get(listKeySet.get(i)) * (long)numArr[i]);
        }
        System.out.println(sum);
    }
    static Map<Character, Long> makeCharIntMap(BufferedReader br, int n) throws IOException{
        checkSet = new HashSet<>();
        Map<Character, Long> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            long exp = 1;
            StringBuilder builder = new StringBuilder(br.readLine());
            checkSet.add(builder.charAt(0));
            for(int num = builder.length()-1; num >= 0; num--){
                Character numChar = builder.charAt(num);
                try{
                    long take = map.get(numChar);
                    map.replace(numChar, take + exp);
                }catch(NullPointerException ex){
                    map.put(numChar,exp);
                }
                exp *= 10;
            }
        }

        return map;
    }
    static void swap(int[] arr, int a, int b){
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
}
