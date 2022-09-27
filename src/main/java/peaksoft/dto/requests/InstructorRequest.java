package peaksoft.dto.requests;

import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Role;

@Getter
@Setter
public class InstructorRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private String specialization;
    private Long companyId;

}
