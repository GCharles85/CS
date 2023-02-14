import java.util.ArrayList;
import java.util.Random;

public class Money {

    // fields
    private double amount;
    
    // constructor
    public Money() {
        // create an arrayList of possible denominations.
        ArrayList<Double> possible_amounts = new ArrayList<Double>();
        possible_amounts.add( 0.01 );
        possible_amounts.add( 0.05 );
        possible_amounts.add( 0.1 );
        possible_amounts.add( 0.25 );
        possible_amounts.add( 1.0 );
        // now, randomly pick one of them.
        Random generator = new Random();
        int index = generator.nextInt(possible_amounts.size());
        this.amount = possible_amounts.get(index);
    }
    
    public double getAmount() {
        return this.amount;
    }
    
    public static void main(String[] args){
   	 float Sum = 0;
   	 ArrayList<Money> listCash = new ArrayList<Money>();
   	 for(int count = 0; count<5; count++) {
   		 Money pay = new Money();
   		 listCash.add(pay);
   		 System.out.println("Amount chosen: " + pay.getAmount());
   	 }
   	 
   	 for(int count = 0; count<5; count++) {
   		 Money newPay = listCash.get(count);
   		 Sum += newPay.getAmount();
     }
   	 System.out.print("Total is: " + Sum);
    }

}
