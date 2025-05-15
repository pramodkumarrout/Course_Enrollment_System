package jsp.courseenrollment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.courseenrollment.dao.StudentDao;
import jsp.courseenrollment.dto.ResponseStructure;
import jsp.courseenrollment.entity.Course;
import jsp.courseenrollment.entity.Instructor;
import jsp.courseenrollment.entity.Student;
import jsp.courseenrollment.exception.IdNotFoundException;
import jsp.courseenrollment.repository.InstructorRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentDao studentDao;
	
	
	 @Autowired
	 private InstructorRepository instructorRepository;
	 
	
	
	 // Save Student
	 
	 public ResponseEntity<ResponseStructure<Student>> saveStudent(int instructorId, Student student) {
	        Optional<Instructor> opt = instructorRepository.findById(instructorId);

	        if (opt.isEmpty()) {
	            throw new IdNotFoundException();  // ✅ Use global exception handler
	        }

	        student.setInstructor(opt.get());
	        Student saved = studentDao.saveStudent(student);

	        ResponseStructure<Student> structure = new ResponseStructure<>();
	        structure.setStatusCode(HttpStatus.CREATED.value());
	        structure.setMessage("Student saved successfully");
	        structure.setData(saved);

	        return new ResponseEntity<>(structure, HttpStatus.CREATED);
	    }
	
	 
	 
	 // Get All Students
	 
	    public ResponseEntity<ResponseStructure<List<Student>>> getAllStudents() {
	        List<Student> students = studentDao.getAllStudents();
	        
	        ResponseStructure<List<Student>> structure = new ResponseStructure<>();
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("All students retrieved successfully");
	        structure.setData(students);
	        
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    }
	 
	 
	    
	 // Get Student By Id
	    
	    public ResponseEntity<ResponseStructure<Student>> getStudentById(Long studentId) {
	        Optional<Student> studentOpt = studentDao.getStudentById(studentId);
	        
	        if (studentOpt.isEmpty()) {
	            throw new IdNotFoundException();  
	        }
	        
	        ResponseStructure<Student> structure = new ResponseStructure<>();
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Student retrieved successfully");
	        structure.setData(studentOpt.get());
	        
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    }
	 
	 
	 
	    // Update Student
	    
	    
	    public ResponseEntity<ResponseStructure<Student>> updateStudent(Long studentId, Student student) {
	        Optional<Student> studentOpt = studentDao.getStudentById(studentId);
	        
	        if (studentOpt.isEmpty()) {
	            throw new IdNotFoundException(); 
	        }

	        // Setting the ID to the existing student so that it can be updated properly
	        student.setId(studentId);
	        Student updatedStudent = studentDao.saveStudent(student);
	        
	        ResponseStructure<Student> structure = new ResponseStructure<>();
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Student updated successfully");
	        structure.setData(updatedStudent);
	        
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    }
	 
	    
	    
	    
	 // Delete Student
	    
	    
	    public ResponseEntity<ResponseStructure<String>> deleteStudent(Long studentId) {
	        Optional<Student> studentOpt = studentDao.getStudentById(studentId);

	        if (studentOpt.isEmpty()) {
	            throw new IdNotFoundException(); 
	        }

	        studentDao.deleteStudentById(studentId); 

	        ResponseStructure<String> structure = new ResponseStructure<>();
	        structure.setStatusCode(HttpStatus.NO_CONTENT.value());
	        structure.setMessage("Student deleted successfully");
	        structure.setData("Student with ID " + studentId + " deleted");

	        return new ResponseEntity<>(structure, HttpStatus.NO_CONTENT);
	    }
	 
	 
	    
	 // Get Course By Student Id
	    
	    
	    public ResponseEntity<ResponseStructure<List<Course>>> getCoursesByStudentId(Long studentId) {
	        List<Course> courses = studentDao.getCoursesByStudentId(studentId);

	        if (courses.isEmpty()) {
	            throw new IdNotFoundException();
	        }

	        ResponseStructure<List<Course>> structure = new ResponseStructure<>();
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Courses fetched successfully");
	        structure.setData(courses);

	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    }
	    
	    
	 // Get all students with pagination and sorting
	    
	    
	    public ResponseEntity<ResponseStructure<Page<Student>>> getStudents(int page, int size, String sortBy) {
	        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
	        Page<Student> studentsPage = studentDao.getStudents(pageable);

	        ResponseStructure<Page<Student>> structure = new ResponseStructure<>();
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Students retrieved successfully with pagination and sorting");
	        structure.setData(studentsPage);

	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    }
	    
	    
}



     
	
	
