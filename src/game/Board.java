package game;

import java.util.Scanner;

public class Board {

	int DIMENSION = 3;
	char[][] grid = new char[DIMENSION][DIMENSION];
	
	enum GameResult {EX_WINS, OH_WINS, DRAW, IN_PROGRESS};
	
	GameResult status;
	
	Board () {
			status = GameResult.IN_PROGRESS;
	}
}
