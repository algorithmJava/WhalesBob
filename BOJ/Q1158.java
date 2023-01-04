import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N 명의 사람이 원을 이루며 앉아 있고, 양의 정수 K 가 주어진다.
        // 순서대로 K번째 사람을 제거한다

        // 한 사람이 제거되면 남은 사람들로 이루어진 원을 따라 과정을 계속한다
        // 이 과정은 N명의 사람이 모두 제거될때까지 계속된다.

        // 원에서 사람들이 제거되는 순서를 N-K 요세푸스 순열이라고 한다.
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        BOJQueue queue = new BOJQueue();
        queue.setQueue(N);

        StringBuilder builder = new StringBuilder(josephusArray(queue,K).toString());
        builder.replace(0,1,"<");
        builder.replace(builder.length()-1, builder.length(),">");
        System.out.println(builder);
    }
    static List<Integer> josephusArray(BOJQueue queue, int k){
        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()){
            list.add(queue.turnJosephus(k));
        }
        return list;
    }
}
class BOJQueue{
    private final LinkedList<Integer> list = new LinkedList<>();

    public void setQueue(Integer N){
        for(int i = 1; i <= N; i++){
            this.enQueue(i);
        }
    }

    public void enQueue(Integer data) {
        list.addLast(data);
    }

    public Integer deQueue(){
        try{
            return list.removeFirst();
        }catch(NoSuchElementException ex){
            return null;
        }
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public int turnJosephus(int turn){
        for(int i = 1; i < turn; i++){
            this.enQueue(this.deQueue());
        }
        return this.deQueue();
    }
}
