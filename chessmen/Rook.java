package chessmen;

public class Rook extends Chessmen {

	public Rook(String position, char color) throws Exception {
		super(position, color);
		name = "R" + color;
	}

}
