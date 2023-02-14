public class Person implements Comparable<Person>{

	private int age;

	public Person(int a ) { 
		this.age = a; 
	}

	public int getAge() { 
		return this.age; 
	}
	
	/**
	   * Rules for how should we compare two persons
	   * If THIS person has HIGHER age than the person b, return -1
	   * If THIS person has LOWER age than the person b, return +1
	   * Return 0 for equal ages
	*/

	public int compareTo(Person b) {
		if (this.age < b.getAge())
			return -1;
		else if (this.age > b.getAge())
			return 1;
		else
			return 0;
	}
}

