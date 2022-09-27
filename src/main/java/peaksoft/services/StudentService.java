package peaksoft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.mappers.StudentMapper;
import peaksoft.dto.requests.StudentRequest;
import peaksoft.dto.responses.StudentResponse;
import peaksoft.dto.responses.StudentResponseView;
import peaksoft.dto.responses.simpl.SimpleResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Course;
import peaksoft.models.Instructor;
import peaksoft.models.Student;
import peaksoft.models.User;
import peaksoft.repositories.CourseRepository;
import peaksoft.repositories.StudentRepository;
import peaksoft.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final PasswordEncoder encoder;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;
    private final UserRepository userRepository;

    public List<StudentResponse> getAll() {
        List<Student> students = studentRepository.findAll();
        return studentMapper.convertAllStudentsToView(students);
    }


    public StudentResponse findById(Long id) {
        Student student = getById(id);
        return studentMapper.convertToView(student);
    }

    private Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("student with id: " + id + " not found"));
    }

    public StudentResponse save(StudentRequest studentRequest) {
        Student student = studentMapper.convertToEntity(studentRequest);
        Course course = courseRepository.findById(studentRequest.getCourseId())
                .orElseThrow(() -> new NotFoundException("course with id: " + studentRequest.getCourseId() + " does not exists"));
        student.setCourse(course);
        course.setStudent(student);
        student.setPassword(encoder.encode(studentRequest.getPassword()));
        studentRepository.save(student);
        User user = new User();
        user.setFirstName(student.getFirstName());
        user.setLastName(student.getLastName());
        user.setEmail(student.getEmail());
        user.setPassword(student.getPassword());
        user.setRole(student.getRole());
        userRepository.save(user);
        return studentMapper.convertToView(student);
    }

    public SimpleResponse remove(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException("student with id: " + id + " does not exists");
        }
        Student student = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findByEmail(student.getEmail()).orElseThrow(() ->
                new UsernameNotFoundException(
                        "user with email: " + student.getEmail() + " not found!"
                ));

        userRepository.delete(user);
        studentRepository.deleteById(id);
        return new SimpleResponse(
                "DELETED",
                "student with id: " + id + "deleted successfully!"
        );
    }

    public StudentResponse update(Long id, StudentRequest studentRequest) {
        Student student = getById(id);
        Student update = studentMapper.update(studentRequest, student);
        studentRepository.save(update);
        return studentMapper.convertToView(update);
    }

    public StudentResponseView getAllStudentsPagination(String text, int page, int size) {
        StudentResponseView responseView = new StudentResponseView();
        Pageable pageable = PageRequest.of(page - 1, size);
        responseView.setStudentResponses(studentMapper.convertAllStudentsToView(search(text, pageable)));
        return responseView;
    }


    private List<Student> search(String name, Pageable pageable) {
        String text = name == null ? " " : name;
        return studentRepository.searchByFirstName(text.toUpperCase(), pageable);
    }
}
