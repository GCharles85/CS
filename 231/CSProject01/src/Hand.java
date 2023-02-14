import Card.java;

public class Hand {
	
		public Hand() {
	      ArrayList<Integer> newHand = new ArrayList<Integer>();
		}
		
		public void reset() {
			Arrays.fill(newHand, null);
		}
		
		public void add(Card card) {
			this.newHand.add(card);
		}
		
		public int size() {
			return newHand.size();
		}
		
		public Card getCard(int i) {
			return this.newHand.get(i).getValue;
		}
		
		public int getTotalValue() {
			int Sum = 0;
			for( int i = 0; i < newHand.size(); i++) {
				Sum += newHand.get(i).getValue;
			}
			return Sum;
		}
		
		public String toString() {
			for(int i = 0; i < newHand.size(); i++) {
				System.out.println(newHand.get(i).getValue);
			}
		}
}
