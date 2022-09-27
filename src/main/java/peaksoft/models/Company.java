package peaksoft.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "companies")
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(generator = "company_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "company_seq", sequenceName = "company_seq", allocationSize = 1)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "located_country")
    private String locatedCountry;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "is_active")
    private boolean isActive = true;

    @OneToMany(mappedBy = "company",cascade = {ALL})
    private List<Course> courses;

    @OneToMany(mappedBy = "company",cascade = ALL)
    private List<Instructor> instructors;

    public Company() {
    }

    public Company(String companyName, String locatedCountry, LocalDate createdAt, boolean isActive) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }


    public void setCourse(Course course) {
        this.courses.add(course);
    }

    public void setInstructor(Instructor instructor) {
        if (instructors == null) {
            instructors = new ArrayList<>();
        }
        instructors.add(instructor);
    }
}

