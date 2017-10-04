package game;

import java.util.Scanner;

public class Board {

	int DIMENSION = 3;
	char BLANK = ' ';
	char EX_MARK = 'X';
	char OH_MARK = 'O';
	
	char[][] grid = new char[DIMENSION][DIMENSION];
	
	enum GameResult {EX_WINS, OH_WINS, DRAW, IN_PROGRESS};
	enum Player {EX, OH};
	
	GameResult status;
	Player turn;
	int[][] winning_line = new int[2][2];
	
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
		
		for(int i = 0; i < DIMENSION; ++i) {
			for(int r_delta = -1; r_delta <= 1; ++r_delta) {
				for(int c_delta = -1; c_delta <= 1; ++c_delta) {
					
				}
			}
		}
	}
	
	public void get_direction() {
		
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
