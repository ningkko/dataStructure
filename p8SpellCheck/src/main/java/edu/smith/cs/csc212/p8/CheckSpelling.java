package edu.smith.cs.csc212.p8;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class CheckSpelling {
    /**
     * Read all lines from the UNIX dictionary.
     * @return a list of words!
     */
    public static List<String> loadDictionary() {
        long start = System.nanoTime();
        List<String> words;
        try {
            words = Files.readAllLines(new File("src/main/resources/words").toPath());
        } catch (IOException e) {
            throw new RuntimeException("Couldn't find dictionary.", e);
        }
        long end = System.nanoTime();
        double time = (end - start) / 1e9;
        System.out.println("Loaded " + words.size() + " entries in " + time +" seconds.");
        return words;
    }
    
    public static List<String> loadBook(){
    	 
    	 /**
    	  * to store raw txt of the book
    	  */
    	 String book_raw = "";
    	 List<String> lst;
    	 //read in all lines from the txt
    	 try {
             lst = Files.readAllLines(new File("src/main/resources/London Tower").toPath());
         } catch (IOException e) {
             throw new RuntimeException("Couldn't find dictionary.", e);
         }
    	 
    	 //append each line to book_raw
		 for(String line : lst) {
    		 book_raw=book_raw+line+" ";
    	 }
		 
		 //split into words
		 List<String>book=WordSplitter.splitTextToWords(book_raw);
    	 
		 return book;
    }
    
    /**
     * This method looks for all the words in a dictionary.
     * @param words - the "queries"
     * @param dictionary - the data structure.
     */
    public static void lookUp(List<String> words, Collection<String> dictionary) {
        
        int found = 0;
        for (String w : words) {
            if (dictionary.contains(w.toLowerCase())) {
                found++;
            }
        }
        System.out.println("Not found: "+(words.size()-found));
        System.out.println("Book size: "+words.size());
        System.out.println("Misspelled ratio: "+(double)(words.size()-found)/words.size());
        
    }
    
    /**
     * This method looks for all the words in a dictionary.
     * @param words - the "queries"
     * @param dictionary - the data structure.
     */
    public static void timeLookup(List<String> words, Collection<String> dictionary, boolean doPrint) {
        long startLookup = System.nanoTime();
        
        int found = 0;
        for (String w : words) {
            if (dictionary.contains(w)) {
                found++;
            }
        }
       
        long endLookup = System.nanoTime();
        double fractionFound = found / (double) words.size();
        double timeSpentPerItem = (endLookup - startLookup) / ((double) words.size());
        int nsPerItem = (int) timeSpentPerItem;
        if(doPrint) {
        	System.out.println(dictionary.getClass().getSimpleName()+": Lookup of items found="+fractionFound+" time="+nsPerItem+" ns/item");
        }
    }
    
  
    /**
     * Generates data set with mixed words(correctly spelled -> hits and incorrectly spelled -> misses)
     * 
     * @param words
     * 		input raw data set
     * @param amount
     * 		of total number of the returned data set
     * @param fraction 
     * 		of hits
     * @return
     * 		new mixed data set
     */
    public static List<String> createMixedDataset(List<String> words,int amount,double fraction){
		
    	List<String> mixed=new ArrayList<String>();
    
    	int i=0;
    	int hit_amount=(int)(amount*fraction);
    	while (i<hit_amount) {
    		mixed.add(words.get(i));
    		i++;
    	}
    	
    	while(i<amount) {
    		mixed.add(words.get(i)+"xyz");
    		i++;
    	}
    	return mixed;
    	
    }
    
    public static void main(String[] args) {
        // --- Load the dictionary.
        List<String> listOfWords = loadDictionary();

        // --- Create a bunch of data structures for testing:
        
        // Tree
        long startTreeTime = System.nanoTime();
        TreeSet<String> treeOfWords = new TreeSet<>(listOfWords);
        long endTreeTime = System.nanoTime();
        long treeTime=endTreeTime-startTreeTime;
        
        // Tree
        long startTreeTime2 = System.nanoTime();
        TreeSet<String> treeOfWords2 = new TreeSet<>();
        for (String w : listOfWords) {
        	treeOfWords2.add(w);
        }
        long endTreeTime2 = System.nanoTime();
        long treeTime2=endTreeTime2-startTreeTime2;
        
        // Hash
        long startHashTime=System.nanoTime();
        HashSet<String> hashOfWords = new HashSet<>(listOfWords);
        long endHashTime= System.nanoTime();
        long hashTime = endHashTime-startHashTime;

        long startHashTime2=System.nanoTime();
        HashSet<String> hashOfWords2 = new HashSet<>();
        for (String w : listOfWords) {
        	hashOfWords2.add(w);
        }
        long endHashTime2= System.nanoTime();
        long hashTime2 = endHashTime2-startHashTime2;

        // String list
        long startStringList =System.nanoTime();
        SortedStringListSet bsl = new SortedStringListSet(listOfWords);
        long endStringList =System.nanoTime();
        long stringListTime=endStringList-startStringList;

        // Char Trie
        long startTrieTime=System.nanoTime();
        CharTrie trie = new CharTrie();
        for (String w : listOfWords) {
            trie.insert(w);
        }
        long endTrieTime=System.nanoTime();
        long trieTime=endTrieTime-startTrieTime;

        // LLHash
        long startLLHashTime=System.nanoTime();
        LLHash hm100k = new LLHash(100000);
        for (String w : listOfWords) {
            hm100k.add(w);
        }
        long endLLHashTime=System.nanoTime();
        long LLHashTime=endLLHashTime-startLLHashTime;
        
        // LLHash
        long startLLHashTime2=System.nanoTime();
        LLHash hm200k = new LLHash(200000);
        for (String w : listOfWords) {
            hm200k.add(w);
        }
        long endLLHashTime2=System.nanoTime();
        long LLHashTime2=endLLHashTime2-startLLHashTime2;

        
        // --- linear list timing:
        
        //Test building time
        System.out.println("Building time - tree: "+treeTime);
        System.out.println("Building time - tree by loop: "+treeTime2);
        System.out.println("Building time - hash: "+hashTime);
        System.out.println("Building time - hash by loop: "+hashTime2);
        System.out.println("Building time - stringList: "+stringListTime);
        System.out.println("Building time - char trie: "+trieTime);
        System.out.println("Building time - LLHash: "+LLHashTime);
        System.out.println("Building time - LLHash2: "+LLHashTime2);

        System.out.println(" ");
        
        //Test insertion time
        System.out.println("Insertion time/element - tree: "+treeTime/treeOfWords.size());
        System.out.println("Insertion time/element - hash: "+hashTime/hashOfWords.size());
        System.out.println("Insertion time/element - string list: "+stringListTime/bsl.size());
        System.out.println("Insertion time/element - char trie: "+trieTime/trie.size);
        System.out.println("Insertion time/element - LLHash: "+LLHashTime/hm100k.size());
        System.out.println("Insertion time/element - LLHash2: "+LLHashTime2/hm200k.size());

        System.out.println(" ");
        
        // --- Make sure that every word in the dictionary is in the dictionary:
        timeLookup(listOfWords, treeOfWords, true);
        timeLookup(listOfWords, hashOfWords, true);
        timeLookup(listOfWords, bsl, true);
        timeLookup(listOfWords, trie, true);
        timeLookup(listOfWords, hm100k, true);
        timeLookup(listOfWords, hm200k, true);

        
        System.out.println("\n=================On real-world data================\n");
        // spell check
        List<String> book=loadBook();
       			
		timeLookup(book, treeOfWords, true);
		timeLookup(book, hashOfWords, true);
		timeLookup(book, bsl, true);
		timeLookup(book, trie, true);
		timeLookup(book, hm100k, true);
        timeLookup(book, hm200k, true);

		lookUp(book,hashOfWords);

        
        
        // hits and misses
        System.out.println("\n==============Hits and Misses=============");
        for (int j=0; j<4; j++) {
        	// warm up
        	for (int i=0; i<=10; i++) {
        		double fraction = i / 10.0;
        		// --- Create a data set of mixed hits and misses:
        		List<String> hitsAndMisses = createMixedDataset(listOfWords, 10000, fraction);
        		boolean doPrint=false;
        		if (j==3) {
        			doPrint=true;
        		}
        		timeLookup(hitsAndMisses, treeOfWords, doPrint);
        		timeLookup(hitsAndMisses, hashOfWords, doPrint);
        		timeLookup(hitsAndMisses, bsl, doPrint);
        		timeLookup(hitsAndMisses, trie, doPrint);
        		timeLookup(hitsAndMisses, hm100k, doPrint);
                timeLookup(hitsAndMisses, hm200k, doPrint);

        	}
        }
        System.out.println("\n===========================================");

 
         
        // Looking up in a list is so slow, we need to sample:
        System.out.println("Start of list: ");
        timeLookup(listOfWords.subList(0, 1000), listOfWords, true);
        System.out.println("End of list: ");
        timeLookup(listOfWords.subList(listOfWords.size()-100, listOfWords.size()), listOfWords, true);
        
    
        // --- print statistics about the data structures:
        System.out.println("Count-Nodes: "+trie.countNodes());
        System.out.println("Count-Items: "+hm100k.size());

        System.out.println("Count-Collisions[100k]: "+hm100k.countCollisions());
        System.out.println("Count-Used-Buckets[100k]: "+hm100k.countUsedBuckets());
        System.out.println("Load-Factor[100k]: "+hm100k.countUsedBuckets() / 100000.0);
        System.out.println("Count-Items: "+hm200k.size());

        System.out.println("Count-Collisions[200k]: "+hm200k.countCollisions());
        System.out.println("Count-Used-Buckets[200k]: "+hm200k.countUsedBuckets());
        System.out.println("Load-Factor[200k]: "+hm200k.countUsedBuckets() / 100000.0);

        
        System.out.println("log_2 of listOfWords.size(): "+listOfWords.size());
        
        System.out.println("Done!");
        

        }

    }

