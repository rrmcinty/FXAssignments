package fxAssignments;

import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * REORGANIZE
 */



public class AssignmentsMain extends Application{
	 public static void main(String[] args) {
	        launch(args);
	    }
	    
	 	Handler handler = new Handler();
	 	
	    public void start(Stage primaryStage) {
	    	//creates window with grid layout
	    	primaryStage.setTitle("JavaFX Scheduler");
	    	GridPane grid = new GridPane();
	    	grid.setAlignment(Pos.CENTER); //instead of top left of scene the center
	    	grid.setHgap(10); //spacingi between rows 
	    	grid.setVgap(10); // and columns
	    	grid.setPadding(new Insets(25, 25, 25, 25)); //space around edge of gridpane
	    		    	
	    	
	    	//TEXTFIELDS AND COMBOBOXES
	    	Text scenetitle = new Text("Scheduler");
	    	scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	    	grid.add(scenetitle, 0, 0,2,1);
	    	
	    	Text sceneInstruction = new Text("Choose class and assignment to add or remove. ");
	    	sceneInstruction.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
	    	grid.add(sceneInstruction, 0, 0, 2,2);
	    	
	    	Label userName = new Label("Class Name:");
	    	grid.add(userName, 0, 1);
	    	
	    	//CLASS SECTION
	    	//add horizontal box
	    	HBox hbClass = new HBox(10);
	    	hbClass.setAlignment(Pos.BASELINE_LEFT);

	    	//add combobox and button
	    	ComboBox cmbClass = new ComboBox();
	    	Button btnClassAdd = new Button("+");

	    	//add hBox and class tools
	    	grid.add(hbClass, 1, 1);
	    	hbClass.getChildren().add(cmbClass);
	    	hbClass.getChildren().add(btnClassAdd);
	    	
	    	
	    	HBox hbDate = new HBox(10);
	    	
	    	Label due = new Label("Due date:");
	        //create the datepicker
	        DatePicker datePicker = new DatePicker();
	        
	        //add action
	        datePicker.setOnAction(event-> {
	        	LocalDate date = datePicker.getValue();
	        	System.out.println("Date selected is " + date);
	        });
	        
	        //add the datepicker to stage
	        
	        //add to hbdate
	        
	        hbDate.getChildren().add(due);
	        hbDate.getChildren().add(datePicker);
	        hbDate.setAlignment(Pos.BOTTOM_LEFT);
	    	
	    	grid.add(hbDate, 1, 4);
	    	
	    	
	    	Label lblAssignment = new Label("Assignment:");
	    	grid.add(lblAssignment, 0, 2);
	    	
	    	TextField txtAssignment = new TextField();
	    	grid.add(txtAssignment, 1, 2);
	    	
	    	//CREATE BUTTONS
	    	Button btnView = new Button("View All");
	    	Button btnAdd = new Button("Add");
	    	Button btnRemove = new Button("Remove");
	    	HBox hbBtn = new HBox(10); //horizontal layout for buttons
	    	
	    	
	    	
	    	//ADD BOTTOM BUTTONS
	    	hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	    	hbBtn.getChildren().add(btnView);
	    	hbBtn.getChildren().add(btnAdd);
	    	hbBtn.getChildren().add(btnRemove);
	    	
	    	grid.add(hbBtn, 1, 5);
	    	
	    	final Text actiontarget = new Text();
	    	grid.add(actiontarget, 1, 6);
	    	

	    	/**
	    	 * EVENT COMMANDS
	    	 */
	    
	    	btnClassAdd.setOnAction(new EventHandler<ActionEvent>() {
	    		
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
	    	        	cmbClass.getItems().add(entered);
	    	        }
	    	        
	    	      //  ======================================NOT WORKING==========================================================================
	    	        		

	    	        
	    	        actiontarget.setText("Add chosen");
	    	    }
	    	});
	    	/*
	    	 * added alert
	    	 */
	    	btnView.setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override
	    	    public void handle(ActionEvent e) {
//	    	    	Hhandler = new Handler();
	    	    	
	    	        actiontarget.setFill(Color.BLACK);
	    	        /**
	    	         * print out assignment objects from alist in handler class
	    	         */
	    	        Alert alert = new Alert(AlertType.INFORMATION);
	    	        alert.setTitle("All assignments");
	    	        alert.setHeaderText("View of all current assignments");
	    	        alert.setContentText(handler.printAll());
	    	        alert.showAndWait();
	    	        actiontarget.setText("View chosen");
	    	    }
	    	});
	    	/*
	    	 * now adds assignment to handler list = shows empty list when added
	    	 */
	    	btnAdd.setOnAction(new EventHandler<ActionEvent>() {
	    		 
	    	    @Override
	    	    public void handle(ActionEvent e) {
	    	        actiontarget.setFill(Color.FIREBRICK);
	    	        /**
	    	         * add assignment object from combobox info and assignment info
	    	         * Then add to aList in handler
	    	         */
	    	        //dialog box once add is pressed
//	    	        Handler handler = new Handler();
	    	        String classVal = (String) cmbClass.getValue();
	    	        String assignVal = txtAssignment.getText();
	    	        //LOOK INTO THIS TRY AND CATCH
	    	        try {
						Assignments added = new Assignments(classVal,assignVal);
						handler.addAssignment(added);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//	    	        System.out.println(txtAssignment.getText());


	    	        
//	    	        DatePicker datePicker = new DatePicker();
//	    	        datePicker.setOnAction(new EventHandler<ActionEvent>() {
//	    			public void handle(ActionEvent arg0) {
//						// TODO Auto-generated method stub
//    	                LocalDate date = datePicker.getValue();
//    	                System.err.println("Selected date: " + date);
//						}
//			    	});
	    	        
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
	    	
	    	
	    	Scene scene = new Scene(grid, 600, 275); //width and height of window
	    	primaryStage.setScene(scene);
	    	
	    	
	    	//shows stage
	    	primaryStage.show();
	    }
}
