package chessmen;

public class Bishop extends Chessmen {

	public Bishop(String position, char color) throws Exception {
		super(position, color);
		name = "B" + color;
	}
	
}
