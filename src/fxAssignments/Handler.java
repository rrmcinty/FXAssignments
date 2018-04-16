package fxAssignments;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Handler {
	ArrayList<Assignments> aList = new ArrayList<Assignments>();
	/**
	 * this wont add assignments object to aList
	 * @param a
	 */
	public void addAssignment(Assignments a) {
		aList.add(a);

		System.out.println(a.toString() + " added to aList");
		
	}
	
	public String printAll() {
		String allAssignments = "";
		System.out.print(aList.toString() + " should be printed.");
		if (aList.size() == 0) {
			allAssignments = ("There are no current assignments");
		}
		for (Assignments a : aList) {
			//just prints %n instead of new line
			allAssignments = allAssignments + a.toString() + System.lineSeparator();
		}
		
		return allAssignments;
	}
	
	public void remove(String s) {
		for(Assignments a : aList) {
			if (a.getAssignment().equals(s)) {
				aList.remove(a);
			}
		}
	}

	//READ AND WRITE METHODS
	//MAKE SURE REWRITES INSTEAD OF ADD
	
	public void saveFile() {
		//NO MORE ERROR IS IT WORKING?
		try {
		FileOutputStream fos = new FileOutputStream("object.data");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		for(Assignments a : aList) {
			oos.writeObject(a);
		}
		
		oos.close();
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void openFile() {
		//opens object.data file at beginning of program and populates list

	}
	
}
