package peaksoft.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import peaksoft.enums.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static javax.persistence.CascadeType.*;

//Instructor(id, firstName, lastName, phoneNumber, email, specialization)
@Entity
@Table(name = "instructors")
@Getter
@Setter
public class Instructor implements UserDetails {

    @Id
    @GeneratedValue(generator = "instructor_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "instructor_seq", sequenceName = "instructor_seq", allocationSize = 1)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(unique = true)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToMany(cascade = {PERSIST,MERGE,REFRESH})
    private List<Course> courses = new ArrayList<>();

    @ManyToOne(cascade = {PERSIST,REFRESH,MERGE})
    private Company company;


    public Instructor() {
    }

    public Instructor(String firstName, String lastName, String phoneNumber,
                      String email, String specialization, LocalDate createdAt, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.specialization = specialization;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }

    public void setCourse(Course course) {
        this.courses.add(course);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
