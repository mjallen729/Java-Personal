import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class FileExtract {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		println("Enter path of file:");
		String path = scan.nextLine();
		
		File directory = new File(path);
		File[] fList = directory.listFiles();
		
		File dest = new File("/Users/Matthew/Desktop/Export");
		
		go(fList, dest);
		
		print("\nDONE!");
		
	}
	
	public static void go(File[] fl, File dest) {
		
		for(File f : fl) {
			
			if(f.isDirectory()) {
				
				print("\nLooking...\n");
				go(f.listFiles(),dest);
				
			} else {
				
				try {
					FileUtils.copyFileToDirectory(f, dest);
				} catch (IOException e) {
					System.err.println("Stopped with error: IOException!");
				}
				
				println("\t(" + f.getName() + ")");
				
			}
			
		}
		
	}
	
	private static void print(Object s) {
		System.out.print(s);
	}
	
	private static void println(Object s) {
		System.out.println(s);
	}

}
