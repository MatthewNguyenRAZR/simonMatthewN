package partnerCode;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import gui.components.Component;
import simonMatthewN.ProgressInterfaceMatthew;

public class Progress extends Component implements ProgressInterfaceMatthew {
	
	private String round;
	private String sequence;
	private boolean gameOver;
	
	private static final int W = 150;
	private static final int H= 70;

	public Progress() {
		super(60, 60, W, H);
		
	}
	public void setRound(int roundNumber) {
		round = "Round "+roundNumber;
		update();
	}
	public void gameOver() {
		gameOver = true;
		update();
	}
	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		FontMetrics fm = g.getFontMetrics();
		if (gameOver) {
			g.setColor(new Color(255, 150, 150));
			g.fillRect(0, 0, W, H);
			g.setColor(Color.white);
			String go = "GAME OVER!";
			g.drawString(go, (W - fm.stringWidth(go)) / 2, 20);
			g.drawString(sequence, (W - fm.stringWidth(sequence)) / 2, 40);

		} else {
			g.setColor(new Color(150, 255, 150));
			g.fillRect(0, 0, W, H);
			g.setColor(Color.black);
			if (round != null && sequence != null) {

				g.drawString(round, (W - fm.stringWidth(round)) / 2, 20);
				g.drawString(sequence, (W - fm.stringWidth(sequence)) / 2, 40);
			}
		}
	}

	@Override
	public void setSequenceSize(int size) {
		sequence = "Sequence length "+size;
		update();
	}

}
