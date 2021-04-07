package clientSide;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
	
	private String username;
	private String password;
	
	public Login(String u, String p) {
		
		username = u;
		password = p;
		
	}
	
	public boolean logon() throws FileNotFoundException {
		
		Scanner read = new Scanner(new File("/Users/Matthew/Desktop/Database/##accounts##.txt"));
		ArrayList<String> accs = new ArrayList<String>();
		
		while (read.hasNext()) {
			accs.add(read.nextLine());
		}
		
		read.close();
		
		if (accs.contains(username + "," + password)) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
