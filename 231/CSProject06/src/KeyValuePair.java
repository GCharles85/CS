/*Guyriano Charles
 * 4/2/2019
 * CS231
 */

public class KeyValuePair<Key, Value> {
	private Key key;
	private Value value;
	
	public KeyValuePair(Key k, Value v) {
		this.value = v;
		this.key = k;
	}
	
	public Key getKey() {
		return this.key;
	}
	
	public Value getValue() {
		return this.value;
	}
	
	public void setValue(Value v) {
		this.value = v;
	}
	
	public void setKey(Key k) {
		this.key = k;
	}
	
	public String toString() {
		return "Key:" + this.key + " " + "Val:" + this.value;
	}

	public static void main(String[] args) {
		KeyValuePair<String, Integer> dict = new KeyValuePair<String, Integer>("New", 2);
        System.out.println(dict.toString());
        System.out.println(dict.getValue());
        System.out.println(dict.getKey());
        dict.setValue(4);
        System.out.println(dict.toString());
	}

}
