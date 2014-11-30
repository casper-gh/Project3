
public class Board {
	public int moves = 4;
	public long begin, end;
	public String myBoard[][] = new String[8][8];
	
	public Board(long t) {
		for(int i = 0; i < myBoard.length; i++) 
			for(int j = 0; j < myBoard.length; j++)
				myBoard[i][j] = "-";		
		end = 1000 * t;
	}
	
	public void move() {
		int row = 0,
			col = 0,
			top =- 20000,
			tmp = moves,
			points;
		
		begin = System.currentTimeMillis();
		for (int i=0; i<myBoard.length; i++) {
			for (int j=0; j<myBoard.length; j++) {
				if (myBoard[i][j].equals("-")) {
					myBoard[i][j]="X"; 
					points = min(tmp-1, Integer.MIN_VALUE, Integer.MAX_VALUE);
					if (points > top) {
						row=i;
						col=j;
						top=points;
					}
					myBoard[i][j]="-";
					if(System.currentTimeMillis() - begin >= end) break;
				}
			}
		}
		System.out.println("\nA.I. move: " + row + " " + col);
		myBoard[row][col] = "X";
	}
	
	public int calculate() {
		int res = 0;
		for(int i=0; i<myBoard.length; i++) {
			for(int j=0; j<myBoard.length - 2; j++) {
				if( j<myBoard.length - 3) {
					if(myBoard[i][j].equals("O") && myBoard[i][j+1].equals("O") && myBoard[i][j+2].equals("O")) {
						if((j==0 && myBoard[i][j+3].equals("X")) || (j+3==7 && myBoard[i][j].equals("X"))
						|| (j>=2 && (myBoard[i][j-1].equals("X") && myBoard[i][j+3].equals("X"))))
							res += 250;
					}
				}
				if(myBoard[i][j].equals("O") && myBoard[i][j+1].equals("O")) {
					if((j == 0 && myBoard[i][j+2].equals("X"))
					|| (j+2 == 7 && myBoard[i][j].equals("X"))
					|| (j >= 1 && (myBoard[i][j-1].equals("X") || myBoard[i][j+2].equals("X"))))
						res += 50;
				}
			}
		}

		for(int i = 0; i < myBoard.length-3; i++) {
			for(int j = 0; j < myBoard.length; j++) {
				if(i < myBoard.length-3) {
					if(myBoard[i][j].equals("O") && myBoard[i+1][j].equals("O") && myBoard[i+2][j].equals("O")) {
						if((i == 0 && myBoard[i+3][j].equals("X"))
						|| (i+3 == 7 && myBoard[i][j].equals("X"))
						|| (i >= 2 && (myBoard[i-1][j].equals("X") && myBoard[i+3][j].equals("X"))))
							res += 250;
					}
				}
				if(myBoard[i][j].equals("O") && myBoard[i+1][j].equals("O")) {
					if((i == 0 && myBoard[i+2][j].equals("X"))
					|| (i+2 == 7 && myBoard[i][j].equals("X"))
					|| (i >= 1 && (myBoard[i-1][j].equals("X") || myBoard[i+2][j].equals("X"))))
						res += 50;
				}
			}
		}

		for(int i = 0; i < myBoard.length; i++) {
			for(int j = 0; j < myBoard.length; j++) {
				if(myBoard[i][j].equals("O")) {
					if(i==0 && j==0 && (myBoard[1][0].equals("X") || myBoard[0][1].equals("X"))
					|| i==0 && j==7 && (myBoard[1][7].equals("X") || myBoard[0][6].equals("X"))
					|| i==7 && j==0 && (myBoard[6][0].equals("X") || myBoard[7][1].equals("X"))
					|| i==7 && j==7 && (myBoard[6][7].equals("X") || myBoard[7][6].equals("X"))
					|| i==0 && j>=1 && j<=6 && 
					(myBoard[i][j-1].equals("X") || myBoard[i+1][j].equals("X") || myBoard[i][j+1].equals("X"))
					|| i==7 && j>=1 && j<=6 && 
					(myBoard[i][j-1].equals("X") || myBoard[i-1][j].equals("X") || myBoard[i][j+1].equals("X"))
					|| j==0 && i>=1 && i<=6 && 
					(myBoard[i-1][j].equals("X") || myBoard[i][j+1].equals("X") || myBoard[i+1][j].equals("X"))
					|| j==7 && i>=1 && i<=6 && 
					(myBoard[i-1][j].equals("X") || myBoard[i][j-1].equals("X") || myBoard[i+1][j].equals("X"))
					|| i>=1 && i<=6 && j>=1 && j<=6 
					&& (myBoard[i-1][j].equals("X") || myBoard[i][j-1].equals("X") || myBoard[i+1][j].equals("X") || myBoard[i][j+1].equals("X")))
						res += 50;					
				}
			}
		}
		
		if(myBoard[3][3].equals("X") 
		|| myBoard[3][4].equals("X") 
		|| myBoard[4][3].equals("X") 
		|| myBoard[4][4].equals("X"))
			res += 100;
		
		for(int i=0; i<myBoard.length; i++) {
			for(int j=0; j<myBoard.length; j++) {
				if(myBoard[i][j].equals("X")) {
					if(i==0 && j==0 && (myBoard[1][0].equals("X") || myBoard[0][1].equals("X"))
					|| i==0 && j==7 && (myBoard[1][7].equals("X") || myBoard[0][6].equals("X"))
					|| i==7 && j==0 && (myBoard[6][0].equals("X") || myBoard[7][1].equals("X"))
					|| i==7 && j==7 && (myBoard[6][7].equals("X") || myBoard[7][6].equals("X"))
					|| i==0 && j>=1 && j<=6 && 
					(myBoard[i][j-1].equals("X") || myBoard[i+1][j].equals("X") || myBoard[i][j+1].equals("X"))
					|| i==7 && j>=1 && j<=6 && 
					(myBoard[i][j-1].equals("X") || myBoard[i-1][j].equals("X") || myBoard[i][j+1].equals("X"))
					|| j==0 && i>=1 && i<=6 && 
					(myBoard[i-1][j].equals("X") || myBoard[i][j+1].equals("X") || myBoard[i+1][j].equals("X"))
					|| j==7 && i>=1 && i<=6 && 
					(myBoard[i-1][j].equals("X") || myBoard[i][j-1].equals("X") || myBoard[i+1][j].equals("X"))
					|| i>=1 && i<=6 && j>=1 && j<=6 
					&& (myBoard[i-1][j].equals("X") || myBoard[i][j-1].equals("X") || myBoard[i+1][j].equals("X") || myBoard[i][j+1].equals("X")))
						res += 50;					
				}
			}
		}
		return res;
	}
	
