package jsp.courseenrollment.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import jsp.courseenrollment.entity.Course;
import jsp.courseenrollment.entity.Student;
import jsp.courseenrollment.repository.StudentRepository;

@Repository
public class StudentDao {
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	// Save Student   // Update Student   
	
	 public Student saveStudent(Student student) {
	        return studentRepository.save(student);
	    }
	
	 
    // Get All Student
	 
	 
	 public List<Student> getAllStudents() {
	        return studentRepository.findAll();
	    }
	 
	 
	 
    // Get Student By Id
	 
	 public Optional<Student> getStudentById(Long studentId) {
	        return studentRepository.findById(studentId);
	    }
	 
	 
	 
	// Delete Student 
	 
	 
	 public void deleteStudentById(Long studentId) {
	        studentRepository.deleteById(studentId);
	    }
	 
	 
	 
	 
	// Get Course By Student Id
	 
	 
	 public List<Course> getCoursesByStudentId(Long studentId) {
	        return studentRepository.findCourseById(studentId);
	    }
	
	
	// Get Student Using Pagination and Sorting
	 
	 
	 public Page<Student> getStudents(Pageable pageable) {
	        return studentRepository.findAll(pageable);
	    }
	 
	 
	
	
	
	
}
