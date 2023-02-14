import java.util.Random;
import Card.java;

public class Deck {
	
	public Deck() {
		build();
	}
	
	public void build() {
		ArrayList<Object> deck = ArrayList<Object>(52);
		for(int i = 0; i < 4; i++) {
		    for(int j = 2; j < 10; j++) {
		    	deck.add(new Card(j));
		    }
		    
		 deck.add(new Card(11));
		}
		for(int k = 0; k < 16; k++) {
			deck.add(new Card(10));
		}
	}
	
	public int size() {
		deck.size();
	}
	
	public Card deal() {
		return deck.get(0);
		deck.remove(0);
	}
	
	public Card pick(int i) {
		return deck.get(deck.size());
		deck.remove(deck.size());
	}
	
	public void shuffle() {
		Arrays.fill(deck,  null);
		ArrayList<Object> deckNew = ArrayList<Object>(52);
		for(int i = 0; i < 4; i++) {
		    for(int j = 2; j < 10; j++) {
		    	deckNew.add(new Card(j));
		    }
		    
		 deckNew.add(new Card(11));
		}
		for(int k = 0; k < 16; k++) {
			deckNew.add(new Card(10));
		}
		
		for(int e = 0; e < deckNew.size(); e++) {
			Random generator = new Random(System.currentTimeMillis());
			int rNum = generator.nextInt();
			deck.add(new Card(deckNew.get(rNum)));
		}
	}
	
	public String toString() {
		for(int i = 0; i < deck.size(); i++) {
			System.out.println(deck.get(i).getValue);
		}
	}
	
}
