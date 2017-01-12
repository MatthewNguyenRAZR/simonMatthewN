package simonMatthewN;

import java.awt.Color;
import java.util.ArrayList; 
import java.util.List;

import gui.components.Action;
import gui.components.TextLabel;
import gui.components.Visible;
import gui.screens.ClickableScreen;

public class SimonScreenMatthew extends ClickableScreen implements Runnable {
	
	private ProgressInterfaceMatthew user;
	private ArrayList<MoveInterfaceMatthew> order;
	private ButtonInterfaceMatthew[] button;
	private int round;
	private boolean acceptingInput;
	private int orderIndex;
	private int last;
	private TextLabel label;
	private ProgressInterfaceMatthew progress;

	public SimonScreenMatthew(int width, int height) {
		super(width, height);
		Thread simonStart = new Thread(this);
		simonStart.start();
	}

	@Override
	public void run(){
	    label.setText("");
	    nextRound();
	}

	private void nextRound() {
		acceptingInput = false;
		round++;
		order.add(randomMove());
		ProgressInterfaceMatthew.setRound(round);
		ProgressInterfaceMatthew.setSequenceSize(order.size());
		changeText("Simon's turn");
		label.setText("");
		playSequence();
		changeText("Your turn");
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

	public void initAllObjects(ArrayList<Visible> viewObjects) {
		addButtons(viewObjects);
		progress = getProgress();
		label = new TextLabel(130,230,300,40," ");
		order = new ArrayList<MoveInterfaceMatthew>();
		//add 2 moves to start
		round = 0;
		last = -1;
		order.add(randomMove());
		order.add(randomMove());
		viewObjects.add(progress);
		viewObjects.add(label);
	}
	
	private MoveInterfaceMatthew randomMove() {
		ButtonInterfaceMatthew b;
		//code that randomly selects a ButtonInterfaceX
		int rand = (int)(Math.random()*button.length);
		//if its equal then pick a new
		while(rand == last){
			rand = (int) (Math.random()*button.length);
		}
		//change the last select into rand
		last = rand;
		/**
		 * FIX LATER
		 */
		b = button[rand];
		return getAMove(b);
	}


	private MoveInterfaceMatthew getAMove(ButtonInterfaceMatthew b) {
		return null;
	}

	private void addButtons(List<Visible> viewObjects) {
		int numberOfButtons = 5;
		//colors
		Color[] buttonColor = {Color.blue, Color.red,Color.black,Color.orange,Color.pink};
		//place all buttons
		for(int i =0; i < numberOfButtons; i++){
			//b is an object that is a button interface
			final ButtonInterfaceMatthew b = getAButton();
			b.setColor(buttonColor[i]);
			b.setX(100+(i*20));
			b.setY(100+(i*20));
			b.setAction(new Action(){
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
							ProgressInterfaceMatthew.getOver();
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

	/**
	 * FOR PARTNER TO FINSISH
	 */
	private ProgressInterfaceMatthew getProgress() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * FOR PARTNER TO FINSISH
	 */
	private ButtonInterfaceMatthew getAButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initAllObjects(List<Visible> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initObjects(ArrayList<Visible> arg0) {
		// TODO Auto-generated method stub
		
	}
}