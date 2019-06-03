/* Name: Kashaf Masood 
Date: Nov 18, 2017
Application Name: STL REGISTRY 
Last Modified: Nov 18, 2017 
 */

package stlstudentsregistry;

import java.util.ArrayList;
import java.util.Scanner;

public class STLStudentsRegistry {

    public static Scanner in = new Scanner(System.in);
    private static ArrayList<Student> studentList = new ArrayList<Student>();
    private static ArrayList<Courses> courseList = new ArrayList<Courses>();
    private static ArrayList<Integer> selected = new ArrayList<Integer>();

    // continue or exit program 
    public static void main(String[] args) {
        String choice = "";

        for (int i = 0; i < 5; i++) {

        }

        System.out.println("WELCOME TO THE STL REGISTRY");
        addCoursesList();
        preloadStudents();
        while (true) {
            mainMenu();
            System.out.println("-------------------------");
            System.out.println("[1] Back to Main Screen");
            System.out.println("[2] Exit Registry");
            System.out.print("CHOICE: ");
            choice = in.nextLine();
            switch (Integer.parseInt(choice)) {
                case 1:
                    break;
                case 2:
                    System.exit(0);
                    break;
            }
        }
    }

    // desired task 
    public static void mainMenu() {
        System.out.println("ENTER NUMBER BESIDE DESIRED TASK: ");
        System.out.println("[1] Manage Students");
        System.out.println("[2] Manage Courses");
        String choice;
        choice = in.nextLine();
        switch (choice) {
            case "1":
                manageStudentsDropDown();
                break;
            case "2":
                manageCoursesDropDown();
                break;
        }
    }

    // manage student drop down
    public static void manageStudentsDropDown() {
        String choice;
        System.out.println("[1] Add Student");
        System.out.println("[2] Get Student Information");
        System.out.println("[3] Remove Student from School");
        System.out.println("[4] Add/Remove Courses from Student");
        System.out.println("[5] Update Student Information");
        choice = in.nextLine();
        switch (choice) {
            case "1":
                addStudentSchool();
                break;
            case "2":
                getStudentInfo();
                break;
            case "3":
                removeStudentSchool();
                break;
            case "4":
                addRemoveCourse();
                break;
            case "5":
                updateStudentInfo();
                break;

        }
    }

    // manage courses drop down
    public static void manageCoursesDropDown() {
        String choice;
        System.out.println("[1] Add Course to School");
        System.out.println("[2] Remove Course from School");
        System.out.println("[3] Update Course Information");
        choice = in.nextLine();
        switch (choice) {
            case "1":
                addCourseSchool();
                break;
            case "2":
                removeCourseSchool();
                break;
            case "3":
                updateCourseInfo();
                break;
        }
    }

    // display student either searching or student list 
    public static int displayStudent() {
        String choice;
        int studentIndex = 0;
        System.out.println("CHOOSE HOW TO FIND STUDENT");
        System.out.println("[1] Search Student");
        System.out.println("[2] Student List");
        choice = in.nextLine();
        switch (choice) {
            case "1":
                studentIndex = searchStudent();
                break;
            case "2":
                studentIndex = studentList();
                break;
        }
        return studentIndex;
    }

    // add student to school 
    public static void addStudentSchool() {
        String firstName, lastName, age, grade, address, email;
        System.out.println("Enter Student Information");
        System.out.print("First Name: ");
        firstName = in.nextLine();
        System.out.print("Last Name: ");
        lastName = in.nextLine();
        System.out.print("Age: ");
        age = in.nextLine();
        System.out.print("Grade: ");
        grade = in.nextLine();
        System.out.print("Address: ");
        address = in.nextLine();
        System.out.print("Email: ");
        email = in.nextLine();
        Student student = new Student(grade, firstName, lastName, age, address, email);
        studentList.add(student);
        selectCourse(student);
    }

