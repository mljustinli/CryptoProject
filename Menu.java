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
	
	public GridKey promptForGridFile(Scanner scan) {
		System.out.println("The grid key file should by 8x8 "
				+ "with 1s representing holes and 0s representing non holes.");
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
	
	public GridKey promptForGridManually(Scanner scan) {
		String line = "";
		int[][] key = new int[8][8];
		
		System.out.println("Enter the grid key row by row. Each row should by 8 digits long. ");
		System.out.println("Press return after each row. A hole is a 1 and a non hole is a 0.");
		System.out.println("For example: 10000011");
		for (int i = 0; i < 8; i++) {
			System.out.print("> Row " + (i + 1) + ": ");
			line = scan.nextLine();
			for (int j = 0; j < line.length(); j++) {
				key[i][j] = (int)line.charAt(j) - (int)'0';
			}
		}
		GridKey gk = new GridKey(key);
		if (GridKey.isValidKey(gk)) {
			System.out.println("You entered this grid key:");
			System.out.println(gk);
			saveToFile(gk, scan);
			return gk;
		} else {
			return null;
		}
	}
	
	public void saveToFile(GridKey gk, Scanner scan) {
		String input = "";
		System.out.println("Enter a file name to save this grid key to.");
		input();
		input = scan.nextLine();
		
		PrintWriter output = null;
		try {
			output = new PrintWriter(new File(input));
			int[][] key = gk.getKey();
			for (int i = 0; i < key.length; i++) {
				for (int j = 0; j < key[0].length; j++) {
					output.print(key[i][j]);
				}
				if (i != key.length - 1) {
					output.println();
				}
			}
			System.out.println("Grid key saved.\n");
			output.close();
		} catch (FileNotFoundException ex) {
			System.out.println("File could not be found...\n");
		}
	}
	
	public void saveToFile(ArrayList<String> result, Scanner scan, String eOrD) {
		String input = "";
		System.out.println("Enter a file name to save the " + eOrD + " to.");
		input();
		input = scan.nextLine();
		
		PrintWriter output = null;
		try {
			output = new PrintWriter(new File(input));
			for (int i = 0; i < result.size(); i++) {
				if (i == result.size()) {
					output.print(result.get(i));
				} else {
					output.println(result.get(i));
				}
			}
			System.out.println("The " + eOrD + " was saved.\n");
			output.close();
		} catch (FileNotFoundException ex) {
			System.out.println("File could not be found...\n");
		}
	}
	
	public void printOptionList() {
		System.out.println("What would you like to do?");
		System.out.println("The last grid key you entered is the one that will be use for encryption and decryption.");
		System.out.println("1) Enter name of text file to encrypt." + "\n"
				+ "2) Enter name of text file to decrypt." + "\n"
				+ "3) Enter a grid key file name." + "\n"
				+ "4) Enter a grid key manually." + "\n"
				+ "5) Generate a grid key randomly." + "\n"
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
