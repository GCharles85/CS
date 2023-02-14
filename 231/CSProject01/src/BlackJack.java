import Deck.java;
import Hand.java;
import Card.java;

public class BlackJack {
	
	public BlackJack() {
		ArrayList<Object> pHand = new ArrayList<Object>();
		ArrayList<Object> hHand = new ArrayList<Object>();
	 }
	 

	 public void reset(boolean newDeck) {
		 Arrays.fill(deck,  null);
		 if(newDeck == true) {
			 shuffle();
		 }
	 }

	 public void deal() {
		Random generator = new Random(System.currentTimeMillis());
	    int pCard = generator.nextInt();
	    int hCard = generator.nextInt();
	    pHand.add(deck.get(pCard));
	    hHand.add(deck.get(hCard));
		System.out.println("Player card: " + deck.get(pCard).getValue());
		System.out.println("House card: " + deck.get(hCard).getValue());
	 }

	 public String toString() {
		int psum;
		int hsum;
		int actualSum;
		
		for(int i = 0; i < pHand.size(); i++) {
		psum += pHand.get(i).getValue();
		}
		for(int i = 0; i < pHand.size(); i++) {
		hsum += hHand.get(i).getValue();
		}
		
		System.out.println("Player Hand: " + psum);
		System.out.println("House Hand: " + hsum);
		
	 }

	 public boolean playerTurn() {
		for(int i = 0; i < pHand.size(); i++) {
			psum += pHand.get(i).getValue();
		}
		
		while( psum <= 16) {
			Random generator = new Random(System.currentTimeMillis());
		    int pCard = generator.nextInt();
		    pHand.add(deck.get(pCard));
		    psum++;
		}
		
		if( psum > 21) {
			return false;
		}
	 }

	 public boolean dealerTurn() {
		for(int i = 0; i < pHand.size(); i++) {
			hsum += hHand.get(i).getValue();
		}
		
		 while( hsum <= 17) {
			 Random generator = new Random(System.currentTimeMillis());
			 int hCard = generator.nextInt();
			 hHand.add(deck.get(hCard));
			 hsum++;
			}
			
			if( hsum > 21) {
				return false;
			}
	 }
	 
	 public int game(boolean verbose) {
		 BlackJack card.deal();
		 if(Hand playerHand.getTotalValue > 21) { 
		   return -1
		 }else if(Hand houseHand.getTotalValue > 21) {
			 return 1
		 }else if(Hand playerHand.getTotalValue == Hand houseHand.getTotalValue) {
			 return 0;
		 }
		 
		 
	 }
}
 
 public static void main(String[] args) {
	 BlackJack round = new BlackJack();
	 round.game(true);
	 round.game(true);
	 round.game(true);
 }


