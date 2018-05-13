//Marc Fors
package player;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Player {
	//Properties
	private String email;
	private char color;
	private String name;
	protected Exception InvalidEmail;
	protected Exception InvalidColor;
	protected Exception InvalidName;
	
	//Constructors
	public Player(String email, char color, String name) throws Exception {
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
	
	public char getColor() {
		return this.color;
	}
	
	public void setColor(char color) throws Exception {
		if (color == 'b' || color == 'w') {
			this.color = color;
		}else {
			throw InvalidColor;
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) throws Exception {
		if (isValidName(name)) {
			this.name = name;
		}else {
			throw InvalidName;
		}
	}
	
	//Methods
	public static boolean isValidEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
	}
	
	public static boolean isValidName(String str) {
		str = str.trim(); //Delete white spaces at the end and start

	    if(str == null || str.equals("") || str.length() > 20) {
	        return false;
	    }

	    if(!str.matches("[a-zA-Z]*")) {
	        return false;
	    }

	    return true;
	}
	
	/*
	public static void main(String[] args) throws Exception {
		//comentar todas la pruebas menos la que se quiere probar
		//Todo ok
		Player player= new Player("marcfs31@gmail.com",'b',"Marc");
		System.out.println("Color: "+player.color);
		System.out.println("email: "+player.email);
		System.out.println("name: "+player.name);
		
		//Primero mal
		Player player2= new Player("marcfs31@gmail..com",'b',"Marc");
		System.out.println("Color: "+player2.color);
		System.out.println("email: "+player2.email);
		System.out.println("name: "+player2.name);
		
		//Primero mal
		Player player3= new Player("marcfs31@gmail..com",'b',"Marc");
		System.out.println("Color: "+player3.color);
		System.out.println("email: "+player3.email);
		System.out.println("name: "+player3.name);
				
				
		//Primero mal
		Player player4= new Player("marcfs31@gmail..com",'b',"Marc");
		System.out.println("Color: "+player4.color);
		System.out.println("email: "+player4.email);
		System.out.println("name: "+player4.name);
		
	}
	*/
}
