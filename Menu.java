import java.util.*;
import java.io.*;

public class Menu {
	
	public String promptForSaveFile(Scanner scan) {
		System.out.println("Please enter the save file name.");
		input();
		return scan.nextLine();
	}
	
	public String promptForInputFile(Scanner scan) {
		System.out.println("Please enter the input file name.");
		input();
		return scan.nextLine();
	}
	
	public GridKey promptForGrid(Scanner scan) {
		String fileName = promptForInputFile(scan);	
		System.out.println();
		Scanner io;
		try {
			io = new Scanner(new File(fileName));
			String line = "";
			int count = 0;
			int[][] key = new int[8][8];
			while (io.hasNext()) {
				if (count > 7) {
					System.out.println("The grid key is too long.\n");
					io.close();
					return null;
				}
				line = io.nextLine();
				if (line.length() > 8) {
					System.out.println("The grid key is too wide.\n");
					io.close();
					return null;
				} else if (line.length() < 8) {
					System.out.println("The grid key is too narrow.\n");
					io.close();
					return null;
				}
				for (int i = 0; i < line.length(); i++)
					key[count][i] = (int)line.charAt(i) - (int)'0';
				count++;
			}
			if (count < 8) {
				System.out.println("The grid key is too short.\n");
				io.close();
				return null;
			}
			io.close();
			GridKey gk = new GridKey(key);
			if (GridKey.isValidKey(gk)) {
				System.out.println("You entered this grid key:");
				System.out.println(gk);
				return gk;
			} else {
				return null;
			}
		} catch (FileNotFoundException e) {
			noFile();
			return null;
		}
	}
	
	public void printOptionList() {
		System.out.println("What would you like to do?");
		System.out.println("1) Enter name of text file to encrypt." + "\n"
				+ "2) Enter name of file to decrypt." + "\n"
				+ "3) Enter a grid key file name." + "\n"
				+ "4) Generate a grid key randomly." + "\n"
				+ "Q) Exit the program.");
		input();
	}
	
	public void printExitText() {
		System.out.println("Bye now.");
	}
	
	public static void noFile() {
		System.out.println("Could not find that file.");
	}
	
	public static void input() {
		System.out.print("> ");
	}
}
