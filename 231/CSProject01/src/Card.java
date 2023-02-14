import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Card {
	
	private int cValue;
	
	public Card(int v) {
	 if(v >= 1 && v <= 11) {
		this.cValue = v; 
	}else{
		System.out.println("Input a valid card number");
	}
 }
 public int getValue() {return this.cValue};
}


