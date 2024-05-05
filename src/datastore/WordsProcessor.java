package datastore;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Map;
import java.util.HashMap;

public class WordsProcessor {
    private List<List<String>> listOfWords;
    private String fileName;

    public WordsProcessor(String fileName){
        this.listOfWords = new ArrayList<>();
        this.fileName = fileName;
    }

    public String getFileName(){
        return this.fileName;
    }

    public List<List<String>> getListOfWords(){
        return this.listOfWords;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public void setListOfWords(List<List<String>> listOfWords){
        this.listOfWords = listOfWords;
    }

    public void createListOfWords() {
        String word;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((word = reader.readLine()) != null) {
                increaseList(word.length());
                listOfWords.get(word.length() - 1).add(word);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void increaseList(int wordLength){
        if (wordLength > listOfWords.size()){
            for (int i = listOfWords.size(); i < wordLength; i++){
                listOfWords.add(new ArrayList<>());
            }
        }
    }

    public Map<String, List<String>> createMapOfWords(){
        Map<String, List<String>> mapOfWords = new HashMap<>();
        for (int i = 0; i < listOfWords.size(); i++){
            for (String word : listOfWords.get(i)){
                List<String> differenceOne = new ArrayList<String>();
                for (String word2 : listOfWords.get(i)){
                    int count = 0;
                    for (int j = 0; j < word.length(); j++){
                        if (word.charAt(j) != word2.charAt(j))
                            count++;
                    }
                    if (count == 1)
                        differenceOne.add(word2);
                }
                mapOfWords.put(word, differenceOne);
            }
        }
        return mapOfWords;
    }

    public void saveListOfWords(Map<String, List<String>> mapOfWords){
        for (int i = 0; i < listOfWords.size(); i++){
            String fileName = "length_" + (i + 1) + ".txt";
            try {
                File file = new File(fileName);
                if (!file.exists()){
                    file.createNewFile();
                }

                FileWriter writer = new FileWriter(fileName);
                for (String word : listOfWords.get(i)){
                    writer.write(word + "\n");
                    writer.write(mapOfWords.get(word).size() + "\n");
                    for (String Diff : mapOfWords.get(word)){
                        writer.write(Diff + "\n");
                    }
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args){
        WordsProcessor WP = new WordsProcessor("words_alpha.txt");
        WP.createListOfWords();
        // List<List<String>> listOfWords = WP.getListOfWords();
        Map<String, List<String>> mapOfWords = WP.createMapOfWords();
        WP.saveListOfWords(mapOfWords);
        // for (int i = 0; i < listOfWords.size(); i++){
        //     System.out.println("Words with " + (i + 1) + " characters: " + listOfWords.get(i).size());
        // }

        // System.out.println("Word with 31 character: " + listOfWords.get(30).get(0));
    }
}