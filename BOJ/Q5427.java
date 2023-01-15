import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q5427 {
    static int initialX;
    static int initialY;
    static char[][] map;
    static List<List<Integer>> initialFire;
    static List<List<Integer>> direction = List.of(List.of(0,-1),List.of(-1,0),List.of(1,0),List.of(0,1));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());

        for(int i = 0; i < cases; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int wide = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            initialFire = new ArrayList<>();
            map = makeMap(br,wide,height);
            int result = bfs();
            if(result != -1){
                System.out.println(result);
            }else{
                System.out.println("IMPOSSIBLE");
            }
        }
    }
    static char[][] makeMap(BufferedReader br,int wide, int height) throws IOException{
        char[][] map = new char[height][wide];
        for(int y = 0; y < height; y++){
            char[] input = br.readLine().toCharArray();
            for(int x = 0; x < wide; x++){
                map[y][x] = input[x];
                if(input[x] == '@'){
                    initialX = x; initialY = y;
                }
                if(input[x] == '*'){
                    initialFire.add(List.of(x,y));
                }
            }
        }
        return map;
    }
    static int bfs(){
        int count = 0;
        Queue<List<Integer>> personQueue = new LinkedList<>();
        personQueue.add(List.of(initialX, initialY));
        Queue<List<Integer>> fireQueue = new LinkedList<>(initialFire);

        while(!personQueue.isEmpty()){
            setFire(fireQueue);
            int queueSize = personQueue.size();
            count++;
            for(int i = 0; i < queueSize; i++){
                List<Integer> currentXY = personQueue.remove();

                for(int j = 0; j < 4; j++){
                    int moveX = currentXY.get(0) + direction.get(j).get(0);
                    int moveY = currentXY.get(1) + direction.get(j).get(1);
                    if(!isInBound(moveX,moveY)){
                        return count;
                    }
                    if(map[moveY][moveX] == '.'){
                        map[moveY][moveX] = '@';
                        personQueue.add(List.of(moveX,moveY));
                    }
                }
            }
        }
        return -1;
    }
    static void setFire(Queue<List<Integer>> fireQueue){
        int queueSize = fireQueue.size();

        for(int i = 0; i < queueSize; i++){
            List<Integer> fire = fireQueue.remove();
            moveFire(fireQueue, fire.get(0), fire.get(1));
        }
    }
    static void moveFire(Queue<List<Integer>> fireQueue,int x, int y){
        for(int i = 0; i < 4; i++){
            int moveX = x + direction.get(i).get(0);
            int moveY = y + direction.get(i).get(1);
            if(isInBound(moveX,moveY) && map[moveY][moveX] == '.'){
                map[moveY][moveX] = '*';
                fireQueue.add(List.of(moveX, moveY));
            }
        }
    }
    static boolean isInBound(int x, int y){
        return (0 <= x && x < map[0].length) && (0 <= y && y < map.length);
    }
}
