package jsp.courseenrollment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jsp.courseenrollment.dto.ResponseStructure;
import jsp.courseenrollment.entity.Course;
import jsp.courseenrollment.entity.Student;
import jsp.courseenrollment.service.StudentService;


@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	
	// Save Student   http://localhost:8080/student?instructorId=5
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Student>> createStudent(
	        @RequestParam int instructorId,
	        @RequestBody Student student) {
	    return studentService.saveStudent(instructorId, student);
	}

	
	
	 // Get All Students  http://localhost:8080/student
	
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Student>>> getAllStudents() {
        return studentService.getAllStudents();
    }
    
	  
    
    // Get Student By Id    http://localhost:8080/student/1
    
    @GetMapping("/{studentId}")
    public ResponseEntity<ResponseStructure<Student>> getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }
    
    
    // Update Student       http://localhost:8080/student/10
    
    
    @PutMapping("/{studentId}")   
    public ResponseEntity<ResponseStructure<Student>> updateStudent(
            @PathVariable Long studentId, 
            @RequestBody Student student) {
        return studentService.updateStudent(studentId, student);
    }
    
    
   // Delete Student     http://localhost:8080/student/3
    
    
    @DeleteMapping("/{studentId}")
    public ResponseEntity<ResponseStructure<String>> deleteStudent(
            @PathVariable Long studentId) {
        return studentService.deleteStudent(studentId);
    }
	  	
    
    
    
   // Get courses by student ID      http://localhost:8080/student/3/courses
    
    
    @GetMapping("/{studentId}/courses")
    public ResponseEntity<ResponseStructure<List<Course>>> getCoursesByStudentId(
            @PathVariable Long studentId) {
        return studentService.getCoursesByStudentId(studentId);
    }
    
    
    
    // Get students with pagination and sorting        http://localhost:8080/student/all?page=0&size=5&sortBy=name
    
    
    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<Page<Student>>> getStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        return studentService.getStudents(page, size, sortBy);
    }
    
    
   
    
    
}
