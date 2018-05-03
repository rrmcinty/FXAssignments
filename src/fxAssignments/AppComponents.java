package fxAssignments;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class AppComponents {
	
	public static MenuBar createMenu() {
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		//use menu?
		MenuItem openItem = new MenuItem("Open");
		menuFile.getItems().add(openItem);
		menuBar.getMenus().addAll(menuFile);
		
		return menuBar;
	}

}
