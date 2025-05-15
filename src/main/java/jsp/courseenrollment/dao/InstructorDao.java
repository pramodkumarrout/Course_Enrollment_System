package jsp.courseenrollment.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import jsp.courseenrollment.entity.Instructor;
import jsp.courseenrollment.entity.Student;
import jsp.courseenrollment.repository.InstructorRepository;
import jsp.courseenrollment.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class InstructorDao {

    @Autowired
    private InstructorRepository instructorRepository;
    
    
    @Autowired
    private StudentRepository studentRepository;  
    
    

    // Save Instructor and Update Instructor Work
    public Instructor saveInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    // Get All Instructors
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    // Get Instructor by Id
    public Optional<Instructor> getInstructorById(int id) {
        return instructorRepository.findById(id); //
    }

    // Delete Instructor by Id
    public void deleteInstructorById(int id) {
        instructorRepository.deleteById(id);
    }
    
   
    // Get Student By Instructor Id
    public List<Student> getStudentsByInstructorId(int instructorId) {
        return studentRepository.findByInstructorId(instructorId); // Custom query method
    }
    
    
    // Get Instructor By using Pagination and Sorting
    
//    public Page<Instructor> findAll(Pageable pageable) {
//        return instructorRepository.findAll(pageable);
//    }

    
    public Page<Instructor> findAll(Pageable pageable) {
        return instructorRepository.findAll(pageable);
    }
    
    
    
    
    
    
    
}
