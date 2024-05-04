import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordsReader {
    private Map<String, List<String>> words;

    public WordsReader(){
        this.words = new HashMap<>();
    }

    public void mapWords(int wordLength){
        String fileName = "datastore/length_" + wordLength + ".txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String word;
            while ((word = reader.readLine()) != null) {
                List<String> wordList = new ArrayList<>();
                String number = reader.readLine();
                int neighbourCount = Integer.parseInt(number);
                for (int j = 0; j < neighbourCount; j++){
                    String wordDiffOne = reader.readLine();
                    wordList.add(wordDiffOne);
                }
                words.put(word, wordList);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, List<String>> getWords(){
        return this.words;
    }

}
