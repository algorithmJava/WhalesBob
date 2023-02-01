import java.io.*;

public class Q5639 {
    static Node headNode;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        if(input != null){
            headNode = new Node(Integer.parseInt(input));
            input = br.readLine();
        }
        while(input != null){
            int data = Integer.parseInt(input);
            headNode.findAndInsert(data);
            input = br.readLine();
        }

        headNode.postOrder();
        bw.flush();
        bw.close();
    }
    static class Node{
        int data;
        Node leftChild;
        Node rightChild;

        public Node(int data) {
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }

        void findAndInsert(int data){
            if(data < this.data){
                if(this.leftChild != null){
                    this.leftChild.findAndInsert(data);
                }else{
                    this.leftChild = new Node(data);
                }
            }else{
                if(this.rightChild != null){
                    this.rightChild.findAndInsert(data);
                }else{
                    this.rightChild = new Node(data);
                }
            }
        }

        void postOrder() throws IOException{
            if(this.leftChild != null){
                this.leftChild.postOrder();
            }
            if(this.rightChild != null){
                this.rightChild.postOrder();
            }
            if(this.data == headNode.data){
                bw.write(Integer.toString(this.data));
            }else{
                bw.write(this.data + "\n");
            }
        }
    }
}
