package peaksoft.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

//Course(id, courseName, dateOfStart, duration, image, description)
@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(generator = "course_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "course_seq", sequenceName = "course_seq", allocationSize = 1)
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "date_of_start")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfStart;

    @Column(name = "duration")
    private byte duration;

    @Column(name = "is_active")
    private boolean isActive;




//Бир курста бир канча инструкторлор, студенттер жана сабактар боло алат

    @ManyToOne(cascade = {PERSIST,REFRESH,MERGE})
    private Company company;

    @ManyToMany(mappedBy = "courses",cascade = {PERSIST,REFRESH,DETACH,MERGE})
    private List<Instructor> instructors = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = {ALL})
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = ALL)
    private List<Lesson> lessons = new ArrayList<>();

    public Course() {
    }

    public Course(String courseName, LocalDate dateOfStart, byte duration, boolean isActive) {
        this.courseName = courseName;
        this.dateOfStart = dateOfStart;
        this.duration = duration;
        this.isActive = isActive;
    }

    public void setInstructor(Instructor instructor) {
        this.instructors.add(instructor);
    }

    public void setLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }

    public void setStudent(Student student) {
        this.students.add(student);
    }
}
