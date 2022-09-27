package peaksoft.dto.responses;

import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.StudyFormat;

import java.time.LocalDate;

@Getter
@Setter
public class StudentResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private StudyFormat studyFormat;
    private LocalDate createdAt;
    private boolean isActive;
}
