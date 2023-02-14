import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LandscapeDisplay {
	 JFrame win;
	    protected Landscape scape;
	    private LandscapePanel canvas;
	    private int gridScale; // width (and height) of each square in the grid

	    /**
	     * Initializes a display window for a Landscape.
	     * @param scape the Landscape to display
	     * @param scale controls the relative size of the display
	     */
	    public LandscapeDisplay(Landscape scape, int scale)
	    {
	        // setup the window
	        this.win = new JFrame("Game of Life");
	        this.win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        this.scape = scape;
	        this.gridScale = scale;

	        // create a panel in which to display the Landscape
		// put a buffer of two rows around the display grid
	        this.canvas = new LandscapePanel( (int) (this.scape.getCols()+4) * this.gridScale,
						  (int) (this.scape.getRows()+4) * this.gridScale);

	        // add the panel to the window, layout, and display
	        this.win.add(this.canvas, BorderLayout.CENTER);
	        this.win.pack();
	        this.win.setVisible(true);
	    }

	    /**
	     * Saves an image of the display contents to a file.  The supplied
	     * filename should have an extension supported by javax.imageio, e.g.
	     * "png" or "jpg".
	     *
	     * @param filename  the name of the file to save
	     */
	    public void saveImage(String filename)
	    {
	        // get the file extension from the filename
	        String ext = filename.substring(filename.lastIndexOf('.') + 1, filename.length());

	        // create an image buffer to save this component
	        Component tosave = this.win.getRootPane();
	        BufferedImage image = new BufferedImage(tosave.getWidth(), tosave.getHeight(), 
	                                                BufferedImage.TYPE_INT_RGB);

	        // paint the component to the image buffer
	        Graphics g = image.createGraphics();
	        tosave.paint(g);
	        g.dispose();

	        // save the image
	        try
	                {
	                        ImageIO.write(image, ext, new File(filename));
	                }
	        catch (IOException ioe)
	                {
	                        System.out.println(ioe.getMessage());
	                }
	    }

	    /**
	     * This inner class provides the panel on which Landscape elements
	     * are drawn.
	     */
	    private class LandscapePanel extends JPanel
	    {
	        /**
	         * Creates the panel.
	         * @param width     the width of the panel in pixels
	         * @param height        the height of the panel in pixels
	         */
	        public LandscapePanel(int width, int height)
	        {
	                super();
	                this.setPreferredSize(new Dimension(width, height));
	                this.setBackground(Color.lightGray);
	        }

	        /**
	         * Method overridden from JComponent that is responsible for
	         * drawing components on the screen.  The supplied Graphics
	         * object is used to draw.
	         * 
	         * @param g     the Graphics object used for drawing
	         */
	        public void paintComponent(Graphics g)
	        {
	            super.paintComponent(g);
	            scape.draw( g, gridScale );
	        } // end paintComponent
	        
	    } // end LandscapePanel

	    public void repaint() {
		this.win.repaint();
	    }

	    public static void main(String[] args) throws InterruptedException {
	        Landscape scape = new Landscape(100,100);
	        Random gen = new Random();
	        double density = 0.3;

		// initialize the grid to be 30% full
	        for (int i = 0; i < scape.getRows(); i++) {
	            for (int j = 0; j < scape.getCols(); j++ ) { 
	                scape.getCell( i, j ).setAlive( gen.nextDouble() <= density );
	            }
	        }

	        LandscapeDisplay display = new LandscapeDisplay(scape, 8);
	    }
}
