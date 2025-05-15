package jsp.courseenrollment.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate enrollDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(LocalDate enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Enrollment [id=" + id + ", enrollDate=" + enrollDate + "]";
	}

}