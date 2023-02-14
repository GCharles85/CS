import java.util.Random;

public class Grid {

	public static void main(String[] args) {
		Random r = new Random();
		if(args.length == 0) 
			System.out.println(" Enter a value for array args");
		
		
		int yogi;
		int booboo;
		yogi = Integer.parseInt(args[0]);
		booboo = Integer.parseInt(args[1]);
		String[][] ranger = new String[yogi][booboo];
		
		for(int i = 0; i < args.length ; i++) {
			System.out.println(args[i]);
		}
		
		for(int row = 0; row < yogi; row++) {
			for(int col = 0; col < booboo; col++) {
				ranger[row][col] = Character.toString((char) (97 + r.nextInt(26)));
			}
		}
		
		System.out.println("Number of rows: " + yogi);
		System.out.println("Number of rows: " + booboo);
		for(int row = 0; row < yogi; row++) {
			for(int col = 0; col < booboo; col++) {
				System.out.println(ranger[row][col]);
			}
		}
		

	}

}
