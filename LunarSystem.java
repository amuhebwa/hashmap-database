import java.util.*;
import java.io.*;

/**This is the graphical user interface for the LunarSystem scheduling program. It allows users to register students into course
 * it contains the HashMap database object and writes and reads from a Lunar.ser file. 
 * 
 * 
 * @author Aura 
 *
 */
public class LunarSystem {

	// private static final Object currentStudent = null;
	private static HashMap<String, Student> database;
	private static HashMap<String, List<Student>> enrolled; // course is key and
															// students are
															// values
	private static SemesterComparator semesterComparator = new SemesterComparator();
	private static CourseNameComparator courseNameComparator = new CourseNameComparator();

	public static void main(String[] args) {
		
		database = new HashMap<String, Student>();
		enrolled = new HashMap<String, List<Student>>();
		readFile();
		//System.out.print("size" + database.size());
		Scanner input = new Scanner(System.in);
		boolean run = true;
		while (run) {
			System.out.println("Welcome to Lunar System, a course registration system");
			System.out.println("Menu: ");
			System.out.println("\tL) Login");
			System.out.println("\tX) Save sate and quit");
			System.out.println("\tQ) Quit without saving state");
			System.out.println("\tPlease select an option: ");
			String choice = input.nextLine();
			// login
			/* General Menu Options */
			switch (choice) {

			case "L":
			case "l":
				boolean go = true;
				System.out.println("Please enter webid: ");
				String id = input.nextLine();

				/****
				 * ********* REGISTRAR*********************
				 */
				if (id.equals("REGISTRAR")) {
					System.out.println("Welcome to Registrar");
					registrarMenu();
					// System.out.println("Please select option: ");
					// String option = input.nextLine();
					// option.toUpperCase();

					while (go) {
						System.out.println("Please select option: ");
						String option = input.nextLine().toUpperCase();
						switch (option) {

						case "R":
						case "r":
							// Register a student
							System.out.println("Please enter a webID for the new student: ");
							String newID = input.nextLine();
							if (database.containsKey(newID)){
								System.out.printf("%s is already registered.",newID);
							}
							Student newStudent = new Student(newID);
							
							// newStudent.setWebID(newID);
							database.put(newID, newStudent); // places a student
							
							System.out.println(newStudent.getwebID() + " has been registered");
							break;

						case "D":
						case "d":
							// De-Register a student
							System.out.println("Enter the student id you want to deregister");
							String deregisterID = input.nextLine();
							deregisterStudent(deregisterID);
							break;

						case "E":
						case "e":
							// Collections.sort(currentStudent.getCourses(), new
							// SemesterComparator());
							printData();
							// View students enrolled in a class
							// prints the students in a certain course
							// department and course number
							break;
						case "L":
						case "l":
							System.out.println("You have been logged out of registrar");
							go = false;
							break;

						default:
							break;
						}
					}
				}

				else {
					boolean studentGo = true;

					if (database.containsKey(id)) {
						Student currentStudent = retrieveStudent(id);

						studentMenu();

						while (studentGo) {
							System.out.println("Select an option: ");
							String answer = input.nextLine();
						
							switch (answer) {

							case "a":
							case "A":
								Course newCourse = new Course();
								currentStudent.addCourse(newCourse);
								System.out.println("Please enter course name: ");
								String courseName = input.nextLine();
								String dept = courseName.substring(0, 3);
								newCourse.setDepartment(dept);
								// extracting the number of the course, which
								// has 3
								// numbers
								String[] num = courseName.split(" ");
								String numString = num[1];
								int number = Integer.parseInt(numString);
								newCourse.setNumber(number);
								System.out.println("Please select a semester: (Ex: F2017) ");
								String cSem = input.nextLine();
								String year = cSem.substring(1, 5);
								//System.out.println(year);
								int yearNum = Integer.parseInt(year);
								if(yearNum > 2010 && yearNum < 2025){
									newCourse.setSemester(cSem);
								}else {
									System.out.println("Please enter a spring or fall semester between the years 2010 or 2025");
								}
								//check semester for number
								
								
								// check if the course name is in the course
								// hashmap
								
								if (enrolled.containsKey(newCourse.stringName())) {
									// list exists
									enrolled.get(newCourse.stringName()).add(currentStudent);
								} else {
									List<Student> studentsList = new ArrayList<Student>();
									studentsList.add(currentStudent);
									enrolled.put(newCourse.stringName(), studentsList);
								}
								break;

							case "D":
							case "d":
								System.out.println("Please enter course name: ");
								String className = input.nextLine().toUpperCase();
								
								boolean done = false; //check if dropped
								if(currentStudent.isEnrolled(className)){
									currentStudent.drop(className);
									done = true;
								}
								if (done){
									System.out.printf("%s has been dropped. \n", className);
								}else 
									System.out.println("You cannot drop an un-enrolled course");
									

								
								break;

							case "C":
							case "c":
								Collections.sort(currentStudent.getCourses(), courseNameComparator);
								System.out.println("Student " + currentStudent.getwebID());
								System.out.printf("%1s", "Department");
								System.out.printf("%28s", "Number");
								System.out.printf("%30s", "Semester\n");
								System.out.println(
										"===================================================================\n");
								System.out.println(currentStudent.toString());
								
								break;
								
							case "S":
							case "s":
								Collections.sort(currentStudent.getCourses(), semesterComparator);
								System.out.println(currentStudent.toString());
								break;
							case "L":
							case "l":
								// logout
								System.out.println("Logged out of student menu");
								generalOptions();
								studentGo = false;
								break;

							default:

								break;
							}
						}
					} else {
						System.out.println("The student does not seem to be registered.\n Try loggin in with REGISTRAR");
					}

				}

				break;

			case "X":
			case "x":
				// save and quit
				System.out.println("Now exiting Lunar");
				writeFile();
				
				run = false;
				break;

			case "Q":
			case "q":
				// delete any save file
				System.out.println("Your file will not be saved");
				
				//catch file not found exception if file does not exist
				
				File file = new File("Lunar.ser");
				file.delete();
				run = false;
				break;
				

			default:
				break;

			}
		}
	}
//	public static void dropCourse(Student s){
//		System.out.println("Please enter the course name: ");
//		String courseName = input.nextLine().toUpperCase();
//		
//	}

