import java.util.Scanner;

public class Q1541 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("-");

        int result = calculatePlusResult(input[0]);
        for(int i = 1; i < input.length; i++){
            result -= calculatePlusResult(input[i]);
        }
        System.out.println(result);
    }
    static int calculatePlusResult(String input){
        String[] realNumberString = input.split("[^0-9]");
        int sum = 0;
        for (String numberString : realNumberString) {
            sum += Integer.parseInt(numberString);
        }
        return sum;
    }
}
