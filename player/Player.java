package player;

public class Player {
	//Propieties
	private String email;
	public String color;
	public String name;
	protected Exception InvalidEmail;
	
	//Constructors
	public Player(String email, String color, String name) throws Exception {
		setEmail(email);
		setColor(color);
		setName(name);
	}
	
	//Getters Setters
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email)throws Exception {
		if (isValidEmail(email)) {
			this.email = email;
		}else {
			throw InvalidEmail;
		}
	}
	
	public String getColor() {
		return this.color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	//Methods
	public static boolean isValidEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}
}
