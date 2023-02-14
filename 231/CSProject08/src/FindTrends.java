
public class FindTrends {
    private WordCounter counts;
	
    public FindTrends() {
		this.counts = new WordCounter(0,0);
	}
    
    //Prints the word from the array words and its frequency in the given file
	public void trends(String filename, String[] words) {
		counts.readWordCountFile(filename);
		for(int i = 0; i < words.length; i++) {
			System.out.println(words[i] + " " + counts.getFrequency(words[i])); //WordCounter's getFrequency method is used to get a frequency
		}
		System.out.println(" ");
	}
	
	
	public static void main(String[] args) {
		FindTrends ft = new FindTrends();
		String[] find = {"clinton","sanders", "rubio", "trump", "obama", "romney", "cruz", "palin"};
		for(int i =0; i < args.length; i++) {
			ft.trends(args[i], find);
		}

	}

}
