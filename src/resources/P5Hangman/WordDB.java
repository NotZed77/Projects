package resources.P5Hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class WordDB{
    private HashMap<String, String[]> wordList; // Will contain key --> category, value --> words

    private ArrayList<String> categories; // Used to pick random categories (Can't get randomly get index with HashMap)

    public WordDB(){
        try{
            wordList = new HashMap<>();
            categories = new ArrayList<>();

            // Get file path
            String filePath = getClass().getClassLoader().getResource(CommonConstants.DATA_PATH).getPath() ;
            if(filePath.contains("%20")) filePath = filePath.replaceAll("%20"," ");
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // Iterates through each line in the data.txt
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(","); // Splits the data by ","
                String category = parts[0]; // The first word of each line represents the category
                categories.add(category);

                String values[] = Arrays.copyOfRange(parts,1,parts.length); // The rest of the values will be the words relative to the category
                wordList.put(category, values);
            }
        }catch(IOException e){
            System.out.println("Error: " + e );
        }
    }

    public String[] loadChallenge(){
        Random rand = new Random();

        // Generate random number to choose Category
        String category = categories.get(rand.nextInt(categories.size()));

        // Generate random number to choose the value from category
        String[] categoryValues = wordList.get(category);
        String word = categoryValues[rand.nextInt(categoryValues.length)];

        return new String[]{category.toUpperCase(), word.toUpperCase()}; // [0] --> category and [1] --> Word
    }
}
