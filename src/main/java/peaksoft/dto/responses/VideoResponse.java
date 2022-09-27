package peaksoft.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VideoResponse {

    private Long id;
    private String videoName;
    private String link;
    private LocalDate createdAt;

}
