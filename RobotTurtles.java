import java.util.Scanner;

public class RobotTurtles {

	// Kö, max 60 stor då detta var "gränsen" i uppgiften
	private MyQueue<Character> commands = new MyQueue<Character>(60);
	// Spelfältet
	private Board playField;
	// Variabel som håller koll på om någon bugg uppkommer när kommandon körs, denna ligger på klass nivå då "winCondition" använder denna. 
	private boolean bugFree = true;

	// köra programmet
	public void runGame() {
		populateBoard();
		executeCommands();
		winCondition();
	}

	// Sätta in rätt värden på matrix och skapa command array
	private void populateBoard() {
		Scanner input = new Scanner(System.in);
		Square[][] board = new Square[8][8];

		for (int v = 0; v < 9; v++) {
			String s = input.next();
			char[] line = s.toCharArray();
			// skapar kommando kö
			if (v == 8) {
				for (char c : line) {
					commands.add(c);
				}
				// skapar och lägger in Squares på brädet
			} else {
				for (int h = 0; h < 8; h++) {
					board[v][h] = new Square(line[h]);
				}
			}
		}
		// skapar brädet, stänger scanner.
		playField = new Board(board);
		input.close();
	}

	// Utför alla kommandon i command arrayen
	private void executeCommands() {
		// rekursiv funktion för att urätta alla kommandon i kön.
		if (!commands.isEmpty()) {
			if (playField.makePlay(commands.remove()) != false) {
				executeCommands();
			}
			// om kommandot returnar false så har den hittat en bug;
			else {
				bugFree = false;
			}
		}

	}

	// kollar om den slutar på diamanten
	private void winCondition() {
		if (playField.checkForDiamond() == true && bugFree == true) {
			System.out.print("Diamond!");
		} else {
			System.out.print("Bug!");
		}
	}

	public static void main(String[] args) {
		new RobotTurtles().runGame();
	}

}
