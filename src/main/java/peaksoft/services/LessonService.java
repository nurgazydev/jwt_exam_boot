package peaksoft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.mappers.LessonMapper;
import peaksoft.dto.requests.LessonRequest;
import peaksoft.dto.responses.LessonResponse;
import peaksoft.dto.responses.simpl.SimpleResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Course;
import peaksoft.models.Lesson;
import peaksoft.repositories.CourseRepository;
import peaksoft.repositories.LessonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final LessonMapper lessonMapper;


    public List<LessonResponse> getAll() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessonMapper.convertToFindAllLessons(lessons);
    }


    public LessonResponse findById(Long id) {
        Lesson lesson = getById(id);
        return lessonMapper.convertToView(lesson);
    }

    private Lesson getById(Long id) {
       return lessonRepository.findById(id)
               .orElseThrow(()-> new NotFoundException("lesson with id: " + id + " not found!"));
    }

//    public List<Lesson> findLessonsByCourseId(Long courseId) {
//        return lessonRepository.findLessonsByCourseId(courseId);
//    }

    public LessonResponse save(LessonRequest lessonRequest) {
        Lesson lesson = lessonMapper.convertToEntity(lessonRequest);
        Course course = courseRepository.findById(lessonRequest.getCourseId())
                .orElseThrow(() -> new NotFoundException("course with id: " + lessonRequest.getCourseId() + " not found"));
        course.setLesson(lesson);
        lesson.setCourse(course);
        lessonRepository.save(lesson);
        return lessonMapper.convertToView(lesson);
    }


    public SimpleResponse delete(Long id) {
        boolean exists = lessonRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException("lesson with id: "+id+"does not exists");
        }
        lessonRepository.deleteById(id);
        return new SimpleResponse(
                "DELETED",
                "lesson with id: " +id+ " deleted successfully"
        );
    }

    public LessonResponse update(Long id, LessonRequest lessonRequest) {
        Lesson lesson = getById(id);
        lessonMapper.convertToUpdate(lessonRequest, lesson);
        lesson.setId(id);
        lessonRepository.save(lesson);
        return lessonMapper.convertToView(lesson);
    }
}
