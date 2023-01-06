import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q2667 {
    static int count;
    static List<List<Integer>> direction = List.of(List.of(0,-1),
                                                    List.of(1,0),
                                                    List.of(0,1),
                                                    List.of(-1,0));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] matrix = makeMatrix(br,n);
        List<Integer> houseList = makeHouseList(matrix);
        Collections.sort(houseList);
        System.out.println(houseList.size());

        for (Integer houseCount : houseList) {
            System.out.println(houseCount);
        }
    }
    static int[][] makeMatrix(BufferedReader br, int size) throws IOException{
        int[][] matrix = new int[size][size];

        for(int i = 0; i < size; i++){
            String[] stringArray = br.readLine().split("");
            for(int j = 0; j < size; j++){
                matrix[i][j] = Integer.parseInt(stringArray[j]);
            }
        }
        return matrix;
    }
    static List<Integer> makeHouseList(int[][] matrix){
        List<Integer> houseList = new ArrayList<>();
        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix.length; x++){
                if(matrix[y][x] == 1){
                    count = 0;
                    dfs(matrix,x,y);
                    houseList.add(count);
                }
            }
        }
        return houseList;
    }
    static void dfs(int[][] matrix, int x, int y){
        count++;
        matrix[y][x] = 0;
        for(int i = 0; i < 4; i++){
            int moveX = x + direction.get(i).get(0);
            int moveY = y + direction.get(i).get(1);
            if(isInBound(matrix.length,moveX,moveY) && matrix[moveY][moveX] == 1 && isCloseHouse(x,moveX,y,moveY)){
                dfs(matrix,moveX,moveY);
            }
        }
    }
    static boolean isCloseHouse(int x1, int x2, int y1, int y2){
        if(x1 == x2){
            return Math.abs(y1-y2) == 1;
        }
        if(y1 == y2){
            return Math.abs(x1-x2) == 1;
        }
        return false;
    }
    static boolean isInBound(int matrixSize, int x, int y){
        return (0 <= x && x < matrixSize) && (0 <= y && y < matrixSize);
    }
}
