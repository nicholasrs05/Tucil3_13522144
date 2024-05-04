import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;

public class WordLadder {
    private String startWord;
    private String endWord;
    private String algorithm;
    private Map<String, List<String>> wordList;

    public WordLadder(String startWord, String endWord, String algorithm, Map<String, List<String>> wordList) {
        this.startWord = startWord;
        this.endWord = endWord;
        this.algorithm = algorithm;
        this.wordList = wordList;
    }

    public void findPath(){
        if (this.algorithm.equals("1")|| this.algorithm.equals("UCS") || this.algorithm.equals("Uniform Cost Search")){
            findPathUCS();
        } else if (this.algorithm.equals("2") || this.algorithm.equals("GBFS") || this.algorithm.equals("Greedy Best First Search")){
            findPathGBFS();
        } else if (this.algorithm.equals("3") || this.algorithm.equals("A*") || this.algorithm.equals("A* Search")){
            findPathAStar();
        }
    }
    
    public void findPathUCS(){
        Queue<Node> queue = new LinkedList<>();
        Node startNode = new Node(null, startWord, 0);
        queue.offer(startNode);
        List<Node> path = new ArrayList<>();
        List<String> visited = new ArrayList<>();
        int nodeVisited = 0;
        
        long startTime = System.currentTimeMillis();

        while (!queue.isEmpty()){
            // mengambil node dari queue paling depan
            Node node = queue.poll();
            nodeVisited += 1;
            
            // menambahkan node ke path dan visited
            path.add(node);
            visited.add(node.getWord());
            
            // mengambil daftar tetangga node
            List<String> neighbours = wordList.get(node.getWord());
            
            // memeriksa seluruh tetangga
            for (int i = 0; i < neighbours.size(); i++){
                if (neighbours.get(i).equals(endWord)){
                    // // iterate queue
                    // Iterator<Node> iterator = queue.iterator();
        
                    // // konten queue:
                    // while (iterator.hasNext()){
                    //     iterator.next().printNode();
                    //     System.out.print(" | ");
                    // }
                    // System.out.println();

                    System.out.println("Path found!");
                    path.add(new Node(node.getWord(), endWord, 0));
                    long endTime = System.currentTimeMillis();

                    // rekonstruksi path terpendek
                    List<String> shortestPath = findShortestPath(path);

                    for (int j = 0; j < shortestPath.size(); j++){
                        if (j != 0){
                            System.out.print(" -> ");
                        }
                        System.out.print(shortestPath.get(j));
                    }
                    
                    System.out.println("\nLangkah: " + (shortestPath.size() - 1));
                    System.out.println("Kata dikunjungi: " + nodeVisited);
                    System.out.println("Execution time: " + (endTime - startTime) + " ms");
                    return;

                } else {
                    // menghitung cost
                    int cost = getUCSCost(neighbours.get(i));
                    
                    // loop queue untuk memeriksa apakah kata sudah ada pada queue, jika ada bandingkan cost-nya
                    boolean Enqueue = true;
                    for (Node n : queue){
                        if (n.getWord().equals(neighbours.get(i))){
                            if (n.getCost() > cost){ // jika cost lebih kecil, ganti cost
                                queue.remove(n);
                                Enqueue = true;
                            } else { // jika cost lebih besar, tidak enqueue
                                Enqueue = false;
                            }
                            break;
                        }
                    }
                    
                    if (Enqueue && !visited.contains(neighbours.get(i))){
                        Node newNode = new Node(node.getWord(), neighbours.get(i), cost);
                        // queue.offer(newNode);
                        queue = enqueuePrio(queue, newNode);

                        // for (Node n : queue){
                        //     n.printNode();
                        //     System.out.print(" | ");
                        // }
                        // System.out.println();
                    }
                }
            }
        }
        
        System.out.println("Path not found!");
    }

