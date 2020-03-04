package CW2;

import java.util.*;
import java.io.*;

public class DictionaryMaker
{
    //note to self*-Integer is a wrapper class and helps in converting int into object
    public static void FormDictionary(String fileName, Map<String, Integer> wordOccurences) throws FileNotFoundException {
        //create a hashmap to count the occurences of the each word in the document
        //ArrayList<String> readLines = new ArrayList<String>();

        Scanner openFileCount = new Scanner(new File(fileName));
        int wordCounter = 0;

        while (openFileCount.hasNext()) {
            String word = openFileCount.next();
            Integer wordCount = wordOccurences.get(word);
            if (wordCount != null) {
                wordCount++;
            } else {
                wordCount = 1;
            }
            wordOccurences.put(word, wordCount);
        }
        openFileCount.close();
    }

    public static void main (String[] args) throws FileNotFoundException {
       /*ArrayList<String> readLines = new ArrayList<String>();
        try
        {
            //scanner openFile opens the file and reads through each line
            Scanner openFile = new Scanner(new File("/home/rohan_samuelh/Documents/Year 2/DSA/CW2/TextFiles/lotr.csv"));
            //if the file has a next line then it adds it to the arraylist
            while(openFile.hasNext())
            {
                readLines.add(openFile.next());
            }
            openFile.close();
            System.out.println( "Part 1.1: \n" + readLines);
        }
        //if the file is not found or the location is wrong or any other error it catches the error that is thrown
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("Soz file not found");
        }*/

        //creating a new hashmap
        Map<String, Integer> wordOccurences = new HashMap<String, Integer>();
        FormDictionary("/home/rohan_samuelh/Documents/Year 2/DSA/CW2/TextFiles/lotr.csv", wordOccurences);
        PrintStream printOut = new PrintStream(new File("/home/rohan_samuelh/Documents/Year 2/DSA/CW2/Output.txt"));
        System.out.println(wordOccurences);




    }
}
