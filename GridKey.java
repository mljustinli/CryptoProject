import java.util.*;
import java.io.*;

public class GridKey {
	public static final String[] DIRS = {"North", "East", "South", "West"};
	public static final int[][] NUM_KEY = {
		{1, 2, 3, 4, 13, 9, 5, 1},
		{5, 6, 7, 8, 14, 10, 6, 2},
		{9, 10, 11, 12, 15, 11, 7, 3},
		{13, 14, 15, 16, 16, 12, 8, 4},
		{4, 8, 12, 16, 16, 15, 14, 13},
		{3, 7, 11, 15, 12, 11, 10, 9},
		{2, 6, 10, 14, 8, 7, 6, 5},
		{1, 5, 9, 13, 4, 3, 2, 1}
	};
	
	private int[][] key;
	
	public GridKey(int[][] _key) {
		key = _key;
	}
	
	public GridKey(String inputFile) {
		
	}
	
	public String encrypt(String text) {
		String newText = "";
		for (int i = 0; i < 64 - text.length(); i++) {
			newText += "^";
		}
		for (int i = text.length() - 1; i >= 0; i--) {
			newText += text.charAt(i);
		}
		
		String[][] rArr = new String[8][8];
		int count = 0;
		for (int d = 0; d < DIRS.length; d++) {
			int[][] nKey = getKey(DIRS[d], key);
			for (int i = 0; i < nKey.length; i++) {
				for (int j = 0; j < nKey[0].length; j++) {
					if (nKey[i][j] == 1) {
						rArr[i][j] = newText.charAt(count) + "";
						count++;
					}
				}
			}
		}
		
		String rText = "";
		for (int i = 0; i < rArr.length; i++) {
			for (int j = 0; j < rArr[0].length; j++) {
				rText += rArr[i][j];
			}
		}
		return rText;
	}
	
	public String decrypt(String text) {
		String[][] strArr = new String[8][8];
		String rText = "";
		
		int count = 0;
		for (int i = 0; i < strArr.length; i++) {
			for (int j = 0; j < strArr[0].length; j++) {
				strArr[i][j] = text.charAt(count) + "";
				count++;
			}
		}
		
		for (int d = DIRS.length - 1; d >= 0; d--) {
			int[][] nKey = getKey(DIRS[d], key);
			for (int i = nKey.length - 1; i >= 0; i--) {
				for (int j = nKey[0].length - 1; j >= 0; j--) {
					if (nKey[i][j] == 1) {
						rText += strArr[i][j];
					}
				}
			}
		}
		
		return stripCaret(rText);
	}
	
	public static String stripCaret(String text) {
		if (text.indexOf("^") == -1) {
			return text;
		} else {
			return text.substring(0, text.indexOf("^"));
		}
	}
	
	public void setKey(int[][] _key) {
		key = _key;
	}
	
	public int[][] getKey() {
		return key;
	}
	
	public void printArray(int[][] _key) {
		for (int i = 0; i < _key.length; i++) {
			for (int j = 0; j < _key[0].length; j++) {
				System.out.print(_key[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public String toString() {
		String rStr = "";
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < key[0].length; j++) {
				rStr += key[i][j] + " ";
			}
			rStr += "\n";
		}
		return rStr;
	}
	
	/*
	 * Static functions
	 */
	
	public static int[][] getKey(String dir, int[][] key) {
		int[][] rKey = new int[8][8];
		
		switch (dir) {
		case "North":
			rKey = key;
			break;
		case "East":
			for (int i = 0; i < key.length; i++)
				for (int j = 0; j < key[0].length; j++)
					rKey[j][key[0].length - i - 1] = key[i][j];
			break;
		case "South":
			for (int i = 0; i < key.length; i++)
				for (int j = 0; j < key[0].length; j++)
					rKey[key.length - i - 1][key[0].length - j - 1] = key[i][j];
			break;
		case "West":
			for (int i = 0; i < key.length; i++)
				for (int j = 0; j < key[0].length; j++)
					rKey[key.length - j - 1][i] = key[i][j];
			break;
		default:
			System.out.println("Something went wrong in the switch statement!");
		}
		
		return rKey;
	}
	
	public static GridKey genRandomKey(Scanner scan) {
		int[][] key = new int[8][8];
		
		String dir;
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				dir = DIRS[(int)(Math.random() * 4)];
				switch(dir) {
				case "North":
					key[i][j] = 1;
					break;
				case "East":
					key[j][key[0].length - i - 1] = 1;
					break;
				case "South":
					key[key.length - i - 1][key[0].length - j - 1] = 1;
					break;
				case "West":
					key[key.length - j - 1][i] = 1;
					break;
				}
			}
		}
		return new GridKey(key);
	}
	
	public static boolean isValidKey(GridKey gk) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < gk.getKey().length; i++) {
			for (int j = 0; j < gk.getKey()[0].length; j++) {
				if (gk.getKey()[i][j] == 1) {
					if (!set.add(NUM_KEY[i][j])) {
						System.out.println("The grid key has two spaces with the same number.\n");
						return false;
					}
				} else if (gk.getKey()[i][j] != 1 && gk.getKey()[i][j] != 0) {
					System.out.println("The grid key has a number other than 0 and 1.\n");
					return false;
				}
			}
		}
		if (set.size() < 16) {
			System.out.println("The grid key has too few spaces.\n");
			return false;
		}
		return true;
	}
}
