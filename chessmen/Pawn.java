package chessmen;

import java.util.Arrays;

public class Pawn extends Chessmen{
	
	public Pawn (String position, char color) throws Exception {
		super(position, color);
		name = "P" + color;
	}
	
	public void moveTo (String new_position) throws Exception {

	}
}
