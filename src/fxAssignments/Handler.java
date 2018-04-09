package fxAssignments;

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
			allAssignments = allAssignments + a.toString() + "%n";
		}
		
		return allAssignments;
	}
}
