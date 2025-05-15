package jsp.courseenrollment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jsp.courseenrollment.entity.Course;
import jsp.courseenrollment.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Get Course By Student Id
    @Query("SELECT e.course FROM Enrollment e WHERE e.student.id = ?1")
    List<Course> findCourseById(Long studentId);  

    // Corrected: Find students by Instructor ID directly using the instructorId field
    List<Student> findByInstructorId(int instructorId);
    
    
//    List<Course> findCourseByStudentId(Long studentId);
    List<Student> findByInstructorId(Long instructorId);
    
    
    // Method to find student by ID
    Optional<Student> findById(int id); 
    


}
	