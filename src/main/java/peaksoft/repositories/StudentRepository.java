package peaksoft.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.models.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

//    @Query("select s from Student s where s.company.id = ?1")
//    List<Student> findAllStudentsByCompanyId(Long companyId);

    @Query("select s from Student s where upper(s.firstName) like concat('%',:text,'%')" +
            "or upper(s.lastName) like concat('%',:text,'%') " +
            "or upper(s.email) like concat('%',:text,'%') ")
    List<Student> searchByFirstName(@Param("text") String text, Pageable pageable);

}
