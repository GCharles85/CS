import java.util.ArrayList;

public abstract class Customer {
	protected int num_items;
	protected  int time_steps;
	
	public Customer(int num_items) {
		this.time_steps = 0;
		this.num_items = num_items;
	}
	
	public Customer(int num_items, int time_steps) {
		this.time_steps = time_steps;
		this.num_items = num_items;
	}
	
	public void incrementTime() {
		this.time_steps++;
	}
	
	public int getTime() {
		return this.time_steps;
	}
	
	public void giveUpItem() {
		this.num_items--;
	}
	
	public int getNumItems() {
		return this.num_items;
	}
	
	public abstract int chooseLine(ArrayList<CheckoutAgent> checkouts);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}