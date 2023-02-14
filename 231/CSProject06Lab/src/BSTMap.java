/*Guyriano Charles
 * 4/2/2019
 * 
*/
import java.util.ArrayList;
import java.util.Comparator;

public class BSTMap<K, V> implements MapSet<K, V> {
	private TNode root;
	private int size;
	private Comparator<K> cmp;
	private ArrayList<K> keys;
	private ArrayList<V> values;
	private ArrayList<KeyValuePair<K,V>> pairings;
	private ArrayList<K> track;
	

	// constructor: takes in a Comparator object
	public BSTMap( Comparator<K> comp) {
		this.root = new TNode(null, null);
		this.cmp = comp;
		this.size = 1;
		this.keys = new ArrayList<K>();
		this.values = new ArrayList<V>();
		this.pairings = new ArrayList<KeyValuePair<K,V>>(this.size);
		this.track = new ArrayList<K>();
	}

	// adds or updates a key-value pair
	// If there is already a pair with new_key in the map, then update
	// the pair's value to new_value.
	// If there is not already a pair with new_key, then
	// add pair with new_key and new_value.
	// returns the old value or null if no old value existed
	public V put( K key, V value ) {
		if(this.root.pairs.getValue() == null && this.root.pairs.getKey() == null) {
			this.root.pairs.setValue(value);
			this.root.pairs.setKey(key);
			this.keys.add(key);
			return null;
		}
		if(this.cmp.compare(this.root.pairs.getKey(), key) == 0) {
			V temp = this.root.pairs.getValue();
			this.root.pairs.setValue(value);
			return temp;
		}
			return this.root.put(key, value, this.cmp);
        }

    // gets the value at the specified key or null
    public V get( K key ) {
           if(this.cmp.compare(this.root.pairs.getKey(), key) == 0) {
            return this.root.pairs.getValue();
           }
          return this.root.get(key, this.cmp);
           
    }
    
    public int size() {
		return this.size;
	}
    
    public boolean containsKey(K key) {
    	if(BSTMap.this.root.pairs.getKey() == null) {
    		return false;
    	}
    	if(this.cmp.compare(this.root.pairs.getKey(), key) == 0) {
             return true;
         }
         return this.root.containsKey(key, this.cmp);
	}
    
    public ArrayList<K> keySet() {
		if(this.root.pairs.getKey() == null && this.root.pairs.getValue() == null) {return this.keys;}
		return this.keys;
	}
    
    public ArrayList<V> values() {
    	if(this.root.pairs.getKey() == null && this.root.pairs.getValue() == null) {return this.values;}
    	for(K e: this.keys) {
    		this.values.add(this.get(e));
    	}
		return this.values;
	}
    
    public ArrayList<KeyValuePair<K,V>> entrySet() {
    	this.pairings.clear();
    	if(this.root.pairs.getKey() == null && this.root.pairs.getValue() == null) {
		    return this.pairings;
    	}else if(this.size == 1) {
    		this.pairings.add(this.root.pairs);
    		return this.pairings;
    	}else {
    		this.pairings.add(this.root.pairs);
    	}
    	return this.root.EntrySet(this.pairings);
	} 
    
    public void clear() {
    	this.root = new TNode(null, null);
    	this.keys.clear();
    	this.values.clear();
    	this.pairings.clear();
    	this.track.clear();
    	this.size = 1;
    }
     
    private class TNode {
    	   TNode left;
    	   TNode right;
    	   KeyValuePair<K, V> pairs;

            // constructor, given a key and a value
            public TNode( K k, V v ) {
                    this.left = null;
                    this.right = null;
                    this.pairs =  new KeyValuePair<K, V>(k, v);
            }

            // Takes in a key, a value, and a comparator and inserts the TNode
            // Returns the old value of the node, if replaced, or null if inserted
            public V put( K key, V value, Comparator<K> comp ) {
            	   if(comp.compare(this.pairs.getKey(), key) == 0) {
            		   V temp =  this.pairs.getValue();
            		   this.pairs.setValue(value);
            		   return temp;
            	   }
            	   TNode n = new TNode(key, value);
            	 
            	   if(comp.compare(this.pairs.getKey(), key) > 0) {
            		   if(this.pairs.getKey() == BSTMap.this.root.pairs.getKey() && BSTMap.this.containsKey(key) == false) {
        				   BSTMap.this.track.add(n.pairs.getKey());
        			   }
            		   if(this.left != null) {
            			  return this.left.put(key, value, comp);
            		   }else {
            			   this.left = n;
            			   BSTMap.this.keys.add(key);
            			   BSTMap.this.size++;
            		   }
            	   }else if(comp.compare(this.pairs.getKey(), key) < 0) {
            		   if(this.right != null) {
            			   return this.right.put(key, value, comp);
            		   }else {
            		     this.right = n;
            		     BSTMap.this.keys.add(key);
            		     BSTMap.this.size++;
            		   }
            		   }
            	   return null;
            }
            
            // Takes in a key and a comparator
            // Returns the value associated with the key or null
            public V get( K key, Comparator<K> comp ) {
            	if(comp.compare(this.pairs.getKey(), key) == 0) {
            		return this.pairs.getValue();
            	}
  
                if(comp.compare(this.pairs.getKey(), key) > 0 && this.left != null) {
         		   return this.left.get(key, comp);
         	   }else if(comp.compare(this.pairs.getKey(), key) < 0 && this.right !=  null) {
         		   return this.right.get(key, comp);
           	   }
                 return null;
              }
            
            public boolean containsKey( K key, Comparator<K> comp ) {
            	if(comp.compare(this.pairs.getKey(), key) == 0) {
            		return true;
            	}
  
                if(comp.compare(this.pairs.getKey(), key) > 0 && this.left != null) {
         		   return this.left.containsKey(key, comp);
         	   }else if(comp.compare(this.pairs.getKey(), key) < 0 && this.right !=  null) {
         		   return this.right.containsKey(key, comp);
           	   }
                 return false;
              }
            
            public ArrayList<KeyValuePair<K,V>> EntrySet(ArrayList<KeyValuePair<K,V>> pairings) {
            	if(this.left != null || this.right != null) {
            		if(BSTMap.this.pairings.contains(this.pairs) ==  false) {
            		BSTMap.this.pairings.add(this.pairs);
            		}
                	if(this.left != null) {
                	this.left.EntrySet(pairings);
                	}
                	if(this.right != null) {
                	this.right.EntrySet(pairings);
                	}
            	}else {
            		if(BSTMap.this.pairings.contains(this.pairs) ==  false) {
                		BSTMap.this.pairings.add(this.pairs);
                	}
            	}
            	
            	return BSTMap.this.pairings;
            }
            
    
            
    }// end of TNode class
    
    static class StringAscending implements Comparator<String> {
        public int compare( String a, String b ) {
        			return a.compareTo(b);
        }
      
     }

    // test function
    public static void main( String[] argv ) {
            // create a BSTMap
            BSTMap<String, Integer> bst = new BSTMap<String, Integer>(new StringAscending());

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
            bst.clear();
      }
    }

    



