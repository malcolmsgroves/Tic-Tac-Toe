package game;

import java.util.Scanner;

public class Board {

	private static int DIMENSION = 3;
	private static final char BLANK = ' ';
	private static final char EX_MARK = 'X';
	private static final char OH_MARK = 'O';
	
	private static char[][] grid = new char[DIMENSION][DIMENSION];
	
	private static enum GameResult {EX_WINS, OH_WINS, DRAW, IN_PROGRESS};
	private static enum Player {EX, OH};
	
	private static GameResult status;
	private static Player turn;
	private static int[][] winning_line = new int[2][2];
	
	Board () {
			status = GameResult.IN_PROGRESS;
			for(int i = 0; i < DIMENSION; ++i) {
				for(int j = 0; j < DIMENSION; ++j) {
					grid[i][j] = BLANK;
				}
			}
	}
	
	public void play () {
		
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
		boolean isStreak = true;
		
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
			int c_int = c_start + i * r_delta;
			
			if(grid[r_int][c_int] == BLANK) {
				isStreak = false;
			}
			else if(curr_char == BLANK) {
				curr_char = grid[r_int][c_int];
			}
			else if(grid[r_int][c_int] != curr_char) {
				return GameResult.DRAW;
			}
		}
		if(isStreak) {
			winning_line[0][0] = r_start;
			winning_line[0][1] = c_start;
			winning_line[1][0] = r_end;
			winning_line[1][1] = c_end;
			
			switch(curr_char) {
			case EX_MARK:
				return GameResult.OH_WINS;
			case OH_MARK:
				return GameResult.EX_WINS;
			}
		}
		return GameResult.IN_PROGRESS;
	}
	
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
		
	}
}
