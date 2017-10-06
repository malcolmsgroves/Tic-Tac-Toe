package game;

import javafx.application.Application;

import javafx.scene.Group;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.input.MouseEvent;

import javafx.event.EventHandler;

import javafx.stage.Stage;


public class JavaFXApp extends Application {  

	protected static JavaFXBoard root;
	
	@Override     
	public void start(Stage primaryStage) throws Exception { 

//		//Creating a Text object 
//		Text text = new Text(); 
//
//		//Setting font to the text 
//		text.setFont(new Font(45)); 
//
//		//setting the position of the text 
//		text.setX(50); 
//		text.setY(50);          
//
//		//Setting the text to be added. 
//		text.setText("Welcome to Tic-Tac-Toe"); 
//
//		//Creating the mouse event handler 
//		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
//			@Override 
//			public void handle(MouseEvent e) { 
//				System.out.println("Hello World"); 
//			} 
//		};  
//		text.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);


		//Creating a Group object  
		//Group root = new Group(); 
		//ObservableList list = root.getChildren();
		


		
		root = new JavaFXBoard();



		// create scene object from root
		Scene scene = new Scene(root,500, 500);

		primaryStage.setTitle("TicTacToe");

		primaryStage.setScene(scene);


		primaryStage.show();
	}         
	public static void main(String args[]){           
		launch(args);  
	} 
} 