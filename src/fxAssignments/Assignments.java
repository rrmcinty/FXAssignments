package fxAssignments;

import java.io.Serializable;
import java.time.LocalDate;

public class Assignments implements Serializable{
//	Date due, date1;
	
	String assignment, className;
	LocalDate date;
	Handler handler;

	public Assignments(String c, String a, LocalDate d) throws Exception {
		className = c;
		assignment = a;
		date = d;
	}


	public String toString() {
		return className + " - " + assignment + " - " + date;
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
