package fxAssignments;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Assignments implements Serializable, Comparable<Assignments>{
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLLL dd");
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
	public LocalDate getDate() {
		return date;
	}
	
	public int compareTo(LocalDate other) {
		// TODO Auto-generated method stub
//		LocalDate otherDate = other.getDate();
		int cmp = (date.getYear() - other.getYear());
		if (cmp == 0) {
			cmp = (date.getDayOfMonth() - other.getDayOfMonth());
		}
		System.out.println(cmp);
		return cmp;

	}


	@Override
	public int compareTo(Assignments arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
