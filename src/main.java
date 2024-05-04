import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selamat datang di program Word Ladder!");
        System.out.print("Masukkan kata pertama: ");
        String startWord = scanner.nextLine().trim().toLowerCase();
        String endWord = "";
        String algorithm = "";

        boolean valid = true;

        do {
            if (!valid){
                System.out.println("Kata pertama harus memiliki panjang yang sama dengan kata kedua!");
            }
            System.out.print("Masukkan kata kedua: ");
            endWord = scanner.nextLine().trim().toLowerCase();
            valid = checkWords(startWord, endWord);
        } while (!valid);

        int wordLength = startWord.length();
        WordsReader wordsReader = new WordsReader();
        wordsReader.mapWords(wordLength);
        Map<String, List<String>> words = wordsReader.getWords();

        // for (Map.Entry<String, List<String>> entry : words.entrySet()) {
        //     System.out.println("Key = " + entry.getKey());
        //     System.out.println("Value = " + entry.getValue());
        // }

        valid = true;

        System.out.println("Pilih algoritma yang ingin digunakan (boleh angka, nama, atau singkatan): ");
        System.out.println("1. Uniform Cost Search (UCS)");
        System.out.println("2. Greedy Best First Search (GBFS)");
        System.out.println("3. A* Search (A*)");
        System.out.println("4. Ketiganya");

        do {
            if (!valid){
                System.out.println("Pilihan tidak valid!");
            }
            algorithm = scanner.nextLine().trim();
            valid = checkAlgorithm(algorithm);
        } while (!valid);

        WordLadder wordLadder = new WordLadder(startWord, endWord, algorithm, words);
        System.out.println("Kata pertama: " + wordLadder.getStartWord());
        System.out.println("Kata kedua: " + wordLadder.getEndWord());
        System.out.println("Algoritma: " + wordLadder.getAlgorithm());

        System.out.println("Mencari jalur kata...");
        wordLadder.findPathUCS();
        wordLadder.findPathGBFS();
        wordLadder.findPathAStar();

        scanner.close();
    }

    public static boolean checkWords(String startWord, String endWord){
        if (startWord.length() != endWord.length()){
            return false;
        }
        return true;
    }

    public static boolean checkAlgorithm(String algorithm){
        if (algorithm.equals("1") || algorithm.equals("2") || algorithm.equals("3") || algorithm.equals("4")
            || algorithm.equals("UCS") || algorithm.equals("GBFS") || algorithm.equals("A*")
            || algorithm.equals("Uniform Cost Search") || algorithm.equals("Greedy Best First Search") || algorithm.equals("A* Search")
            || algorithm.equals("Ketiganya")){
            return true;
        }
        return false;
    }
}