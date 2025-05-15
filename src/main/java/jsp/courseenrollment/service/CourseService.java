package jsp.courseenrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.courseenrollment.dao.CourseDao;
import jsp.courseenrollment.dao.InstructorDao;
import jsp.courseenrollment.dto.ResponseStructure;
import jsp.courseenrollment.entity.Course;
import jsp.courseenrollment.entity.Instructor;
import jsp.courseenrollment.exception.IdNotFoundException;

@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private InstructorDao instructorDao;

    
    // Save Course
    
    public ResponseEntity<ResponseStructure<Course>> saveCourse(int instructorId, Course course) {
        Instructor instructor = instructorDao.getInstructorById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found with ID: " + instructorId));

        course.setInstructor(instructor); 
        Course saved = courseDao.saveCourse(course); 

        ResponseStructure<Course> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.CREATED.value());
        structure.setMessage("Course saved successfully");
        structure.setData(saved);

        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }
    
    
    // Get All Courses
    
    
    public ResponseEntity<ResponseStructure<List<Course>>> getAllCourses() {
        List<Course> courses = courseDao.getAllCourses();

        ResponseStructure<List<Course>> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Courses fetched successfully");
        structure.setData(courses);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }
    
    
    
    // Get Course By Id
    
    public ResponseEntity<ResponseStructure<Course>> getCourseById(int id) {
        Course course = courseDao.getCourseById(id);
        if (course == null) {
            throw new IdNotFoundException();
        }

        ResponseStructure<Course> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Course fetched successfully");
        structure.setData(course);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }
    
    
    
    // Update Course
    
    
    public ResponseEntity<ResponseStructure<Course>> updateCourse(int courseId, Course updatedCourse) {
        Course existingCourse = courseDao.getCourseById(courseId);
        if (existingCourse == null) {
            throw new IdNotFoundException();
        }

        existingCourse.setTitle(updatedCourse.getTitle());
        existingCourse.setDuration(updatedCourse.getDuration());
        existingCourse.setFee(updatedCourse.getFee());

        Course saved = courseDao.saveCourse(existingCourse);

        ResponseStructure<Course> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Course updated successfully");
        structure.setData(saved);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }
    
    
    
    // Delete Course
    
    
    public ResponseEntity<ResponseStructure<String>> deleteCourse(int courseId) {
        Course course = courseDao.getCourseById(courseId);
        if (course == null) {
            throw new IdNotFoundException();
        }

        courseDao.deleteCourse(courseId);

        ResponseStructure<String> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Course deleted successfully");
        structure.setData("Course with ID " + courseId + " has been deleted.");

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    
    
    
     // Get Course By Instructor Id
    
    
    public ResponseEntity<ResponseStructure<List<Course>>> getCoursesByInstructorId(int instructorId) {
        List<Course> courses = courseDao.getCoursesByInstructorId(instructorId);

        if (courses.isEmpty()) {
            throw new IdNotFoundException(); 
        }

        ResponseStructure<List<Course>> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Courses retrieved successfully");
        structure.setData(courses);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    
    
    // Get Course With Pagination and Sorting
    
    
    public ResponseEntity<ResponseStructure<List<Course>>> getCoursesWithPaginationAndSorting(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        Page<Course> coursePage = courseDao.getAllCourses(pageable);

        List<Course> courses = coursePage.getContent();
        if (courses.isEmpty()) {
            throw new IdNotFoundException();  
        }

        ResponseStructure<List<Course>> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Courses retrieved with pagination and sorting");
        structure.setData(courses);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    
    
    
}
