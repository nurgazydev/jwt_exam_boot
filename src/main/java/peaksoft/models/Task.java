package peaksoft.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

import static javax.persistence.CascadeType.*;

//Task(id, taskName, taskText, deadline)
@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(generator = "task_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "task_seq", sequenceName = "task_seq", allocationSize = 1)
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_text")
    private String taskText;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "created_at")
    private LocalDate createdAt;


    @ManyToOne(cascade = {DETACH, REFRESH,MERGE,DETACH})
    private Lesson lesson;

    public Task() {
    }

    public Task(String taskName, String taskText, LocalDate deadline, LocalDate createdAt) {
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadline = deadline;
        this.createdAt = createdAt;
    }
}
