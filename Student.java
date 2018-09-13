import java.io.Serializable;
import java.util.*;

/**
 * CSE 214.R01 Homework 6
 * 
 * @author Aura Gomez-Tagle 109832579
 *
 *         This student class creates a ID, name and student information
 */
public class Student implements Serializable {
	private static int studentCounter = 0;
	private String webID;
	private List<Course> courses;

	public void setWebID(String webID) {
		this.webID = webID;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void addCourse(Course course) {
		courses.add(course);
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	

	/**
	 * This will get the number of studentCounter for the user.
	 * 
	 * @return This returns the studentCounter which is also equal to the webID.
	 */
	public static int getStudentCounter() {
		return studentCounter;
	}

	/**
	 * This will return the webID for the user.
	 * 
	 * @return This returns the Id of the student which is set in the
	 *         constructor.
	 */
	public String getwebID() {
		return webID;
	}

	/**
	 * This is the default constructor which will always set a timeArrived, a
	 * course object, and a time arrived for each student object.
	 * 
	 * 
	 */
	public Student(String webID) {
		this.webID = webID;
		// this.name = name;
		courses = new ArrayList<Course>();

		webID = "";

	}
	/**The default constructor
	 * 
	 * @param webID
	 * @param courses
	 */
//	public Student(String webID) {
//		
//		this.webID = webID;
//		this.courses = courses;
//	}

	// public String getName(){
	// return this.name;
	// }
	public String toString() {
		return "Student:\t" + webID + " " + this.getCourses().toString();
	}
	
	public boolean isEnrolled(String s){
		for(int i=0; i<this.getCourses().size(); i++){
			if(this.getCourses().get(i).haveEqualName(s)){
				return true;
			}
			//check if that course at that moment equals the other course
		}
		return false;
	}
	
	
	public void drop(String name){
		
		for(int i=0; i<this.getCourses().size(); i++){
			if(this.getCourses().get(i).haveEqualName(name)){
				this.getCourses().remove(this.getCourses().get(i));
			}
		}
	}
	
	
//	
//	public void semesterSort(){
//		
//		int j = 0;
//		for (int i =0; i< this.getCourses().size(); i++){
//			courses.sort(SemesterComparator.compare(this.getCourses().get(i), this.getCourses().get(i) ));
//			
//			
//		}
//	}
//		//For loop for each course
//			//call teh comparator
//			//if condition is met, swap course
//		
//		
//	}
	
}
