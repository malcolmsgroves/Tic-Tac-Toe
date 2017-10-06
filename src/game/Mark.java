package game;

import javafx.scene.Group;

import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;



public class Mark extends Group {

	private int row = 0;
	private int col = 0;

	private int x_coord;
	private int y_coord;
	private Text text;
	private Rectangle square;

	public Mark(int row, int col, Board board) {
		this.row = row;
		this.col = col;
		

		x_coord = 100 + col * JavaFXBoard.BOX_WIDTH;
		y_coord = 100 + row * JavaFXBoard.BOX_WIDTH;
		create_square();
		create_text();
		add_event_handler();
		this.getChildren().addAll(square, text);
	}

	private void create_square() {
		square = new Rectangle();
		square.setWidth(80);
		square.setHeight(80);
		square.setX(10 + x_coord);
		square.setY(10 + y_coord);
		square.setFill(Color.WHITE);
	}

	private void create_text() {
		text = new Text(String.valueOf(Board.BLANK));
		text.setFont(Font.font(100));
		text.setX(10 + x_coord);
		text.setY(10 + y_coord + 80);
	}

	private void add_event_handler() {
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent e) { 
				System.out.println(row);
				System.out.println(col);
				JavaFXBoard.board.print_board();
				JavaFXBoard.board.make_play(row,  col);
				switch(JavaFXBoard.board.turn) {
					case EX:
						text.setText(String.valueOf(Board.OH_MARK));
						break;
					case OH:
						text.setText(String.valueOf(Board.EX_MARK));
						break;
				}
				JavaFXBoard.board.check_win();
				
				// CLOSE MOUSE HANDLER IF TRUE
				if(JavaFXBoard.board.status != Board.GameResult.IN_PROGRESS) {
					JavaFXApp.root.end_game();
				}
			}
		};
		square.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
	}


}
