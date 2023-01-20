import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q7576 {
    static List<List<Integer>> alreadyDo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        alreadyDo = new ArrayList<>();

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] matrix = makeMatrix(br,M,N);
        System.out.println(bfs(matrix));
    }
    static int[][] makeMatrix(BufferedReader br, int wide, int height) throws IOException{
        int[][] matrix = new int[height][wide];
        for(int y = 0; y < height; y++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int x = 0; x < wide; x++){
                matrix[y][x] = Integer.parseInt(st.nextToken());
                if(matrix[y][x] == 1){
                    alreadyDo.add(List.of(x,y));
                }
            }
        }
        return matrix;
    }
    static int bfs(int[][] matrix){
        List<List<Integer>> directions = List.of(List.of(-1,0),List.of(0,1),List.of(1,0),List.of(0,-1));
        Queue<List<Integer>> queue = new LinkedList<>(alreadyDo);

        while(!queue.isEmpty()){
            List<Integer> point = queue.remove();

            for(int i = 0; i < 4; i++){
                int x = point.get(0), y = point.get(1);
                int moveX = x + directions.get(i).get(0);
                int moveY = y + directions.get(i).get(1);
                if(isInBound(moveX,moveY,matrix) && matrix[moveY][moveX] == 0){
                    matrix[moveY][moveX] = matrix[y][x] + 1;
                    queue.add(List.of(moveX,moveY));
                }
            }
        }

        return getMaxValue(matrix);
    }
    static boolean isInBound(int x, int y, int[][] matrix){
        return (0 <= y && y < matrix.length) && (0 <= x && x < matrix[0].length);
    }

    static int getMaxValue(int[][] matrix){
        int max = -1;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    return -1;
                }
                if(matrix[i][j] > max){
                    max = matrix[i][j];
                }
            }
        }
        return max-1;
    }
}