    // select course drop down
    public static void selectCourse(Student student) {
        int counter = 0;
        boolean check = true;
        System.out.println("SELECT FOUR COURSES");
        String choice;
        selected.clear();
        for (int i = 0; i < 4; i++) {
            do {
                displayCourses();
                if (counter > 0 && check == false) {
                    System.out.println("COURSE IS FULL... CHOOSE ANOTHER COURSE...");
                } else if (counter > 0) {

                    System.out.println("COURSE ALREADY ENTERED... CHOOSE ANOTHER COURSE...");
                }
                System.out.print("CHOICE: ");
                choice = in.nextLine();
                check = checkCourseFull(courseList.get(Integer.parseInt(choice) - 1));
                counter += 1;
            } while (selected.contains(Integer.parseInt(choice)) || check == false);
            counter = 0;
            selected.add(Integer.parseInt(choice));

            switch (i) {
                case 0:
                    student.getTimetable().setCourseOne(courseList.get(Integer.parseInt(choice) - 1));
                    courseList.get(Integer.parseInt(choice) - 1).setNumStudents(courseList.get(Integer.parseInt(choice) - 1).getNumStudents() + 1);
                    break;
                case 1:
                    student.getTimetable().setCourseTwo(courseList.get(Integer.parseInt(choice) - 1));
                    courseList.get(Integer.parseInt(choice) - 1).setNumStudents(courseList.get(Integer.parseInt(choice) - 1).getNumStudents() + 1);
                    break;
                case 2:
                    student.getTimetable().setCourseThree(courseList.get(Integer.parseInt(choice) - 1));
                    courseList.get(Integer.parseInt(choice) - 1).setNumStudents(courseList.get(Integer.parseInt(choice) - 1).getNumStudents() + 1);
                    break;
                default:
                    student.getTimetable().setCourseFour(courseList.get(Integer.parseInt(choice) - 1));
                    courseList.get(Integer.parseInt(choice) - 1).setNumStudents(courseList.get(Integer.parseInt(choice) - 1).getNumStudents() + 1);
                    break;
            }
        }

    }

    // checks if course if full 
    public static boolean checkCourseFull(Courses course) {
        if (course.getCourseName().equalsIgnoreCase("spare")) {
            return true;
        } else if (course.getNumStudents() > 30) {
            return false;
        }
        return true;
    }

    // displays student information 
    public static void getStudentInfo() {
        int studentIndex;
        studentIndex = displayStudent();
        if (studentIndex == -1) {
            System.out.println("NO STUDENTS FOUND...");
            return;
        }
        System.out.println("STUDENT INFORMATION");
        System.out.println("Name: " + studentList.get(studentIndex - 1).getInfo().getFirstName() + " " + studentList.get(studentIndex - 1).getInfo().getLastName());
        System.out.println("Age: " + studentList.get(studentIndex - 1).getInfo().getAge());
        System.out.println("Grade: " + studentList.get(studentIndex - 1).getGrade());
        System.out.println("Address: " + studentList.get(studentIndex - 1).getInfo().getAddress());
        System.out.println("Email: " + studentList.get(studentIndex - 1).getInfo().getEmail());
        System.out.println("Locker Number: " + studentList.get(studentIndex - 1).getlockerInfo().getLockerNum());
        getCourseInfo(studentList.get(studentIndex - 1));
    }

    // displays course information of student
    public static void getCourseInfo(Student student) {
        System.out.println(" ");
        System.out.println("COURSE INFORMATION");
        System.out.println("Course # 1: " + student.getTimetable().getCourseOne().getCourseName());
        System.out.println("Course Code: " + student.getTimetable().getCourseOne().getCourseCode());
        System.out.println("Course # 2: " + student.getTimetable().getCourseTwo().getCourseName());
        System.out.println("Course Code: " + student.getTimetable().getCourseTwo().getCourseCode());
        System.out.println("Course # 3: " + student.getTimetable().getCourseThree().getCourseName());
        System.out.println("Course code: " + student.getTimetable().getCourseThree().getCourseCode());
        System.out.println("Course # 4: " + student.getTimetable().getCourseFour().getCourseName());
        System.out.println("Course code: " + student.getTimetable().getCourseFour().getCourseCode());
    }

    // removes a student from school 
    public static void removeStudentSchool() {
        int studentIndex;
        System.out.println("CHOOSE STUDENT TO REMOVE FROM SCHOOL");
        studentIndex = displayStudent();
        if (studentIndex == -1) {
            System.out.println("NO STUDENTS FOUND...");
            return;
        }
        studentList.remove(studentIndex - 1);
        System.out.println("STUDENT HAS SUCCESSFULLY BEEN REMOVED FROM SCHOOL");
    }

