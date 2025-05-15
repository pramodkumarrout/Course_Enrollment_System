package jsp.courseenrollment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jsp.courseenrollment.dto.ResponseStructure;
import jsp.courseenrollment.entity.Instructor;
import jsp.courseenrollment.entity.Student;
import jsp.courseenrollment.service.InstructorService;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    // Save Instructor
    @PostMapping
    public ResponseEntity<ResponseStructure<Instructor>> createInstructor(@RequestBody Instructor instructor) {
        return instructorService.saveInstructor(instructor);
    }

    // Get All Instructors
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Instructor>>> getAllInstructors() {
        return instructorService.getAllInstructors();
    }

    // Get Instructor By Id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Instructor>> getInstructorById(@PathVariable int id) {
        return instructorService.getInstructorById(id);
    }

    // Update Instructor
    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Instructor>> updateInstructor(
            @PathVariable int id,
            @RequestBody Instructor instructor) {
        return instructorService.updateInstructor(id, instructor);
    }
    
    // Delete Instructor
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteInstructor(@PathVariable int id) {
        return instructorService.deleteInstructor(id);
    }
    
    
    // Get Student By Instructor Id
    @GetMapping("/{id}/students")
    public ResponseEntity<ResponseStructure<List<Student>>> getStudentsByInstructorId(@PathVariable int id) {
        return instructorService.getStudentsByInstructorId(id);
    }
    
    
    
    // Get Instructor By Pagination and Sorting
    
//    // Combined method to get instructors with optional pagination and sorting
//    @GetMapping("/filtered")  // postman url: http://localhost:8080/instructor/filtered?page=1&size=3&sortBy=name&order=asc  
//    public ResponseEntity<ResponseStructure<Page<Instructor>>> getInstructors(
//            @RequestParam int page,
//            @RequestParam int size,
//            @RequestParam(required = false) String sortBy,
//            @RequestParam(required = false) String order
//    ) {
//        Page<Instructor> instructorsPage = instructorService.getInstructorsWithPagination(page, size, sortBy, order);
//
//        // Prepare ResponseStructure
//        ResponseStructure<Page<Instructor>> structure = new ResponseStructure<>();
//        structure.setStatusCode(HttpStatus.OK.value());
//        structure.setMessage("Instructors fetched successfully");
//        structure.setData(instructorsPage);
//
//        return new ResponseEntity<>(structure, HttpStatus.OK);
//    }
    
    
    
    @GetMapping("/filtered")
    public ResponseEntity<ResponseStructure<List<Instructor>>> getInstructorsWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return instructorService.getInstructorsWithPaginationAndSorting(page, size, sortBy, direction);
    }

    
    
}
