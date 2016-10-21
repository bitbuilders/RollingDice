package rolling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Stones {
	
	private static int numOfDice = 0;
	private static int[] dice;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		System.out.println("Welcome to the Rolling Dice program!");
		promptForNum();
	}
	
	public static void promptForNum() {
		String input = "";
		System.out.print("How many dice do you want rolled? Enter a positive integer greater than 0: ");
		do {
			try {
				input = reader.readLine();
				numOfDice = Integer.parseInt(input);
			}catch(IOException ioe) {
				System.out.println("Invalid input.");
			}catch(NumberFormatException nfe) {
				System.out.println("Input a valid integer greater than 0.");
			}
			
			if (numOfDice <= 0 ) {
				input = "";
				System.out.println("A number more than 0 please.");
			}
			else {
				dice = new int[numOfDice];
			}
		}while(input.equals(""));
		randomize();
	}
	
	private static void randomize() {
		Random rand = new Random();
		for (int i = 0; i < dice.length; i++) {
			int num = rand.nextInt(6) + 1;
			dice[i] = num;
		}
		output();
	}
	
	private static void output() {
		int sum = 0;
		int maxNum = 0;
		int minNum = 7;
		String rolled = "Your Rolled: ";
		double mostSum = 0;
		double mostCount = 0;
		double mostNumber = 0;
		
		for (int i = 0; i < dice.length; i++) {
			rolled += dice[i] + ", ";
			sum += dice[i];
			
			if (dice[i] > maxNum)
				maxNum = dice[i];
			if (dice[i] < minNum)
				minNum = dice[i];
		
		}
		
		for (int i = 0; i < 7; i++) {
			int count = rolled.length() - rolled.replace(i + "", "").length();
			//System.out.println("Number of " + i + "'s: " + count);
			if (count > mostCount) {
				mostCount = count;
				mostSum = i;
				mostNumber = 1;
			}
			else if (count == mostCount) {
				mostSum += i;
				mostNumber++;
			}
		}
		
		//For loop for second version of 'mode' function
//		for (int i = 0; i < 6; i++) {
//			int count = rolled.length() - rolled.replace(i + "", "").length();
//			if (count > mostCount) {
//				mostCount = count;
//				mostRolled = i + "";
//			}
//			else if (count == mostCount) {
//				mostRolled += ", and " + i;
//			}
//		}
		
		double diceD = numOfDice;
		double sumd = sum;
		double d = sumd / diceD;
		double mean = Math.round(d * 100d) / 100d;
		
		double db = mostSum / mostNumber;
		double most = Math.round(db * 100d) / 100d;
		
		System.out.println(rolled);
		System.out.println("The sum of all of the dice rolled is: " + sum);
		System.out.println("The highest number rolled is: " + maxNum);
		System.out.println("The lowest number rolled is: " + minNum);
		System.out.println("The mode of the rolls is: " + most);
		System.out.println("The average of all the rolls is: " + mean);
		System.out.println("");
		
		again();
	}
	
	public static void again() {
		String input = "";
		System.out.print("Roll again? Type: 'Yes', or 'No'. ");
		do {
			try {
			input = reader.readLine();
			}catch(IOException ioe) {
				System.out.println("Invalid input.");
			}
			
			if (input.equalsIgnoreCase("yes")) {
				numOfDice = 0;
				promptForNum();
			}
			else if (input.equalsIgnoreCase("no")) {
				System.out.println("Closing application...");
			}
			else {
				input = "";
				System.out.print("Enter 'Yes', or 'No'. ");
			}
		}while(input.equals(""));
	}
}
