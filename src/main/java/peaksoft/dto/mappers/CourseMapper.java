package peaksoft.dto.mappers;

import org.springframework.stereotype.Component;
import peaksoft.dto.requests.CourseRequest;
import peaksoft.dto.responses.CourseResponse;
import peaksoft.models.Course;

import java.util.ArrayList;
import java.util.List;

@Component
public class  CourseMapper {

    public Course convertToEntity(CourseRequest courseRequest) {
        if (courseRequest == null) {
            return null;
        }
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(courseRequest.getDateOfStart());
        course.setDuration(courseRequest.getDuration());
        course.setActive(true);
        return course;
    }

    public CourseResponse convertToView(Course course) {
        if (course == null) {
            return null;
        }
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setDateOfStart(course.getDateOfStart());
        courseResponse.setDuration(course.getDuration());
        courseResponse.setActive(course.isActive());
        return courseResponse;
    }

    public List<CourseResponse> findAll(List<Course> courses) {
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (Course course : courses) {
            courseResponses.add(convertToView(course));
        }
        return courseResponses;
    }

    public void convertToUpdate(Course course, CourseRequest courseRequest) {
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(courseRequest.getDateOfStart());
        course.setDuration(courseRequest.getDuration());
        course.setActive(true);
    }



}
