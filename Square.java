
public class Square {

	private boolean castle = false;
	private boolean iceCastle = false;
	private boolean turtle = false;
	private boolean edge = false;
	private boolean diamond = false;

	// Skapar rutan broende på typ, char avgör typ, om ingen stämmer förblir allt false och den printas some en "."
	public Square(char c) {
		if (c == 'C') {
			castle = true;
		}
		if (c == 'I') {
			iceCastle = true;
		}
		if (c == 'T') {
			turtle = true;
		}
		if (c == 'D') {
			diamond = true;
		}

	}
	// Skickar true om rutan är ett slott, annars false
	public boolean isCastle() {
		if(iceCastle == true || castle == true) {
		return true;
		}
		else {
			return false;
		}
	}

	public boolean isIceCastle() {
		return iceCastle;
	}

	public boolean isEdge() {
		return edge;
	}

	public boolean isDiamond() {
		return diamond;
	}

	public void setNormal() {
		turtle = false;
		iceCastle = false;
	}

	public void setTurtle() {
		turtle = true;
	}
	
	// Printar broende på vad som den är, placeringen av turtle över diamond gör att den printas även om båda är rätt. 
	public String toString() {
		if (castle == true) {
			return "C";
		}
		if (iceCastle == true) {
			return "I";
		}
		if (turtle == true) {
			return "T";
		}
		if (diamond == true) {
			return "D";
		}
		return ".";
	}

}
