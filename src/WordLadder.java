import java.util.List;
import java.util.Queue;
// import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
// import java.util.Iterator;
import java.lang.Math;

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
    
    public String getStartWord(){
        return this.startWord;
    }
    
    public String getEndWord(){
        return this.endWord;
    }
    
    public String getAlgorithm(){
        return this.algorithm;
    }

    public Map<String, List<String>> getWordList(){
        return this.wordList;
    }

    public void setStartWord(String startWord){
        this.startWord = startWord;
    }

    public void setEndWord(String endWord){
        this.endWord = endWord;
    }

    public void setAlgorithm(String algorithm){
        this.algorithm = algorithm;
    }

    public void setWordList(Map<String, List<String>> wordList){
        this.wordList = wordList;
    }

    public void findPath(){
        if (this.algorithm.equals("1")|| this.algorithm.equals("UCS") || this.algorithm.equals("Uniform Cost Search")
            || (this.algorithm.equals("4")) || (this.algorithm.equals("Ketiganya"))){
            System.out.println("Mencari dengan algoritma UCS");
            findPathUCS();
            System.out.println();
        }
        
        if (this.algorithm.equals("2") || this.algorithm.equals("GBFS") || this.algorithm.equals("Greedy Best First Search")
            || (this.algorithm.equals("4")) || (this.algorithm.equals("Ketiganya"))){
            System.out.println("Mencari dengan algoritma GBFS");
            findPathGBFS();
            System.out.println();
        }
        
        if (this.algorithm.equals("3") || this.algorithm.equals("A*") || this.algorithm.equals("A* Search")
            || (this.algorithm.equals("4")) || (this.algorithm.equals("Ketiganya"))){
            System.out.println("Mencari dengan algoritma A*");
            findPathAStar();
            System.out.println();
        }
    }

    public void findPathUCS(){
        Queue<Node> queue = new LinkedList<>();
        Node startNode = new Node(null, startWord, 0);
        queue.offer(startNode);
        List<Node> path = new ArrayList<>();
        List<String> visited = new ArrayList<>();
        int nodeVisited = 0;
        
        long startTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long before = runtime.totalMemory() - runtime.freeMemory();

        while (!queue.isEmpty()){
            // mengambil node dari queue paling depan
            Node node = queue.poll();
            nodeVisited += 1;
            
            // menambahkan node ke path dan visited
            path.add(node);
            visited.add(node.getWord());
            
            if (node.getWord().equals(this.endWord)){
                long endTime = System.nanoTime();

                List<String> shortestPath = findShortestPath(path);

                for (int j = 0; j < shortestPath.size(); j++){
                    if (j != 0){
                        System.out.print(" -> ");
                    }
                    System.out.print(shortestPath.get(j));
                }

                long after = runtime.totalMemory() - runtime.freeMemory();
                
                System.out.println();
                System.out.println("Langkah         : " + (shortestPath.size() - 1));
                System.out.println("Kata dikunjungi : " + nodeVisited);
                System.out.println("Waktu pencarian : " + (endTime - startTime) / 1000 + " microdetik");
                System.out.println("Memori          : " + Math.abs(after - before) + " bytes");
                return;
            }
            
            // mengambil daftar tetangga node
            List<String> neighbours = wordList.get(node.getWord());
        
            // memeriksa seluruh tetangga
            for (int i = 0; i < neighbours.size(); i++){
                // menghitung cost
                float cost = getUCSCost(neighbours.get(i));
                
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
                    }
                }

                for (int j = 0; j < path.size(); j++){
                    if ((path.get(j).getWord().equals(neighbours.get(i))) && (cost < path.get(j).getCost())){
                        path.get(j).setParent(node.getWord());
                        path.get(j).setCost(cost);
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
        
        System.out.println("Path not found!");
        long endTime = System.nanoTime();
        long after = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Waktu pencarian : " + (endTime - startTime) / 1000 + " microdetik");
        System.out.println("Memori          : " + Math.abs(after - before) + " bytes");
    }

    public void findPathGBFS(){
        Queue<Node> queue = new LinkedList<>();
        Node startNode = new Node(null, startWord, 0);
        queue.offer(startNode);
        List<Node> path = new ArrayList<>();
        List<String> visited = new ArrayList<>();
        int nodeVisited = 0;
        
        long startTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long before = runtime.totalMemory() - runtime.freeMemory();

        while (!queue.isEmpty()){
            // mengambil node dari queue paling depan
            Node node = queue.poll();
            nodeVisited += 1;
            
            // menambahkan node ke path dan visited
            path.add(node);
            visited.add(node.getWord());
            
            if (node.getWord().equals(this.endWord)){
                long endTime = System.nanoTime();

                List<String> shortestPath = findShortestPath(path);

                for (int j = 0; j < shortestPath.size(); j++){
                    if (j != 0){
                        System.out.print(" -> ");
                    }
                    System.out.print(shortestPath.get(j));
                }

                long after = runtime.totalMemory() - runtime.freeMemory();
                
                System.out.println();
                System.out.println("Langkah         : " + (shortestPath.size() - 1));
                System.out.println("Kata dikunjungi : " + nodeVisited);
                System.out.println("Waktu pencarian : " + (endTime - startTime) / 1000 + " microdetik");
                System.out.println("Memori          : " + Math.abs(after - before) + " bytes");
                return;
            }
            
            // mengambil daftar tetangga node
            List<String> neighbours = wordList.get(node.getWord());
        
            // memeriksa seluruh tetangga
            for (int i = 0; i < neighbours.size(); i++){
                // menghitung cost
                float cost = getGBFSCost(neighbours.get(i));
                
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
                    }
                }

                for (int j = 0; j < path.size(); j++){
                    if ((path.get(j).getWord().equals(neighbours.get(i))) && (cost < path.get(j).getCost())){
                        path.get(j).setParent(node.getWord());
                        path.get(j).setCost(cost);
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
        
        System.out.println("Path not found!");
        long endTime = System.nanoTime();
        long after = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Waktu pencarian : " + (endTime - startTime) / 1000 + " microdetik");
        System.out.println("Memori          : " + Math.abs(after - before) + " bytes");
    }

    public void findPathAStar(){
        Queue<Node> queue = new LinkedList<>();
        Node startNode = new Node(null, startWord, 0);
        queue.offer(startNode);
        List<Node> path = new ArrayList<>();
        List<String> visited = new ArrayList<>();
        int nodeVisited = 0;
        
        long startTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long before = runtime.totalMemory() - runtime.freeMemory();

        while (!queue.isEmpty()){
            // mengambil node dari queue paling depan
            Node node = queue.poll();
            nodeVisited += 1;
            
            // menambahkan node ke path dan visited
            path.add(node);
            visited.add(node.getWord());
            
            if (node.getWord().equals(this.endWord)){
                long endTime = System.nanoTime();

                List<String> shortestPath = findShortestPath(path);

                for (int j = 0; j < shortestPath.size(); j++){
                    if (j != 0){
                        System.out.print(" -> ");
                    }
                    System.out.print(shortestPath.get(j));
                }

                long after = runtime.totalMemory() - runtime.freeMemory();
                
                System.out.println();
                System.out.println("Langkah         : " + (shortestPath.size() - 1));
                System.out.println("Kata dikunjungi : " + nodeVisited);
                System.out.println("Waktu pencarian : " + (endTime - startTime) / 1000 + " microdetik");
                System.out.println("Memori          : " + Math.abs(after - before) + " bytes");
                return;
            }
            
            // mengambil daftar tetangga node
            List<String> neighbours = wordList.get(node.getWord());
        
            // memeriksa seluruh tetangga
            for (int i = 0; i < neighbours.size(); i++){
                // menghitung cost
                float cost = getAStarCost(neighbours.get(i));
                
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
                    }
                }

                for (int j = 0; j < path.size(); j++){
                    if ((path.get(j).getWord().equals(neighbours.get(i))) && (cost < path.get(j).getCost())){
                        path.get(j).setParent(node.getWord());
                        path.get(j).setCost(cost);
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
        
        System.out.println("Path not found!");
        long endTime = System.nanoTime();
        long after = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Waktu pencarian : " + (endTime - startTime) / 1000 + " microdetik");
        System.out.println("Memori          : " + Math.abs(after - before) + " bytes");
    }
    
    public float getUCSCost(String nextWord){
        float g = 0;

        for (int i = 0; i < startWord.length(); i++){
            if (startWord.charAt(i) != nextWord.charAt(i)){
                g++;
            }
        }

        return g;
    }

    public float getGBFSCost(String nextWord){
        float h = 0;
        
        for (int i = 0; i < endWord.length(); i++){
            if (endWord.charAt(i) != nextWord.charAt(i)){
                h++;
            }
        }
        
        return h;
    }
    
    public float getAStarCost(String nextWord){
        float g = getUCSCost(nextWord);
        
        float h = 0;

        for (int i = 0; i < endWord.length(); i++){
            if (endWord.charAt(i) != nextWord.charAt(i)){
                h++;
            }
            // if ((endWord.charAt(i) == nextWord.charAt(i)) && (startWord.charAt(i) != nextWord.charAt(i))){
            //     h -= 1;
            // }
        }

        return (g + h);
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
}