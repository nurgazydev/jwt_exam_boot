package peaksoft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.mappers.CourseMapper;
import peaksoft.dto.requests.CourseRequest;
import peaksoft.dto.responses.CourseResponse;
import peaksoft.dto.responses.simpl.SimpleResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Company;
import peaksoft.models.Course;
import peaksoft.repositories.CompanyRepository;
import peaksoft.repositories.CourseRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
    private final CourseMapper courseMapper;


    public CourseResponse save(CourseRequest courseRequest) {
        Course course = courseMapper.convertToEntity(courseRequest);
        Company company = companyRepository.findById(courseRequest.getCompanyId()).orElseThrow(() ->
                new NotFoundException("Company with id: " + courseRequest.getCompanyId() + " not found !"));
        course.setCompany(company);
        company.setCourse(course);
        course.setCompany(company);
        company.setCourse(course);
        Course courseSave = courseRepository.save(course);
        return courseMapper.convertToView(courseSave);
    }

//    public List<Course> findAllCoursesByCompanyId(Long id) {
//        return courseRepository.findAllCoursesByCompanyId(id);
//    }

    public CourseResponse findById(Long id) {
        Course course = getById(id);
        return courseMapper.convertToView(course);
    }

    private Course getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("course with id: " + id + " not found !"));
    }

    public List<CourseResponse> findAll() {
        List<Course> allCourses = courseRepository.findAll();
        return  courseMapper.findAll(allCourses);
    }

    public SimpleResponse removeById(Long id) {
        boolean exists = courseRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException("course with id: "+id+" does not exists!");
        }

        courseRepository.deleteById(id);
        return new SimpleResponse(
                "DELETED",
                "Deleted successfully"
        );
    }



    @Transactional
    public CourseResponse update(Long id, CourseRequest courseRequest) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("course with id: " + id + "does not exists"));
        courseMapper.convertToUpdate(course, courseRequest);
        Course save = courseRepository.save(course);
        return courseMapper.convertToView(save);
    }
}
