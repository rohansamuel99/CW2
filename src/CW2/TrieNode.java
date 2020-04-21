package CW2;

import java.util.*;

public class TrieNode
{
    //the character that is stored inside the node
    public char charValueLetter;
    // variable to check if this is the last character in the string
    public boolean isEnd;
    // variable to check whether the node has been node
    public boolean visitedNode;
    //creating an array of fixed size 26
    public TrieNode[] offSpring;

    public TrieNode()
    {
        // initialize the array of fixed size 26
        this.offSpring = new TrieNode[26];
        //sets both the booleans to false
        this.isEnd = false;
        this.visitedNode = false;
        // iterates through the length of the array and sets all the elements to null
        for(int i = 0; i < offSpring.length; i++)
            offSpring[i] = null;
    }

    public TrieNode(char c)
    {
        charValueLetter = c;
    }

    public static TrieNode makeNode(char cNode)
    {
        //create a new TrieNode
        TrieNode newTrieN = new TrieNode();
        newTrieN.isEnd = false;
        newTrieN.charValueLetter = cNode;

        return newTrieN;
    }

    // getting the offSpring with the specific character
    public TrieNode getOffSpring (char cOffSpring)
    {
        for(int i = 0; i < offSpring.length; i++)
        {
            //make sure its not invalid or null and is equal to the the specific character, then adding and updating each item in the array
            if (offSpring[i] != null && offSpring[i].charValueLetter == cOffSpring)
                return offSpring[i];
        }
        return null;
    }

    // gets character from next node and converts it to an integer and then removes 97(cos ascii for a)
    // basically turning a to z to 0 to 25 and then storing next at that index
    public void toCharArray(TrieNode nextNode)
    {
        int node = (int)nextNode.charValueLetter - 97;
        offSpring[node] = nextNode;
    }

    public char getCharValueLetter()
    {
        return charValueLetter;
    }

    //toString for getSubTrie
}
