import edu.duke.*;
import java.util.*;
import java.io.*;

/**
* 
* @author: Amir Armion 
* 
* @version: V.01
* 
*/
public class WordsInFiles 
{
    private HashMap<String, ArrayList<String>> myMap;

    public WordsInFiles()
    {
        myMap = new HashMap<>();
    }

    private void addWordsFromFile(File f)
    {
        String fileName = f.getName();

        FileResource fr = new FileResource(f);

        for(String word: fr.words())
        {
            if(!myMap.containsKey(word))
            {
                ArrayList<String> newFileName = new ArrayList<>();
                newFileName.add(fileName);
                myMap.put(word, newFileName);
            }
            else
            {
                for(String key: myMap.keySet())
                {
                    if(key.equals(word) && !myMap.get(key).contains(fileName))
                    {             
                        myMap.get(key).add(fileName);
                    }
                }
            }
        }
    }

    public void buildWordFileMap()
    {
        myMap.clear();

        DirectoryResource dr = new DirectoryResource();

        for(File f: dr.selectedFiles())
        {
            addWordsFromFile(f);
        }
    }

    public int maxNumber()
    {
        int maxNumber = 0;

        for(String key: myMap.keySet())
        {
            if(myMap.get(key).size() >  maxNumber)
            {
                maxNumber = myMap.get(key).size();            
            }            
        }

        return maxNumber;
    }

    public ArrayList<String> wordsInNumFiles(int number)
    {
        ArrayList<String> words = new ArrayList<>();

        for(String key: myMap.keySet())
        {
            if(myMap.get(key).size() == number)
            {
                if(!words.contains(key))
                {
                    words.add(key);
                }
            }            
        }

        return words;
    }

    public void printFilesIn(String word)
    {
        int count = 0;
        
        for(String key: myMap.keySet())
        {
            if(key.equals(word))
            {
                count++;
                
                for(int i = 0; i < myMap.get(key).size(); i++)
                {
                    System.out.println(myMap.get(key).get(i));
                }
            }
        }
        
        if(count == 0)
        {
            System.out.println("\nNo such this word in these files!");
        }
    }

    public void tester()
    {
        buildWordFileMap();
        
        System.out.println("\n- All words is: " + myMap.size());

        System.out.println("\n- The maximum number of files any word appears in is: " + maxNumber());
        
        System.out.println("\n- All words in 4 files is: " + wordsInNumFiles(4).size());

        //System.out.println("\n- All words in 4 files: ");

        //for(int i = 0; i < wordsInNumFiles(2).size(); i++)
        //{
        //    System.out.println(wordsInNumFiles(2).get(i));
        //}

        System.out.println("\n- All files name that word \"tree\" appears in: ");

        printFilesIn("tree");

        //System.out.println("\n\n- All Map:");

        //for(String word: myMap.keySet())
        //{
        //    System.out.println("\nWord \"" + word + "\" appears in these files: ");
            
        //    for(int i = 0; i < myMap.get(word).size(); i++)
        //    {
        //        System.out.println(myMap.get(word).get(i));
        //    }
        //}
    }
}
