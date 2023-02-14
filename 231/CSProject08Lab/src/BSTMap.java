/*Guyriano Charles
 * 4/2/2019
 * 
*/
import java.util.ArrayList;
import java.util.Comparator;

public class BSTMap<K, V> implements MapSet<K, V> {
	private TNode root;
	private int size;
	private int depth;
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
           if(this.cmp.compare(this.root.pairs.getKey(), key) == 0) { //if the key given is the root's, return the root's value
            return this.root.pairs.getValue();
           }
          return this.root.get(key, this.cmp);
           
    }
    
    //returns the size of the tree
    public int size() {
		return this.size;
	}
    
    //returns a boolean value indicating whether or not the key is in the tree
    public boolean containsKey(K key) {
    	if(BSTMap.this.root.pairs.getKey() == null) {
    		return false;
    	}
    	if(this.cmp.compare(this.root.pairs.getKey(), key) == 0) {
             return true;
         }
         return this.root.containsKey(key, this.cmp);
	}
    
    public boolean ContainsKey(K key) {
    	return this.keys.contains(key);
    }
    
    //returns the set of keys in the tree
    public ArrayList<K> keySet() {
		if(this.root.pairs.getKey() == null && this.root.pairs.getValue() == null) {return this.keys;}
		return this.keys;
	}
    
    //returns the set of values in the tree
    public ArrayList<V> values() {
    	if(this.root.pairs.getKey() == null && this.root.pairs.getValue() == null) {return this.values;}
    	for(K e: this.keys) {
    		this.values.add(this.get(e));
    	}
		return this.values;
	}
    
    //returns the key-value pairs in the tree using pre-order traversal
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
    
    //clears the tree
    public void clear() {
    	this.root = new TNode(null, null);
    	this.keys.clear();
    	this.values.clear();
    	this.pairings.clear();
    	this.track.clear();
    	this.size = 1;
    }
    
    //returns the tree's depth
    public int getDepth() {
    	if(this.size == 1) {
    		return 1;
    	}else {
    		return this.root.GetDepth();
    	}
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
            	   TNode n = new TNode(key, value); //creates a new node n
            	 
            	   if(comp.compare(this.pairs.getKey(), key) > 0) { //if we need to go left
            		   if(this.pairs.getKey() == BSTMap.this.root.pairs.getKey() && BSTMap.this.containsKey(key) == false) {//if we are at the root and the tree does not contain this key
        				   BSTMap.this.track.add(n.pairs.getKey()); //add the key to track
        			   }
            		   if(this.left != null) {
            			  return this.left.put(key, value, comp);
            		   }else { //if the left is null, set Node n as this node's left node
            			   this.left = n;
            			   BSTMap.this.keys.add(key); //add the key to keys
            			   BSTMap.this.size++; //increase the size value of the tree
            		   }
            	   }else if(comp.compare(this.pairs.getKey(), key) < 0) { //if we need to go right
            		   if(this.right != null) {
            			   return this.right.put(key, value, comp);
            		   }else { //if the right is null, set Node n as this node's right node
            		     this.right = n;
            		     BSTMap.this.keys.add(key); //add key to keys
            		     BSTMap.this.size++; //increase size value of the tree
            		   }
            		   }
            	   return null;
            }
            
            // Takes in a key and a comparator
            // Returns the value associated with the key or null
            public V get( K key, Comparator<K> comp ) {
            	if(comp.compare(this.pairs.getKey(), key) == 0) { //if the key given is this node's, returns this node's value
            		return this.pairs.getValue();
            	}
  
                if(comp.compare(this.pairs.getKey(), key) > 0 && this.left != null) { //if we have to go left and the left node is null, 
         		   return this.left.get(key, comp);                                   //call the left node's get function
         	   }else if(comp.compare(this.pairs.getKey(), key) < 0 && this.right !=  null) {//if we have to go right and the right node is null,
         		   return this.right.get(key, comp);                                        //call the right node's get function
           	   }
                 return null;
              }
            
            public boolean containsKey( K key, Comparator<K> comp ) {
            	if(comp.compare(this.pairs.getKey(), key) == 0) { //if the key given is this node's, return true
            		return true;
            	}
  
                if(comp.compare(this.pairs.getKey(), key) > 0 && this.left != null) { //if left is not null and we need to go left
         		   return this.left.containsKey(key, comp); //call left node's containsKey function
         	   }else if(comp.compare(this.pairs.getKey(), key) < 0 && this.right !=  null) { //if right is not null and we need to go right
         		   return this.right.containsKey(key, comp); //call right node's containsKey function
           	   }
                 return false; //if we've reached the leaf nodes and have not found the key, return false
              }
            
            public ArrayList<KeyValuePair<K,V>> EntrySet(ArrayList<KeyValuePair<K,V>> pairings) {
            	if(this.left != null || this.right != null) { //if we can move left or right
            		if(BSTMap.this.pairings.contains(this.pairs) ==  false) { //if this node's pair is not in pairings, add it
            		BSTMap.this.pairings.add(this.pairs);
            		}
                	if(this.left != null) {
                	this.left.EntrySet(pairings);
                	}
                	if(this.right != null) {
                	this.right.EntrySet(pairings);
                	}
            	}else {
            		if(BSTMap.this.pairings.contains(this.pairs) ==  false) { // if we are at a leaf node and the pair in the node is not in pairings,
                		BSTMap.this.pairings.add(this.pairs);                 //add it
                	}
            	}
            	
            	return BSTMap.this.pairings; 
            }
            
            public int GetDepth() {
            	if(this.left != null || this.right != null) { //if we can move left or right, increment depth value then move left or right
            		BSTMap.this.depth++;
                 if(this.left != null) {
            	  this.left.GetDepth();
                 }
                 if(this.right != null) {
            	   this.right.GetDepth();
                 }
                 
            	}
            	
                return BSTMap.this.depth; //if we are at a leaf node, return the depth value
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


