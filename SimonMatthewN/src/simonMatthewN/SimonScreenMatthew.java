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
		/*
		acceptingInput = false;
		round++;
		order.add(randomMove());
		ProgressInterfaceMatthew.setRound(round);
		ProgressInterfaceMatthew.setSequenceSize(order.size());
		changeText("It is Simon's turn.");
		label.setText("");
		playSequence();
		changeText("It is your turn.");
		acceptingInput = true;
		orderIndex = 0;
		*/
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
		/*
		addButtons(viewObjects);
		progress = getProgress();
		label = new TextLabel(130,230,300,40," ");
		order = new ArrayList<MoveInterfaceMatthew>();
		//add 2 moves to start
		round = 0;
		lastChosen = -1;
		order.add(randomMove());
		order.add(randomMove());
		viewObjects.add(progress);
		viewObjects.add(label);
		*/
		Color[] colors = {Color.red, Color.blue, new Color(240,160,70), new Color(20,255,140), Color.yellow, new Color(180,90,210)};
		String[] names = {"RED", "BLUE", "ORANGE", "GREEN", "YELLOW", "PURPLE"};
		int buttonCount = 6;
		button = new ButtonInterfaceMatthew[buttonCount];
		for(int i = 0; i < buttonCount; i++ ){
			button[i] = getAButton();
			button[i].setName(names[i]);
			button[i].setColor(colors[i]);
			button[i].setX(160 + (int)(100*Math.cos(i*2*Math.PI/(buttonCount))));
			button[i].setY(200 - (int)(100*Math.sin(i*2*Math.PI/(buttonCount))));
			final ButtonInterfaceMatthew b = button[i];
			System.out.println(b+" has x = "+b.getX()+", y ="+b.getY());
			b.dim();
			button[i].setAction(new Action() {

				public void act() {

						Thread buttonPress = new Thread(new Runnable() {
							
							public void run() {
								b.highlight();
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								b.dim();
								
							}
						});
						buttonPress.start();
						

						if(acceptingInput && order.get(orderIndex).getButton() == b){
							orderIndex++;
						}else if(acceptingInput){
							gameOver();
							return;
						}
						if(orderIndex == order.size()){
							Thread nextRound = new Thread(SimonScreenMatthew.this);
							nextRound.start();
						}
					}

			});
			viewObjects.add(button[i]);
		}
		progress = getProgress();
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
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
	/*
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
							ProgressInterfaceMatthew.gameOver();
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
	*/
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
	@Override
	public void initObjects(ArrayList<Visible> arg0) {
		// TODO Auto-generated method stub
		
	}
}
