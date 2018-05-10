package chessmen;

import java.util.ArrayList;

public abstract class Chessmen {
	
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
		ArrayList<String> posible = this.previewMovement();
		
		if (posible.indexOf(new_position) >= 0) {
			this.position = new_position;
		} else {
			throw InvalidPosition;
		}
	}
	
	abstract public ArrayList<String> previewMovement();

	private boolean correctColor(char color) {
		if (color == 'w' || color == 'b') {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return this.name;
	}
}
