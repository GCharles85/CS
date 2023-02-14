/*
CS231 Homework 07



Questions:

1 - Draw out the hash table that is created by the main method.
2 - How many collisions occur across the entire hash table? There are 4 collisions
3 - Write the method public int getNumCollisions() to calculate the number of collisions that happen across the entire hash table
4 - Draw the hash table from the main method if we used linear probing to address the collision problem.

Note: You can take pictures of your drawing and upload it in your homework folder 7. Label your questions properly.

*/

import java.util.ArrayList;

/**
 * PlanetAssignment.java
 * Hash table representing planets where aliens are exiled.
 */
public class PlanetAssignment
{
  private ArrayList<ArrayList<String>> planets;
  
  public PlanetAssignment(int numLocations) 
  { 
    planets = new ArrayList<ArrayList<String>>(numLocations);
    for(int i = 0; i < numLocations; i++) {
    	planets.add(i, new ArrayList<String>());
    }
  }

  private int hash(String alien)
  {	
   return alien.length() % planets.size();
  }

  public void put(String alien)
  {
    int location = hash(alien);
    planets.get(location).add(alien);
  }
  

  public int getNumCollisions()
  {
	int Colls = 0;
	int bucketl = 0;
    for(int i = 0; i < this.planets.size(); i++) {
    	bucketl = this.planets.get(i).size();
    	if(bucketl > 1) {
    	Colls += bucketl;
    	}
    }
    return Colls;
  }

  public static void main(String[] args)
  {
    PlanetAssignment alienList = new PlanetAssignment(8);

    alienList.put("Capelons"); 
    alienList.put("Adipose"); 
    alienList.put("Baltan"); 
    alienList.put("Neadlehead"); 
    alienList.put("Romulans"); 
    alienList.put("Weevils"); 
    alienList.put("Zerg"); 

    System.out.println(alienList.getNumCollisions());
    
    
  }
}