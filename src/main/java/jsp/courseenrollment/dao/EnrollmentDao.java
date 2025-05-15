package jsp.courseenrollment.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import jsp.courseenrollment.entity.Enrollment;
import jsp.courseenrollment.entity.Student;
import jsp.courseenrollment.repository.EnrollmentRepository;
import jsp.courseenrollment.repository.StudentRepository;

@Repository
public class EnrollmentDao {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    
    
    @Autowired
    private StudentRepository studentRepository;
    
    
    // Save Enrollment  & Update Enrollment 
     
    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }
    
    
    // Get All Enrollment
    
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }
    
    
    // Get Enrollment By Id
    
    public Optional<Enrollment> getEnrollmentById(int id) {
        return enrollmentRepository.findById(id);
    }
    
    
    
    // Delete Enrollment
    
    public void deleteEnrollmentById(int id) {
        enrollmentRepository.deleteById(id);
    }
    
    
    
    // Get Enrollment By Student Id
    
    
    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }
    
    
    
    // Get Enrollment By Course Id
    
    
    public List<Enrollment> getEnrollmentsByCourseId(int courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }
    
    
    // Get Enrollment By Pagination and Sorting
    
    
    public Page<Enrollment> getEnrollments(Pageable pageable) {
        return enrollmentRepository.findAll(pageable);
    }
    
    
        
    
    
}
