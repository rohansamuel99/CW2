package CW2;

import java.io.*;
import java.util.*;

import static CW2.DictionaryFinder.readWordsFromCSV;

public class AutoCompletion
{
    // reads the queries from lotrQueries.csv and uses the new line character as a delimiter.
    public static ArrayList<String> readWordsNewLine(String file) throws FileNotFoundException
    {
        Scanner sc=new Scanner(new File(file));
        sc.useDelimiter("\n");
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

    public static void main(String[] args) throws Exception
    {
        // PrintWriter prints the matches and their probabilities to the specified
        PrintWriter writeToFile= new PrintWriter
        ("C:\\Users\\rohan\\IdeaProjects\\CW2\\src\\TextFiles\\lotrMatches.csv");
        // reading and pushing all the queries from lotrQueries.csv to an arraylist called lotrQueries
        ArrayList<String> lotrQueries = readWordsNewLine
        ("C:\\Users\\rohan\\IdeaProjects\\CW2\\src\\TextFiles\\lotrQueries.csv");
        //System.out.println(lotrQueries);
        // creating a new AutoCompletionTrie called autoTrie
        AutoCompletionTrie autoTrie = new AutoCompletionTrie();
        // for each word in the lotr.csv, add it to autoTrie
        for (String word: readWordsFromCSV
        ("C:\\Users\\rohan\\IdeaProjects\\CW2\\src\\TextFiles\\lotr.csv"))
            autoTrie.add(word);
        //System.out.println(autoTrie.getAllWords());
        //System.out.println(autoTrie.getWordFrequency("yellow"));
        // for each query in the arraylist, lotrQueries
        for (String query: lotrQueries)
        {
            // push the query into another AutoCompletionTrie called newSubTrie
            AutoCompletionTrie newSubTrie = autoTrie.getSubTrie(query);
            // initializing totalFrequencies to 0;
            int totalFrequencies=0;
            // prints out all the words in newSubTrie
            //System.out.println(newSubTrie.getAllWords());
            // pushing all the words in newSubTrie to an arraylist called, wordsToSort
            ArrayList<String> wordsToSort = newSubTrie.getAllWords();
            // for loop that goes through the size of the arraylist, wordsToSort
            for (int i =0; i<wordsToSort.size(); i++)
                // returns the element at the given index after going through wordsToSort
                wordsToSort.set(i, query+wordsToSort.get(i));
            // if the frequency of a query is more than 0, it adds it to the arraylist, wordsToSort
            if(autoTrie.getWordFrequency(query) > 0)
                wordsToSort.add(query);
            // sort the words using a lambda and Integer.compare to compare the frequency of the words and then sort.
            wordsToSort.sort((String word1, String word2)->-Integer.compare(autoTrie.getWordFrequency(word1), (autoTrie.getWordFrequency(word2))));
            //for each newWord in wordsToSort add the frequency of the newWord to the totalFrequencies
            for (String newWord : wordsToSort)
            {
                totalFrequencies += autoTrie.getWordFrequency(newWord);
            }
            // writeToFile.print() is used to write to lotrMatches.csv
            writeToFile.print(query+",");
            // for loop that only prints the top three frequencies or less if the prefix appears less times
            for (int i = 0; i < Math.min(3,wordsToSort.size()); i++)
            {
                writeToFile.print(wordsToSort.get(i)+",");
                // prints the probabilities of the number of time each word is likely to occur
                writeToFile.print((float)autoTrie.getWordFrequency
                        (wordsToSort.get(i))/totalFrequencies+",");
            }
            // makes sure each query and its occurrences and its probabilities
            writeToFile.println();
        }
        writeToFile.close();
    }
}
