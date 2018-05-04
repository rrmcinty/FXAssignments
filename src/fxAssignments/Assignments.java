package fxAssignments;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
    	String d = date.format(formatter);
    	
		return className + " - " + assignment + " - " + d;
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
