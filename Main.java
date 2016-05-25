import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Scanner fileIO;
		
		GridKey gk = null;
		Menu menu = new Menu();
		String inputFile = "", saveFile = "", gridFile = "";
		String input = "";
		
		while (!input.equals("Q") && !input.equals("q")) {
			menu.printOptionList();
			input = scan.nextLine();
			switch (input) {
			case "1":
				if (gk == null) {
					System.out.println("Please enter a grid key file first before encrypting.\n");
					break;
				}
				inputFile = menu.promptForInputFile(scan);
				System.out.println();
				try {
					fileIO = new Scanner(new File(inputFile));
					System.out.println("The result is:");
					while (fileIO.hasNextLine()) {
						System.out.println(gk.encrypt(fileIO.nextLine()));
					}
					System.out.println();
				} catch (FileNotFoundException e) {
					Menu.noFile();
				}
				break;
			case "2":
				if (gk == null) {
					System.out.println("Please enter a grid key file first before encrypting.\n");
					break;
				}
				inputFile = menu.promptForInputFile(scan);
				System.out.println();
				try {
					fileIO = new Scanner(new File(inputFile));
					System.out.println("The result is:");
					while (fileIO.hasNextLine()) {
						System.out.println(gk.decrypt(fileIO.nextLine()));
//						System.out.println(fileIO.nextLine());
					}
					System.out.println();
				} catch (FileNotFoundException e) {
					Menu.noFile();
				}
				break;
			case "3":
				gk = menu.promptForGridFile(scan);
				break;
			case "4":
				gk = menu.promptForGridManually(scan);
				break;
			case "5":
				gk = GridKey.genRandomKey(scan);
				break;
			case "Q":
			case "q":
				menu.printExitText();
				break;
			}
		}
		
		
		
		
		
		scan.close();
	}
}
