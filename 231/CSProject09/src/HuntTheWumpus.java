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
public class HuntTheWumpus {

    // These four fields are as in the LandscapeDisplay class (though 
    // they are now all private)
    private JFrame win;
    private LandscapePanel canvas;    
    private Landscape scape; 
    private int scale;
    private Graph graph;
    private Hunter hunt;
    private Wumpus villain;
    JLabel fieldW; 
    JLabel fieldL;
    JPanel panel;
    Random ran;
    
   
    // controls whether the game is playing or exiting
    private enum PlayState { PLAY, STOP }
    private PlayState state;

    /**
     * Creates the main window
     * @param scape the Landscape that will hold the objects we display
     * @param scale the size of each grid element
     **/		 
    public HuntTheWumpus() {
        // The game should begin in the play state.
        this.state = PlayState.PLAY;
        this.ran =  new Random();
        this.graph = new Graph();
        this.hunt = new Hunter(0,0);
        this.villain = new Wumpus(ran.nextInt(4), ran.nextInt(3));
        this.fieldW = new JLabel("Wumpus Killed");
        this.fieldL = new JLabel("Wumpus Wins");
        this.panel = new JPanel( new FlowLayout(FlowLayout.RIGHT));
        
        // Create the elements of the Landscape and the game.
        this.scale = 64; // determines the size of the grid
        this.scape = new Landscape(scale*10,scale*7);
        Vertex v2 = new Vertex( 1, 0  );
        v2.setCost(2);
        Vertex v3 = new Vertex( 2, 0 );
        v3.setCost(3);
        Vertex v4 = new Vertex( 3, 0 );
        v4.setCost(4);
        Vertex v5 = new Vertex( 0, 1 );
        v5.setCost(5);
        Vertex v6 = new Vertex( 1, 1 );
        v6.setCost(6);
        Vertex v7 = new Vertex( 2, 1 );
        v7.setCost(7);
        Vertex v8 = new Vertex( 3, 1 );
        v8.setCost(8);
        Vertex v9 = new Vertex( 0, 2 );
        v9.setCost(9);
        Vertex v10 = new Vertex( 1, 2 );
        v10.setCost(10);
        Vertex v11 = new Vertex( 2, 2 );
        v11.setCost(11);
        Vertex v12 = new Vertex( 3, 2 );
        v12.setCost(12);
        graph.addEdge(v2, Vertex.Direction.east, hunt.getVertex());
        graph.addEdge(v3, Vertex.Direction.east, v2);
        graph.addEdge(v4, Vertex.Direction.east, v3);
        graph.addEdge(hunt.getVertex(), Vertex.Direction.north, v5);
        graph.addEdge(v2, Vertex.Direction.north, v6);
        graph.addEdge(v3, Vertex.Direction.north, v7);
        graph.addEdge(v4, Vertex.Direction.north, v8);
        graph.addEdge(v6, Vertex.Direction.east, v5);
        graph.addEdge(v7, Vertex.Direction.east, v6);
        graph.addEdge(v8, Vertex.Direction.east, v7);
        graph.addEdge(v5, Vertex.Direction.north, v9);
        graph.addEdge(v6, Vertex.Direction.north, v10);
        graph.addEdge(v7, Vertex.Direction.north, v11);
        graph.addEdge(v8, Vertex.Direction.north, v12);
        graph.addEdge(v10, Vertex.Direction.east, v9);
        graph.addEdge(v11, Vertex.Direction.east, v10);
        graph.addEdge(v12, Vertex.Direction.east, v11);
        hunt.getVertex().connect(v5,Vertex.Direction.south );
        v5.connect(hunt.getVertex(), Vertex.Direction.north);
        hunt.getVertex().connect(v2,Vertex.Direction.east );
        v2.connect(hunt.getVertex(), Vertex.Direction.west);
        hunt.getVertex().setCost(3);
        for(Vertex e: graph.getVert()) {
        	if(e.getX() == villain.getX() && e.getY() == villain.getY()) {
        		 e.setCost(0);
        		 graph.shortestPath(e);
        	}
        }
       
        
        this.scape.addBackgroundAgent( hunt.getVertex() );
        this.scape.addBackgroundAgent( v2 );
        this.scape.addBackgroundAgent( v3 );
        this.scape.addBackgroundAgent( v4 );        
        this.scape.addBackgroundAgent( v5 );
        this.scape.addBackgroundAgent( v6 );
        this.scape.addBackgroundAgent( v7 );
        this.scape.addBackgroundAgent( v8 );
        this.scape.addBackgroundAgent( v9 );        
        this.scape.addBackgroundAgent( v10 );
        this.scape.addBackgroundAgent( v11 );
        this.scape.addBackgroundAgent( v12 );
        
                
        
        // Make the main window
        this.win = new JFrame("Basic Interactive Display");
        win.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);

