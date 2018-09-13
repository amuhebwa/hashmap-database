import java.util.Comparator;

/**
 * This class allows us to compare two course names with the following priority: department and then number.
 *  The CourseNameComparator class should implement the Comparator interface and override the compare method.
 *  int compare (Course left, Course right) 
 * @author Aura Gomez-Tagle
 *
 */
public class CourseNameComparator implements Comparator<Course> {
	//compare method
	//based on course name, year, and alphabetically and numerically, 
	
	
	
	public int compare(Course left, Course right) {
		//compares and sorts the course by department, then by number
		if (left.getDepartment().equals(right.getDepartment())){
			if ( left.getNumber() == right.getNumber()){
				return 0;
			}else {
				if ((left.getNumber() - right.getNumber()) < 0){
					//right is greater
					return -1;
				}
				else{
					//the left is greater
					return 1;
				}
			}
		}
		else {
			return left.getDepartment().compareTo(right.getDepartment()) ; //-1 if its less CSE - ESE 
		}
	}
	

	
}
