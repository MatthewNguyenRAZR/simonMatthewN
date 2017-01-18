package simonMatthewN;

import java.awt.Color;

import gui.components.Action;
import gui.components.Clickable;

public interface ButtonInterfaceMatthew extends Clickable {

	void dim();

	void highlight();

	void setColor(Color color);

	void setAction(Action action);

}
