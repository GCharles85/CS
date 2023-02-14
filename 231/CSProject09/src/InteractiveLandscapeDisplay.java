/*
  Template created by Bruce A. Maxwell and Stephanie R Taylor

  Guyriano Charles
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Point;

import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

import java.util.*;

/**
 * Creates a window with two text fields, one buttons, and a large
 * display area. The app then tracks the mouse over the display area.
 **/
public class InteractiveLandscapeDisplay {

    // These four fields are as in the LandscapeDisplay class (though 
    // they are now all private)
    private JFrame win;
    private LandscapePanel canvas;    
    private Landscape scape; 
    private int scale;

    /** fields related to demo of mouse interaction **/

    // controls whether the game is playing or exiting
    private enum PlayState { PLAY, STOP }
    private PlayState state;

    /**
     * Creates the main window
     * @param scape the Landscape that will hold the objects we display
     * @param scale the size of each grid element
     **/		 
    public InteractiveLandscapeDisplay() {
        // The game should begin in the play state.
        this.state = PlayState.PLAY; 
        
        // Create the elements of the Landscape and the game.
        this.scale = 64; // determines the size of the grid
        this.scape = new Landscape(scale*10,scale*7);
        // This is test code that adds a few vertices.
        // You will need to update this to make a Graph first, then
        // add the vertices to the Landscape.
        Vertex v1 = new Vertex( 0, 0 );
        v1.setVisible( true );
        v1.setCost( 0 );
        Vertex v2 = new Vertex( 1, 0 );
        v2.setVisible( true );
        v2.setCost( 1 );
        Vertex v3 = new Vertex( 2, 0 );
        v3.setVisible( true );
        v3.setCost( 2 );
        Vertex v4 = new Vertex( 3, 0 );
        v4.setVisible( true );
        v4.setCost( 3 );
        v1.connect( v2, Vertex.Direction.east );
        v2.connect( v1, Vertex.Direction.west);
        v2.connect( v3, Vertex.Direction.east );
        v3.connect( v2, Vertex.Direction.west );
        v3.connect( v4, Vertex.Direction.east );
        v4.connect( v3, Vertex.Direction.west );
        this.scape.addBackgroundAgent( v1 );
        this.scape.addBackgroundAgent( v2 );
        this.scape.addBackgroundAgent( v3 );
        this.scape.addBackgroundAgent( v4 );        
        
        // Make the main window
        this.win = new JFrame("Basic Interactive Display");
        win.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);

        // make the main drawing canvas (this is the usual
        // LandscapePanel we have been using). and put it in the window
        this.canvas = new LandscapePanel( this.scape.getWidth(),
					                                        this.scape.getHeight() );
        this.win.add( this.canvas, BorderLayout.CENTER );
        this.win.pack();

        // make the labels and a button and put them into the frame
        // below the canvas.
        JButton quit = new JButton("Quit");
        JPanel panel = new JPanel( new FlowLayout(FlowLayout.RIGHT));
        panel.add( quit );
        this.win.add( panel, BorderLayout.SOUTH);
        this.win.pack();

        // Add key and button controls.
        // We are binding the key control object to the entire window.
        // That means that if a key is pressed while the window is
        // in focus, then control.keyTyped will be executed.
        // Because we are binding quit (the button) to control, control.actionPerformed will
        // be called if the quit button is pressed. If you make more than one button,
        // then the same function will be called. Use an if-statement in the function
        // to determine which button was pressed (check out Control.actionPerformed and
        // this advice should make sense)
        Control control = new Control();
        this.win.addKeyListener(control);
        this.win.setFocusable(true);
        this.win.requestFocus();
        quit.addActionListener( control );

        // The last thing to do is make it all visible.
        this.win.setVisible( true );

    }

    private class LandscapePanel extends JPanel {
		
        /**
         * Creates the drawing canvas
         * @param height the height of the panel in pixels
         * @param width the width of the panel in pixels
         **/
        public LandscapePanel(int height, int width) {
            super();
            this.setPreferredSize( new Dimension( width, height ) );
            this.setBackground(Color.white);
        }

        /**
         * Method overridden from JComponent that is responsible for
         * drawing components on the screen.  The supplied Graphics
         * object is used to draw.
         * 
         * @param g		the Graphics object used for drawing
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            scape.draw( g, scale );
        }
    } // end class LandscapePanel

    private class Control extends KeyAdapter implements ActionListener {

        public void keyTyped(KeyEvent e) {
            System.out.println( "Key Pressed: " + e.getKeyChar() );
            if( ("" + e.getKeyChar()).equalsIgnoreCase("q") ) {
                state = PlayState.STOP;
            }
        }

        public void actionPerformed(ActionEvent event) {
            // If the Quit button was pressed
            if( event.getActionCommand().equalsIgnoreCase("Quit") ) {
		        System.out.println("Quit button clicked");
                state = PlayState.STOP;
            }
        }
    } // end class Control

    public void repaint() {
    	this.win.repaint();
    }

    public void dispose() {
	    this.win.dispose();
    }


    public static void main(String[] argv) throws InterruptedException {
        InteractiveLandscapeDisplay w = new InteractiveLandscapeDisplay();
        while (w.state == PlayState.PLAY) {
            w.repaint();
            Thread.sleep(33);
        }
        System.out.println("Disposing window");
        w.dispose();
    }
	
} // end class InteractiveLandscapeDisplay