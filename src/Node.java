// import java.util.Queue;
// import java.util.PriorityQueue;

public class Node implements Comparable<Node>{
    private String parent;
    private String word;
    private float cost;

    public Node(String parent, String word, float cost){
        this.parent = parent;
        this.word = word;
        this.cost = cost;
    }

    public String getParent(){
        return this.parent;
    }

    public String getWord(){
        return this.word;
    }

    public float getCost(){
        return this.cost;
    }

    public void setParent(String parent){
        this.parent = parent;
    }

    public void setWord(String word){
        this.word = word;
    }

    public void setCost(float cost){
        this.cost = cost;
    }

    public int compareTo(Node node){
        if (this.cost < node.cost){
            return -1;
        } else if (this.cost > node.cost){
            return 1;
        } else {
            return 0;
        }
    }

    public void printNode(){
        System.out.print(this.parent + " " + this.word + " " + this.cost);
    }

    // public static void main(String[] args) {
    //     PriorityQueue<Node> queue = new PriorityQueue<>();
    //     Node node1 = new Node("parent1", "word1", 5);
    //     Node node2 = new Node("parent2", "word2", 1);
    //     Node node3 = new Node("parent3", "word3", 3);
    //     Node node4 = new Node("parent4", "word4", 1);
    //     queue.offer(node1);
    //     queue.offer(node2);
    //     queue.offer(node3);
    //     queue.offer(node4);

    //     while (!queue.isEmpty()){
    //         Node node = queue.poll();
    //         System.out.println(node.getWord());
    //     }
    // }
}