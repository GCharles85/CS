/*
    Stephanie Taylor (Spring 2019)
    TestRandomCustomer.java
    Purpose: to test the line-choosing strategy of the RandomCustomer by visualizing
    the lines as customers join them. No customers leave.
*/
import java.util.ArrayList;

public class TestRandomCustomer {
    
    public static void main(String[] args) throws InterruptedException {
        ArrayList<CheckoutAgent> checkouts = new ArrayList<CheckoutAgent>(5);

        for(int i=0;i<5;i++) {
            CheckoutAgent checkout = new CheckoutAgent( i*100+50, 480 );
            checkouts.add( checkout );
        }
        Landscape scape = new Landscape(500,500, checkouts);
        LandscapeDisplay display = new LandscapeDisplay(scape);
        
        for (int j = 0; j < 999; j++) {
            Customer cust = new RandomCustomer(1);
            int choice = cust.chooseLine( checkouts );
            checkouts.get(choice).addCustomerToQueue( cust );
            scape.updateCheckouts();
            display.repaint();
            Thread.sleep( 250 );
            if(j % 100 == 0 && j != 0) {
            	scape.printFinishedCustomerStatistics();
            }
        }

    }

}
