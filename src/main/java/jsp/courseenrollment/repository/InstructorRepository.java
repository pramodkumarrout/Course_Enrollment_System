package jsp.courseenrollment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jsp.courseenrollment.entity.Instructor;


@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
	
	

    // Find all instructors with pagination and sorting
	Page<Instructor> findAll(Pageable pageable);
	
}
