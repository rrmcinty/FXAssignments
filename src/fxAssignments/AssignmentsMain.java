package fxAssignments;

import java.io.IOException;
import java.util.logging.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
//import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * REORGANIZE
 */

public class AssignmentsMain extends Application{
	
	private final static Logger logr = ApplicationLogger.createLogger();
	private LocalDate datepicked;
	
	 public static void main(String[] args) {
	        launch(args);
	        
	    }
	    
	 	Handler handler = new Handler();
	 	
	    public void start(Stage primaryStage) {
	    	//LOGGER INIT

	    	
	    	logr.log(Level.INFO, "File opening");
	    	
	    	//opens previous schedule of assignments
	    	//open in separate class?
	    	try {
				handler.openFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	
	    	
	    	logr.log(Level.INFO, "Grid creating");
	    	
	    	
	    	//creates window with grid layout
	    	BorderPane borderPane = new BorderPane();
	    	//possibly different class
	    	
	    	logr.log(Level.INFO, "Menu creating");
	    	
	    	//create menu
	    	MenuBar menuBar = AppComponents.createMenu();
	    	menuBar.prefWidthProperty().bind(primaryStage.maxWidthProperty());


	    	borderPane.setTop(menuBar);
	    	
	    	primaryStage.setTitle("JavaFX Scheduler");
	    	GridPane grid = new GridPane();
	    	borderPane.setCenter(grid);
	    	grid.setAlignment(Pos.CENTER); //instead of top left of scene the center
	    	grid.setHgap(10); //spacing between rows 
	    	grid.setVgap(10); // and columns
	    	grid.setPadding(new Insets(25, 25, 25, 25)); //space around edge of gridpane
	    		
	    	logr.log(Level.INFO, "Textfields and comboboxes creating");
	    	
	    	//TEXTFIELDS AND COMBOBOXES
	    	Text scenetitle = new Text("Scheduler");
	    	scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	    	grid.add(scenetitle, 0, 0,2,1);
	    	
	    	Text sceneInstruction = new Text("Choose class and assignment to add or remove. ");
	    	sceneInstruction.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
	    	grid.add(sceneInstruction, 0, 0, 2,2);
	    	
	    	Label className = new Label("Class Name:");
	    	grid.add(className, 0, 1);
	    	
	    	//CLASS SECTION
	    	//add horizontal box
	    	HBox hbClass = new HBox(10);
	    	hbClass.setAlignment(Pos.BASELINE_LEFT);

	    	//add combobox and button
	    	//need to get rid of repeat classes in combobox
	    	ComboBox<String> cmbClass = new ComboBox<String>(FXCollections.observableList(handler.getClassList()));
	    	Button btnClassAdd = new Button("+");

	    	//add hBox and class tools
	    	grid.add(hbClass, 1, 1);
	    	hbClass.getChildren().add(cmbClass);
	    	hbClass.getChildren().add(btnClassAdd);
	    	
	    	//ASSIGNMENT SECTION
	    	Label lblAssignment = new Label("Assignment:");
	    	grid.add(lblAssignment, 0, 2);
	    	
	    	TextField txtAssignment = new TextField();
	    	grid.add(txtAssignment, 1, 2);
	    	
	    	//DATE SECTION
	    	HBox hbDate = new HBox(10);
	    	
	    	Label due = new Label("Due date:");
	    	grid.add(due, 0, 3);
	    	
	    	
	    	logr.log(Level.INFO, "DatePicker creating");
	    	
	        //create the datepicker
	        DatePicker datePicker = new DatePicker();
	        //DATEPICKER CLOSES WINDOW AFTER CHOSEN
	        //add action
	        
	        
	        datePicker.setOnAction(event-> {
	        	LocalDate date = datePicker.getValue();

	        	//scope?
	        	datepicked = date;
	        	System.out.println("Date selected is " + date);
	        });
	        
	        hbDate.getChildren().add(datePicker);
	        hbDate.setAlignment(Pos.BOTTOM_LEFT);
	    	
	    	grid.add(hbDate, 1, 3);
	    	
	    	
	    	logr.log(Level.INFO, "Buttons creating");
	    	
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
	    	
	    	//TEST BUTTONS
	    	
	    	Button btnClear = new Button("Clear");
//	    	Button btnOpen = new Button("Open");
	    	HBox hbBtnTest = new HBox(10);
	    	
	    	
	    	hbBtnTest.getChildren().add(btnClear);
//	    	hbBtnTest.getChildren().add(btnOpen);
	    	grid.add(hbBtnTest, 1, 8);
	    	

	    	
	    	
	    	/**
	    	 * EVENT COMMANDS
	    	 */
//CLASS ADD	    
	    	btnClassAdd.setOnAction(new EventHandler<ActionEvent>() {
	    		
	    	    @Override
	    	    public void handle(ActionEvent e) {
	    	        actiontarget.setFill(Color.FIREBRICK);

	    	        TextInputDialog dialog = new TextInputDialog("Enter Class Name");
	    	        dialog.setTitle("Class Chooser");
	    	        dialog.setHeaderText("Add Class Name");
	    	        dialog.setContentText("Enter a class id name");
	    	        //response
	    	        Optional<String> result = dialog.showAndWait();
	    	        if (result.isPresent()) {
	    	        	String entered = result.get().toUpperCase();
	    	        	System.out.println(entered);
	    	        	cmbClass.getItems().add(entered);
	    	        }
	    	        System.out.println(datepicked);
	    	        actiontarget.setText("Add chosen");
	    	    }
	    	});
//VIEW
	    	btnView.setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override
	    	    public void handle(ActionEvent e) {
//	    	    	Hhandler = new Handler();
	    	    	
	    	        actiontarget.setFill(Color.BLACK);
	    	        
	    	        Alert due = handler.dueAlert();
	    	        due.showAndWait();
	    	        Alert alert = new Alert(AlertType.INFORMATION);
	    	        alert.setTitle("All assignments");
	    	        alert.setHeaderText("View of all current assignments");
	    	        alert.setContentText(handler.printAll());
	    	        alert.showAndWait();
	    	        actiontarget.setText("View chosen");
	    	    }
	    	});
	    	
