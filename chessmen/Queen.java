package chessmen;

public class Queen extends Chessmen {

	public Queen(String position, char color) throws Exception {
		super(position, color);
		name = "Q" + color;
	}

}
