import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

//4/8/15: added game over screen, updated ammo picture when mouse released,
//		  enabled looping back start of game from game over

public class Background{		
	public static void main (String [] args){
		JFrame frame = new JFrame( "Duck Hunt");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		GamePanel gamepanel = new GamePanel();	
		GameOver overpanel = new GameOver();
		
		overpanel.addMouseListener(new MouseListener(){
			@Override
	        public void mouseReleased(MouseEvent e) {
				gamepanel.ammo = 3;
		    	overpanel.setVisible(false);
		    	gamepanel.setVisible(true);
		    	
	        }
	        @Override
	        public void mousePressed(MouseEvent e) {
	            System.out.println("----------------------------------\n:MOUSE_PRESSED_EVENT:");
	        }
	       @Override
	        public void mouseExited(MouseEvent e) {
	   //         System.out.println(":MOUSE_EXITED_EVENT:");
	        }
	       @Override
	        public void mouseEntered(MouseEvent e) {
	 //           System.out.println(":MOUSE_ENTER_EVENT:");
	        }
	       @Override
	        public void mouseClicked(MouseEvent e) {
	    	   
	    	}	        	
		});	
		gamepanel.addMouseListener(new MouseListener(){
			@Override
	        public void mouseReleased(MouseEvent e) {
				frame.add(overpanel);
				gamepanel.ammo--;
		    	   if (gamepanel.ammo <= 0){
		    		   	 overpanel.setVisible(true);
		    		   	 gamepanel.setVisible(false);
		  	    		
		    	   }
		    	  System.out.println(gamepanel.ammo);
			}
	        @Override
	        public void mousePressed(MouseEvent e) {
	            System.out.println("----------------------------------\n:MOUSE_PRESSED_EVENT:");
	        }
	       @Override
	        public void mouseExited(MouseEvent e) {
	            System.out.println(":MOUSE_EXITED_EVENT:");
	        }
	       @Override
	        public void mouseEntered(MouseEvent e) {
	            System.out.println(":MOUSE_ENTER_EVENT:");
	        }
	       @Override
	        public void mouseClicked(MouseEvent e) {
	    	   
	    	}	        	
		});	
		frame.add(gamepanel);
		frame.setSize(805, 505);
		frame.setVisible(true);		
		frame.setResizable(false);	
		
	
		
	}
		

static class GamePanel extends JPanel implements ActionListener{

	private boolean wingsdown = true;
	private final int MIN_X = 38;
	private final int MAX_X = 770;	//750;				//779; the duck went off the edge
	private final int MIN_Y = 35; 			//this one somehow effects the ground not the top		
	private final int MAX_Y = 345;			//above the ground
	private final int INC_AMOUNT = 35;		//start at 35 we can inc this by 10 each round and cap at 100?
	private int DELAY = 500;
	protected Timer timer;
	private Random random= new Random();
	private int x = random.nextInt(MAX_X);
	private int y = random.nextInt(MAX_Y);
	private int dx = INC_AMOUNT;
	private int dy = INC_AMOUNT;
	private BufferedImage bg;			//background
	private BufferedImage inflight,duck1, duck2;	//duck
	private BufferedImage bar10,bar9,bar8,bar7,bar6,bar5,bar4,bar3,bar2,bar1;		//full bar
	private BufferedImage ammo3,ammo2,ammo1,ammo0,ammobar;
	public static int ammo = 3;
	
	@Override
	public void actionPerformed(ActionEvent e)
	{	
		wingsdown = !wingsdown;
		inflight = wingsdown ? duck1: duck2;	
		
		if(ammo == 3)
			ammobar = ammo3;
		else if (ammo == 2)
			ammobar = ammo2;
		else if(ammo == 1)
			ammobar = ammo1;
		else 
			ammobar = ammo0;
		
		repaint();	
	}
	
	public GamePanel(){
		try{
			bg = ImageIO.read(getClass().getResource("/res/img/background.jpg"));	//800x500
			duck1 =ImageIO.read(getClass().getResource("/res/img/flyingduck.png")); 
			duck2 =ImageIO.read(getClass().getResource("/res/img/flyingduck2.png")); 
			bar10 = ImageIO.read(getClass().getResource("/res/img/bar10.png"));
			ammo3 = ImageIO.read(getClass().getResource("/res/img/Ammo3.png"));
			ammo2 = ImageIO.read(getClass().getResource("/res/img/Ammo2.png"));
			ammo1 = ImageIO.read(getClass().getResource("/res/img/Ammo1.png"));
			ammo0 = ImageIO.read(getClass().getResource("/res/img/Ammo0.png"));
			
		}catch (IOException ex ){}
		
		timer = new Timer(DELAY,this);	//(delay,this);  timer is what will redraw
										//the images by time, 300 is intermediate
		timer.start();
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);

		//check boundaries
		if (x <  MIN_X)			dx = Math.abs(dx);
		if (x > MAX_X - MIN_X)	dx = -Math.abs(dx);
		if (y < MIN_Y )			dy = Math.abs(dy);
		if (y > MAX_Y - MIN_Y)	dy = -Math.abs(dy);
		
		//adjust bird position
		x += dx;
		y += dy;
		g.drawImage(bg,0,0,null);
		g.drawImage(bar10, 305, 426, null);
		g.drawImage(ammobar, 25, 426,null);
		g.drawImage(inflight, x-MIN_X, y-MIN_Y, null);
	}

}
	
}
	