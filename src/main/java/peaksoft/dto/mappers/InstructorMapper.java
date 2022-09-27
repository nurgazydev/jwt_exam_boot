package peaksoft.dto.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import peaksoft.dto.requests.CourseRequest;
import peaksoft.dto.requests.InstructorRequest;
import peaksoft.dto.responses.InstructorResponse;
import peaksoft.enums.Role;
import peaksoft.models.Course;
import peaksoft.models.Instructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InstructorMapper {

    public Instructor convertToEntity(InstructorRequest instructorRequest) {
        if (instructorRequest == null) {
            return null;
        }
        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setEmail(instructorRequest.getEmail());
        instructor.setPassword(instructorRequest.getPassword());
        instructor.setRole(Role.INSTRUCTOR);
        instructor.setSpecialization(instructorRequest.getSpecialization());
        instructor.setCreatedAt(LocalDate.now());
        instructor.setActive(true);
        return instructor;
    }

    public InstructorResponse convertToView(Instructor instructor) {
        if (instructor == null) {
            return null;
        }
        InstructorResponse instructorResponse = new InstructorResponse();
        instructorResponse.setId(instructor.getId());
        instructorResponse.setFirstName(instructor.getFirstName());
        instructorResponse.setLastName(instructor.getLastName());
        instructorResponse.setPhoneNumber(instructor.getPhoneNumber());
        instructorResponse.setEmail(instructor.getEmail());
        instructorResponse.setSpecialization(instructor.getSpecialization());
        instructorResponse.setCreatedAt(LocalDate.now());
        instructorResponse.setActive(instructor.isActive());
        return instructorResponse;
    }

    public List<InstructorResponse> convertAllCoursesToView(List<Instructor> instructors) {
        List<InstructorResponse> instructorResponses = new ArrayList<>();
        for (Instructor instructor : instructors) {
            instructorResponses.add(convertToView(instructor));
        }
        return instructorResponses;
    }

    public Instructor convertToUpdate(InstructorRequest instructorRequest, Instructor instructor) {
       instructor.setFirstName(instructorRequest.getFirstName());
       instructor.setLastName(instructorRequest.getLastName());
       instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
       instructor.setEmail(instructorRequest.getPhoneNumber());
       instructor.setSpecialization(instructorRequest.getSpecialization());
       instructor.setCreatedAt(LocalDate.now());
       instructor.setActive(instructor.isActive());
       return instructor;
    }
}
