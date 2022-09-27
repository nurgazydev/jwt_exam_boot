package peaksoft.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.models.Lesson;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

//    @Query("select l from Lesson l where l.course.id = ?1")
//    List<Lesson> findLessonsByCourseId(Long courseId);

}
