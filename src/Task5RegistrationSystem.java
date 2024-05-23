import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class to represent a Course
class Course {
    private final String courseId;
    private final String courseName;

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return courseId + ": " + courseName;
    }
}

// Class to represent a Student
class Student {
    private final String studentId;
    private final String studentName;
    private final List<Course> registeredCourses;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void registerCourse(Course course) {
        registeredCourses.add(course);
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    @Override
    public String toString() {
        return studentId + ": " + studentName;
    }
}

// Class for the Registration System
class RegistrationSystem {
    private final List<Student> students;
    private final List<Course> courses;

    public RegistrationSystem() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
    }

    public static void main(String[] args) {
        RegistrationSystem system = new RegistrationSystem();

        // Adding some courses
        system.addCourse(new Course("CS101", "Introduction to Computer Science"));
        system.addCourse(new Course("MA101", "Calculus I"));
        system.addCourse(new Course("PH101", "Physics I"));

        // Adding some students
        system.addStudent(new Student("S001", "Alice Johnson"));
        system.addStudent(new Student("S002", "Bob Smith"));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Register for a course");
            System.out.println("2. View registered courses");
            System.out.println("3. View available courses");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    Student student = system.findStudentById(studentId);
                    if (student != null) {
                        System.out.print("Enter course ID: ");
                        String courseId = scanner.nextLine();
                        Course course = system.findCourseById(courseId);
                        if (course != null) {
                            student.registerCourse(course);
                            System.out.println("Course registered successfully.");
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    student = system.findStudentById(studentId);
                    if (student != null) {
                        List<Course> registeredCourses = student.getRegisteredCourses();
                        System.out.println("Registered courses for " + student.getStudentName() + ":");
                        for (Course registeredCourse : registeredCourses) {
                            System.out.println(registeredCourse);
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    System.out.println("Available courses:");
                    for (Course availableCourse : system.getCourses()) {
                        System.out.println(availableCourse);
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public Course findCourseById(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    public List<Course> getCourses() {
        return courses;
    }
}