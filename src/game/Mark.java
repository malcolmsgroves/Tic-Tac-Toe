package game;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;

import javafx.stage.Stage;


public class Mark extends Group {

	private int row;
	private int col;

	private int x_coord;
	private int y_coord;
	private Board board;

	public Mark(int row, int col, Board board) {
		this.row = row;
		this.col = col;
		this.board = board;

		x_coord = 100 + col * JavaFXBoard.BOX_WIDTH;
		y_coord = 100 + row * JavaFXBoard.BOX_WIDTH;

		this.getChildren().addAll(create_box(), create_mark());
	}

	private Rectangle create_box() {
		Rectangle square = new Rectangle();
		square.setWidth(80);
		square.setHeight(80);
		square.setX(10 + x_coord);
		square.setY(10 + y_coord);
		square.setFill(Color.WHITE);

		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent e) { 
				while(!board.make_play(row, col));
				switch(board.get_turn()) {
				case Board.Player.EX:
					
				}
			} 
		};
		text.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

		return square;
	}

	private Text create_mark() {
		Text mark = new Text(String.valueOf(Board.EX_MARK));
		mark.setFont(Font.font(100));
		mark.setX(10 + x_coord);
		mark.setY(10 + y_coord + 80);
		return mark;
	}

	Board tic_tac_toe = new Board();


}
