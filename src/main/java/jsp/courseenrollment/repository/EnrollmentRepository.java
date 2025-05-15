package jsp.courseenrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jsp.courseenrollment.entity.Enrollment;


@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{
	
	
	// Custom query method by student id
    List<Enrollment> findByStudentId(int studentId);
	
    
 // Custom finder method by course id
    List<Enrollment> findByCourseId(int courseId);

}
