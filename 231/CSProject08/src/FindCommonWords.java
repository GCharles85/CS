import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FindCommonWords {
  PQHeap<KeyValuePair<String,Integer>> map;
  
  public FindCommonWords() {
	  this.map = new PQHeap<KeyValuePair<String,Integer>>(new PQHeap.ObjectAscending());
  }
  
  
  //Reads a word count file into a heap
  public void readWordCountFile(String filename) {
		try {
			  this.map.clear();
		      FileReader loc = new FileReader(filename);
		      BufferedReader fil = new BufferedReader(loc);
		      String line = fil.readLine();
		      line = fil.readLine();
		      while(line != null) {
		    	  String[] words = line.split("[^a-zA-Z0-9']");
		          String keys = words[0];
		          Integer val = Integer.parseInt(words[1]);
		    	  this.map.add(new KeyValuePair<String, Integer>(keys, val));
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
    
    //Prints N most common words in a file
    public void comWords(String numF) {
    	int count = 0;
    	Integer numFs = Integer.parseInt(numF);
    	for(int i = 0; i < this.map.numEl; i++) { //Runs for the amount of words in the heap
    		@SuppressWarnings("unchecked")
			KeyValuePair<String, Integer> q = (KeyValuePair<String, Integer>) this.map.heap[i];
    		count += q.getValue(); //each value in the heap is added up to get a total word count
    	}
    	for(int i = 0; i < numFs; i++) { //Run for the amount of words it is given to search
    		@SuppressWarnings("unchecked")
			KeyValuePair<String, Integer> p = (KeyValuePair<String, Integer>) this.map.remove();
    		System.out.println(p.getKey() + " " + (double) p.getValue()/ (double) count); //individual key-value pair values are added up to get a frequency
    		
    	}
    	System.out.println(" ");
    	
    	}
    
	
 
	public static void main(String[] args) {
		FindCommonWords cw = new FindCommonWords();
		for(int i = 0; i < args.length; i++) {
			if(i > 0) {
				cw.readWordCountFile(args[i]);
				cw.comWords(args[0]);
			}
		}
	}

}
