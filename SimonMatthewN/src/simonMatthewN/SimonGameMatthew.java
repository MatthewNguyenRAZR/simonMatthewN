package simonMatthewN;

import gui.GUIApplication;

public class SimonGameMatthew extends GUIApplication {

	public SimonGameMatthew(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub 
	}

	@Override 
	public void initScreen() {
		SimonScreenMatthew simonScreen = new SimonScreenMatthew(getWidth(),getHeight());
		setScreen(simonScreen);
	}

	public static void main(String[] args) {
		SimonGameMatthew simonGame = new SimonGameMatthew(800,500); //instantiate
		Thread game = new Thread(simonGame); //create thread for game
		game.start(); //start the thread/game
	}

}
