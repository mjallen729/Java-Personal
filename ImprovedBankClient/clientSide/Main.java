package clientSide;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		while (true) {	
			
			println("Welcome to Tamarian!");
			println("Type one of the following numbers:");
			print("(1)Login\n(2)Register\n");
			int choice = scan.nextInt();
			
			if (choice == 1) {  //login
				
				Scanner reg = new Scanner(System.in);
				
				print("Username: ");
				String user = reg.nextLine();
				
				print("Password: ");
				String pass = reg.nextLine();
				
				Login acc = new Login(user,pass);
				
				try {
					if (acc.logon() == true) {
						
						println("Logon Successful!\n");
						Portal u = new Portal(user);
						
						while(true) {
							
							println("Welcome " + u.getName() + "!");
							println("Your balance: " + u.getBalance());
							println("(1)withdrawal\n(2)deposit\n(3)transfer\n(4)logout");
							int v = scan.nextInt();
							
							if(v == 1) {
								
								print("\nAmount to withdrawal: ");
								double i = reg.nextDouble();
								
								if (u.withdrawal(i) == true) {
									println("Withdrawal Successful!\n");
								} else {
									println("Withdrawal Unsuccessful!\n");
								}
								
							} else if (v == 2) {
								
								print("\nAmount to deposit: ");
								double i = reg.nextDouble();
								
								u.deposit(i);
								println("Deposit Successful!\n");
								
							} else if (v == 3) {
								println("In development!\n");
							} else if (v == 4) {
								println("Goodbye...\n");
								break;
							} else {
								println("Invalid input!\n");
							}
						}
					} else {
						println("Login failed! Invalid user/pass\n");
						continue;
					}
				} catch (Exception e) {
					System.err.println("Critical Error - acc.logon");
					return;
				}
				
			} else if (choice == 2) {  //register
				
				Scanner reg = new Scanner(System.in);
				
				print("Enter username: ");
				String user = reg.nextLine();
				
				if (user.contains(" ")) {
					println("Invalid username: no spaces!\n");
					continue;
				} else if (user.length() < 1) {
					println("Invalid username: blank space!\n");
					continue;
				}
				
				print("Enter password: ");
				String pass = reg.nextLine();
				
				if (pass.length() < 4) {
					println("Password too short!\n");
					continue;
				}
				if (!(pass.matches(".*\\d.*"))) {
					println("Password must contain a number!\n");
					continue;
				}
				
				print("Enter full name: ");
				String name = reg.nextLine();
				
				Register newAcc = new Register(user,pass,name);
				
				try {
					if (newAcc.checkUser() == false) {
						
						println("Invalid username: username taken!\n");
						continue;
						
					} else {
						
						newAcc.write();
						println("Success!\n");
						
					}
				} catch (Exception e) {
					System.err.println("Critical Error - newAcc.checkUser");
					return;
				}
				
			} else {
				println("Invalid choice!\n");
			}
			
		}
		
	}
	
	
	private static void test() {
		
		
	}
	
	private static void print(Object s) {
		System.out.print(s);
	}
	
	private static void println(Object s) {
		System.out.println(s);
	}

}
