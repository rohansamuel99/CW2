package CW2;

import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;

public class TrieNode
{
    public char c;
    char[] offSpring = new char[26];



    public TrieNode()
    {

    }

    public TrieNode(char c)
    {
        this.c =c;
    }
}
