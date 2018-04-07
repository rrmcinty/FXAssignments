package fxAssignments;

public class Assignments {
//	Date due, date1;
	
	String assignment, className;

	public Assignments(String c, String a) throws Exception {
		className = c;
		assignment = a;

	}

//	public String getDue() {
//		return due.toString();
//	}
	
//	public void setDue(String d) throws Exception{
//		//string "dd/MM/yyyy
//		Date date1= new SimpleDateFormat("dd/MM/yyyy").parse(d);
//		due = date1;
//	}
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