    public void findPathGBFS(){
        Queue<Node> queue = new LinkedList<>();
        Node startNode = new Node(null, startWord, 0);
        queue.offer(startNode);
        List<Node> path = new ArrayList<>();
        List<String> visited = new ArrayList<>();
        int nodeVisited = 0;
        
        long startTime = System.currentTimeMillis();

        while (!queue.isEmpty()){
            // mengambil node dari queue paling depan
            Node node = queue.poll();
            nodeVisited += 1;
            
            // menambahkan node ke path dan visited
            path.add(node);
            visited.add(node.getWord());
            
            // mengambil daftar tetangga node
            List<String> neighbours = wordList.get(node.getWord());
            
            // memeriksa seluruh tetangga
            for (int i = 0; i < neighbours.size(); i++){
                if (neighbours.get(i).equals(endWord)){
                    // // iterate queue
                    // Iterator<Node> iterator = queue.iterator();
        
                    // // konten queue:
                    // while (iterator.hasNext()){
                    //     iterator.next().printNode();
                    //     System.out.print(" | ");
                    // }
                    // System.out.println();

                    System.out.println("Path found!");
                    path.add(new Node(node.getWord(), endWord, 0));
                    long endTime = System.currentTimeMillis();

                    // rekonstruksi path terpendek
                    List<String> shortestPath = findShortestPath(path);

                    for (int j = 0; j < shortestPath.size(); j++){
                        if (j != 0){
                            System.out.print(" -> ");
                        }
                        System.out.print(shortestPath.get(j));
                    }
                    
                    System.out.println("\nLangkah: " + (shortestPath.size() - 1));
                    System.out.println("Kata dikunjungi: " + nodeVisited);
                    System.out.println("Execution time: " + (endTime - startTime) + " ms");
                    return;

                } else {
                    // menghitung cost
                    int cost = getGBFSCost(neighbours.get(i));
                    
                    // loop queue untuk memeriksa apakah kata sudah ada pada queue, jika ada bandingkan cost-nya
                    boolean Enqueue = true;
                    for (Node n : queue){
                        if (n.getWord().equals(neighbours.get(i))){
                            if (n.getCost() > cost){ // jika cost lebih kecil, ganti cost
                                queue.remove(n);
                                Enqueue = true;
                            } else { // jika cost lebih besar, tidak enqueue
                                Enqueue = false;
                            }
                            break;
                        }
                    }
                    
                    if (Enqueue && !visited.contains(neighbours.get(i))){
                        Node newNode = new Node(node.getWord(), neighbours.get(i), cost);
                        // queue.offer(newNode);
                        queue = enqueuePrio(queue, newNode);

                        // for (Node n : queue){
                        //     n.printNode();
                        //     System.out.print(" | ");
                        // }
                        // System.out.println();
                    }
                }
            }
        }
        
        System.out.println("Path not found!");
    }

