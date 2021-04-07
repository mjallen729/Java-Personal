package clientSide;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Portal {
	
	private String username;
	private String name;
	private double balance;
	
	public Portal(String u) throws FileNotFoundException {
		
		username = u;
		
		Scanner read = new Scanner(new File("/Users/Matthew/Desktop/Database/" + username));
		ArrayList<String> userInfo = new ArrayList<String>();
		
		while (read.hasNextLine()) {
			userInfo.add(read.nextLine());
		}
		
		name = userInfo.get(0);
		balance = Double.parseDouble(userInfo.get(userInfo.size() - 1));
		
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String getName() {
		return name;
	}
	
	public void deposit(double d) throws IOException {
		
		double b = d * 100;
		int bb = (int) b;
		d = (double) bb / 100;
		balance += d;
		
		FileWriter fw = new FileWriter("/Users/Matthew/Desktop/Database/" + username, true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		
		pw.print(balance + "\n");
		pw.close();
	}
	
	public boolean withdrawal(double d) throws IOException {
		
		if (balance - d >= 0.0) {
			
			double b = d * 100;
			int bb = (int) b;
			d = (double) bb / 100;
			balance -= d;
			
			FileWriter fw = new FileWriter("/Users/Matthew/Desktop/Database/" + username, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.print(balance + "\n");
			pw.close();
			
			return true;
			
		} else {
			return false;
		}
		
		
		
	}
	
	private static void print(Object s) {
		System.out.print(s);
	}
	
	private static void println(Object s) {
		System.out.println(s);
	}
}
