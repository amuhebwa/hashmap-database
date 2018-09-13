import java.util.Comparator;

/**
 * This class allows us to compare two courses based on semester and includes the method compare, which return 0 if they are equal.
 * @author Aura Gomez-Tagle
 *
 */
public class SemesterComparator implements Comparator<Course> {
	//compare method
	//based on course name, year, and alphabetically and numerically, 
	
	public int compare(Course left, Course right) {
		//compares and sorts the course by department, then by number
		String leftSem = left.getSemester().substring(1, 5);
		int leftYr = Integer.parseInt(leftSem);
		String rightSem =right.getSemester().substring(1, 5);
		int rightYr = Integer.parseInt(rightSem);
		if (left.getSemester().equals(right.getSemester())){
			return 0;
		}
		else if(leftYr - rightYr < 0){
				if (left.getSemester().compareTo(right.getSemester()) > 0){
					return -1;
				}else {
					return 1;
				}
					
		}else {
			return 1;
		}
		
		
	
	}
	
}
//if (left.getSemester().equals(right.getSemester())){
//	return 0;
//}
//else if(left.getSemester().compareTo(right.getSemester()) > 0){
//	return -1;
//	
//}else {
//	return 1;
//}