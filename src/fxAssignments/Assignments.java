package fxAssignments;

import java.io.Serializable;

public class Assignments implements Serializable{
//	Date due, date1;
	
	String assignment, className;
	Handler handler;

	public Assignments(String c, String a) throws Exception {
		className = c;
		assignment = a;
	}


	public String toString() {
		return className + " - " + assignment;
	}
	public String getAssignment() {
		return assignment;
	}
	public void setAssignment(String a) {
		assignment = a;
	}
	public String getName() {
		return className;
	}
	public void setClass(String c) {
		className = c;
	}
	
	
}
