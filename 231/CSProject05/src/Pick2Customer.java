import java.util.ArrayList;
import java.util.Random;

public class Pick2Customer extends Customer {
	
	public Pick2Customer(int num_items) {
		super(num_items, 2);
	}
	
	public int chooseLine(ArrayList<CheckoutAgent> checkouts) {
		Random line = new Random();
		int line1 = line.nextInt(checkouts.size());
		int line2 = line.nextInt(checkouts.size());
		if(checkouts.get(line1).getNumInQueue() < checkouts.get(line1).getNumInQueue()) {
			return line1;
		}else {
			return line2;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
