package chessmen;

import java.util.ArrayList;

public class Knight extends Chessmen {

	public Knight(String position, char color) throws Exception {
		super(position, color);
		name = "N" + color;
	}
	
	public String[] previewMovement(String position) {
		return new String[1];
	}

	@Override
	public ArrayList<String> previewMovement() {
		// TODO Auto-generated method stub
		return null;
	}
}
