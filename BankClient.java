import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Diagnostic {
	
	public static void main(String[] args) throws IOException {
		String username = null;
		String password = null;
		String userreg = null;
		String passreg = null;
		String choice = null;
		String transfer = null;
		String passTemp = null;
		double transferVal = 0;
		double memory = 0;
		double balance = 0.00;
		double secure;
		boolean logout = false;
		boolean nametaken = true;
		boolean badpass = true;
		Scanner scan = new Scanner(System.in);
		Scanner transaction = new Scanner(System.in);
		List<String> accounts = new ArrayList<String>();
		List<String> usernames = new ArrayList<String>();
		
		while (true) {
		Scanner reader = new Scanner(new File("accounts.txt"));
		accounts.clear();
		while (reader.hasNext()){
		   String str = reader.nextLine();
		   accounts.add(str);
		}
		
		Scanner reader2 = new Scanner(new File("usernames.txt"));
		usernames.clear();
		while (reader2.hasNext()){
		   String str2 = reader2.nextLine();
		   usernames.add(str2);
		}
		
		nametaken = true;
		badpass = true;
		logout = false;
		System.out.println("Welcome to Tamarin© online banking!");
		System.out.println("Select register or login:");
		
		choice = scan.nextLine();
		
		if (choice.equalsIgnoreCase("register")) {
			while (nametaken == true) {
				System.out.println("\nWelcome to register! Please type your username:");
				userreg = scan.nextLine();
				
				if (usernames.contains(userreg)) {
					System.out.println("Username taken! Try again.");
				} else {
					nametaken = false;
					
					while (badpass == true) {
						System.out.println("Great! Now create a password:");
						passreg = scan.nextLine();
						
						if (!(passreg.matches(".*\\d.*"))) {
							System.out.println("Password must contain a number");
						} else {
							badpass = false;
							System.out.println("Successfully registered! You may now log in!\n");
							
							PrintWriter writer = new PrintWriter(passreg + ".txt");
							
						}
					} //while badpass
				}
			} //while nametaken
			try(FileWriter fw = new FileWriter("accounts.txt", true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
				{
				    out.println(userreg + "," + passreg + "\n");
				} catch (IOException e) {
					System.out.println("DEV MSG: Failed to write files!");
					System.out.println("Error- 'out\\println\\userreg,passreg\\accounts.txt'");
					e.printStackTrace();
					return;
				}
			try(FileWriter fw = new FileWriter("usernames.txt", true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
				{
				    out.println(userreg + "\n");
				} catch (IOException e) {
					System.out.println("DEV MSG: Failed to write username!");
					System.out.println("Error- 'out\\println\\userreg\\usernames.txt'");
					e.printStackTrace();
					return;
				}
		} //if register
		
		if (choice.equalsIgnoreCase("login")) {
			System.out.println("\nWhat is your username?");
			username = scan.nextLine();
			System.out.println("Awesome! Now enter your password:");
			password = scan.nextLine();
			
			while (logout == false) {
			if (accounts.contains(username + "," + password)) {
				Scanner reader4 = new Scanner(new File(password + ".txt"));
				reader4 = new Scanner(new File(password + ".txt"));
				while (reader4.hasNextDouble()) {
				   double str4 = reader4.nextDouble();
				   balance = str4;
				}
			System.out.println("\nWelcome " + username + "! Type 'logout' to return home. Type 'withdrawal', 'deposit', or 'transfer' to make a transaction.");
			System.out.println("Your account balance is currently $" + balance + "!");
			
			choice = scan.nextLine();
			
			} else {
				System.out.println("Invalid username or password!\n");
				logout = true;
			}
			
			if (choice.equalsIgnoreCase("accounts")) {
				if (username.equals("admin")) {
					System.out.println((accounts));
				}
			}
			
			if (choice.equalsIgnoreCase("logout")) {
				System.out.println("Successfully logged out!\n");
				logout = true;
			}
			
			if (choice.equalsIgnoreCase("withdrawal")) {
				if (balance != 0.00) {
					System.out.print("Select an amount: ");
					secure = transaction.nextDouble();
					secure = Math.round(secure * 100.0) / 100.0;
					
					if ((balance - secure) < 0.00) {
						System.out.println("\nYou cannot withdrawal more than you own!");
					} else {
						balance = (balance - secure);
						System.out.println("Success!");
						
						try(FileWriter fw = new FileWriter(password + ".txt", true);
							    BufferedWriter bw = new BufferedWriter(fw);
							    PrintWriter out = new PrintWriter(bw))
							{
							    out.println(balance + "\n");
							} catch (IOException e) {
								System.err.println("DEV MSG: Failed to write files!");
								System.err.println("Error- 'out\\println\\balance'");
								e.printStackTrace();
								return;
							}
					}
					
				} else {
					System.out.println("\nYou have no money!");
				}
			}
			
			if (choice.equalsIgnoreCase("deposit")) {
				System.out.println("How much do you want to deposit?");
				secure = transaction.nextDouble();
				secure = Math.round(secure * 100.0) / 100.0;
				System.out.println("Success!");
				balance = (balance + secure);
				
				try(FileWriter fw = new FileWriter(password + ".txt", true);
					    BufferedWriter bw = new BufferedWriter(fw);
					    PrintWriter out = new PrintWriter(bw))
					{
					    out.println(balance + "\n");
					} catch (IOException e) {
						System.out.println("DEV MSG: Failed to write files!");
						System.out.println("Error- 'out\\println\\balance'");
						e.printStackTrace();
						return;
					}
			}
			
			if (choice.equalsIgnoreCase("transfer")) {
				System.out.println("To whom would you like to transfer?");
				transfer = scan.nextLine();
				
				if (usernames.contains(transfer)) {
					System.out.print("$--");
					transferVal = scan.nextDouble();
					
					if ((balance - transferVal) >= 0) {
						balance = balance - transferVal;
						
						try(FileWriter fw = new FileWriter(password + ".txt", true);
							    BufferedWriter bw = new BufferedWriter(fw);
							    PrintWriter out = new PrintWriter(bw))
							{
							    out.println(balance + "\n");
							} catch (IOException e) {
								System.err.println("DEV MSG: Failed to write files!");
								System.err.println("Error- 'out\\println\\balance'");
								e.printStackTrace();
								return;
							}
						
						for (int i = 0; i < accounts.size(); i++) {
							if (accounts.get(i).contains(transfer)) {
								char[] transTemp = accounts.get(i).toString().toCharArray();
								
								for (int e = 0; e < transTemp.length; e++) {
									if (transTemp[e] == ',') {
										String temp = new String(transTemp);
										passTemp = temp.substring(e+1, temp.length());
										break;
									}
								}
								break;
							}
						}
						
						Scanner transScan = new Scanner(new File(passTemp + ".txt"));
						while (transScan.hasNextDouble()) {
							memory = transScan.nextDouble();
						}
						
						memory = memory + transferVal;
						
						try(FileWriter fw = new FileWriter(passTemp + ".txt", true);
								BufferedWriter bw = new BufferedWriter(fw);
								PrintWriter out = new PrintWriter(bw))
							{
								out.println(memory + "\n");	
							} catch (IOException e) {
								System.err.println("DEV MSG: Failed to write files!");
								System.err.println("Error- 'out\\println\\balance'");
								e.printStackTrace();
								return;
							}
						
						System.out.println("Transfer Success!");	
					} else {
						System.out.println("You cannot transfer more than you own!");
					}
				} else {
					System.err.println("Error: Unable to transfer, username not recognized.");
				}
			}
			} //while logout
		} //if login
		} //while true
	} //LEAVE
} //LEAVE