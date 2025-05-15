package jsp.courseenrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jsp.courseenrollment.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
	
	
	
	    // Get Course By Instructor Id
	    List<Course> findByInstructorId(int instructorId);
	
}	
