
public class Board {
	Square[][] board;
	// Brädans turtle
	Turtle turtle;
// skapar brädet, denna skulle kunna ändras så att man kunde skapa olika stora bräder. 
	public Board(Square[][] field) {
		board = field;
		turtle = new Turtle(7, 0);
//     Printar matrixen när den skapas
//		printMatrix();
	}

	// Flyttar och kollar kanter/slott/isslott
	public boolean moveTurtle() {
		board[turtle.getVertical()][turtle.getHorizontal()].setNormal();
		if (turtle.move()) {
			board[turtle.getVertical()][turtle.getHorizontal()].setTurtle();
			if (board[turtle.getVertical()][turtle.getHorizontal()].isCastle() == false) {
				return true;
			}
		}
		return false;

	}

	// Förstör isslott och letar efter kanter(för att undvika nullpointers)
	// tänkte lägga denna i turtle klassen men då den inte utför något på den klassen så hamna den här
	// för att göra denna snyggare skulle man kunna implementera sentinel noder i matrixen. 
	public boolean shootCastle() {
		int newH = turtle.getHorizontal();
		int newV = turtle.getVertical();

		if (turtle.getDir() == "right") {
			if (newH + 1 == 8) {
				return false;
			} else {
				newH++;
			}
		}
		if (turtle.getDir() == "left") {
			if (newH - 1 < 0) {
				return false;
			} else {
				newH--;
			}
		}
		if (turtle.getDir() == "up") {
			if (newV - 1 < 0) {
				return false;
			} else {
				newV--;
			}
		}
		if (turtle.getDir() == "down") {
			if (newV + 1 == 8) {
				return false;
			} else {
				newV++;
			}
		}
		if (board[newV][newH].isIceCastle()) {
			board[newV][newH].setNormal();
			return true;
		}
		return false;

	}

	// Letar efter diamanter
	public boolean checkForDiamond() {
		return (board[turtle.getVertical()][turtle.getHorizontal()].isDiamond());

	}
	
	// utför kommandon som skickas från RobotTurtles klassen
	public boolean makePlay(char cmd) {
		
		boolean bug = true;

		if (cmd == 'F') {
			bug = moveTurtle();
		}
		if (cmd == 'L') {
			turtle.turnLeft();
		}
		if (cmd == 'R') {
			turtle.turnRight();
		}
		if (cmd == 'X') {
			bug = shootCastle();
		}
//      Printar matrixen efter varje kommando
//		printMatrix();
		return bug;

	}

	// printa matrix, bara för felsökning
//	public void printMatrix() {
//
//		for (Square[] row : board) {
//
//			for (Square col : row) {
//
//				Square toPrint = col;
//				System.out.print(toPrint.toString() + " ");
//			}
//
//			System.out.println();
//		}
//		System.out.println("-------------");
//	}
	

}
