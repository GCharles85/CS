import java.util.ArrayList;
import java.util.Random;

public class RandomCustomer extends Customer {
	
	public RandomCustomer(int num_items) {
		super(num_items, 1);
	}
	
	public int chooseLine(ArrayList<CheckoutAgent> checkouts) {
		Random ran = new Random();
		int ranL = ran.nextInt(checkouts.size());
		return ranL;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
