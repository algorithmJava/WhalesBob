import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2573 {
    static Queue<List<Integer>> queue; // x,y 좌표 저장
    static Set<List<Integer>> tempSet;
    static List<List<Integer>> surround = List.of(List.of(-1,0), List.of(0,-1), List.of(1,0), List.of(0,1));
    static int depth;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        queue = new LinkedList<>();
        int[][] matrix = makeMatrix(br,height,width);

        try{
            System.out.println(bfs(matrix));
        }catch (NullPointerException ex){
            System.out.println(0);
        }
    }
    static int[][] makeMatrix(BufferedReader br, int height, int width) throws IOException{
        int[][] matrix = new int[height][width];
        for(int y = 0; y < height; y++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int x = 0; x < width; x++){
                matrix[y][x] = Integer.parseInt(st.nextToken());
                if(matrix[y][x] != 0){
                    queue.add(List.of(x,y));
                }
            }
        }
        return matrix;
    }

    static int bfs(int[][] matrix) throws NullPointerException{
        tempSet = new HashSet<>();
        int count = 0;
        do {
            count++;
            depth = 0;
            int currentQueueSize = queue.size();
            for (int i = 0; i < currentQueueSize; i++) {
                List<Integer> coordinate = queue.remove();
                int x = coordinate.get(0), y = coordinate.get(1);
                melt(matrix, x, y);
                if (matrix[y][x] != 0) {
                    queue.add(List.of(x, y));
                }else{
                    tempSet.add(List.of(x,y));
                }
            }
            int[][] clone = Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);
            dfs(clone, queue.peek());
            tempSet.clear();
        } while (depth == queue.size());
        return count;
    }
    static void melt(int[][] matrix, int x, int y){
        for(int i = 0; i < 4 && matrix[y][x] != 0; i++){
            int surroundX = x + surround.get(i).get(0);
            int surroundY = y + surround.get(i).get(1);
            if(matrix[surroundY][surroundX] == 0 && !tempSet.contains(List.of(surroundX,surroundY))){
                matrix[y][x]--;
            }
        }
    }
    static void dfs(int[][] matrix, List<Integer> coordinate){
        depth++;
        int x = coordinate.get(0), y = coordinate.get(1);
        matrix[y][x] = 0;
        for(int i = 0; i < 4; i++){
            int moveX = coordinate.get(0) + surround.get(i).get(0);
            int moveY = coordinate.get(1) + surround.get(i).get(1);
            if(matrix[moveY][moveX] != 0){
                dfs(matrix, List.of(moveX, moveY));
            }
        }
    }
}
