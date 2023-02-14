/* Guyriano Charles
 * CS 231
 * CS Project 02
 */

import java.util.Random;

public class SocialAgentSimulation {
	
	public static void main(String[] args) throws InterruptedException{
		    Random num = new Random();
			Landscape view = new Landscape(500,500);
			for(int i = 0; i < 200; i++) {
				Random num2 = new Random();
				view.addAgent(new SocialAgent(num.nextDouble()*100 + 99, num2.nextDouble()*100 + 99));
			}
			
			for(int i = 0; i < 50; i++) {
				
				LandscapeDisplay newL = new LandscapeDisplay(view);
				view.updateAgents();
				newL.repaint();
				newL.saveImage( "C:\\Users\\Gchar\\Documents\\Colby\\CS\\231\\CSProject04\\SA/Agent_frame_" + String.format( "%03d", i ) + ".png" );
                Thread.sleep(250);
			}
	}

}
