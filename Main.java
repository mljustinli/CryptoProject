import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Scanner fileIO;
		
		GridKey gk = null;
		Menu menu = new Menu();
		String inputFile = "";
		String input = "";
		
//		System.out.println(GridKey.isValidKey(GridKey.genRandomKey(scan)));
		
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
					ArrayList<String> result = new ArrayList<String>();
					fileIO = new Scanner(new File(inputFile));
					System.out.println("The result is:");
					while (fileIO.hasNextLine()) {
						String encrypted = gk.encrypt(fileIO.nextLine());
						result.add(encrypted);
						System.out.println(encrypted);
					}
					System.out.println();
					menu.saveToFile(result, scan, "encryption");
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
					ArrayList<String> result = new ArrayList<String>();
					fileIO = new Scanner(new File(inputFile));
					System.out.println("The result is:");
					while (fileIO.hasNextLine()) {
						String decrypted = gk.decrypt(fileIO.nextLine());
						result.add(decrypted);
						System.out.println(decrypted);
					}
					System.out.println();
					menu.saveToFile(result, scan, "decryption");
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
				System.out.println("The generated grid key is: ");
				System.out.println(gk);
				menu.saveToFile(gk, scan);
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
