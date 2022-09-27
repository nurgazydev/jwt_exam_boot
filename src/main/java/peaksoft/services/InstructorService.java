package peaksoft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.mappers.InstructorMapper;
import peaksoft.dto.requests.InstructorAssignRequest;
import peaksoft.dto.requests.InstructorRequest;
import peaksoft.dto.responses.InstructorResponse;
import peaksoft.dto.responses.simpl.SimpleResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Company;
import peaksoft.models.Course;
import peaksoft.models.Instructor;
import peaksoft.models.User;
import peaksoft.repositories.CompanyRepository;
import peaksoft.repositories.CourseRepository;
import peaksoft.repositories.InstructorRepository;
import peaksoft.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final CompanyRepository companyRepository;


    public InstructorResponse save(InstructorRequest instructorRequest) {
        Instructor instructor = instructorMapper.convertToEntity(instructorRequest);
        Company company = companyRepository.findById(instructorRequest.getCompanyId())
                .orElseThrow(() -> new NotFoundException("company with id: " + instructorRequest.getCompanyId() + " does not exists!"));

        company.setInstructor(instructor);
        instructor.setCompany(company);
        instructor.setPassword(encoder.encode(instructorRequest.getPassword()));
        instructorRepository.save(instructor);
        User user = new User();
        user.setFirstName(instructor.getFirstName());
        user.setLastName(instructor.getLastName());
        user.setEmail(instructor.getEmail());
        user.setPassword(instructor.getPassword());
        user.setRole(instructor.getRole());
        userRepository.save(user);
        return instructorMapper.convertToView(instructor);
    }

    public List<InstructorResponse> getAll() {
        List<Instructor> allInstructors = instructorRepository.findAll();
        return instructorMapper.convertAllCoursesToView(allInstructors);
    }


    public InstructorResponse findById(Long id) {
        Instructor instructor = getById(id);
        return instructorMapper.convertToView(instructor);
    }

    private Instructor getById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("instructor with id: " + id + "does not exists"));
    }


    public SimpleResponse assignInstructorToCourse(InstructorAssignRequest assignRequest) {
        Instructor instructor = getById(assignRequest.getInstructorId());
        Course course = courseRepository.findById(assignRequest.getCourseId()).
                orElseThrow(() -> new RuntimeException("course with id: " + assignRequest.getCourseId() + "does not exists"));
        instructor.setCourse(course);
        course.setInstructor(instructor);
        instructorRepository.save(instructor);
        return new SimpleResponse(
                "ASSIGNED",
                "instructor assigned to course successfully"
        );
    }

    public SimpleResponse removeById(Long id) {
        boolean exists = instructorRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException("instructor with id: " + id + "does not exists");
        }
        Instructor instructor = instructorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findByEmail(instructor.getEmail()).orElseThrow(() ->
                new UsernameNotFoundException(
                        "user with email: " + instructor.getEmail() + " not found!"
                ));

        userRepository.delete(user);
        instructorRepository.deleteById(id);
        return new SimpleResponse(
                "DELETED",
                "instructor with id: " + id + "deleted successfully"
        );

    }

    @Transactional
    public InstructorResponse update(Long id, InstructorRequest instructorRequest) {
        Instructor instructor = getById(id);
        instructorMapper.convertToUpdate(instructorRequest, instructor);
        instructorRepository.save(instructor);
        return instructorMapper.convertToView(instructor);
    }
}
