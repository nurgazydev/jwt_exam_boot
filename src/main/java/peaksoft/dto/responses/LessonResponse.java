package peaksoft.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LessonResponse {

    private Long id;
    private String courseName;
    private LocalDate createdAt;
    private boolean isActive;
}
