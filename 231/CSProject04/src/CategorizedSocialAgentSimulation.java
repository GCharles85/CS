import java.util.ArrayList;
import java.util.Random;

/*Guyriano Charles
 * 3/5/19
 * CS231
 */

public class CategorizedSocialAgentSimulation  {

	public static void main(String[] args) throws InterruptedException {
		Random num = new Random();
		Landscape view = new Landscape(500,500);
		for(int i = 0; i < 60; i++) {
			Random num2 = new Random();
			view.addAgent(new CategorizedSocialAgent(num.nextDouble()*100 + 99, num2.nextDouble()*100 + 99, num.nextInt(16)+5));
		}
		ArrayList<Agent> run = view.blist.toArrayList();
		for(int i = 0; i < 50; i++) {
			for(int j = 0; j < view.blist.size(); j++) {
			 run.get(i).updateState(view);
		    }
			LandscapeDisplay newL = new LandscapeDisplay(view);
			newL.repaint();
			newL.saveImage( "C:\\Users\\Gchar\\Documents\\Colby\\CS\\231\\CSProject04\\CSA/CA_frame_" + String.format( "%03d", i ) + ".png" );
			Thread.sleep(250);
		}

	}

}