	public int getBoardRow(char row) {
		switch(row) {
		case 'A': return 0;
		case 'B': return 1;
		case 'C': return 2;
		case 'D': return 3;
		case 'E': return 4;
		case 'F': return 5;
		case 'G': return 6;
		case 'H': return 7;
		default: return -1;		
		}
	}
	public int getBoardColumn(char col) {
		switch(col) {
		case '1': return 0;
		case '2': return 1;
		case '3': return 2;
		case '4': return 3;
		case '5': return 4;
		case '6': return 5;
		case '7': return 6;
		case '8': return 7;
		default: return -1;		
		}
	}
	
	public boolean isMovable(String move) {
		if(move.length() < 2) return false; 
		
		int row = getBoardRow(move.toUpperCase().charAt(0));
		if(row < 0) return false; 
		
		int col = getBoardColumn(move.toUpperCase().charAt(1));
		if(col < 0) return false; 
		
		if(!myBoard[row][col].equals("-")) return false; 
		
		myBoard[row][col] = "O";
		return true;
	}
	
	public boolean isDraw() {
		for(int i = 0; i < myBoard.length; i++) 
			for(int j = 0; j < myBoard.length; j++) 
				if(myBoard[i][j].equals("-"))				
					return false;	
		System.out.println("\n"+toString() + "Game is draw!");
		return true;
	}	
	
	public int isWinner() {
		for(int i = 0; i < myBoard.length; i++) {
			for(int j = 0; j < myBoard.length-4; j++) {
				if(myBoard[i][j].equals("O") && myBoard[i][j+1].equals("O")
				&& myBoard[i][j+2].equals("O") && myBoard[i][j+3].equals("O"))
					return -5000;				
				if(myBoard[i][j].equals("X") && myBoard[i][j+1].equals("X")
				&& myBoard[i][j+2].equals("X") && myBoard[i][j+3].equals("X"))				
					return 5000;				
			}
		}
		
		for(int i = 0; i < myBoard.length-4; i++) {
			for(int j = 0; j < myBoard.length; j++) {
				if(myBoard[i][j].equals("O") && myBoard[i+1][j].equals("O")
				&& myBoard[i+2][j].equals("O") && myBoard[i+3][j].equals("O"))				
					return -5000;
				
				if(myBoard[i][j].equals("X") && myBoard[i+1][j].equals("X")
				&& myBoard[i+2][j].equals("X") && myBoard[i+3][j].equals("X"))				
					return 5000;				
			}
		}

		for(int i = 0; i < myBoard.length; i++) {
			for(int j = 0; j < myBoard.length; j++) {
				if(myBoard[i][j].equals("-"))				
					return 0;				
			}
		}
		System.out.println("\n"+toString()+"Draw game");
		return 1;
	}	
	
	public boolean makeMove(String move) {
		if(!isMovable(move)) {
			System.out.println("\nInvalid move!!");
			return false;
		}
		return true;
	}
	
	public int min(int depth, int alpha, int beta) {
		int best=20000;
		if(System.currentTimeMillis() - begin < end) {
			if(isWinner() != 0)
				return (isWinner());
			if(depth == 0)
				return (calculate());
			for (int i=0; i<myBoard.length; i++) {
				for (int j=0; j<myBoard.length; j++) {
					if (myBoard[i][j].equals("-")) {
						myBoard[i][j] = "O";
						best = Math.min(best, max(depth-1, alpha, beta));
						if(best <= alpha) {
							myBoard[i][j] = "-";
							return best;
						}
						beta = Math.min(beta, best);
						myBoard[i][j] = "-";
					}
				}
			}
		}
		return(best);
	}

	public int max(int depth, int alpha, int beta) {
		int best=-20000;
		if(System.currentTimeMillis() - begin < end) {
			if(isWinner() != 0)
				return (isWinner());
			if(depth == 0)
				return (calculate());
			for(int i=0; i<myBoard.length; i++) {
				for (int j=0; j<myBoard.length; j++) {
					if (myBoard[i][j].equals("-")) {
						myBoard[i][j] = "X";
						best = Math.max(best, min(depth-1, alpha, beta));
						if(best >= beta) {
							myBoard[i][j] = "-";
							return best;
						}
						alpha = Math.max(alpha, best);
						myBoard[i][j] = "-"; 
					}
				}
			}
		}
		return(best);
	}
	
	public String toString() {
		String curr = "\n  1 2 3 4 5 6 7 8\n";
		String []col = {"A", "B", "C", "D", "E", "F", "G", "H"};
		
		for(int i = 0; i < myBoard.length; i++) {
			curr += col[i];
			for(int j = 0; j < myBoard.length; j++)			
				curr += " "+myBoard[i][j];			
			curr += "\n";
		}
		curr += "\n";
		return curr;
	}
}
