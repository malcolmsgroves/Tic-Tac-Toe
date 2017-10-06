package game;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.event.EventHandler;

import javafx.collections.ObservableList;

import javafx.stage.Stage;

public class JavaFXBoard extends Group {

	public static final int BOX_WIDTH = 100;
	public static final int[][][] pixel_coords = new int[Board.DIMENSION][Board.DIMENSION][2];
	protected Board board;

	{
		for(int i = 0; i < Board.DIMENSION; ++i) {
			for(int j = 0; j < Board.DIMENSION; ++j) {
				pixel_coords[i][j][0] = 100 + i * BOX_WIDTH;
				pixel_coords[i][j][1] = 100 + j * BOX_WIDTH;
				System.out.println(String.valueOf(pixel_coords[i][j][0]) + ", " + 
						String.valueOf(pixel_coords[i][j][1] = 100 + j * BOX_WIDTH));
			}
		}
	}

	public JavaFXBoard () {
		Board board = new Board();
		build_board();
		create_marks();
	}

	public void build_board() {

		Group hash_lines = new Group();


		for(int i = 1; i < Board.DIMENSION; ++i) {
			// add a line
			Line horizontal_line = new Line();

			horizontal_line.setStartX(100); 
			horizontal_line.setStartY(BOX_WIDTH * i + 100); 
			horizontal_line.setEndX(400); 
			horizontal_line.setEndY(BOX_WIDTH * i + 100);

			Line vertical_line = new Line();

			vertical_line.setStartX(BOX_WIDTH * i + 100); 
			vertical_line.setStartY(100); 
			vertical_line.setEndX(BOX_WIDTH * i + 100); 
			vertical_line.setEndY(400);

			hash_lines.getChildren().addAll(horizontal_line, vertical_line);
		}

		this.getChildren().add(hash_lines);
	}

	public void create_marks() {

		Group ex_and_oh = new Group();

		for(int i = 0; i < Board.DIMENSION; ++i) {
			for(int j = 0; j < Board.DIMENSION; ++j) {
				//				Group box = new Group();
				//				
				//				Rectangle square = new Rectangle();
				//				square.setWidth(80);
				//				square.setHeight(80);
				//				square.setX(10 + pixel_coords[i][j][0]);
				//				square.setY(10 + pixel_coords[i][j][1]);
				//				
				//				Text mark = new Text(" ");
				//				mark.setX(10 + pixel_coords[i][j][0]);
				//				mark.setY(10 + pixel_coords[i][j][1]);
				//				
				//				box.getChildren().addAll(square, mark);
				Mark box = new Mark(j, i, board);
				ex_and_oh.getChildren().add(box);
			}
		}

		this.getChildren().add(ex_and_oh);
	}
}
