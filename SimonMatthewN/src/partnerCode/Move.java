package partnerCode;

import simonMatthewN.ButtonInterfaceMatthew;
import simonMatthewN.MoveInterfaceMatthew;

public class Move implements MoveInterfaceMatthew {
	
	private ButtonInterfaceMatthew b;

	public Move(ButtonInterfaceMatthew b) {
		this.b = b;
	}

	@Override
	public ButtonInterfaceMatthew getButton() {
		return b;
	}

}
