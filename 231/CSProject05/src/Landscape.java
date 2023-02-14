import java.awt.Graphics;
import java.util.ArrayList;
import java.lang.Math;

public class Landscape {
	private int width;
	private int height;
	ArrayList<CheckoutAgent> cAgent;
	LinkedList<Customer> cst;
	
	public Landscape(int w, int h, ArrayList<CheckoutAgent> checkouts) {
		this.width = w;
		this.height = h;
		cAgent =  checkouts;
		cst = new LinkedList<Customer>();
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public String toString() {
		String c = "Customers: " + this.cst.size() + "Checkouts: " + this.cAgent.size();
		return c;
	}
	
	public void addFinishedCustomer(Customer c ) {
		this.cst.addLast(c);
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < this.cAgent.size(); i++) {
			this.cAgent.get(i).draw(g);
		}
	}
	
	public void updateCheckouts() {
		for(int i = 0; i < this.cAgent.size(); i++ ) {
			cAgent.get(i).updateState(this);
		}
	}
	
	public void printFinishedCustomerStatistics() {
		double dif = 0;
		double sd = 0;
		int avg = 0;
		int sum = 0;
		for(int i = 0; i < cst.size(); i++) {
			sum += cst.toArrayList().get(i).getTime();
		}
		avg = sum/cst.size();
		for(int i = 0; i < cst.size(); i++) {
			 dif = cst.toArrayList().get(i).getTime() - avg;
			 dif = Math.pow(dif, 2.0);
			 sd += dif;
		}
		sd = sd/cst.size();
		sd = Math.pow(sd, .5);
		System.out.println("Average: " + avg + " " + "SD: " + sd);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
