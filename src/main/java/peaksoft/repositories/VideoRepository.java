package peaksoft.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.models.Video;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

//    @Query("select v from Video v where v.lesson.id = ?1")
//    List<Video> findVideosByLessonId(Long lessonId);

}
