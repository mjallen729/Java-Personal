package clientSide;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Register {
	
	private String username;
	private String password;
	private String name;
	
	public Register(String u, String p, String n) {
		
		username = u;
		password = p;
		name = n;
		
	}
	
	public boolean checkUser() throws FileNotFoundException {
			
		Scanner read = new Scanner(new File("/Users/Matthew/Desktop/Database/##usernames##.txt"));
		ArrayList<String> users = new ArrayList<String>();
			
		while (read.hasNext()) {
			users.add(read.nextLine());
		}
		
		read.close();
		
		if (users.contains(username)) {
			return false;
		}
		
		return true;
	}
	
	public void write() {
		
		try {
			
			FileWriter fw = new FileWriter("/Users/Matthew/Desktop/Database/##usernames##.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.print(username + "\n");
			pw.close();
			
		} catch (Exception e) {
			System.err.println("Critical Error - write().usernames");
			return;
		}
		
		try {
			
			FileWriter fw = new FileWriter("/Users/Matthew/Desktop/Database/##accounts##.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.print(username + "," + password + "\n");
			pw.close();
			
		} catch (Exception e) {
			System.err.println("Critical Error - write().passwords");
			return;
		}
		
		File userLog = new File("/Users/Matthew/Desktop/Database/" + username);
		
		try {
			
			FileWriter fw = new FileWriter(userLog, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.print(name + "\n" + "0\n");
			pw.close();
			
		} catch (Exception e) {
			System.err.println("Critical Error - write().userLog");
			return;
		}
		
	}
	
}
