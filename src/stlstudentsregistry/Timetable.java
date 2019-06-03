package stlstudentsregistry;

import java.util.ArrayList;

public class Timetable {

    private ArrayList<Courses> studentCourses;
    private Courses courseOne, courseTwo, courseThree, courseFour;

    public Timetable() {
        studentCourses = new ArrayList<Courses>();
    }

    public Courses getCourseOne() {
        return courseOne;
    }

    public Courses getCourseTwo() {
        return courseTwo;
    }

    public Courses getCourseThree() {
        return courseThree;
    }

    public Courses getCourseFour() {
        return courseFour;
    }

    public void setCourseOne(Courses courseOne) {
        this.courseOne = courseOne;
    }

    public void setCourseTwo(Courses courseTwo) {
        this.courseTwo = courseTwo;
    }

    public void setCourseThree(Courses courseThree) {
        this.courseThree = courseThree;
    }

    public void setCourseFour(Courses courseFour) {
        this.courseFour = courseFour;
    }

}
