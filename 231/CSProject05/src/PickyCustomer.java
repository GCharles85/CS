import java.util.ArrayList;

public class PickyCustomer extends Customer {
	
	public PickyCustomer(int num_items, int num_lines) {
		super(num_items, num_lines);
	}
	
	public int chooseLine(ArrayList<CheckoutAgent> checkouts) {
		int shorts = 0;
		int sizes = checkouts.get(0).getNumInQueue();
        for(int i = 0; i < checkouts.size(); i++) {
			if(checkouts.get(i).getNumInQueue() < sizes) {
				shorts = i;
			}
	   }
        return shorts;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