    // remove courses from a student 
    public static void addRemoveCourse() {
        String choice, courseSelection;
        int studentIndex, counter = 0;
        Student student;
        boolean check;
        studentIndex = displayStudent();
        if (studentIndex == -1) {
            System.out.println("NO STUDENTS FOUND...");
            return;
        }
        student = studentList.get(studentIndex - 1);
        getCourseInfo(student);
        System.out.println("SELECT COURSE WANT TO REPLACE: ");
        choice = in.nextLine();

        do {
            if (counter > 0) {
                System.out.println("INVALID COURSE...");
            }
            System.out.println("SELECT COURSE WANT TO REPLACE WITH: ");
            displayCourses();
            courseSelection = in.nextLine();
            check = courseValid(courseList.get(Integer.parseInt(courseSelection) - 1), student);
            counter += 1;
        } while (check == false);
        counter = 0;
        if (choice.equals("1")) {
            student.getTimetable().getCourseOne().setNumStudents(student.getTimetable().getCourseOne().getNumStudents() - 1);
            student.getTimetable().setCourseOne(courseList.get(Integer.parseInt(courseSelection) - 1));

        } else if (choice.equals("2")) {
            student.getTimetable().getCourseTwo().setNumStudents(student.getTimetable().getCourseTwo().getNumStudents() - 1);
            student.getTimetable().setCourseTwo(courseList.get(Integer.parseInt(courseSelection) - 1));

        } else if (choice.equals("3")) {
            student.getTimetable().getCourseThree().setNumStudents(student.getTimetable().getCourseThree().getNumStudents() - 1);
            student.getTimetable().setCourseThree(courseList.get(Integer.parseInt(courseSelection) - 1));

        } else {
            student.getTimetable().getCourseFour().setNumStudents(student.getTimetable().getCourseFour().getNumStudents() - 1);
            System.out.println(student.getTimetable().getCourseFour().getNumStudents());
            student.getTimetable().setCourseFour(courseList.get(Integer.parseInt(courseSelection) - 1));

        }
        System.out.println("TIMETABLE SUCCESSFULLY UPDATED...");
    }

    // checks if course is valid 
    public static boolean courseValid(Courses course, Student student) {
        return !(course.equals(student.getTimetable().getCourseOne()) || course.equals(student.getTimetable().getCourseTwo()) || course.equals(student.getTimetable().getCourseThree()) || course.equals(student.getTimetable().getCourseFour()));
    }

    // update student info 
    public static void updateStudentInfo() {
        int studentIndex;
        studentIndex = displayStudent();
        if (studentIndex == -1) {
            System.out.println("NO STUDENTS FOUND...");
            return;
        }
        String choice, updatedAddress, updatedEmail;
        System.out.println("SELECT WHAT NEEDS TO BE UPDATED: ");
        System.out.println("[1] Address");
        System.out.println("[2] Email");
        choice = in.nextLine();
        switch (choice) {
            case "1":
                System.out.print("ENTER UPDATED ADDRESS: ");
                updatedAddress = in.nextLine();
                studentList.get(studentIndex - 1).getInfo().setAddress(updatedAddress);
                System.out.println("ADDRESS SUCCESSFULLY UPDATED...");
                break;
            case "2":
                System.out.print("ENTER UPDATED EMAIL: ");
                updatedEmail = in.nextLine();
                studentList.get(studentIndex - 1).getInfo().setEmail(updatedEmail);
                System.out.println("EMAIL SUCCESSFULLY UPDATED...");
                break;
        }

    }

    // add course to the school 
    public static void addCourseSchool() {
        String newCourse, newCourseCode;
        System.out.print("ENTER NAME OF COURSE ADDING TO SCHOOL: ");
        newCourse = in.nextLine();
        System.out.print("ENTER COURSE CODE OF NEW COURSE: ");
        newCourseCode = in.nextLine();
        Courses course = new Courses(newCourse, newCourseCode, 10);
        courseList.add(course);
        System.out.println("COURSE SUCCESSFULLY ADDED TO SCHOOL...");
    }

    // remove course from school 
    public static void removeCourseSchool() {
        String choice;
        System.out.println("CHOOSE COURSE TO REMOVE FROM SCHOOL: ");
        displayCourses();
        choice = in.nextLine();
        courseList.remove(Integer.parseInt(choice) - 1);
        System.out.println("COURSE SUCCESSFULLY REMOVED FROM SCHOOL...");
    }

