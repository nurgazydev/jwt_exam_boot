package peaksoft.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.models.Instructor;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

//    @Query("select i from Instructor i where i.company.id = ?1")
//    List<Instructor> findInstructorsByCompanyId(Long id);



}
