/*Guyriano Charles
 * 4/2/2019
 * CS231
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class WordCounter {
	private int coll;
	private int wordC;
	private MapSet<String, Integer> map;

	public WordCounter(int choice, int size) {
		this.wordC = 0;
		if(choice == 0) { //Anything besides 0 gives a Hashmap based hash table.
			this.map = new BSTMap<String, Integer>(new BSTMap.StringAscending());
        }else {
		    this.map = new Hashmap<String, Integer>(new Hashmap.StringAscending(), size);
		}
	}
		
	
	public void analyze(String filename) {
		    this.map.clear();
		    try {
		      FileReader loc = new FileReader(filename);
		      BufferedReader fil = new BufferedReader(loc);
		      String line = fil.readLine();
		      while(line != null) {
		    	  String[] words = line.split("[^a-zA-Z0-9']");
		    	  for(int i = 0; i < words.length; i++) { 
		        	if(words[i].length() != 0) {
		        		String word = words[i].trim().toLowerCase();
		    	    if(this.map.containsKey(word) == true) {
		    	    	this.map.put(word, this.map.get(word) + 1);
		    	    	this.coll++; //each repeated key increases the collision count 
		    	    }else {
		    	        this.map.put(word, 1);
		    	    }
		    	    this.wordC++;
		    	}
		    }
		    	   line = fil.readLine();
		  }
		     fil.close();
	      }
		    catch(FileNotFoundException ex) {
		      System.out.println("Unable to open file " + filename );
		    }
		    catch(IOException ex) {
		      System.out.println("Error reading file" + filename);
		    }	  

	}
	
	public int getTotalWordCount() {
		return this.wordC;
	}
	
	public int getUniqueWordCount() {
		return this.map.size();
	}
	
	public int getCount(String word) {
		return this.map.get(word);
	}
	
	public double getFrequency(String word) {
		return (double) this.map.get(word)/(double) this.wordC;
	}
	
	public void writeWordCountFile(String filename) {
		try {
		   FileWriter wt = new FileWriter(filename);
		   wt.write("Total Word Count: " + this.wordC);
		   wt.write(System.getProperty( "line.separator" ));
		   ArrayList<KeyValuePair<String, Integer>> list = new ArrayList<KeyValuePair<String, Integer>>();
		   list = this.map.entrySet();
		    for(int i = 0; i < list.size(); i++) { 
		       if(list.get(i).getKey().length() != 0) {
		        wt.write(list.get(i).getKey() + " " + list.get(i).getValue());
		        wt.write(System.getProperty( "line.separator" ));
		    }
		    }
		   wt.close();
	      }catch(FileNotFoundException ex) {
		      System.out.println("Unable to open file " + filename );
		  } catch (IOException ex) {
			  System.out.println("Cannot write to file " + filename );
		}
	}
	
	public void readWordCountFile(String filename) {
		try {
			  this.map.clear();
			  this.wordC = 0;
		      FileReader loc = new FileReader(filename);
		      BufferedReader fil = new BufferedReader(loc);
		      String line = fil.readLine();
		      line = fil.readLine();
		      while(line != null) {
		    	  String[] words = line.split("[^a-zA-Z0-9']");
		          String keys = words[0];
		          Integer val = Integer.parseInt(words[1]);
		    	  this.map.put(keys, val);
		    	  line = fil.readLine();
		    	  this.wordC = this.wordC + val;
		  }
		     fil.close();
	      }
		    catch(FileNotFoundException ex) {
		      System.out.println("Unable to open file " + filename );
		    }
		    catch(IOException ex) {
		      System.out.println("Error reading file" + filename);
		    }	  
	}
	
    public int getDepth() {
    	return this.map.getDepth();
    }
    
    public int getColl() {
    	return this.coll;
    }

	public static void main(String[] args) {
		WordCounter wc = new WordCounter(0,10);
		wc.analyze(args[0]);
		wc.writeWordCountFile(args[1]);
		wc.analyze(args[2]);
		wc.writeWordCountFile(args[3]);
		wc.analyze(args[4]);
		wc.writeWordCountFile(args[5]);
		wc.analyze(args[6]);
		wc.writeWordCountFile(args[7]);
		wc.analyze(args[8]);
		wc.writeWordCountFile(args[9]);
		wc.analyze(args[10]);
		wc.writeWordCountFile(args[11]);
		wc.analyze(args[12]);
		wc.writeWordCountFile(args[13]);
		}
	

}
