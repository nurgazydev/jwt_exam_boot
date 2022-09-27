package peaksoft.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "videos")
@Getter
@Setter
public class Video {

    @Id
    @GeneratedValue(generator = "video_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "video_seq", sequenceName = "video_seq", allocationSize = 1)
    private Long id;

    @Column(name = "video_name")
    private String videoName;

    @Column(name = "link")
    private String link;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @OneToOne(cascade = {PERSIST,REFRESH,MERGE,DETACH})
    private Lesson lesson;


    public Video() {
    }

    public Video(String videoName, String link, LocalDate createdAt) {
        this.videoName = videoName;
        this.link = link;
        this.createdAt = createdAt;
    }
}
