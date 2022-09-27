package peaksoft.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseResponse {

    private Long id;
    private String courseName;
    private LocalDate dateOfStart;
    private byte duration;
    private boolean isActive;
}
