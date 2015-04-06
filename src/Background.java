import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.Timer;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Background extends JPanel implements ActionListener{

	private final int MIN_X = 38;
	private final int MAX_X = 770;	//750;				//779; the duck went off the edge
	private final int MIN_Y = 35; 			//this one somehow effects the ground not the top		
	private final int MAX_Y = 345;			//above the ground
	private final int INC_AMOUNT = 100;		//we can inc this by 10 each round and cap at 100?
	private int DELAY = 300;
	protected Timer timer;
	private Random random= new Random();
	private int x = random.nextInt(MAX_X);
	private int y = random.nextInt(MAX_Y);
	
	private int dx = INC_AMOUNT;
	private int dy = INC_AMOUNT;
	
	private BufferedImage bg;			//background
	private BufferedImage duck;			//duck
	public void actionPerformed(ActionEvent e)
	{	repaint();	}
	
	public Background(){
		try{
			bg = ImageIO.read(getClass().getResource("background.jpg"));	//800x500
			duck =ImageIO.read(getClass().getResource("flyingduck.png")); 
		
		}catch (IOException ex ){}
		
		timer = new Timer(DELAY,this);	//(delay,this);  timer is what will redraw
										//the images by time, 300 is intermediate
		timer.start();
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		/*
		if (x < radius)			dx = Math.abs(dx);
		if (x > getWidth() - radius)	dx = -Math.abs(dx);
		if (y < radius)			dy = Math.abs(dy);
		if (y > getHeight() - radius)	dy = -Math.abs(dy);*/
		
		//check boundaries
		if (x <  MIN_X)			dx = Math.abs(dx);
		if (x > MAX_X - MIN_X)	dx = -Math.abs(dx);
		if (y < MIN_Y )			dy = Math.abs(dy);
		if (y > MAX_Y - MIN_Y)	dy = -Math.abs(dy);
		
		//adjust bird position
		x += dx;
		y += dy;
		g.drawImage(bg,0,0,null);
		g.drawImage(duck, x-MIN_X, y-MIN_Y, null);
	}
	
	public static void main (String [] args){
		JFrame frame = new JFrame( "Duck Hunt");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Background bpanel = new Background();
	    bpanel.setPreferredSize(new Dimension(800, 500));
	    bpanel.setBackground(Color.BLACK);
	    bpanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
	    Box box = new Box(BoxLayout.Y_AXIS);

	    box.add(Box.createVerticalGlue());
	    box.add(bpanel);     
	    box.add(Box.createVerticalGlue()); // causes a deformation
	    
	    frame.add(bpanel);
	    frame.setSize(new Dimension(1600, 1000));
		frame.setSize(805, 505);
		frame.setVisible(true);
		//doesn't allow user to resize frame
		frame.setResizable(false);
	}
	
	
    /*JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(100, 100));
    panel.setBackground(Color.RED); // for debug 

    panel.setAlignmentX(JComponent.CENTER_ALIGNMENT); // have no effect

    Box box = new Box(BoxLayout.Y_AXIS);

    box.add(Box.createVerticalGlue());
    box.add(panel);     
    box.add(Box.createVerticalGlue()); // causes a deformation

    frame.add(box);
    frame.setSize(new Dimension(200, 200));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);*/

	
	
	
}