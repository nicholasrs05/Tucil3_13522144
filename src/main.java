import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean valid = true;
        boolean exists = true;
        String startWord = "";
        String endWord = "";
        String algorithm = "";
        int wordLength = 0;
        Map<String, List<String>> words;
        WordsReader wordsReader;

        System.out.println("Selamat datang di program Word Ladder!\n");

        do {
            if (!exists){
                System.out.println("Kata pertama tidak ditemukan dalam kamus!");
            }

            System.out.print("Masukkan kata pertama: ");
            startWord = scanner.nextLine().trim().toLowerCase();
            wordLength = startWord.length();
            wordsReader = new WordsReader();
            wordsReader.mapWords(wordLength);
            words = wordsReader.getWords();

            exists = false;
            for (Map.Entry<String, List<String>> entry : words.entrySet()){
                if (entry.getKey().equals(startWord)){
                    exists = true;
                    break;
                }
            }
        } while (!exists);
    
        System.out.println();

        do {
            if (!valid){
                System.out.println("Kata kedua harus memiliki panjang yang sama dengan kata pertama!");
                exists = true;
            }

            if (!exists){
                System.out.println("Kata kedua tidak ditemukan dalam kamus!");
            }

            System.out.print("Masukkan kata kedua: ");
            endWord = scanner.nextLine().trim().toLowerCase();
            valid = checkWords(startWord, endWord);

            exists = false;
            for (Map.Entry<String, List<String>> entry : words.entrySet()){
                if (entry.getKey().equals(endWord)){
                    exists = true;
                    break;
                }
            }
        } while ((!valid) || (!exists));
        System.out.println( );

        valid = true;

        System.out.println("Pilih algoritma yang ingin digunakan (boleh angka, nama, atau singkatan): ");
        System.out.println("1. Uniform Cost Search (UCS)");
        System.out.println("2. Greedy Best First Search (GBFS)");
        System.out.println("3. A* Search (A*)");
        System.out.println("4. Ketiganya");
        System.out.println();

        do {
            if (!valid){
                System.out.println("Pilihan tidak valid!");
            }
            System.out.print("Algoritma: ");
            algorithm = scanner.nextLine().trim();
            valid = checkAlgorithm(algorithm);
        } while (!valid);

        WordLadder wordLadder = new WordLadder(startWord, endWord, algorithm, words);

        System.out.println("\nMencari jalur kata...\n");
        wordLadder.findPath();

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