package peaksoft.dto.mappers;

import org.springframework.stereotype.Component;
import peaksoft.dto.requests.CompanyRequest;
import peaksoft.dto.requests.LessonRequest;
import peaksoft.dto.responses.CompanyResponse;
import peaksoft.dto.responses.LessonResponse;
import peaksoft.models.Company;
import peaksoft.models.Lesson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class LessonMapper {

    public Lesson convertToEntity(LessonRequest lessonRequest) {
        if (lessonRequest == null) {
            return null;
        }
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonRequest.getLessonName());
        lesson.setCreatedAt(LocalDate.now());
        lesson.setActive(true);
        return lesson;
    }

    public LessonResponse convertToView(Lesson lesson) {
        if (lesson == null) {
            return null;
        }
        LessonResponse lessonResponse = new LessonResponse();
        lessonResponse.setId(lesson.getId());
        lessonResponse.setCourseName(lesson.getLessonName());
        lessonResponse.setCreatedAt(LocalDate.now());
        lessonResponse.setActive(lesson.isActive());
        return lessonResponse;
    }

    public List<LessonResponse> convertToFindAllLessons(List<Lesson> lessons) {
        List<LessonResponse> lessonResponses = new ArrayList<>();
        for (Lesson lesson : lessons) {
            lessonResponses.add(convertToView(lesson));
        }
        return lessonResponses;
    }

    public Lesson convertToUpdate(LessonRequest lessonRequest, Lesson lesson) {
        lesson.setLessonName(lessonRequest.getLessonName());
        lesson.setCreatedAt(LocalDate.now());
        lesson.setActive(lesson.isActive());
        return lesson;
    }
}
