package jsp.courseenrollment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import jsp.courseenrollment.entity.Enrollment;
import jsp.courseenrollment.service.EnrollmentService;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;


     // Save Enrollment       http://localhost:8080/enrollment?studentId=1&courseId=2
      
    @PostMapping
    
    public ResponseEntity<ResponseStructure<Enrollment>> saveEnrollment(
            @RequestParam int studentId,
            @RequestParam int courseId,
            @RequestBody Enrollment enrollment) {
        return enrollmentService.saveEnrollment(studentId, courseId, enrollment);
    }
    
    
    // Get All Enrollment          http://localhost:8080/enrollment
    
    
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Enrollment>>> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }
    
    
    // Get Enrollment By Id       http://localhost:8080/enrollment/6
    
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Enrollment>> getEnrollmentById(@PathVariable int id) {
        return enrollmentService.getEnrollmentById(id);
    }
    
    
    
    // Update Enrollment             http://localhost:8080/enrollment/3
    
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Enrollment>> updateEnrollment(@PathVariable int id, @RequestBody Enrollment enrollment) {
        return enrollmentService.updateEnrollment(id, enrollment);
    }
    
    
    
    // Delete Enrollment       http://localhost:8080/enrollment/1
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteEnrollment(@PathVariable int id) {
        return enrollmentService.deleteEnrollment(id);
    }
    
    
    
    // Get Enrollment By Student Id       http://localhost:8080/enrollment/student/2
    
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<ResponseStructure<List<Enrollment>>> getEnrollmentsByStudentId(@PathVariable int studentId) {
        return enrollmentService.getEnrollmentsByStudentId(studentId);
    }
    
    
    
    // Get Enrollment By Course Id       http://localhost:8080/enrollment/course/6
    
    
    @GetMapping("/course/{courseId}")
    public ResponseEntity<ResponseStructure<List<Enrollment>>> getEnrollmentsByCourseId(@PathVariable int courseId) {
        return enrollmentService.getEnrollmentsByCourseId(courseId);
    }
    
    
    // Get Enrollment By Pagination And Sorting     http://localhost:8080/enrollment/filtered?page=0&size=5&sort=enrollDate,desc
    
    
    @GetMapping("/filtered")
    public ResponseEntity<ResponseStructure<Page<Enrollment>>> getEnrollmentsFiltered(Pageable pageable) {
        return enrollmentService.getEnrollmentsWithPaginationAndSorting(pageable);
    }
    
    
}