    // update course info 
    public static void updateCourseInfo() {
        String choice, updatedCourseName, updatedCourseCode, courseSelection;
        System.out.println("SELECT COURSE TO UPDATE: ");
        displayCourses();
        courseSelection = in.nextLine();
        System.out.println("CHOOSE WHAT NEEDS TO BE UPDATED: ");
        System.out.println("[1] Course Name");
        System.out.println("[2] Course Code");
        choice = in.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Current Course Name: " + courseList.get(Integer.parseInt(courseSelection) - 1).getCourseName());
                System.out.print("ENTER UPDATED COURSE NAME: ");
                updatedCourseName = in.nextLine();
                courseList.get(Integer.parseInt(courseSelection) - 1).setCourseName(updatedCourseName);
                System.out.println("COURSE NAME SUCCESSFULLY UPDATED...");
                break;
            case "2":
                System.out.println("Current Course Code: " + courseList.get(Integer.parseInt(courseSelection) - 1).getCourseCode());
                System.out.print("ENTER UPDATED COURSE CODE: ");
                updatedCourseCode = in.nextLine();
                courseList.get(Integer.parseInt(courseSelection) - 1).setCourseCode(updatedCourseCode);
                System.out.println("COURSE CODE SUCCESSFULLY UPDATED");
                break;
        }

    }

    // list students 
    public static int studentList() {
        String choice;
        int counter = 0;
        System.out.println("SELECT A STUDENT: ");
        for (int i = 0; i < studentList.size(); i++) {
            counter += 1;
            System.out.println("[" + (i + 1) + "] " + studentList.get(i).getInfo().getFirstName() + " " + studentList.get(i).getInfo().getLastName());
        }
        if (counter == 0) {
            return -1;
        }
        choice = in.nextLine();
        return Integer.parseInt(choice);
    }

    // search a student
    public static int searchStudent() {
        String choice, studentIndex = " ";
        int numbering = 0;
        System.out.println("SEARCH STUDENT BY: ");
        System.out.println("[1] First Name");
        System.out.println("[2] Last Name");
        System.out.println("[3] Age");
        System.out.println("[4] Grade");
        System.out.println("[5] Course");
        choice = in.nextLine();
        switch (choice) {
            case "1":
                studentIndex = searchFirst();
                break;
            case "2":
                studentIndex = searchLast();
                break;
            case "3":
                studentIndex = searchAge();
                break;
            case "4":
                studentIndex = searchGrade();
                break;
            case "5":
                studentIndex = searchCourse();
                break;
        }
        if (studentIndex.equals(" ")) {
            return -1;
        }
        return Integer.parseInt(studentIndex);
    }

    // pre-load courses 
    public static void addCoursesList() {
        Courses math = new Courses("Math", "MATH101", 0);
        courseList.add(math);
        Courses science = new Courses("Science", "SCIENCE101", 0);
        courseList.add(science);
        Courses computerScience = new Courses("Computer Science", "COMPSCI101", 0);
        courseList.add(computerScience);
        Courses french = new Courses("French", "FRENCH101", 0);
        courseList.add(french);
        Courses history = new Courses("History", "HISTORY101", 0);
        courseList.add(history);
        Courses spare = new Courses("Spare", "NULL");
        courseList.add(spare);
    }

    // display course list
    public static void displayCourses() {
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println((i + 1) + ": " + courseList.get(i).getCourseName());
        }
    }

    // searches student using first name 
    public static String searchFirst() {
        String firstName, choice;
        boolean check;
        int numbering = 0;
        System.out.print("ENTER FIRST NAME OF STUDENTS LOOKING FOR: ");
        firstName = in.nextLine();
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getInfo().getFirstName().equalsIgnoreCase(firstName)) {
                numbering += 1;
                System.out.println("[" + (i + 1) + "] " + studentList.get(i).getInfo().getFirstName() + " " + studentList.get(i).getInfo().getLastName());
            }
        }
        check = checkList(numbering);
        if (check == true) {
            System.out.print("SELECT STUDENT: ");
            return choice = in.nextLine();
        }
        return " ";
    }

    // search last name 
    public static String searchLast() {
        String lastName, choice;
        boolean check;
        int numbering = 0;
        System.out.print("ENTER LAST NAME OF STUDENTS LOOKING FOR: ");
        lastName = in.nextLine();
        for (int i = 0; i < studentList.size(); i++) {

            if (studentList.get(i).getInfo().getLastName().equalsIgnoreCase(lastName)) {
                numbering += 1;
                System.out.println("[" + (i + 1) + "] " + studentList.get(i).getInfo().getFirstName() + " " + studentList.get(i).getInfo().getLastName());
            }
        }
        check = checkList(numbering);
        if (check == true) {
            System.out.print("SELECT STUDENT: ");
            return choice = in.nextLine();
        }
        return " ";
    }

    // search age 
    public static String searchAge() {
        String age, choice = " ";
        boolean check;
        int numbering = 0;
        System.out.print("ENTER AGE OF STUDENTS LOOKING FOR: ");
        age = in.nextLine();
        for (int i = 0; i < studentList.size(); i++) {

            if (studentList.get(i).getInfo().getAge().equalsIgnoreCase(age)) {
                numbering += 1;
                System.out.println("[" + (i + 1) + "] " + studentList.get(i).getInfo().getFirstName() + " " + studentList.get(i).getInfo().getLastName());
            }
        }
        check = checkList(numbering);
        if (check == true) {
            System.out.print("SELECT STUDENT: ");
            return choice = in.nextLine();
        }
        return " ";
    }

    // search grade 
    public static String searchGrade() {
        String choice, grade;
        boolean check;
        int numbering = 0;
        System.out.print("ENTER GRADE OF STUDENTS LOOKING FOR: ");
        grade = in.nextLine();
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getGrade().equalsIgnoreCase(grade)) {
                System.out.println("[" + (i + 1) + "] " + studentList.get(i).getInfo().getFirstName() + " " + studentList.get(i).getInfo().getLastName());
            }
        }
        check = checkList(numbering);
        if (check == true) {
            System.out.print("SELECT STUDENT: ");
            return choice = in.nextLine();
        }
        return " ";

    }

    // search course 
    public static String searchCourse() {
        String choice, course;
        boolean check;
        int numbering = 0;
        System.out.print("ENTER GRADE OF STUDENTS LOOKING FOR: ");
        course = in.nextLine();
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getTimetable().getCourseOne().getCourseName().equalsIgnoreCase(course) || studentList.get(i).getTimetable().getCourseTwo().getCourseName().equalsIgnoreCase(course) || studentList.get(i).getTimetable().getCourseThree().getCourseName().equalsIgnoreCase(course) || studentList.get(i).getTimetable().getCourseFour().getCourseName().equalsIgnoreCase(course)) {
                numbering += 1;
                System.out.println("[" + (i + 1) + "] " + studentList.get(i).getInfo().getFirstName() + " " + studentList.get(i).getInfo().getLastName());
            }
        }
        check = checkList(numbering);
        if (check == true) {
            System.out.print("SELECT STUDENT: ");
            return choice = in.nextLine();
        }
        return " ";
    }

    // pre-load students 
    public static void preloadStudents() {
        Student studentOne = new Student("12", "Frank", "Bell", "17", "56 Silver Rd.", "frank.bell18@gmail.com");
        studentList.add(studentOne);
        preloadCourses(studentOne);
        Student studentTwo = new Student("10", "Bella", "Trix", "16", "77 Pottermore Dr.", "bella.trix@hogwarts.com");
        studentList.add(studentTwo);
        preloadCourses(studentTwo);
        Student studentThree = new Student("9", "Andy", "Zhang", "15", "89 Sicily Crst.", "andy.zhang@yahoo.com");
        studentList.add(studentThree);
        preloadCourses(studentThree);

    }

    // pre-load courses 
    public static void preloadCourses(Student student){
        int randomNum = 0;

        selected.clear();
        for (int i = 0; i < 4; i++) {
            randomNum = generateRandom();
            switch (i) {
                case 0:
                    student.getTimetable().setCourseOne(courseList.get(randomNum));
                    courseList.get(randomNum).setNumStudents(1);
                    break;
                case 1:
                    student.getTimetable().setCourseTwo(courseList.get(randomNum));
                    courseList.get(randomNum).setNumStudents(1);
                    break;
                case 2:
                    student.getTimetable().setCourseThree(courseList.get(randomNum));
                    courseList.get(randomNum).setNumStudents(1);
                    break;
                default:
                    student.getTimetable().setCourseFour(courseList.get(randomNum));
                    courseList.get(randomNum).setNumStudents(1);
                    break;
            }
        }
    }

    // generates and checks if random num called already 
    public static int generateRandom() {
        double highNum = 5, lowNum = 0;
        int randomNum;

        do {
            randomNum = (int) ((highNum - lowNum + 1) * Math.random() + lowNum);
        } while (selected.contains(randomNum));

        selected.add(randomNum);
        return randomNum;
    }
    // checks if there is a student list or not 
    public static boolean checkList(int length) {
        return length != 0;
    }
}
