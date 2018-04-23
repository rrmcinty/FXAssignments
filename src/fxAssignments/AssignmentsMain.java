package fxAssignments;

import java.io.IOException;
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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
	 public static void main(String[] args) {
	        launch(args);
	        
	    }
	    
	 	Handler handler = new Handler();
	 	
	    public void start(Stage primaryStage) {
	    	//opens previous schedule of assignments
	    	try {
				handler.openFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	
	    	//creates window with grid layout
	    	BorderPane borderPane = new BorderPane();
	    	//possibly different class
	    	MenuBar menuBar = new MenuBar();
	    	menuBar.prefWidthProperty().bind(primaryStage.maxWidthProperty());
	    	Menu menuFile = new Menu("File");
	    	//use menu?
	    	MenuItem openItem = new MenuItem("Open");
	    	menuFile.getItems().add(openItem);
	    	menuBar.getMenus().addAll(menuFile);
	    	

	    	borderPane.setTop(menuBar);
	    	
	    	primaryStage.setTitle("JavaFX Scheduler");
	    	GridPane grid = new GridPane();
	    	borderPane.setCenter(grid);
	    	grid.setAlignment(Pos.CENTER); //instead of top left of scene the center
	    	grid.setHgap(10); //spacing between rows 
	    	grid.setVgap(10); // and columns
	    	grid.setPadding(new Insets(25, 25, 25, 25)); //space around edge of gridpane
	    		    	
	    	
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
	        //create the datepicker
	        DatePicker datePicker = new DatePicker();
	        //DATEPICKER CLOSES WINDOW AFTER CHOSEN
	        //add action
	        datePicker.setOnAction(event-> {
	        	LocalDate date = datePicker.getValue();
	        	System.out.println("Date selected is " + date);
	        });
	        
	        hbDate.getChildren().add(datePicker);
	        hbDate.setAlignment(Pos.BOTTOM_LEFT);
	    	
	    	grid.add(hbDate, 1, 3);
	    	
	    	
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
	    	        	String entered = result.get();
	    	        	System.out.println(entered);
	    	        	cmbClass.getItems().add(entered);
	    	        }
	    	        	    	        
	    	        actiontarget.setText("Add chosen");
	    	    }
	    	});
//VIEW
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
	    	
	    	/**
	    	 * need a constraint for null. if either combobox or gettext is empty.
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
	    	        if (cmbClass.getValue() == null || txtAssignment.getText().trim().isEmpty()) {
	    	        	System.out.println("Fields are empty");
	    	        	System.out.println(assignVal);
	    	        	System.out.println(txtAssignment.getText());
	    	        }
	    	        else {
	    	        	try {
							Assignments added = new Assignments(classVal,assignVal);
							handler.addAssignment(added);
							handler.saveFile();
							System.out.println("can I save here?");
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	    	        }


	    	        actiontarget.setText("Add chosen");
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
	    	

	    	
	    	
//	    	((VBox) scene.getRoot()).getChildren().addAll(menuBar);
	    	
	    	primaryStage.setScene(scene);


	    	//shows stage
	    	primaryStage.show();
	    	
	
	    	

	    }
}
