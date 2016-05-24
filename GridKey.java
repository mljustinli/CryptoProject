
public class GridKey {
	public static final String[] DIRS = {"North", "East", "South", "West"};
	
	private int[][] key;
	
	public GridKey(int[][] _key) {
		key = _key;
	}
	
	public int[] getKey(String dir) {
		if (dir.equals("North")) {
			
		}
	}
	
	public String encrypt(String text) {
		
	}
	
	public String decrypt(String text) {
		
	}
	
	public void setKey(int[][] _key) {
		key = _key;
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
	
	public static GridKey genRandomKey() {
		
	}
	
	public static boolean isValidKey(GridKey gk) {
		
	}
}