	    	/**
	    	 * need a constraint for null. if either combobox or gettext is empty.
	    	 */
	    	btnAdd.setOnAction(new EventHandler<ActionEvent>() {
	    		 
	    	    @Override
	    	    public void handle(ActionEvent e) {
	    	        actiontarget.setFill(Color.FIREBRICK);
	    	        actiontarget.setText("Add chosen");
	    	        /**
	    	         * add assignment object from combobox info and assignment info
	    	         * Then add to aList in handler
	    	         */
	    	        //dialog box once add is pressed
//	    	        Handler handler = new Handler();
	    	        String classVal = (String) cmbClass.getValue();
	    	        String assignVal = txtAssignment.getText();
	    	        //LOOK INTO THIS TRY AND CATCH
	    	        if (cmbClass.getValue() == null || txtAssignment.getText().trim().isEmpty() || datepicked == null){
//	    	        	System.out.println("Fields are empty");
	    	        	actiontarget.setText("Please fill out all fields.");
	    	        	System.out.println(assignVal);
	    	        	System.out.println(txtAssignment.getText());
	    	        }
	    	        else {
	    	        	try {
							Assignments added = new Assignments(classVal,assignVal,datepicked);
							handler.addAssignment(added);
							handler.saveFile();
							logr.log(Level.INFO, "Saving assignment");
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	    	        }
	    	    }  
	    	});
/**
 * removes from list but adds them back once program started. also errors found, something to do with reading/writing?
 * change to a list of selectable assignment objects to remove
 */
	    	btnRemove.setOnAction(new EventHandler<ActionEvent>() {
	    		 
	    	    @Override
	    	    public void handle(ActionEvent e) {

	    	    ChoiceDialog choices = handler.createChoice();	
    	    	Optional<String> result = choices.showAndWait();
    	    	if (result.isPresent()) {
    	    		/**
    	    		 * lead this back to assignment to be removed
    	    		 */
    	    		String s = result.get();
    	    		System.out.println(s + " is the result");
    	    		Scanner scanner = new Scanner(s).useDelimiter("\\s*-\\s*");
    	    		String c = scanner.next();
    	    		System.out.println(c + " is the class found");
    	    		String a = scanner.next();
    	    		System.out.println(a + " is the assignment found");
    	    		Assignments removed = handler.isInList(c, a);
//    	    		System.out.println(removed.toString() + " assignment to be removed.");
    	    		handler.remove(removed);
    	    		handler.saveFile();
    	    		//add confirmation dialog
    	    	}
	    	    }
	    	});

	    	/**
	    	 * clears assignment list and saves object file. 
	    	 * should clear combobox as well?
	    	 */
	    	btnClear.setOnAction(new EventHandler<ActionEvent>() {
	    		 
	    	    @Override
	    	    public void handle(ActionEvent e) {
	    	    	//add this all to a class called buttonHandler?
	    	        actiontarget.setFill(Color.BLUEVIOLET);
	    	        actiontarget.setText("Clear chosen");
	    	        Alert alert = new Alert(AlertType.CONFIRMATION);
	    	        alert.setTitle("Clear Confirmation");
	    	        alert.setHeaderText("This will permanently clear all assignments.");
	    	        alert.setContentText("Are you sure you want to continue?");
	    	        Optional<ButtonType> result = alert.showAndWait();
	    	        if (result.get() == ButtonType.OK) {
		    	    	handler.clearList();
		    	    	handler.saveFile();
	    	        }
	    	        else {
	    	        	System.out.println("User cancelled");
	    	        }
	    	    }
	    	});
	    	

	    	//menu file

	    	
	    	Scene scene = new Scene(borderPane, 400, 325); //width and height of window
	    	

	    	
	    	primaryStage.setScene(scene);


	    	//shows stage
	    	primaryStage.show();
	    	
	
	    	

	    }
}
