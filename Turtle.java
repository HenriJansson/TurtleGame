
public class Turtle {

	// Nuvarande horizontell
	int curH;
	// Nuvarande vertial
	int curV;
	// Start riktningen
	String currentDir = "right";

	public Turtle(int v, int h) {
		curH = h;
		curV = v;
	}

	// flyttar sig beroende på nuvarande status, för att göra denna snyggare skulle man kunna implementera sentinel noder i matrixen. 
	public boolean move() {

		int newH = curH;
		int newV = curV;

		if (currentDir == "right") {
			if (newH + 1 == 8) {
				return false;
			} else {
				newH++;
			}
		}
		if (currentDir == "left") {
			if (newH - 1 < 0) {
				return false;
			} else {
				newH--;
			}
		}
		if (currentDir == "up") {
			if (newV - 1 < 0) {
				return false;
			} else {
				newV--;
			}
		}
		if (currentDir == "down") {
			if (newV + 1 == 8) {
				return false;
			} else {
				newV++;
			}
		}
		
		curV = newV;
		curH = newH;
		return true;

	}
	// vänder höger beroende på tidigare riktning
	public void turnRight() {
		if (currentDir == "right") {
			currentDir = "down";
			return;
		}
		if (currentDir == "left") {
			currentDir = "up";
			return;
		}
		if (currentDir == "up") {
			currentDir = "right";
			return;
		}
		if (currentDir == "down") {
			currentDir = "left";
			return;
		}
	}
	// Vänder vänster beroende på tidigare rikting
	public void turnLeft() {
		if (currentDir == "right") {
			currentDir = "up";
			return;
		}
		if (currentDir == "left") {
			currentDir = "down";
			return;
		}
		if (currentDir == "up") {
			currentDir = "left";
			return;
		}
		if (currentDir == "down") {
			currentDir = "right";
			return;
		}
	}

	public int getVertical() {
		return curV;
	}
	
	public int getHorizontal() {
		return curH;
	}
	
	public String getDir() {
		return currentDir;
	}
	
}