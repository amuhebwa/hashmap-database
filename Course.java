import java.io.Serializable;
/**
 * This class will be associated with the Student class and create 
 * a Course object. Each course will have a designated department 
 * (Ex. “CSE”), a three-digit course number (Ex. 214), 
 * and a semester associated with it (Ex. “F2017”).
 * @author Aura Gomez-Tagle
 *
 */
public class Course implements Serializable {
	
	private String department;
	private int number;
	private String semester;
	
	
	/**
	 * This is the default constructor for course which initializes the department, number and semester. 
	 * @param department : the three letter string that corresponds to department CSE, ESE, AMS
	 * @param number: the course number 100 - 400 which signifies its difficulty or upper division
	 * @param semester: Fall, Spring, and year of semester
	 */
	public Course(String department, int number, String semester) {
		this.department = department;
		this.number = number;
		this.semester = semester;
	}
	/**
	 * This default constructor creates an instance of the course
	 * @param department
	 */
	public Course(){	
	}
	/**
	 * This returns the department of the course as a three letter string 
	 * @return department: private String 
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * This sets the course department and takes a string 
	 * @param department: Three letter string in Object Course
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * This returns the course number
	 * @return number: integer
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * This return the number as an integer
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * This method returns the String semester variable for the object Course
	 * @return semester: returns a string SPRING or FALL
	 */
	public String getSemester() {
		return semester;
	}
	/**
	 * This sets the string semester 
	 * @param semester: A string that corresponds to Course
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}
	@Override
	public String toString() {
		return "\n" + department + "\t" + number + "\t" + semester + " ";
	}
	public boolean haveEqualName(String s){
		String fullName = this.getDepartment()+" "+this.getNumber();
		
		if (s.equals(fullName)){
			return true;
		}
		return false;
	}
	
	/**Returns 
	 * 
	 * @return
	 */
	public String stringName(){
		return department + " " + number + " " + semester;
		
	}
	
}