        // make the main drawing canvas (this is the usual
        // LandscapePanel we have been using). and put it in the window
        this.canvas = new LandscapePanel( this.scape.getWidth(), this.scape.getHeight());
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
            hunt.draw(g, scale);
            HuntTheWumpus.this.villain.draw(g, scale);
        }
    } // end class LandscapePanel

    private class Control extends KeyAdapter implements ActionListener {
    	private int actionState = 0;

        public void keyTyped(KeyEvent e) {
        	
            System.out.println( "Key Pressed: " + e.getKeyChar() );
            if( ("" + e.getKeyChar()).equalsIgnoreCase("q") ) {
                state = PlayState.STOP;
            }else if( ("" + e.getKeyChar()).equalsIgnoreCase("w") ) {
            	if(actionState == 1) {
            		Vertex[] chance = hunt.getVertex().getNeighbors();
            		Vertex n = chance[Vertex.Direction.north.ordinal()];
            		if(n == null) { return;}
            		if(n.getX() == villain.getX() && n.getY() ==  villain.getY()) {
            			villain.getVertex().setVisible(true);
            			villain.setAlive(false);
            			HuntTheWumpus.this.panel.add(fieldW);
                		HuntTheWumpus.this.win.add( panel, BorderLayout.SOUTH);
                        HuntTheWumpus.this.win.pack();
            			System.out.println("Wumpus killed");
            			return;
            		}
            	 actionState = 0;
            	 return;
            	}
            	Vertex[] pos = hunt.getVertex().getNeighbors();
            	Vertex v = pos[Vertex.Direction.north.ordinal()];
            	if(v == null) {return;}
                hunt.setVertex(v);
                if(v.getX() == villain.getX() && v.getY() ==  villain.getY()) {
                	villain.getVertex().setVisible(true);
        			villain.setAlive(true);
        			HuntTheWumpus.this.panel.add(fieldL);
            		HuntTheWumpus.this.win.add( panel, BorderLayout.SOUTH);
                    HuntTheWumpus.this.win.pack();
        			System.out.println("Wumpus wins");
                }
            } else if( ("" + e.getKeyChar()).equalsIgnoreCase("a") ) {
            	if(actionState == 1) {
            		Vertex[] chance = hunt.getVertex().getNeighbors();
            		Vertex n = chance[Vertex.Direction.west.ordinal()];
            		if(n == null) {return;}
            		if(n.getX() == villain.getX() && n.getY() ==  villain.getY()) {
            			villain.getVertex().setVisible(true);
            			villain.setAlive(false);
            			HuntTheWumpus.this.panel.add(fieldW);
                		HuntTheWumpus.this.win.add( panel, BorderLayout.SOUTH);
                        HuntTheWumpus.this.win.pack();
            			System.out.println("Wumpus killed");
            			return;
            		}
            		actionState = 0;
            		return;
            	}
            	Vertex[] pos = hunt.getVertex().getNeighbors();
            	Vertex v = pos[Vertex.Direction.west.ordinal()];
            	if(v == null) {return;}
                hunt.setVertex(v);
                if(v.getX() == villain.getX() && v.getY() ==  villain.getY()) {
                	villain.getVertex().setVisible(true);
                	HuntTheWumpus.this.panel.add(fieldL);
            		HuntTheWumpus.this.win.add( panel, BorderLayout.SOUTH);
                    HuntTheWumpus.this.win.pack();
        			System.out.println("Wumpus wins");
                }
            }else if( ("" + e.getKeyChar()).equalsIgnoreCase("s") ) {
            	if(actionState == 1) {
            		Vertex[] chance = hunt.getVertex().getNeighbors();
            		Vertex n = chance[Vertex.Direction.south.ordinal()];
            		if(n == null) { return;}
            		if(n.getX() == villain.getX() && n.getY() ==  villain.getY()) {
            			villain.getVertex().setVisible(true);
            			villain.setAlive(false);
            			HuntTheWumpus.this.panel.add(fieldW);
                		HuntTheWumpus.this.win.add( panel, BorderLayout.SOUTH);
                        HuntTheWumpus.this.win.pack();
            			System.out.println("Wumpus killed");
            			return;
            		}
            	  actionState = 0;
            	  return;
            	}
            	Vertex[] pos = hunt.getVertex().getNeighbors();
            	Vertex v = pos[Vertex.Direction.south.ordinal()];
            	if(v == null) {return;}
                hunt.setVertex(v);
                if(v.getX() == villain.getX() && v.getY() ==  villain.getY()) {
                	villain.getVertex().setVisible(true);
                	HuntTheWumpus.this.panel.add(fieldL);
            		HuntTheWumpus.this.win.add( panel, BorderLayout.SOUTH);
                    HuntTheWumpus.this.win.pack();
        			System.out.println("Wumpus wins");
                }
            }else if( ("" + e.getKeyChar()).equalsIgnoreCase("d") ) {
            	if(actionState == 1) {
            		Vertex[] chance = hunt.getVertex().getNeighbors();
            		Vertex n = chance[Vertex.Direction.east.ordinal()];
            		if(n == null) { return;}
            		if(n.getX() == villain.getX() && n.getY() ==  villain.getY()) {
            			villain.getVertex().setVisible(true);
            			villain.setAlive(false);
            			HuntTheWumpus.this.panel.add(fieldW);
                		HuntTheWumpus.this.win.add( panel, BorderLayout.SOUTH);
                        HuntTheWumpus.this.win.pack();
            			System.out.println("Wumpus killed");
            			return;
            		}
            	   actionState = 0;
            	   return;
            	}
            	Vertex[] pos = hunt.getVertex().getNeighbors();
            	Vertex v = pos[Vertex.Direction.east.ordinal()];
            	if(v == null) {return;}
                hunt.setVertex(v);
                if(v.getX() == villain.getX() && v.getY() ==  villain.getY()) {
                	villain.getVertex().setVisible(true);
                	HuntTheWumpus.this.panel.add(fieldL);
            		HuntTheWumpus.this.win.add( panel, BorderLayout.SOUTH);
                    HuntTheWumpus.this.win.pack();
        			System.out.println("Wumpus wins");
                }
            }else if( e.getKeyCode() == 0) {
            	if(actionState == 0) {
            		actionState = 1;
            		System.out.println( "Arrow armed");
            		return;
            	}
            	actionState = 0;
            	System.out.println( "Arrow unarmed");
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
        HuntTheWumpus w = new HuntTheWumpus();
        while (w.state == PlayState.PLAY) {
            w.repaint();
            Thread.sleep(33);
        }
        System.out.println("Disposing window");
        w.dispose();
    }
	
} // end class HuntTheWumpus