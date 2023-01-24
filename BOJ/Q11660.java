import java.io.*;
import java.util.StringTokenizer;

public class Q11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] matrix = makeMatrix(br,n);
        int[][] sumMatrix = makeSumMatrix(matrix);

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine()," ");
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            bw.write(summation(sumMatrix,startX,startY,endX,endY) + "\n");
        }
        bw.flush();
        bw.close();
    }
    static int[][] makeMatrix(BufferedReader br, int size) throws IOException{
        int[][] matrix = new int[size+1][size+1];
        for(int i = 1; i <= size; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int j = 1; j <= size; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return matrix;
    }
    static int[][] makeSumMatrix(int[][] matrix){
        int[][] sumMatrix = new int[matrix.length][matrix[0].length];
        sumMatrix[1][1] = matrix[1][1];
        for(int x = 2; x < matrix[0].length; x++ ){
            sumMatrix[1][x] = sumMatrix[1][x-1] + matrix[1][x];
        }
        for(int y = 2; y < matrix.length; y++){
            for(int x = 1; x < matrix[0].length; x++){
                sumMatrix[y][x] = sumMatrix[y-1][x] + sumMatrix[y][x-1] - sumMatrix[y-1][x-1] + matrix[y][x];
            }
        }
        return sumMatrix;
    }
    static int summation(int[][] sumMatrix, int startX, int startY, int endX, int endY){
        return sumMatrix[endX][endY] - sumMatrix[endX][startY-1]
                - sumMatrix[startX-1][endY] + sumMatrix[startX-1][startY-1];
    }
}
