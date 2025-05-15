package jsp.courseenrollment.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.courseenrollment.dao.InstructorDao;
import jsp.courseenrollment.dto.ResponseStructure;
import jsp.courseenrollment.entity.Instructor;
import jsp.courseenrollment.entity.Student;
import jsp.courseenrollment.exception.IdNotFoundException;

@Service
public class InstructorService {

    @Autowired
    private InstructorDao instructorDao;

    // Save Instructor
    public ResponseEntity<ResponseStructure<Instructor>> saveInstructor(Instructor instructor) {
        Instructor saved = instructorDao.saveInstructor(instructor);

        ResponseStructure<Instructor> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.CREATED.value());
        structure.setMessage("Instructor saved successfully");
        structure.setData(saved);

        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }

    // Get All Instructors
    public ResponseEntity<ResponseStructure<List<Instructor>>> getAllInstructors() {
        List<Instructor> instructors = instructorDao.getAllInstructors();
        ResponseStructure<List<Instructor>> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("All Instructors Retrieved Successfully");
        structure.setData(instructors);
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    // Get Instructor By Id
    public ResponseEntity<ResponseStructure<Instructor>> getInstructorById(int id) {
        Optional<Instructor> optional = instructorDao.getInstructorById(id);
        ResponseStructure<Instructor> structure = new ResponseStructure<>();

        if (optional.isPresent()) {
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Instructor found successfully");
            structure.setData(optional.get());
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new IdNotFoundException();
        }
    }

    // Update Instructor
    public ResponseEntity<ResponseStructure<Instructor>> updateInstructor(int id, Instructor instructor) {
        Optional<Instructor> optional = instructorDao.getInstructorById(id);

        if (optional.isPresent()) {
            // Set the id for the existing instructor to ensure it's updated correctly
            instructor.setId(id);
            Instructor updatedInstructor = instructorDao.saveInstructor(instructor);

            ResponseStructure<Instructor> structure = new ResponseStructure<>();
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Instructor updated successfully");
            structure.setData(updatedInstructor);

            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new IdNotFoundException();
        }
    }
    
    
    
    // Delete Instructor
    public ResponseEntity<ResponseStructure<String>> deleteInstructor(int id) {
        Optional<Instructor> opt = instructorDao.getInstructorById(id);

        if (opt.isPresent()) {
            instructorDao.deleteInstructorById(id);
            ResponseStructure<String> structure = new ResponseStructure<>();
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setMessage("Instructor Deleted Successfully");
            structure.setData("Deleted Instructor with ID: " + id);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            throw new IdNotFoundException();
        }
    }
    
    
    // Get Student By Instructor Id
    
    public ResponseEntity<ResponseStructure<List<Student>>> getStudentsByInstructorId(int instructorId) {
        List<Student> students = instructorDao.getStudentsByInstructorId(instructorId);

        if (students.isEmpty()) {
            throw new IdNotFoundException(); 
        }

        ResponseStructure<List<Student>> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Students fetched by Instructor ID");
        structure.setData(students);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }
    
    
    
    
    // Get Instructor using Pagination And Sorting
    
//    public Page<Instructor> getInstructorsWithPagination(int page, int size, String sortBy, String order) {
//        Sort sort = order.equalsIgnoreCase("asc") ? 
//                    Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//        Pageable pageable = PageRequest.of(page, size, sort);
//        return instructorDao.findAll(pageable);
//    }
    
    
    public ResponseEntity<ResponseStructure<List<Instructor>>> getInstructorsWithPaginationAndSorting(
            int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Instructor> pageResult = instructorDao.findAll(pageable);

        ResponseStructure<List<Instructor>> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Instructors fetched with pagination and sorting");
        structure.setData(pageResult.getContent());

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    
    
}
    
    
 
