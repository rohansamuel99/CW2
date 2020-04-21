package CW2;

import java.util.*;
import java.lang.*;

public class Trie {
    TrieNode root = new TrieNode();


    //adds a key to trie and returns true if the addition was successful i.e. returns false if key already exist in the Trie
    public boolean add(String key)
    {
        TrieNode rootTemp = root;
        for (int i = 0; i < key.length(); i++)
        {
            // charAt returns the character at the specified index
            TrieNode nextNode = rootTemp.getOffSpring(key.charAt(i));
            if (nextNode == null)
            {
                nextNode = TrieNode.makeNode(key.charAt(i));
                rootTemp.toCharArray(nextNode);
            }
            rootTemp = nextNode;
        }
        rootTemp.isEnd = true;
        return rootTemp.isEnd;
    }


    public boolean contains(String key)
    {
        TrieNode rootTemp = root;
        if (rootTemp != null && rootTemp.isEnd )
            return true;
        for(int i  = 0; i < key.length(); i++)
        {
            TrieNode nodeNext = rootTemp.getOffSpring((key.charAt(i)));
            if (nodeNext == null)
                return false;
            else
            {
                rootTemp = nodeNext;
                return true;
            }
        }
        return true;
    }

    // returns a string representing a breadth first traversal
    public String outputBreadthFirstSearch()
    {
        Queue<TrieNode> queue = new LinkedList<>();
        ArrayList<Character> characterArrayList = new ArrayList<>();
        queue.add(root);
        //while the linkedlist is full, we take the item in front of the queue and add it to the list
        // add items that aren't in the list to the back of the queue.
        while (!queue.isEmpty())
        {
            TrieNode currentNode = queue.remove();
            //check that offSpring has another element
            if (currentNode.offSpring != null)
            {
                for(int i = 0; i < currentNode.offSpring.length; i++)
                {
                    if (currentNode.offSpring[i] != null)
                        queue.add(currentNode.offSpring[i]);
                }
                characterArrayList.add(currentNode.charValueLetter);
            }
        }
        // StringBuilders is like an array of strings.
        // creating a new stringbuilder of the size of characterArrayList
        StringBuilder buildString = new StringBuilder(characterArrayList.size());
        //adding each character in characterArrayList to buildString
        for ( Character characterBFS : characterArrayList)
            buildString.append(characterBFS);
        return buildString.toString();
    }

    //returns a string representing a pre-order depth first traversal
    // make it recursive
    public String outputDepthFirstSearch()
    {
        if (root == null)
            return null;
        // create a stack for DFS i.e. which nodes to visit
        Stack<TrieNode> s = new Stack<TrieNode>();
        Stack<Integer> sInt = new Stack<>();
        String word = "";
        // push the current node to top
        s.push(root);
        TrieNode tempTN = root;
        while (!s.isEmpty())
        {
            tempTN = s.pop();
            if(!sInt.isEmpty())
                word = word + (char)(sInt.pop() + 'a');
            for (int i = tempTN.offSpring.length - 1; i >= 0; i--)
            {
                if (tempTN.offSpring[i] != null)
                {
                    s.push(tempTN.offSpring[i]);
                    sInt.push(i);
                }
            }
        }
        return word;
    }


    // returns a new Trie rooted at the prefix
    public Trie getSubTrie (String prefix)
    {
        //create a new TrieNode
        Trie subTrie = new Trie();
        TrieNode tempTN = root;
        if (" ".equals(prefix) || prefix.isEmpty())
            return null;
        for (int i = 0; i < prefix.length(); i++)
        {
            // same concept as toCharArray
            int subTrieInt = (int)prefix.charAt(i) - 97;
            if(tempTN.offSpring[subTrieInt] == null)
            {
                return null;
            }
            subTrie.root = tempTN.getOffSpring(prefix.charAt(i));
            tempTN = tempTN.offSpring[subTrieInt];
        }
        return subTrie;
        // searches letters in trie if yes creates a new trie
        // assign root to characters of the prefix
    }

    // returns a list containing all words in the Trie
    public List getAllWords()
    {
        List allWords = new ArrayList();
        for (TrieNode getWords : root.offSpring )
        {
            if (getWords != null)
                getAllWords(allWords, getWords.charValueLetter + "", getWords);
        }
        return allWords;
    }

    public void getAllWords(List allWords, String words, TrieNode getWords)
    {
        if(getWords.isEnd)
            allWords.add(words);
        for (TrieNode tempNode : getWords.offSpring)
        {
            if (tempNode != null)
                getAllWords(allWords, words + tempNode.charValueLetter, tempNode);
        }
    }

    public static void main(String[] args) {
        Trie newTrie = new Trie();
        newTrie.add("cheers");
        newTrie.add("cheese");
        newTrie.add("chat");
        newTrie.add("cat");
        newTrie.add("bat");

        System.out.println(newTrie.outputBreadthFirstSearch());
        System.out.println(newTrie.outputDepthFirstSearch());
        System.out.println(newTrie.getSubTrie("ch").getAllWords());
        System.out.println(newTrie.getAllWords());
        // no errors for bfs
        //check errors
    }

}
