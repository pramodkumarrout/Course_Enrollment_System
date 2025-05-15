package jsp.courseenrollment.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import jsp.courseenrollment.entity.Course;
import jsp.courseenrollment.repository.CourseRepository;


@Repository
public class CourseDao {

    @Autowired
    private CourseRepository courseRepository;

    
    // Save Course  // Also Update Course
    
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }
    
    
    // Get All Course
    
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    
    
    
    // Get Course By Id
    
    public Course getCourseById(int courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }
    
    
    
    // Delete Course
    
    public void deleteCourse(int courseId) {
        courseRepository.deleteById(courseId);
    }
    
    
    
    // Get Course By Instructor Id   // http://localhost:8080/course/instructor/4
    
    public List<Course> getCoursesByInstructorId(int instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }

    
    
    // Get Course With Pagination and Sorting   // http://localhost:8080/course/filtered?page=0&size=3&sortBy=title&direction=asc
    
    
    public Page<Course> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }
    
    

}
