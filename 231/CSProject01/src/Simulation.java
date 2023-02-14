import BlackJack.java;

public class Simulation {

	public static void main(String[] args) {
		BlackJack round = new BlackJack();
		for(int i = 0; i < 1000; i++) {
		 round.game(true);
		}

	}

}
