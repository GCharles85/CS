/**
 * File: Shuffle.java
 * Author: Guyriano Charles
 * Date: 02/12/2019
 */

import java.util.ArrayList;
import java.util.Random;

public class Shuffle {

	public static void main(String[] args) {
		ArrayList<Integer> newArray = new ArrayList<Integer>();
		Random rand = new Random();
		int place;
	 for(int count = 0; count<10; count++){
		int randNum = (rand.nextInt(10)) + 1;
		newArray.add(randNum);
		System.out.println("Newly added number: " + randNum);
	 }
	 
	 for(int count = 0; count<10; count++){
	 	System.out.println(newArray.get(count));
	 }
	  place = 10;
	 for(int count = 0; count<10; count++){
	 	int index = rand.nextInt(place);
	 	System.out.println("Number removed: " + newArray.get(index));
	 	newArray.remove(index);
	 	place--;
	 	System.out.println("Numbers left");
	 	for(int rNum = 0; rNum<place; rNum++){
	 		System.out.print(" " + newArray.get(rNum));
	 	}
	 	System.out.println(" ");
	 }

	}

}
