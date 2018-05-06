package fxAssignments;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Alert.AlertType;


public class Handler {
	ArrayList<Assignments> aList = new ArrayList<Assignments>();
	List<String> classList = new ArrayList<String>();
	ArrayList<Assignments> dueToday = new ArrayList<Assignments>();
	ArrayList<Assignments> pastDue = new ArrayList<Assignments>();
	
	
	/**
	 * this wont add assignments object to aList
	 * @param a
	 */
	public void addAssignment(Assignments a) {
		aList.add(a);
		System.out.println(a.toString() + " added to aList");
		
	}
	
	public String dueString() {
		String dTodayString = "";
		String pDueString = "";
		LocalDate localDate = LocalDate.now();
		Assignments current;
		for (Assignments a : aList) {
			if (a.compareTo(localDate) == 0)  {
				dueToday.add(a);
				dTodayString = dTodayString + a.toString() + System.lineSeparator();
			}
			else if (a.compareTo(localDate) < 0) {
				pastDue.add(a);
				pDueString = pDueString + a.toString() + System.lineSeparator();
			}
		}
		
		return "Due Today" + System.lineSeparator() + dTodayString + "Past Due" + System.lineSeparator() + pDueString;
		
	}
	public String printAll() {
		String allAssignments = "";
		System.out.print(aList.toString() + " should be printed.");
		if (aList.size() == 0) {
			allAssignments = ("There are no current assignments");
		}
		else {
			Collections.sort(aList);
			for (Assignments a : aList) {
				//just prints %n instead of new line
				allAssignments = allAssignments + a.toString() + System.lineSeparator();
			}
		}
		
		return allAssignments;
	}

	//READ AND WRITE METHODS
	//MAKE SURE REWRITES INSTEAD OF ADD
	//if this works, somehow make it to where it will save only at end of program execution?
	public void saveFile() {
		//NO MORE ERROR IS IT WORKING?
		try {
		FileOutputStream fos = new FileOutputStream("Resources/assignments.ob", false);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		for(Assignments a : aList) { 
			oos.writeObject(a);
			System.out.println(a.toString() + " added to save file");
		}
		
		oos.close();
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
		/**
		 * has errors but works
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
	public void openFile() throws IOException, ClassNotFoundException {
		//opens object.data file at beginning of program and populates list
//		try {
		FileInputStream fis = new FileInputStream("Resources/assignments.ob");
		ObjectInputStream ois = new ObjectInputStream(fis);

		aList.clear();
		//maybe change the while loop?
		try {
			while(true) {
					Object obj = ois.readObject(); //CAST TO ASSIGNMENTS
					Assignments a = (Assignments) obj;
					aList.add(a);
					//adds class to class list to read in for combo box
					if (!classList.contains(a.getName())) {
						classList.add(a.getName());
					}
			}
			}catch (EOFException ex) {
			System.out.println("All objects read");
			}
	}
	public ArrayList<Assignments> getDueToday() {
		return dueToday;
	}
	public ArrayList<Assignments> getPastDue() {
		return pastDue;
	}
	
	public List<String> getClassList() {
		return classList;
	}
	
	/**
	 * used to clear list s o we can test the openfile method
	 */
	public void clearList() {
		aList.clear();
	}
	
	public Alert dueAlert() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Important Dates");
        alert.setHeaderText("Important Upcoming Due Dates");
        alert.setContentText(dueString());
        
        return alert;
	}
	
	/**
	 * creates choice dialog box
	 * @return
	 */
	public ChoiceDialog createChoice() {
		List<String> aChoice = new ArrayList<>();
		for (Assignments a : aList) {
			aChoice.add(a.toString());
		}
		ChoiceDialog<String> choices = new ChoiceDialog<>("Choose assignment", aChoice);
		choices.setTitle("Assignment removal");
		choices.setHeaderText("Choose assignment to remove from list");
		
		return choices;
	}
	/**
	 * removes assignment found from isInList method. used iterator to avoid concurrentModificationException.
	 * @param assignment
	 */
	public void remove(Assignments assignment) {
		Iterator<Assignments> iter = aList.iterator();
		
		while (iter.hasNext()) {
			Assignments a = iter.next();
			if (a.equals(assignment)) {
				iter.remove();
				System.out.println(a.toString() + " should be removed");
			}
		}
		iter = dueToday.iterator();
		while (iter.hasNext()) {
			Assignments a = iter.next();
			if (a.equals(assignment)) {
				iter.remove();
				System.out.println(a.toString() + " should be removed");
			}
		}
		iter = pastDue.iterator();
		while (iter.hasNext()) {
			Assignments a = iter.next();
			if (a.equals(assignment)) {
				iter.remove();
				System.out.println(a.toString() + " should be removed");
			}
		}
	}
	/**
	 * returns assignment found in list
	 * may have to fix the null part if not found
	 * @param c
	 * @param a
	 * @return
	 */
	
	public Assignments isInList(String c, String a) {
		Assignments found = null;

		for (Assignments assignment : aList) {
			if (assignment.getName().equals(c) && assignment.getAssignment().equals(a)) {
				found = assignment;
				break;
			}
		}
		return found;
	}
		
}
