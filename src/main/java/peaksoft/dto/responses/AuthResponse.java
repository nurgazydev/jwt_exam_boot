package peaksoft.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Role;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {

    private Long id;
    private String email;
    private String jwt;
    private Role role;
}
