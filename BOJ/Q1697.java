import java.util.ArrayDeque;
import java.util.Scanner;

public class Q1697 {
    static int[] array;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        array = new int[100001];
        array[N] = 1;
        System.out.println(findMinimumApproach(N,K)-1);
    }
    static void initialSetting(int N, int K){
        if(N == 0){
            array = new int[K+1];
        }else{
            int maxN = N;
            while(maxN <= K){
                maxN *= 2;
            }
            array = new int[maxN + 2];
        }

        array[N] = 1;
    }
    static int findMinimumApproach(int start,int destination){

        if(destination < start){
            return start - destination + 1;
        }

        ArrayDeque<Integer> indexQueue = new ArrayDeque<>();
        indexQueue.addLast(start);
        while(array[destination] == 0 || indexQueue.isEmpty()){

            int index = indexQueue.removeFirst();
            if(index * 2 < array.length && array[index * 2] == 0){
                array[index * 2] = array[index] + 1;
                indexQueue.addLast(index * 2);
            }

            if(index + 1 < array.length && array[index + 1] == 0){
                array[index + 1] = array[index] + 1;
                indexQueue.addLast(index + 1);
            }

            if(index - 1 >= 0 && array[index - 1] == 0){
                array[index - 1] = array[index] + 1;
                indexQueue.addLast(index - 1);
            }
        }
        return array[destination];
    }
}
