package peaksoft.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.models.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


//    @Query("select c from Course c where c.company.id = ?1")
//    List<Course> findAllCoursesByCompanyId(Long id);



}
