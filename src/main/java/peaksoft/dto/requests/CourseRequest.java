package peaksoft.dto.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseRequest {

    private String courseName;
    private LocalDate dateOfStart;
    private byte duration;
    private Long companyId;

}
