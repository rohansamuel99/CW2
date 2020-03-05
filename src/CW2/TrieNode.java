package CW2;

import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;

public class TrieNode
{
    char charValueLetter;
    boolean isEnd;
    //creating an array of fixed size 26
    TrieNode[] offSpring;

    public TrieNode()
    {
        this.offSpring = new TrieNode[26];
        this.isEnd = false;
        for(int i = 0; i < offSpring.length; i++)
            offSpring[i] = null;
    }

    public TrieNode(char c)
    {
        charValueLetter =c;
    }


}
