import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1992 {
    static StringBuilder builder;
    static int[][] matrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        matrix = makeMatrix(br,n);
        builder = new StringBuilder();

        recursive(0,0,matrix.length, matrix.length);
        System.out.println(builder);
    }
    static int[][] makeMatrix(BufferedReader br, int size) throws IOException{
        int[][] matrix = new int[size][size];
        for(int i = 0; i < size; i++){
            String[] input = br.readLine().split("");
            for(int j = 0; j < size; j++){
                matrix[i][j] = Integer.parseInt(input[j]);
            }
        }

        return matrix;
    }
    static void recursive(int startX, int startY, int endX, int endY){
        if(isAllSame(startX, endX, startY, endY)){
            builder.append(matrix[startY][startX]);
        }else{
            builder.append("(");
            int half = (endX-startX) / 2;
            recursive(startX, startY, startX + half, startY + half);
            recursive(startX + half, startY, endX, startY + half);
            recursive(startX, startY + half, startX + half, endY);
            recursive(startX + half, startY + half, endX, endY);
            builder.append(")");
        }
    }
    static boolean isAllSame(int startX, int endX, int startY, int endY){
        int value = matrix[startY][startX];
        for(int y = startY; y < endY; y++){
            for(int x = startX; x < endX; x++){
                if(matrix[y][x] != value){
                    return false;
                }
            }
        }
        return true;
    }
}
