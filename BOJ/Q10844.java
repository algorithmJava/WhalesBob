import java.util.Scanner;
import java.util.stream.IntStream;

public class Q10844 {
    static final int MAX = 1_000_000_000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] numberMatrix = new int[n][10];
        System.out.println(getNumberCount(numberMatrix,n) % MAX);
    }
    static long getNumberCount(int[][] numberMatrix, int n){

        numberMatrix[0] = IntStream.iterate(1,x->x).limit(10).toArray();
        numberMatrix[0][0] = 0;

        for(int i = 1; i < n; i++){
            for(int j = 0; j < 10; j++){
                if(numberMatrix[i-1][j] != 0){
                    if(j == 0){
                        numberMatrix[i][j+1] = (numberMatrix[i][j+1] + numberMatrix[i-1][j]) % MAX;
                    }else if(j == 9){
                        numberMatrix[i][j-1] = (numberMatrix[i][j-1] + numberMatrix[i-1][j]) % MAX;
                    }else{
                        numberMatrix[i][j-1] = (numberMatrix[i][j-1] + numberMatrix[i-1][j]) % MAX;
                        numberMatrix[i][j+1] = (numberMatrix[i][j+1] + numberMatrix[i-1][j]) % MAX;
                    }
                }
            }
        }
        return sum(numberMatrix[n-1]);
    }
    static long sum(int[] array){
        long sum = 0;
        for (int n : array) {
            sum += n;
        }
        return sum;
    }
}
