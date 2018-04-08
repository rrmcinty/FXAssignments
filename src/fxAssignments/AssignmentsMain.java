package fxAssignments;

import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AssignmentsMain extends Application{
	 public static void main(String[] args) {
	        launch(args);
	    }
	    
	    public void start(Stage primaryStage) {
	    	//creates window with grid layout
	    	primaryStage.setTitle("JavaFX Scheduler");
	    	GridPane grid = new GridPane();
	    	grid.setAlignment(Pos.CENTER); //instead of top left of scene the center
	    	grid.setHgap(10); //spacingi between rows 
	    	grid.setVgap(10); // and columns
	    	grid.setPadding(new Insets(25, 25, 25, 25)); //space around edge of gridpane
	    	
	    	//TEXT AND COMBOBOXES
	    	Text scenetitle = new Text("Scheduler");
	    	scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	    	grid.add(scenetitle, 0, 0,2,1);
	    	
	    	Text sceneInstruction = new Text("Choose class and assignment to add or remove. ");
	    	sceneInstruction.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
	    	grid.add(sceneInstruction, 0, 0, 2,2);
	    	
	    	Label userName = new Label("Class Name:");
	    	grid.add(userName, 0, 1);
	    	
	    	ComboBox classBox = new ComboBox();
	    	grid.add(classBox, 1, 1);
	    	
	    	Label pw = new Label("Assignment:");
	    	grid.add(pw, 0, 2);
	    	
	    	ComboBox<String> aBox = new ComboBox<String>();
	    	grid.add(aBox, 1, 2);
	    	
	    	//CREATE BUTTONS
	    	Button btnView = new Button("View All");
	    	Button btnAdd = new Button("Add");
	    	Button btnRemove = new Button("Remove");
	    	HBox hbBtn = new HBox(10); //horizontal layout for buttons
	    	
	    	
	    	//ADD BUTTONS
	    	hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	    	hbBtn.getChildren().add(btnView);
	    	hbBtn.getChildren().add(btnAdd);
	    	hbBtn.getChildren().add(btnRemove);
	    	
	    	grid.add(hbBtn, 1, 4);
	    	
	    	final Text actiontarget = new Text();
	    	grid.add(actiontarget, 1, 6);
	    	

	    	/**
	    	 * EVENT COMMANDS
	    	 */
	    	btnView.setOnAction(new EventHandler<ActionEvent>() {
	    		 
	    	    @Override
	    	    public void handle(ActionEvent e) {
	    	        actiontarget.setFill(Color.BLACK);
	    	        /**
	    	         * print out assignment objects from alist in handler class
	    	         */
	    	        actiontarget.setText("View chosen");
	    	    }
	    	});
	    	btnAdd.setOnAction(new EventHandler<ActionEvent>() {
	    		 
	    	    @Override
	    	    public void handle(ActionEvent e) {
	    	        actiontarget.setFill(Color.FIREBRICK);
	    	        /**
	    	         * add assignment object from combobox info and assignment info
	    	         * Then add to aList in handler
	    	         */
	    	        //dialog box once add is pressed
	    	        TextInputDialog dialog = new TextInputDialog("Enter Class Name");
	    	        dialog.setTitle("Class Chooser");
	    	        dialog.setContentText("Enter a class id name");
	    	        //response
	    	        Optional<String> result = dialog.showAndWait();
	    	        if (result.isPresent()) {
	    	        	String entered = result.get();
	    	        	System.out.println(entered);
	    	        	classBox.getItems().add(entered);
	    	        }
	    	        
	    	        actiontarget.setText("Add chosen");
	    	    }
	    	});
	    	btnRemove.setOnAction(new EventHandler<ActionEvent>() {
	    		 
	    	    @Override
	    	    public void handle(ActionEvent e) {
	    	        actiontarget.setFill(Color.DARKCYAN);
	    	        actiontarget.setText("Remove chosen");
	    	    }
	    	});
	    	
	    	
	    	Scene scene = new Scene(grid, 315, 275); //width and height of window
	    	primaryStage.setScene(scene);
	    	
	    	
	    	//shows stage
	    	primaryStage.show();
	    }
}
