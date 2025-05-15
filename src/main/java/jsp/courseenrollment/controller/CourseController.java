package jsp.courseenrollment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jsp.courseenrollment.dto.ResponseStructure;
import jsp.courseenrollment.entity.Course;
import jsp.courseenrollment.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    
    
    // Save Course
    
    @PostMapping
    public ResponseEntity<ResponseStructure<Course>> createCourse(
            @RequestParam int instructorId,
            @RequestBody Course course) {
        return courseService.saveCourse(instructorId, course);
    }
    
    
    // Get All Course
    
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Course>>> getAllCourses() {
        return courseService.getAllCourses();
    }
    
    
    
    // Get Course By Id
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Course>> getCourseById(@PathVariable int id) {
        return courseService.getCourseById(id);
    }
    
    
    // Update Course  
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Course>> updateCourse(
            @PathVariable int id,
            @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }
    
    
    
    // Delete Course
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteCourse(@PathVariable int id) {
        return courseService.deleteCourse(id);
    }

    
    
    // Get Course By Instructor Id
    
    
    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<ResponseStructure<List<Course>>> getCoursesByInstructorId(@PathVariable int instructorId) {
        return courseService.getCoursesByInstructorId(instructorId);
    }

    
    
    // Get Course By Pagination and Sorting
    
    
    @GetMapping("/filtered")
    public ResponseEntity<ResponseStructure<List<Course>>> getCoursesWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return courseService.getCoursesWithPaginationAndSorting(page, size, sortBy, direction);
    }

    
    
    
}
