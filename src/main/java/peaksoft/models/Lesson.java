package peaksoft.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.*;

//Lesson(id, lessonName)
@Entity
@Table(name = "lessons")
@Getter
@Setter
public class Lesson {

    @Id
    @GeneratedValue(generator = "lesson_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "lesson_seq", sequenceName = "lesson_seq", allocationSize = 1)
    private Long id;

    @Column(name = "lesson_name")
    private String lessonName;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne(cascade = {PERSIST, MERGE,REFRESH,DETACH})
    private Course course;
    @OneToMany(mappedBy = "lesson", cascade = ALL)
    private List<Task> tasks;

    @OneToOne(cascade = ALL, mappedBy = "lesson")
    private Video video;

    public Lesson() {
    }

    public Lesson(String lessonName, LocalDate createdAt) {
        this.lessonName = lessonName;
        this.createdAt = createdAt;
    }

    public void setTask(Task task) {
        this.tasks.add(task);
    }

}
