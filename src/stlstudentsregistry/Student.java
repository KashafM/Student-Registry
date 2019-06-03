package stlstudentsregistry;

public class Student {

    private String grade;
    private PersonalInformation info;
    private Timetable timetable;
    private LockerInformation lockerInfo;

    public Student(String grade, String firstName, String lastName, String age, String address, String email) {
        this.grade = grade;
        info = new PersonalInformation(firstName, lastName, age, address, email);
        timetable = new Timetable();
        lockerInfo = new LockerInformation();
    }
    
    public String getGrade() {
        return grade;
    }

    public PersonalInformation getInfo() {
        return info;
    }

    public LockerInformation getlockerInfo() {
        return lockerInfo;
    }

    public Timetable getTimetable() {
        return timetable;
    }
   

}
