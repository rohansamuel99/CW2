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
        PrintWriter printWriter = new PrintWriter(file);
        for(Object w: c)
        {
            printWriter.println(w.toString());
        }
        printWriter.close();
    }

    public void formDictionary() throws Exception
    {
        // reading the words from lotr.csv into the arraylist, wordRead
        wordRead = readWordsFromCSV
            ("C:\\Users\\rohan\\IdeaProjects\\CW2\\src\\TextFiles\\lotr.csv");
        // sort the wordRead alphabetically
        Collections.sort(wordRead);
        // dictionaryFill is a new TreeMap
        dictionaryFill = new TreeMap();

        //for each word in the arraylist, wordRead
        for(String word : wordRead)
        {
            //if dictionaryFill contains the word then add 1 to the frequency of the word
            if(dictionaryFill.containsKey(word))
            {
                dictionaryFill.put(word, dictionaryFill.get(word)+1);
            }
            // else it leaves the frequency at 1
            else
            {
                dictionaryFill.put(word, 1);
            }
        }

        // for each key in the keySet of dictionaryFill, print out the word with its frequency
        for(String key : dictionaryFill.keySet())
            System.out.println(key + " : " + dictionaryFill.get(key));
    }

    public void saveToFile() throws IOException
    {
        try (BufferedWriter writeToFile = new BufferedWriter(new FileWriter
        ("C:\\Users\\rohan\\IdeaProjects\\CW2\\src\\TextFiles\\Output.txt")))
        {
            for (Map.Entry<String, Integer> entry : this.dictionaryFill.entrySet()) {
                System.out.println("Word = " + entry.getKey() + ", Value = " + entry.getValue());
                writeToFile.write(entry.getKey() + " = " +  entry.getValue() + " times, \n");
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        DictionaryFinder df=new DictionaryFinder();
        ArrayList<String> in=readWordsFromCSV
        ("C:\\Users\\rohan\\IdeaProjects\\CW2\\src\\TextFiles\\lotr.csv");
        df.formDictionary();
        df.saveToFile();
    }
}