    public void findPathAStar(){
        Queue<Node> queue = new LinkedList<>();
        Node startNode = new Node(null, startWord, 0);
        queue.offer(startNode);
        List<Node> path = new ArrayList<>();
        List<String> visited = new ArrayList<>();
        int nodeVisited = 0;
        
        long startTime = System.currentTimeMillis();

        while (!queue.isEmpty()){
            // mengambil node dari queue paling depan
            Node node = queue.poll();
            nodeVisited += 1;
            
            // menambahkan node ke path dan visited
            path.add(node);
            visited.add(node.getWord());
            
            // mengambil daftar tetangga node
            List<String> neighbours = wordList.get(node.getWord());
            
            // memeriksa seluruh tetangga
            for (int i = 0; i < neighbours.size(); i++){
                if (neighbours.get(i).equals(endWord)){
                    // // iterate queue
                    // Iterator<Node> iterator = queue.iterator();
        
                    // // konten queue:
                    // while (iterator.hasNext()){
                    //     iterator.next().printNode();
                    //     System.out.print(" | ");
                    // }
                    // System.out.println();

                    System.out.println("Path found!");
                    path.add(new Node(node.getWord(), endWord, 0));
                    long endTime = System.currentTimeMillis();

                    // rekonstruksi path terpendek
                    List<String> shortestPath = findShortestPath(path);

                    for (int j = 0; j < shortestPath.size(); j++){
                        if (j != 0){
                            System.out.print(" -> ");
                        }
                        System.out.print(shortestPath.get(j));
                    }
                    
                    System.out.println("\nLangkah: " + (shortestPath.size() - 1));
                    System.out.println("Kata dikunjungi: " + nodeVisited);
                    System.out.println("Execution time: " + (endTime - startTime) + " ms");
                    return;

                } else {
                    // menghitung cost
                    int cost = getAStarCost(neighbours.get(i));
                    
                    // loop queue untuk memeriksa apakah kata sudah ada pada queue, jika ada bandingkan cost-nya
                    boolean Enqueue = true;
                    for (Node n : queue){
                        if (n.getWord().equals(neighbours.get(i))){
                            if (n.getCost() > cost){ // jika cost lebih kecil, ganti cost
                                queue.remove(n);
                                Enqueue = true;
                            } else { // jika cost lebih besar, tidak enqueue
                                Enqueue = false;
                            }
                            break;
                        }
                    }
                    
                    if (Enqueue && !visited.contains(neighbours.get(i))){
                        Node newNode = new Node(node.getWord(), neighbours.get(i), cost);
                        // queue.offer(newNode);
                        queue = enqueuePrio(queue, newNode);

                        // for (Node n : queue){
                        //     n.printNode();
                        //     System.out.print(" | ");
                        // }
                        // System.out.println();
                    }
                }
            }
        }
        
        System.out.println("Path not found!");
    }
    
    public int getUCSCost(String nextWord){
        int cost = 0;

        for (int i = 0; i < startWord.length(); i++){
            if (startWord.charAt(i) != nextWord.charAt(i))
                cost++;
        }

        return cost;
    }

    public int getGBFSCost(String nextWord){
        int cost = 0;
        
        for (int i = 0; i < endWord.length(); i++){
            if (endWord.charAt(i) != nextWord.charAt(i))
            cost++;
        }
        
        return cost;
    }
    
    public int getAStarCost(String nextWord){
        int cost = 0;

        for (int i = 0; i < startWord.length(); i++){
            if (startWord.charAt(i) != nextWord.charAt(i))
                cost++;
        }

        for (int i = 0; i < endWord.length(); i++){
            if (endWord.charAt(i) != nextWord.charAt(i))
                cost++;
        }

        return cost;
    }

    public List<String> findShortestPath(List<Node> Path){
        String word = Path.get(Path.size() - 1).getWord();
        String parent = Path.get(Path.size() - 1).getParent();
        List<String> shortestPath = new ArrayList<>();
        
        while (parent != null){
            shortestPath.add(0, word);
            for (int i = 0; i < Path.size() - 1; i++){
                if (Path.get(i).getWord().equals(parent)){
                    word = Path.get(i).getWord();
                    parent = Path.get(i).getParent();
                    break;
                }
            }
        }

        if (parent == null){
            shortestPath.add(0, word);
        }

        return shortestPath;
    }

    public Queue<Node> enqueuePrio(Queue<Node> queue, Node node){
        Queue<Node> tempQueue = new LinkedList<>();
        if (queue.isEmpty()){
            tempQueue.offer(node);
        } else {
            boolean inserted = false;
            while (!queue.isEmpty()){
                Node n = queue.poll();
                if (node.compareTo(n) == -1 && !inserted){
                    tempQueue.offer(node);
                    inserted = true;
                }
                tempQueue.offer(n);
            }
            if (!inserted){
                tempQueue.offer(node);
            }
        }
        return tempQueue;
    }

    public String getStartWord(){
        return this.startWord;
    }

    public String getEndWord(){
        return this.endWord;
    }

    public String getAlgorithm(){
        return this.algorithm;
    }
}