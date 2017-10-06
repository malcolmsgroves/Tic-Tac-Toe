package game;

import java.util.Scanner;

public class Board {

	public static int DIMENSION = 3;
	public static final char BLANK = ' ';
	public static final char EX_MARK = 'X';
	public static final char OH_MARK = 'O';
	
	private static char[][] grid = new char[DIMENSION][DIMENSION];
	
	private static enum GameResult {EX_WINS, OH_WINS, DRAW, IN_PROGRESS};
	protected static enum Player {EX, OH};
	
	public static GameResult status;
	public static Player turn;
	private static int[][] winning_line = new int[2][2];
	
	Board () {
			status = GameResult.IN_PROGRESS;
			turn = Player.EX;
			for(int i = 0; i < DIMENSION; ++i) {
				for(int j = 0; j < DIMENSION; ++j) {
					grid[i][j] = BLANK;
				}
			}
	}
	
	public void play () {
		Scanner scan = new Scanner(System.in);
		
		while(status == GameResult.IN_PROGRESS) {
			
			switch(turn) {
			case EX:
				System.out.println("X's turn");
				break;
			case OH: 
				System.out.println("O's turn");
				break;
			}
			System.out.println("Enter a row: ");
			int row = scan.nextInt();
			System.out.println("Enter a column: ");
			int col = scan.nextInt();
			
			while(!make_play(row, col)) {
				System.out.println("Invalid coordinates, enter a row" +
						" column between 0 and 2, inclusive");
				System.out.println("Enter a row: ");
				row = scan.nextInt();
				System.out.println("Enter a column: ");
				col = scan.nextInt();
			}
			check_win();
			print_board();
		}
		switch(status) {
		case EX_WINS:
			System.out.println("X wins!");
			break;
		case OH_WINS:
			System.out.println("O wins!");
			break;
		default:
			System.out.println("DRAW!");
			break;
		}
		scan.close();
	}
	
	public void check_win() {
		
		boolean isDraw = true;
		
		for(int i = 0; i < DIMENSION; ++i) {
			for(int r_delta = -1; r_delta <= 1; ++r_delta) {
				for(int c_delta = -1; c_delta <= 1; ++c_delta) {
					GameResult row_result = check_direction(i, 0, r_delta, c_delta);
					GameResult col_result = check_direction(0, i, r_delta, c_delta);
					
					if(row_result == GameResult.IN_PROGRESS || col_result == GameResult.IN_PROGRESS) {
						isDraw = false;
					}
					else if(row_result == GameResult.OH_WINS || row_result == GameResult.EX_WINS) {
						status = row_result;
						return;
					}
					else if(col_result == GameResult.OH_WINS || col_result == GameResult.EX_WINS) {
						status = col_result;
						return;
					}
				}
			}
		}
		if(isDraw) {
			status = GameResult.DRAW;
		}
	}
	
	public GameResult check_direction(int r_start, int c_start, int r_delta, int c_delta) {
		
		// keep track of a streak of the same char
		boolean is_streak = true;
		
		// check if the direction is null
		if(r_delta == 0 && c_delta == 0) {
			return GameResult.DRAW;
		}
		
		int r_end = r_start + (DIMENSION - 1) * r_delta;
		int c_end = c_start + (DIMENSION - 1) * c_delta;
		
		
		// check if end point is on board
		if(r_end < 0 || r_end >= DIMENSION || c_end < 0 || c_end >= DIMENSION) {
			return GameResult.DRAW;
		}
		
		// explore the streak
		char curr_char = BLANK;
		
		for(int i = 0; i < DIMENSION; ++i) {
			
			int r_int = r_start + i * r_delta;
			int c_int = c_start + i * c_delta;
			
			if(grid[r_int][c_int] == BLANK) {
				is_streak = false;
			}
			else if(curr_char == BLANK) {
				curr_char = grid[r_int][c_int];
			}
			else if(grid[r_int][c_int] != curr_char) {
				return GameResult.DRAW;
			}
		}
		
		// if someone one
		if(is_streak) {
			winning_line[0][0] = r_start;
			winning_line[0][1] = c_start;
			winning_line[1][0] = r_end;
			winning_line[1][1] = c_end;
			
			switch(curr_char) {
			case EX_MARK:
				return GameResult.EX_WINS;
			case OH_MARK:
				return GameResult.OH_WINS;
			}
		}
		
		// default: game is still going
		return GameResult.IN_PROGRESS;
	}
	
	// place a mark on the grid
	public boolean make_play(int r, int c) {
		if(grid[r][c] != BLANK) {
			return false;
		}
		switch(turn) {
		case EX:
			grid[r][c] = EX_MARK;
			turn = Player.OH;
			break;
		case OH:
			grid[r][c] = OH_MARK;
			turn = Player.EX;
			break;
		}
		return true;
	}
	
	public void print_board () {
		for(int r = 0; r < DIMENSION; r++) {
			for(int c = 0; c < DIMENSION; c++) {
				System.out.print(grid[r][c] + "\t");
			}
			System.out.println();
		}
	}
	
	public Player get_turn() {
		return turn;
	}
}
