package peaksoft.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.models.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

//    @Query("select t from Task t where t.lesson.id = ?1")
//    List<Task> findTasksByLessonId(Long lessonId);

}
