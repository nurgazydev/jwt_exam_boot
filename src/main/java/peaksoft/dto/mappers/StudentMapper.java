package peaksoft.dto.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import peaksoft.dto.requests.LessonRequest;
import peaksoft.dto.requests.StudentRequest;
import peaksoft.dto.responses.LessonResponse;
import peaksoft.dto.responses.StudentResponse;
import peaksoft.enums.Role;
import peaksoft.models.Lesson;
import peaksoft.models.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    public Student convertToEntity(StudentRequest studentRequest) {
        if (studentRequest == null) {
            return null;
        }
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setEmail(studentRequest.getEmail());
        student.setRole(Role.STUDENT);
        student.setPassword(studentRequest.getPassword());
        student.setStudyFormats(studentRequest.getStudyFormat());
        student.setCreatedAt(LocalDate.now());
        student.setActive(true);
        return student;
    }

    public StudentResponse convertToView(Student student) {
        if (student == null) {
            return null;
        }
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setPhoneNumber(student.getPhoneNumber());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setStudyFormat(student.getStudyFormats());
        studentResponse.setCreatedAt(LocalDate.now());
        studentResponse.setActive(student.isActive());
        return studentResponse;
    }

    public List<StudentResponse> convertAllStudentsToView(List<Student> students) {
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student student : students) {
            studentResponses.add(convertToView(student));
        }
        return studentResponses;
    }

    public Student update(StudentRequest studentRequest, Student student) {
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormats(studentRequest.getStudyFormat());
        student.setCreatedAt(LocalDate.now());
        student.setActive(student.isActive());
        return student;
    }
}
