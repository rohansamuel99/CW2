package CW2;

import java.util.*;

public class Trie
{
    TrieNode root = new TrieNode();

    //adds a key to trie and retruns true if the addition was successful i.e. returns false if key already exist in the Trie
    public boolean add(String key)
    {
        TrieNode rootTemp = root;
        for(int i = 0; i < key.length(); i++)
        {
            TrieNode nextNode = rootTemp.getOffSpring(key.charAt(i));
            if (nextNode == null)
            {
                nextNode = TrieNode.makeNode(key.charAt(i));
                rootTemp.addNode(nextNode);
            }
            rootTemp = nextNode;
        }
        rootTemp.isEnd = true;
    }

    public boolean contains(String key)
    {
        boolean keyFound;
        TrieNode rootTemp = root;
        if (rootTemp != null && rootTemp.isEnd )
            return true;
        for(int i  = 0; i < key.length(); i++)
        {
            TrieNode nodeNext = rootTemp.getOffSpring((key.charAt(i)));
            if (nodeNext == null)
                return keyFound = false;
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
        StringBuilder buildString = new StringBuilder(characterArrayList.size());
        for ( Character characterBFS : characterArrayList)
            buildString.append(characterBFS);
        return buildString.toString();
    }

    public String outputDepthFirstSearch()
    {
        return null;
    }

    public Trie getSubTrie (String prefix)
    {
        return null;
    }

    public List getAllWords()
    {
        return null;
    }

}
