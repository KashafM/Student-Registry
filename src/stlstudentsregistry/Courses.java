package stlstudentsregistry;

public class Courses {

    private String courseName, courseCode;
    private int numStudents;
    
    public Courses(String courseName, String courseCode, int numStudents) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.numStudents = numStudents;
    }

    public Courses(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getNumStudents() {
        return numStudents;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }

}