	/**
	 * Method that returns a student based on id
	 * 
	 * @param webID:
	 *            the id the user searches
	 * @return: the Student with corresponding id
	 */
	public static void deregisterStudent(String webID) {
		//Student tmp;
		if (database.containsKey(webID)) {
			database.remove(webID);
			System.out.println(webID + "was removed");
		} else {
			//tmp = null;
			System.out.println("Student was not found");
			;
		}

	}
//	public boolean isEnrolled(String s, Student student){
//		for(int i=0; i<student.getCourses().size(); i++){
//			if(student.getCourses().get(i).haveEqualName(s)){
//				return true;
//			}
//			//check if that course at that moment equals the other course
//		}
//		return false;
//	}
	
	
//might not need this
	public static Student retrieveStudent(String webID) {
		Student tmp;
		if (database.containsKey(webID)) {
			tmp = database.get(webID);
		} else {
			tmp = null;
		}
		return tmp;
	}

	/**
	 * Prints out a table of all the student sin the hashed database
	 * 
	 */
	public static void printData() {
		System.out.println("Name\tCourses");
		System.out.println("=============================================");
		// fix so that it prints line by line
		// this has all the netID of all the student
		Set<String> set = database.keySet();
		for (String key : set) {
			Student current = database.get(key);
			System.out.println(current.toString());
		}
	}

	/**
	 * Method that adds a student to database
	 * <dt>Preconditions:
	 * <dd>HashMap exists and has been created where students are added
	 * <dt>Postconditions:
	 * <dd>The student has been to the database
	 * 
	 * @param webID:
	 *            identification key
	 */
	public static void registerStudent(String webID) {
		if (database.containsKey(webID)) {
			System.out.println("This student has been registered");
		} else {
			Student s = new Student(webID);
			// s.setWebID(webID);
			database.put(webID, s); // places a student
			System.out.println(s.getwebID() + " has been registered");
		}

	}

	public HashMap<String, Student> getDatabase() {
		return database;
	}

	public void setDatabase(HashMap<String, Student> database) {
		this.database = database;
	}

	public static void printMenu() {
		System.out.println("Welcome to Lunar System, a course registration system");
		System.out.println("Menu");
		System.out.println("\tL) Login");
		System.out.println("\tX) Save sate and quit");
		System.out.println("\tQ) Quit without saving state");
		System.out.println("\tPlease select an option: ");
	}

	public static void registrarMenu() {
		System.out.println("Registrar Options");
		System.out.println("\tR) Register A Student");
		System.out.println("\tD) De-Register A Student");
		System.out.println("\tE) View students enrolled in a class");
		System.out.println("\tDepartment \t Course Number");
		System.out.println("\tL) Logout (Return to main menu)");

	}

	// when you are logged out
	public static void generalOptions() {
		System.out.println("\tX) Save and quit");
		System.out.println("\tQ) Quit without saving state (and delete any save-file)");
	}

	public static void studentMenu() {
		System.out.println("Options");
		System.out.println("\tA) Add a class"); // \n Department \n Course
												// Number \n Semester ( S or F
												// followed by a number
												// 2010-2025"
		System.out.println("\tD) Drop a class");
		System.out.println("\tC) View your classes sorted by course name/dept");
		System.out.println("\tS) View your courses sorted by semester");
		System.out.println("\tL) Logout (Return to main)");

	}

	/**
	 * This method allows you to read in a file and its information if an
	 * exception is thrown a new file is created.
	 */

	public static void readFile() {

		try {
			File file = new File("./Lunar.ser");
			FileInputStream fis = new FileInputStream("./Lunar.ser");
			ObjectInputStream in = new ObjectInputStream(fis);
			database = (HashMap<String, Student>)in.readObject();
			System.out.println("Successfully loaded contents of testfile");
			in.close();
			fis.close();

		} catch (FileNotFoundException e) {
			System.out.println("File is not found. Using a new HashMap ");

		} catch (IOException e) {

			System.out.println("New HashMap has been created");
			
		} catch (ClassNotFoundException e) {

			System.out.println("CLASS NOT FOUND");
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		if (database == null) {
			database = new HashMap<String, Student>();
		}

	}
	

	public static void writeFile() {

		FileOutputStream file2;
		String name = "Lunar.ser";
		try {
			file2 = new FileOutputStream("./Lunar.ser");
			ObjectOutputStream outStream = new ObjectOutputStream(file2);
			outStream.writeObject(database);
			outStream.close();
			file2.close();
			System.out.println("System saved");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("There was an error with the output stream or file was not found");
		}
		 
	}

	

}
