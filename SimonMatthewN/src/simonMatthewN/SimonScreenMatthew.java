package simonMatthewN;

import java.awt.Color;
import java.util.ArrayList; 
import java.util.List;

import gui.components.Action;
import gui.components.TextLabel;
import gui.components.Visible;
import gui.screens.ClickableScreen;
import partnerCode.Progress;
import partnerCode.Button;
import partnerCode.Move;

public class SimonScreenMatthew extends ClickableScreen implements Runnable {
	
	private TextLabel label;
	private ButtonInterfaceMatthew[] button;
	private ProgressInterfaceMatthew progress;
	private ArrayList<MoveInterfaceMatthew> order;
	private int round;
	private boolean acceptingInput;
	private int orderIndex;
	private int lastChosen;

	public SimonScreenMatthew(int width, int height) {
		super(width, height);
		Thread simonStart = new Thread(this);
		simonStart.start();
	}

	public void run(){
		changeText("");
	    nextRound();
	} 
	
	private void nextRound() {
		acceptingInput = false;
		round ++;
		progress.setRound(round);
		order.add(randomMove());
		progress.setSequenceSize(order.size());
		changeText("Simon's turn.");
		label.setText("");
		playSequence();
		changeText("Your turn.");
		label.setText("");
		acceptingInput = true;
		orderIndex = 0;
	}

	private void playSequence() {
		ButtonInterfaceMatthew b = null;
		for(int i =0; i<order.size();i++){
			if(b != null){
				b.dim();
			}
			//b = ((ButtonInterface)order).getButton();
			b = order.get(i).getButton();
			b.highlight();
			try {
				Thread.sleep((long)(2000*(2.0/(round+2))));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		b.dim();
	}
	
	public void initAllObjects(List<Visible> viewObjects) {
		addButtons(viewObjects);
		progress = getProgress();
		label = new TextLabel(130,300,300,40,"");
		order = new ArrayList<MoveInterfaceMatthew>();
		//add 2 moves to start
		lastChosen = -1;
		order.add(randomMove());
		order.add(randomMove());
		round = 0;
		viewObjects.add(progress);
		viewObjects.add(label);
	}
	
	protected void gameOver() {
		progress.gameOver();
	}

	private MoveInterfaceMatthew randomMove() {
		ButtonInterfaceMatthew b;
		int rand = (int)(Math.random()*button.length);
		while(rand == lastChosen){
			rand = (int) (Math.random()*button.length);
		}
		lastChosen = rand;
		b = button[rand];
		return new Move(b);
	}
	
	private void addButtons(List<Visible> viewObjects) {
		int numberOfButtons = 5;
		//colors
		Color[] buttonColor = {Color.blue, Color.red,Color.black,Color.orange,Color.pink};
		//place all buttons
		button = new ButtonInterfaceMatthew[numberOfButtons];
		for(int i =0; i < numberOfButtons; i++){
			//b is an object that is a button interface
			button[i] = getAButton();
			button[i].setColor(buttonColor[i]);
			button[i].setX(100 + (i * 130));
			button[i].setY(200);
			final ButtonInterfaceMatthew b = button[i];
			b.dim();
			button[i].setAction(new Action(){
				public void act(){
					if(acceptingInput){
						Thread blink = new Thread(new Runnable(){
							public void run(){
								b.highlight();
								try {
									Thread.sleep(800);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								b.dim(); 
							}
						});
						blink.start();
						if(b == order.get(orderIndex).getButton()){
							orderIndex++;
						}else{
							gameOver();
						}
						if(orderIndex == order.size()){
							Thread nextRound = new Thread(SimonScreenMatthew.this);
							nextRound.start(); 
						}
					}
				}
			});
			viewObjects.add(b);
		}
	}
	
	private void changeText(String string) {
		try{
			label.setText(string);
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private ProgressInterfaceMatthew getProgress() {
		return new Progress();
	}

	private ButtonInterfaceMatthew getAButton() {
		return new Button();
	}



	
}
