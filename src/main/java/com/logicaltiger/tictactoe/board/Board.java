package com.logicaltiger.tictactoe.board;

/**
 * The tic-tac-toe board is a grid that is 3 x 3.
 * This is deliberately limited to 3 in each dimension (no expandability).
 * 
 * The codes are presented to the player in terms of letters:
 *    a (row 0, col 0)    b (row 0, col 1)    c (row 0, col 2)
 *    a (row 1, col 0)    b (row 1, col 1)    c (row 1, col 2)
 *    a (row 2, col 0)    b (row 2, col 1)    c (row 2, col 2)
 *    
 * A prompt for playing might read "Valid choices: ABFQ" where
 * "Q" is "end playing".
 * 
 * The default mark is a one-character blank string.
 */
public class Board {
	public static final String MARK_X = "X";
	public static final String MARK_O = "O";
	public static final String MARK_BLANK = " ";
	public static final String codes = "ABCDEFGHI";
	
	private String[][] grid = new String[3][3];

	public void initialize() {

		for(int row = 0; row < 3; row++) {

			for(int col = 0; col < 3; col++) {
				this.grid[row][col] = MARK_BLANK;
			}

		}

	}

	public String getMark(int row, int col) {
		return this.grid[row][col];
	}

	public void setMark(int row, int col, String mark) {
		this.grid[row][col] = mark;
	}
	
	public void setMark(String code, String mark) {
		
		if(code == null || code.length() == 0)
			return;
		
		if(mark == null || mark.length() == 0)
			return;
		
		int idx = codes.indexOf(code);
		
		if(idx == -1)
			return;
		
		int row = idx / 3;
		int col = idx % 3;
		this.setMark(row, col, mark);
	}
	
	public String getValidMoves() {
		StringBuffer sb = new StringBuffer();

		for(int row = 0; row < 3; row++) {
			
			for(int col = 0; col < 3; col++) {
			
				if(MARK_BLANK.equals(this.grid[row][col])) {
					sb.append(getCode(row, col));
				}
				
			}
			
		}
		
		return sb.toString();
	}

	public String getPosition() {
		StringBuffer sb = new StringBuffer();

		for(int row = 0; row < 3; row++) {
			
			for(int col = 0; col < 3; col++) {
				sb.append(getMark(row, col));
			}
			
		}
		
		return sb.toString();
	}

	
	public String getCode(int row, int col) {
		int idx = row * 3 + col;
		return codes.substring(idx, idx + 1);
	}

	/**
	 * See if X or O is a winner.
	 */
	public String getWinner() {
		
		String winner = checkRows();
		
		if(MARK_BLANK.equals(winner)) {
			winner = checkCols();
		}

		if(MARK_BLANK.equals(winner)) {
			winner = check10To4Diag();
		}
		
		if(MARK_BLANK.equals(winner)) {
			winner = check8To2Diag();
		}
		
		return winner;
	}

	private String checkRows() {
		int countX;
		int countO;

		for(int row = 0; row < 3; row++) {
			countX = 0;
			countO = 0;
			
			for(int col = 0; col < 3; col++) {
				String mark = this.grid[row][col];
				countX += MARK_X.equals(mark) ? 1 : 0;
				countO += MARK_O.equals(mark) ? 1 : 0;
			}
			
			if(countX == 3)
				return MARK_X;
			
			if(countO == 3)
				return MARK_O;
			
		}

		return MARK_BLANK;
	}
	
	private String checkCols() {
		int countX;
		int countO;

		for(int col = 0; col < 3; col++) {
			countX = 0;
			countO = 0;
			
			for(int row = 0; row < 3; row++) {
				String mark = this.grid[row][col];
				countX += MARK_X.equals(mark) ? 1 : 0;
				countO += MARK_O.equals(mark) ? 1 : 0;
			}
			
			if(countX == 3)
				return MARK_X;
			
			if(countO == 3)
				return MARK_O;
			
		}

		return MARK_BLANK;
	}

	private String check10To4Diag() {
		int countX = 0;
		int countO = 0;
		String mark = this.grid[0][0];
		countX += MARK_X.equals(mark) ? 1 : 0;
		countO += MARK_O.equals(mark) ? 1 : 0;
		
		mark = this.grid[1][1];
		countX += MARK_X.equals(mark) ? 1 : 0;
		countO += MARK_O.equals(mark) ? 1 : 0;

		mark = this.grid[2][2];
		countX += MARK_X.equals(mark) ? 1 : 0;
		countO += MARK_O.equals(mark) ? 1 : 0;
			
		if(countX == 3)
			return MARK_X;
		
		if(countO == 3)
			return MARK_O;
			
		return MARK_BLANK;
	}
	
	private String check8To2Diag() {
		int countX = 0;
		int countO = 0;
		String mark = this.grid[0][2];
		countX += MARK_X.equals(mark) ? 1 : 0;
		countO += MARK_O.equals(mark) ? 1 : 0;
		
		mark = this.grid[1][1];
		countX += MARK_X.equals(mark) ? 1 : 0;
		countO += MARK_O.equals(mark) ? 1 : 0;

		mark = this.grid[2][0];
		countX += MARK_X.equals(mark) ? 1 : 0;
		countO += MARK_O.equals(mark) ? 1 : 0;
			
		if(countX == 3)
			return MARK_X;
		
		if(countO == 3)
			return MARK_O;
			
		return MARK_BLANK;
	}
	
	/**
	 * If a move can still be made then not yet a draw.
	 */
	public boolean isDraw() {
	
		for(int row = 0; row < 3; row++) {
			
			for(int col = 0; col < 3; col++) {
			
				if(MARK_BLANK.equals(this.grid[row][col])) {
					return false;
				}
				
			}
			
		}
		
		return true;
	}
	
	public Board clone() {
	
		Board b = new Board();
		
		for(int row = 0; row < 3; row++) {
			
			for(int col = 0; col < 3; col++) {
				b.setMark(row, col, this.getMark(row, col));
			}
			
		}
		
		return b;
	}
	
	private void swap(int rowFirst, int colFirst, int rowSecond, int colSecond) {
		String temp = this.getMark(rowFirst, colFirst);
		this.setMark(rowFirst, colFirst, this.getMark(rowSecond, colSecond));
		this.setMark(rowSecond, colSecond, temp);
	}
	
	/**
	 * Swaps the marks from col 0 and col 2.
	 * For example, "ABC" becomes "CBA".
	 */
	public void mirror() {
		
		for(int row = 0; row < 3; row++) {
			this.swap(row, 0, row, 2);
		}
		
	}

	/**
	 * Rotate the grid counterclockwise about the center cell.
	 * For example, "ABC/DEF/GHI" becomes "CFI/BEH/ADG".
	 */
	public void rotate() {
		Board b = this.clone();
		this.setMark(0, 0, b.getMark(0, 2));
		this.setMark(0, 1, b.getMark(1, 2));
		this.setMark(0, 2, b.getMark(2, 2));
		
		this.setMark(1, 0, b.getMark(0, 1));
		this.setMark(1, 1, b.getMark(1, 1));
		this.setMark(1, 2, b.getMark(2, 1));
		
		this.setMark(2, 0, b.getMark(0, 0));
		this.setMark(2, 1, b.getMark(1, 0));
		this.setMark(2, 2, b.getMark(2, 0));
	}
	
	public void create(String position) {
		int idx = 0;

		for(String s : position.split("")) {
			int row = idx / 3;
			int col = idx % 3;
			this.setMark(row, col, s);
			
			idx++;
		}

	}

}
