	package partnerCode;

import java.awt.Graphics2D;

import gui.components.Component;
import simonMatthewN.ProgressInterfaceMatthew;

public class Progress extends Component implements ProgressInterfaceMatthew {
	
	private String round;
	private String sequence;
	private boolean gameOver;

	public Progress(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	
	public void setRound(int roundNumber) {
		round = "Round "+roundNumber;
		update();
	}
	
	public void setSequenceLength(int size) {
		sequence = "Sequence size "+size;
		update();
	}
	
	public void gameOver() {
		gameOver = true;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		// TODO Auto-generated method stub

	}

}
