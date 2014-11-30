
import java.util.Scanner;

public class Project3 {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("1. Player starts first");
		System.out.println("2. CPU starts first");
		
		int choice = keyboard.nextInt();
		keyboard.nextLine();
		
		System.out.println("\nHow much time do you allow the CPU to think? (in seconds)");
		long thinkTime = 5;
		thinkTime = keyboard.nextLong();
		keyboard.nextLine();
		
		Board board = new Board(thinkTime);
		
		String player = "O", cpu = "X", move = "";
		int turn;
		if(choice == 1) {
			turn = 0;
			System.out.println(board);
		}
		else 
			turn = 1;		
		int value = board.isWinner();
		
		while(value != 1 && value != 5000 && value != -5000) {
			if(turn == 0) {
				System.out.print("Make your move (i.e. C4): ");
				move = keyboard.nextLine();
				while(!board.makeMove(move)) {
					System.out.print("\nMake your move (i.e. C4): ");
					move = keyboard.nextLine();
				}
				turn = 1;
			}
			else {
				board.move();
				turn = 0;
			}
			System.out.println(board);
			value = board.isWinner();
		}
		if(value == -5000) System.out.println("\nYou Win!!");
		else if(value == 5000) System.out.println("\nYou lose...");
		else if(value == 1) System.out.println("\nDraw game");
		System.out.println("Thank you for playing");
	}
}
