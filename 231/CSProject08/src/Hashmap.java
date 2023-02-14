/*Guyriano Charles
 * 4/14/18
 * CS231
 */
	
import java.util.ArrayList;
import java.util.Comparator;


public class Hashmap<K, V> implements MapSet<K, V> {
		private int size;
		private Object[] Hash;
		private Comparator<K> cmp;

		// constructor: takes in a Comparator object and a size value for the array Hash
		public Hashmap( Comparator<K> comp, int intsize) {
			this.Hash = new Object[intsize];
			this.size = intsize;
			this.cmp = comp;
			for(int i = 0; i < this.Hash.length; i++) {
			       this.Hash[i] = new BSTMap<K, V>(this.cmp); //populate Hash with BSTMap objects
			}
		}

		@SuppressWarnings("unchecked")
		public V put( K key, V value ) {
			int hash =  Math.abs(key.hashCode());
			int index = hash % this.Hash.length;
	        if(index >= this.size) { //if the index is too small or large, set it to the first or last index of Hash
	        	index = this.size - 1;
	        }else if(index <= this.size) {
	        	index = 0;
	        }
				
			return ((BSTMap<K,V>) this.Hash[index]).put(key, value);
	     }

	    // gets the value at the specified key or null
	    @SuppressWarnings("unchecked")
		public V get( K key ) {
	    	int hash = Math.abs(key.hashCode());
			int index = hash % this.Hash.length;
			if(index >= this.size) {
		        index = this.size - 1;
		    }else if(index <= this.size) {
		        	index = 0;
		    }
			return ((BSTMap<K,V>) this.Hash[index]).get(key);
	    }
	    
	    public int size() {
			return this.size;
		}
	    
	    @SuppressWarnings("unchecked")
		public boolean containsKey(K key) {
	    	int hash = key.hashCode();
			int index = hash % this.Hash.length;
			if(index >= this.size) {
		       index = this.size - 1;
		    }else if(index <= this.size) {
		       index = 0;
		    }
			return ((BSTMap<K,V>) this.Hash[index]).containsKey(key);
		}
	    
	    @SuppressWarnings("unchecked")
		public ArrayList<K> keySet() {
	    	ArrayList<K> keys = new ArrayList<K>();
	    	for(int i = 0; i < this.Hash.length; i++) {
	    		keys.addAll(((BSTMap<K,V>) this.Hash[i]).keySet());
	    	}
	  
	    	return keys;
		}
	    
	    @SuppressWarnings("unchecked")
		public ArrayList<V> values() {
	    	ArrayList<V> values = new ArrayList<V>();
	    	for(int i = 0; i < this.Hash.length; i++) {
	    		values.addAll(((BSTMap<K,V>) this.Hash[i]).values());
	    	}
	    	
	    	return values;
		}
	    
	    @SuppressWarnings("unchecked")
		public ArrayList<KeyValuePair<K,V>> entrySet() {
	       ArrayList<KeyValuePair<K,V>> pairs = new ArrayList<KeyValuePair<K,V>>();
	       for(int i = 0; i < this.Hash.length; i++) {
	         pairs.addAll(((BSTMap<K,V>) this.Hash[i]).entrySet());
	     }
	     
	     return pairs;
	    }
	    
	    @SuppressWarnings("unchecked")
		public void clear() {
	     for(int i = 0; i < this.Hash.length; i++) {
	  	       ((BSTMap<K,V>) this.Hash[i]).clear();
	  		}
	    }
	    
	    @SuppressWarnings("unchecked")
		public int getDepth() { //returns the combined depth of all the BSTMap objects in the hash table
	    	int depth = 0;
	    	for(int i = 0; i < this.Hash.length; i++) {
	    		depth += ((BSTMap<K,V>) this.Hash[i]).getDepth();
	    	}
	    	return depth;
	    }
	    
	    static class StringAscending implements Comparator<String> {
	        public int compare( String a, String b ) {
	        			return a.compareTo(b);
	        }
	    }

	    // test function
	    public static void main( String[] argv ) {
	            // create a BSTMap
	            Hashmap<String, Integer> bst = new Hashmap<String, Integer>(new StringAscending(), 10);

	            bst.put( "twenty", 20 );
	            bst.put( "ten", 10 );
	            bst.put( "eleven", 11);
	            bst.put( "five", 5 );
	            bst.put( "six", 6 );
	            System.out.println(bst.get( "eleven" ));
	            System.out.println(bst.containsKey("five"));
	            System.out.println(bst.keySet());
	            System.out.println(bst.values());
	            bst.clear();
	            System.out.println(bst.keySet());
	            System.out.println(bst.values());
	            bst.put( "twenty", 20 );
	            bst.put( "ten", 10 );
	            bst.put( "eleven", 11);
	            bst.put( "five", 5 );
	            bst.put( "six", 6 );
	            System.out.println(bst.entrySet());
	            
	      }
}
