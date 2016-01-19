/**
 * HighScore class
 *
 * Gets of the high scores of the game
 * This class includes an ArrayList, and reading and writing to files
 * 
 * #rehan
 **/
import java.io.*;
import java.util.*;

//#method
class HighScore {
	
	//initialize variables
	String name = "";
	int score = 0;
	
	static File file = new File("Scores.txt");
	
	static ArrayList<HighScore> arrHighScoreList = new ArrayList<HighScore>();
	
	//#define
	/**
	 * HighScore method
	 * Creates high score object
	 * @param Sring a - name of the person
	 * @param int b - score the person got
	 **/
	public HighScore (String a, int b) {
		this.name = a;
		this.score = b;
	}//end HighScore
	
	
	public static void getScores() throws IOException {
		//#error
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextInt()) {
				int num = sc.nextInt();
				String line = sc.nextLine();
				//#define
				HighScore hScore = new HighScore(line, num);
				arrHighScoreList.add(hScore);
			}//end while
			sc.close();
		} catch (Exception ex) {
			System.out.println("Error is:" + ex);
		}//end try/catch

		sortScores();
		writeScores();
		
	}//end getScores
	
	// #alg
	public static void sortScores() {
		for (int i = 1; i < arrHighScoreList.size(); i++) {
			HighScore temp = arrHighScoreList.get(i);
			int j = i;
			while (j > 0 && arrHighScoreList.get(j-1).score < temp.score) {
				arrHighScoreList.set(j, arrHighScoreList.get(j-1));
				j--;
			}//end while
			arrHighScoreList.set(j, temp);
		}//end for loop
	}//end sortScores
	
	//#save
	public static void writeScores() throws IOException {
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
			for (int i  = 0; i < 20; i++) {
				pw.println(arrHighScoreList.get(i).score + arrHighScoreList.get(i).name);
			}//end for
		
		pw.close();
		
	}//end writeScores

	public static void printScores() {
		for (int i = 0; i < arrHighScoreList.size();i++) {
			System.out.println(arrHighScoreList.get(i).score + "," + arrHighScoreList.get(i).name);
		}//end for loop
		
	}//end printScores
	
}//end HighScore
