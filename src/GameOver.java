import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.Timer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class GameOver extends JPanel implements ActionListener{
	private BufferedImage flash, gameover1, gameover2;
	private boolean img_switch = true;
	private int delay = 666;
	protected Timer timer;
	
	@Override
	public void actionPerformed(ActionEvent e){			
		img_switch = !img_switch;
		flash = img_switch ? gameover1: gameover2;
		repaint();
	}
	
	public GameOver(){	
		try{
			gameover1 = ImageIO.read(getClass().getResource("/res/img/gameover1.png"));
			gameover2 = ImageIO.read(getClass().getResource("/res/img/gameover2.png"));		
		}catch (IOException io){}
		
		timer = new Timer(delay, this);
		timer.setInitialDelay(1);
		timer.start();	
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(flash, 0, 0, null);		
	}
	
}
