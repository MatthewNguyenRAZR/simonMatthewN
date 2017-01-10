package partnerCode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gui.components.Action;
import gui.components.Component;
import simonMatthewN.ButtonInterfaceMatthew;

public class Button extends Component implements ButtonInterfaceMatthew {
	
	private Action action;
	private Color c;
	private Color displayC;
	private boolean lit;
	private static int W = 45;
	private static int H = 45;
	
	public Button() {
		super(0,0,W,H);
	}

	@Override
	public void act() {
//		Action.act();
	}

	@Override
	public boolean isHovered(int x, int y) {
		return x>getX() && x<getX()+getWidth() && y>getY() && y<getY()+getHeight();
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isAnimated() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void dim() {
		this.setColor(Color.GRAY);
		lit=false;
		update();
	}

	@Override
	public void highlight() {
		if(c != null){
			displayC = c;
		}
		lit = true;
		update();
	}

	@Override
	public void setColor(Color color) {
		this.c = color;
		displayC = c;
		update();
	}

	@Override
	public void setX(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setY(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAction(Action action) {
		this.action = action;
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(displayC != null){
			g.setColor(displayC);
		}else{
			g.setColor(Color.gray);
		}
		g.fillOval(0, 0, W, H);
		g.setColor(Color.BLACK);
		//indents
		g.drawOval(0, 0, W-5, H-5);
		if(lit){
			
		}
	}
}
