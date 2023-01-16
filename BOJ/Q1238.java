import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1238 {
    static int[] distanceFromStart;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[][] distanceMatrix = makeDistanceMatrix(br,N,M);
        takeDijkstraForStart(distanceMatrix,X);
        int max = 0;

        for(int i = 1; i <= N; i++){
            if(i != X){
                int value = distanceFromStart[i] + dijkstraForNode(distanceMatrix,i,X);
                if(value > max){
                    max = value;
                }
            }
        }
        System.out.println(max);
    }
    static int[][] makeDistanceMatrix(BufferedReader br, int size, int count) throws IOException{
        int[][] matrix = new int[size+1][size+1];
        setInitialMatrix(matrix);

        for(int i = 0; i < count; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            matrix[start][dest] = distance;
        }
        return matrix;
    }
    static void setInitialMatrix(int[][] matrix){
        for(int i = 1; i < matrix.length; i++){
            for(int j = 1; j < matrix[0].length; j++){
                matrix[i][j] = (i == j) ? 0 : 100001;
            }
        }
    }
    static void takeDijkstraForStart(int[][] distanceMatrix, int startNode){
        // 초기에는 시작 지점을 잡아 준다.
        boolean[] visited = new boolean[distanceMatrix.length];
        distanceFromStart = takeInitialDistanceSet(startNode,distanceMatrix.length);

        int lookVertex = startNode;
        for(int i = 1; i < distanceMatrix.length; i++){
            visited[lookVertex] = true;
            // 처음부터 바로 가는 것과, lookVertex 를 들르고 가는 것 중 어느 것이 더 짧은지 비교하여
            // 더 짧으면 업데이트해준다.
            for(int j = 1; j < distanceMatrix.length; j++){
                if(!visited[j]){
                    distanceFromStart[j] =
                            Math.min(distanceFromStart[j], distanceFromStart[lookVertex] + distanceMatrix[lookVertex][j] );
                }
            }
            lookVertex = updateIndex(i,distanceFromStart,visited);
        }
    }
    static int dijkstraForNode(int[][] distanceMatrix,int start, int dest){
        boolean[] visited = new boolean[distanceMatrix.length];
        int[] distanceFromNode = takeInitialDistanceSet(start, distanceMatrix.length);

        int lookVertex = start;
        for(int i = 1; i < distanceFromNode.length; i++){
            visited[lookVertex] = true;
            if(lookVertex == dest){
                return distanceFromNode[lookVertex];
            }
            for(int j = 1; j < distanceMatrix.length; j++){
                if(!visited[j]){
                    distanceFromNode[j] = Math.min(distanceFromNode[j], distanceFromNode[lookVertex] + distanceMatrix[lookVertex][j]);
                }
            }
            lookVertex = updateIndex(i,distanceFromNode,visited);
        }
        return -1;
    }
    static int takeMinimumIndex(int[] array, boolean[] visited){
        int minimum = Integer.MAX_VALUE, index = -1;
        for(int i = 1; i < array.length; i++){
            if(!visited[i] && array[i] < minimum){
                minimum = array[i];
                index = i;
            }
        }
        return index;
    }
    static int[] takeInitialDistanceSet(int start, int distanceLength){
        int[] array = new int[distanceLength];
        for(int i = 1; i < array.length; i++){
            array[i] = (i == start) ? 0 : 100001;
        }
        return array;
    }
    static int updateIndex(int index,int[] distanceFromStart, boolean[] visited){
        int lookVertex = -1;
        if(index != distanceFromStart.length -1){
            lookVertex = takeMinimumIndex(distanceFromStart, visited);
            if(lookVertex == -1){
                throw new IllegalArgumentException("중간에 길이 끊어지지는 않았나요?");
            }
        }
        return lookVertex;
    }
}
