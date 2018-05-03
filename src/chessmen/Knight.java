package chessmen;

public class Knight extends Chessmen {

	public Knight(String position, char color) throws Exception {
		super(position, color);
		name = "N" + color;
	}
	
	public void moveTo(String position) {
		
	}
	
	public String[] previewMovement(String position) {
		return new String[1];
	}
}
