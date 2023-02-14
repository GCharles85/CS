/*Guyriano Charles
 * 3/12/19
 * CS231
 */

import java.awt.Graphics;

public class CheckoutAgent {
	private int xPos;
	private int yPos;
	MyQueue<Customer> line;
	
	public CheckoutAgent(int x, int y) {
		line = new MyQueue<Customer>();
		this.xPos = x;
		this.yPos = y;
	}
	
	public void addCustomerToQueue(Customer C) {
		line.offer(C);
	}
	
	public int getNumInQueue() {
		return line.getSize();
	}
	
	public void draw(Graphics g) {
		g.drawRect(this.xPos, this.yPos-450, 10, 10*line.getSize());
	}
	
	public void updateState(Landscape scape) {
		if(line.getSize() == 0) {
			return;
		}
		
		for(int i = 0; i < this.line.getSize(); i++) {
			this.line.toArrayList().get(i).incrementTime();
		}
		
		if(line.toArrayList().get(0).getNumItems() > 0) {
			this.line.toArrayList().get(0).giveUpItem();
		}else if(line.toArrayList().get(0).getNumItems() == 0) {
			scape.addFinishedCustomer(line.toArrayList().get(0));
			line.poll();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}