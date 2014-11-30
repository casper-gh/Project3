// Name: Chi-Wai Lei
// CS420 - Project 3

public class Board {
	private String[][] board = new String[8][8];
	private int plies = 4;
	private long startTime;
	private long thinkTime;
	
	public Board(long time)
	//public Board()
	{
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board.length; j++)
			{
				board[i][j] = "-";
			}
		}
		thinkTime = time*1000;
	}
	
	
	public int evaluate ()
	{
		int result = 0;
	//Defense
		//Check Horizontal
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board.length-2; j++)
			{
				if( j < board.length-3 )
				{
					if(board[i][j].equals("O") && board[i][j+1].equals("O") && board[i][j+2].equals("O"))
					{
						if((j == 0 && board[i][j+3].equals("X"))
						|| (j+3 == 7 && board[i][j].equals("X"))
						|| (j >= 2 && (board[i][j-1].equals("X") && board[i][j+3].equals("X"))))
							result += 250;
					}
				}
				if(board[i][j].equals("O") && board[i][j+1].equals("O"))
				{
					if((j == 0 && board[i][j+2].equals("X"))
					|| (j+2 == 7 && board[i][j].equals("X"))
					|| (j >= 1 && (board[i][j-1].equals("X") || board[i][j+2].equals("X"))))
						result += 50;
				}
			}
		}
		//Check Vertical
		for(int i = 0; i < board.length-3; i++)
		{
			for(int j = 0; j < board.length; j++)
			{
				if(i < board.length-3)
				{
					if(board[i][j].equals("O") && board[i+1][j].equals("O") && board[i+2][j].equals("O"))
					{
						if((i == 0 && board[i+3][j].equals("X"))
						|| (i+3 == 7 && board[i][j].equals("X"))
						|| (i >= 2 && (board[i-1][j].equals("X") && board[i+3][j].equals("X"))))
							result += 250;
					}
				}
				if(board[i][j].equals("O") && board[i+1][j].equals("O"))
				{
					if((i == 0 && board[i+2][j].equals("X"))
					|| (i+2 == 7 && board[i][j].equals("X"))
					|| (i >= 1 && (board[i-1][j].equals("X") || board[i+2][j].equals("X"))))
						result += 50;
				}
			}
		}
		//Check Single
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board.length; j++)
			{
				if(board[i][j].equals("O"))
				{
					if(i == 0 && j == 0 && (board[1][0].equals("X") || board[0][1].equals("X"))
					|| i == 0 && j == 7 && (board[1][7].equals("X") || board[0][6].equals("X"))
					|| i == 7 && j == 0 && (board[6][0].equals("X") || board[7][1].equals("X"))
					|| i == 7 && j == 7 && (board[6][7].equals("X") || board[7][6].equals("X"))
					|| i == 0 && j >= 1 && j <= 6 && 
					(board[i][j-1].equals("X") || board[i+1][j].equals("X") || board[i][j+1].equals("X"))
					|| i == 7 && j >= 1 && j <= 6 && 
					(board[i][j-1].equals("X") || board[i-1][j].equals("X") || board[i][j+1].equals("X"))
					|| j == 0 && i >= 1 && i <= 6 && 
					(board[i-1][j].equals("X") || board[i][j+1].equals("X") || board[i+1][j].equals("X"))
					|| j == 7 && i >= 1 && i <= 6 && 
					(board[i-1][j].equals("X") || board[i][j-1].equals("X") || board[i+1][j].equals("X"))
					|| i >= 1 && i <= 6 && j >= 1 && j <= 6 && 
					(board[i-1][j].equals("X") || board[i][j-1].equals("X") || board[i+1][j].equals("X") || board[i][j+1].equals("X")))
					{
						result += 50;
					}
				}
			}
		}
	//Offense
		if(board[3][3].equals("X") || board[3][4].equals("X") || board[4][3].equals("X") || board[4][4].equals("X"))
		{
			result += 100;
		}
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board.length; j++)
			{
				if(board[i][j].equals("X"))
				{
					if(i == 0 && j == 0 && (board[1][0].equals("X") || board[0][1].equals("X"))
					|| i == 0 && j == 7 && (board[1][7].equals("X") || board[0][6].equals("X"))
					|| i == 7 && j == 0 && (board[6][0].equals("X") || board[7][1].equals("X"))
					|| i == 7 && j == 7 && (board[6][7].equals("X") || board[7][6].equals("X"))
					|| i == 0 && j >= 1 && j <= 6 && 
					(board[i][j-1].equals("X") || board[i+1][j].equals("X") || board[i][j+1].equals("X"))
					|| i == 7 && j >= 1 && j <= 6 && 
					(board[i][j-1].equals("X") || board[i-1][j].equals("X") || board[i][j+1].equals("X"))
					|| j == 0 && i >= 1 && i <= 6 && 
					(board[i-1][j].equals("X") || board[i][j+1].equals("X") || board[i+1][j].equals("X"))
					|| j == 7 && i >= 1 && i <= 6 && 
					(board[i-1][j].equals("X") || board[i][j-1].equals("X") || board[i+1][j].equals("X"))
					|| i >= 1 && i <= 6 && j >= 1 && j <= 6 && 
					(board[i-1][j].equals("X") || board[i][j-1].equals("X") || board[i+1][j].equals("X") || board[i][j+1].equals("X")))
					{
						result += 50;
					}
				}
			}
		}
		return result;
	}


	public void makemove()
	{
		int best=-20000,depth=plies,score,mi=0,mj=0;
		startTime = System.currentTimeMillis();
		for (int i=0; i<board.length; i++)
		{
			for (int j=0; j<board.length; j++)
			{
				if (board[i][j].equals("-"))
				{
					board[i][j]="X"; // make move on board
					score = min(depth-1, Integer.MIN_VALUE, Integer.MAX_VALUE);
					//System.out.println("i:"+i+"\tj:"+j+"\tbest:"+best+"\tscore:"+score);
					if (score > best)
					{
						mi=i;
						mj=j;
						best=score;
					}
					board[i][j]="-"; // undo move
					if(System.currentTimeMillis() - startTime >= thinkTime)
						break;
				}
			}
		}
		System.out.println("\nmy move is "+mi+" "+mj);
		board[mi][mj]="X";
	}
	public int min(int depth, int alpha, int beta)
	{
		int best=20000;
		//System.out.println(System.currentTimeMillis() - startTime);
		if(System.currentTimeMillis() - startTime < thinkTime)
		{
			if(checkWinner() != 0)
				return (checkWinner());
			if(depth == 0)
				return (evaluate());
			for (int i=0; i<board.length; i++)
			{
				for (int j=0; j<board.length; j++)
				{
					if (board[i][j].equals("-"))
					{
						board[i][j] = "O"; // make move on board
						best = Math.min(best, max(depth-1, alpha, beta));
						/*
						for(int k = 0; k < plies-depth; k++)
						{
							System.out.print("  ");
						}
						System.out.println("best:"+best+"\talpha:"+alpha+"\tbeta:"+beta);
						*/
						if(best <= alpha)
						{
							board[i][j] = "-"; // undo move
							return best;
						}
						beta = Math.min(beta, best);
						board[i][j] = "-"; // undo move
					}
				}
			}
		}
		return(best);
	}

	public int max(int depth, int alpha, int beta)
	{
		int best=-20000;
		//System.out.println(System.currentTimeMillis() - startTime);
		if(System.currentTimeMillis() - startTime < thinkTime)
		{
			if(checkWinner() != 0)
				return (checkWinner());
			if(depth == 0)
				return (evaluate());
			for(int i=0; i<board.length; i++)
			{
				for (int j=0; j<board.length; j++)
				{
					if (board[i][j].equals("-"))
					{
						board[i][j] = "X"; // make move on board
						best = Math.max(best, min(depth-1, alpha, beta));
						/*
						for(int k = 0; k < plies-depth; k++)
						{
							System.out.print("  ");
						}
						System.out.println("best:"+best+"\talpha:"+alpha+"\tbeta:"+beta);
						*/
						if(best >= beta)
						{
							board[i][j] = "-"; // undo move
							return best;
						}
						alpha = Math.max(alpha, best);
						board[i][j] = "-"; // undo move
					}
				}
			}
		}
		return(best);
	}
		
	
	public boolean makeMove(String move)
	{
		if(!validMove(move))
		{
			System.out.println("\nInvalid move!!");
			return false;
		}
		return true;
	}
	
	private boolean validMove(String move)
	{
		if(move.length() < 2) { return false; }
		
		int row = getRow(move.toUpperCase().charAt(0));
		if(row < 0) { return false; }
		
		int col = getColumn(move.toUpperCase().charAt(1));
		if(col < 0) { return false; }
		
		if(!board[row][col].equals("-")) { return false; }
		
		board[row][col] = "O";
		return true;
	}
	
	private int getRow(char row)
	{
		if(row == 'A') return 0;
		if(row == 'B') return 1;
		if(row == 'C') return 2;
		if(row == 'D') return 3;
		if(row == 'E') return 4;
		if(row == 'F') return 5;
		if(row == 'G') return 6;
		if(row == 'H') return 7;
		return -1;
	}
	private int getColumn(char col)
	{
		if(col == '1') return 0;
		if(col == '2') return 1;
		if(col == '3') return 2;
		if(col == '4') return 3;
		if(col == '5') return 4;
		if(col == '6') return 5;
		if(col == '7') return 6;
		if(col == '8') return 7;
		return -1;
	}
	
	public int checkWinner()
	{
		//Check Horizontal
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board.length-4; j++)
			{
				if(board[i][j].equals("O") && board[i][j+1].equals("O")
				&& board[i][j+2].equals("O") && board[i][j+3].equals("O"))
				{
					return -5000;
				}
				if(board[i][j].equals("X") && board[i][j+1].equals("X")
				&& board[i][j+2].equals("X") && board[i][j+3].equals("X"))
				{
					return 5000;
				}
			}
		}
		//Check Vertical
		for(int i = 0; i < board.length-4; i++)
		{
			for(int j = 0; j < board.length; j++)
			{
				if(board[i][j].equals("O") && board[i+1][j].equals("O")
				&& board[i+2][j].equals("O") && board[i+3][j].equals("O"))
				{
					return -5000;
				}
				if(board[i][j].equals("X") && board[i+1][j].equals("X")
				&& board[i+2][j].equals("X") && board[i+3][j].equals("X"))
				{
					return 5000;
				}
			}
		}
		//Check Draw
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board.length; j++)
			{
				if(board[i][j].equals("-"))
				{
					return 0;
				}
			}
		}
		System.out.println("\n"+toString()+"Draw game");
		return 1;
	}
	
	
	public boolean checkDraw()
	{
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board.length; j++)
			{
				if(board[i][j].equals("-"))
				{
					return false;
				}
			}
		}
		System.out.println("\n"+toString()+"Draw game");
		return true;
	}

	public String toString()
	{
		String curr = "\n  1 2 3 4 5 6 7 8\n";
		String []col = {"A", "B", "C", "D", "E", "F", "G", "H"};
		
		for(int i = 0; i < board.length; i++)
		{
			curr += col[i];
			for(int j = 0; j < board.length; j++)
			{
				curr += " "+board[i][j];
			}
			curr += "\n";
		}
		curr += "\n";
		return curr;
	}
}
