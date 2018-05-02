package chessmen;

import java.util.ArrayList;

public class Chessmen {
	
	protected char color;
	protected String position;
	protected String name;
	
	protected Exception InvalidPosition;
	protected Exception InvalidColor;
	
	public Chessmen (String position, char color) throws Exception {
		if (correctColor(color)) {
			this.position = position;
			this.color = color;
		} else {
			throw InvalidColor;
		}
	}
	
	public String getPosition() {
		return position;
	}
	
	public String getName() {
		return name;
	}

	public char getColor() {
		return color;
	}
	
	public void moveTo(String new_position) throws Exception {
		position = new_position;
	}
	
	public ArrayList<String> previewMovement() {
		return new ArrayList<String>();
	}	

	private boolean correctColor(char color) {
		if (color == 'w' || color == 'b') {
			return true;
		}
		return false;
	}
}
