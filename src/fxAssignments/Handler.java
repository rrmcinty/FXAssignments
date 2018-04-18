package fxAssignments;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
		while(true) {
			Object obj = ois.readObject(); //CAST TO ASSIGNMENTS
			Assignments a = (Assignments) obj;
			aList.add(a);
		}

//		}catch (Exception ex) {
//			ex.printStackTrace();
//		}
	}
	/**
	 * used to clear list so we can test the openfile method
	 */
	public void clearList() {
		aList.clear();
	}
}
