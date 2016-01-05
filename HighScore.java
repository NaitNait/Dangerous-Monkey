import java.util.*;
import java.io.*;
class HighScore {
	String name = "";
	int score = 0;
	int count = 0;
	
	public HighScore (String a, int b) {
		this.name = a;
		this.score = b;
	}
	
	public static String score (String a) {
		try {
			Scanner sc = new Scanner(new File("Scores.txt"));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				System.out.println(line);
			}
			sc.close();
		} catch (Exception ex){
			System.out.println("Error is:" + ex);
		}
		return null;
	}
}
