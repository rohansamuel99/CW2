package CW2;

import java.io.*;
import java.util.*;

/**
 *
 * @author ajb
 */
public class DictionaryFinder {

    ArrayList<String> wordRead;
    TreeMap<String, Integer> dictionaryFill;

    public DictionaryFinder()
    {
    }

    /**
     * Reads all the words in a comma separated text document into an Array
     * @param
     */

    public static ArrayList<String> readWordsFromCSV(String file) throws FileNotFoundException
    {
        Scanner sc=new Scanner(new File(file));
        sc.useDelimiter(" |,");
        ArrayList<String> words=new ArrayList<>();
        String str;
        while(sc.hasNext())
        {
            str=sc.next();
            str=str.trim();
            str=str.toLowerCase();
            words.add(str);
        }
        return words;
    }

    public static void saveCollectionToFile(Collection<?> c,String file) throws IOException
    {
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for(Object w: c)
        {
            printWriter.println(w.toString());
        }
        printWriter.close();
    }

    public void formDictionary() throws Exception
    {
        /*
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
        */

        wordRead = readWordsFromCSV("/home/rohan_samuelh/Documents/Year 2/DSA/CW2/TextFiles/lotr.csv");
        Collections.sort(wordRead);
        dictionaryFill = new TreeMap();

        for(String word : wordRead)
        {
            if(dictionaryFill.containsKey(word))
            {
                dictionaryFill.put(word, dictionaryFill.get(word)+1);
            }
            else
            {
                dictionaryFill.put(word, 1);
            }
        }

        for(String key : dictionaryFill.keySet())
            System.out.println(key + " : " + dictionaryFill.get(key));
    }

    public void saveToFile() throws IOException
    {
        try (BufferedWriter writeToFile = new BufferedWriter(new FileWriter("/home/rohan_samuelh/Documents/Year 2/DSA/CW2/Output.txt")))
        {
            for (Map.Entry<String, Integer> entry : this.dictionaryFill.entrySet()) {
                System.out.println("Word = " + entry.getKey() + ", Value = " + entry.getValue());
                writeToFile.write(entry.getKey() + " = " +  entry.getValue() + " times, \n");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        DictionaryFinder df=new DictionaryFinder();
        ArrayList<String> in=readWordsFromCSV("/home/rohan_samuelh/Documents/Year 2/DSA/CW2/TextFiles/lotr.csv");
        //DO STUFF TO df HERE in countFrequencies
        df.formDictionary();
        df.saveToFile();



    }

}
