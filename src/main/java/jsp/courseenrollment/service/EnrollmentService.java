package jsp.courseenrollment.service;


import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.courseenrollment.dao.CourseDao;
import jsp.courseenrollment.dao.EnrollmentDao;
import jsp.courseenrollment.dao.StudentDao;  
import jsp.courseenrollment.dto.ResponseStructure;
import jsp.courseenrollment.entity.Enrollment;
import jsp.courseenrollment.entity.Course;
import jsp.courseenrollment.entity.Student;
import jsp.courseenrollment.exception.IdNotFoundException;


@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentDao enrollmentDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CourseDao courseDao;
    
    
    
    // Save Enrollment

    public ResponseEntity<ResponseStructure<Enrollment>> saveEnrollment(int studentId, int courseId, Enrollment enrollment) {

        // Fetch student safely from Optional
        Optional<Student> optionalStudent = studentDao.getStudentById((long) studentId);
        if (!optionalStudent.isPresent()) {
            throw new RuntimeException("Student not found with id: " + studentId);
        }
        Student student = optionalStudent.get();

        // Fetch course (may be null if not found)
        Course course = courseDao.getCourseById(courseId);
        if (course == null) {
            throw new RuntimeException("Course not found with id: " + courseId);
        }

        // Set in enrollment entity
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollDate(LocalDate.now());

        // Save enrollment
        Enrollment saved = enrollmentDao.saveEnrollment(enrollment);

        // Prepare response
        ResponseStructure<Enrollment> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.CREATED.value());
        structure.setMessage("Enrollment saved successfully");
        structure.setData(saved);

        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }
    
    
    
    // Get All Enrollment
    
    
    public ResponseEntity<ResponseStructure<List<Enrollment>>> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentDao.getAllEnrollments();

        ResponseStructure<List<Enrollment>> structure = new ResponseStructure<>();
        structure.setStatusCode(200);
        structure.setMessage("Enrollments fetched successfully");
        structure.setData(enrollments);

        return ResponseEntity.ok(structure);
    }
    
    
    // Get Enrollment By Id
    
    
    public ResponseEntity<ResponseStructure<Enrollment>> getEnrollmentById(int id) {
        Optional<Enrollment> optional = enrollmentDao.getEnrollmentById(id);

        if (!optional.isPresent()) {
            throw new IdNotFoundException();
        }

        ResponseStructure<Enrollment> responseStructure = new ResponseStructure<>();
        responseStructure.setStatusCode(200);
        responseStructure.setMessage("Enrollment found");
        responseStructure.setData(optional.get());

        return ResponseEntity.ok(responseStructure);
    }
    
    
    
    // Update Enrollment
    
    public ResponseEntity<ResponseStructure<Enrollment>> updateEnrollment(int id, Enrollment updatedEnrollment) {
        Optional<Enrollment> optional = enrollmentDao.getEnrollmentById(id);

        if (!optional.isPresent()) {
            throw new IdNotFoundException();
        }

        Enrollment existingEnrollment = optional.get();

        // Update fields — customize as needed
        existingEnrollment.setEnrollDate(updatedEnrollment.getEnrollDate());
        existingEnrollment.setStudent(updatedEnrollment.getStudent());
        existingEnrollment.setCourse(updatedEnrollment.getCourse());

        Enrollment savedEnrollment = enrollmentDao.saveEnrollment(existingEnrollment);

        ResponseStructure<Enrollment> responseStructure = new ResponseStructure<>();
        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setMessage("Enrollment updated successfully");
        responseStructure.setData(savedEnrollment);

        return ResponseEntity.ok(responseStructure);
    }
    
    
    
    // Delete Enrollment
    
    
    public ResponseEntity<ResponseStructure<String>> deleteEnrollment(int id) {
        Optional<?> optional = enrollmentDao.getEnrollmentById(id);

        if (!optional.isPresent()) {
            throw new IdNotFoundException();
        }

        enrollmentDao.deleteEnrollmentById(id);

        ResponseStructure<String> responseStructure = new ResponseStructure<>();
        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setMessage("Enrollment deleted successfully");
        responseStructure.setData("Enrollment with id " + id + " deleted.");

        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }
    
    
    
    // Get Enrollment By Student Id
    
    
    public ResponseEntity<ResponseStructure<List<Enrollment>>> getEnrollmentsByStudentId(int studentId) {
        List<Enrollment> enrollments = enrollmentDao.getEnrollmentsByStudentId(studentId);

        if (enrollments == null || enrollments.isEmpty()) {
            throw new IdNotFoundException();
        }

        ResponseStructure<List<Enrollment>> responseStructure = new ResponseStructure<>();
        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setMessage("Enrollments fetched successfully");
        responseStructure.setData(enrollments);

        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }
    
    
   
    // Get Enrollment By Course Id
    
    
    public ResponseEntity<ResponseStructure<List<Enrollment>>> getEnrollmentsByCourseId(int courseId) {
        List<Enrollment> enrollments = enrollmentDao.getEnrollmentsByCourseId(courseId);

        if (enrollments == null || enrollments.isEmpty()) {
            throw new IdNotFoundException();
        }

        ResponseStructure<List<Enrollment>> responseStructure = new ResponseStructure<>();
        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setMessage("Enrollments fetched successfully");
        responseStructure.setData(enrollments);

        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }
    
    
    
    // Get Enrollment By Pagination And Sorting
    
    
    public ResponseEntity<ResponseStructure<Page<Enrollment>>> getEnrollmentsWithPaginationAndSorting(Pageable pageable) {
        Page<Enrollment> page = enrollmentDao.getEnrollments(pageable);

        ResponseStructure<Page<Enrollment>> responseStructure = new ResponseStructure<>();
        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setMessage("Enrollments fetched successfully");
        responseStructure.setData(page);

        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }
    
    
}
