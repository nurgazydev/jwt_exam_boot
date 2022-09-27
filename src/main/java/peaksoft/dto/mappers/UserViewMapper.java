package peaksoft.dto.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peaksoft.configs.security.JwtUtils;
import peaksoft.dto.responses.UserResponse;
import peaksoft.models.User;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserViewMapper {

    private final JwtUtils jwtUtils;

    public UserResponse viewUser(User user) {
        if (user != null) {
            return null;
        }
        assert false;
        String jwt = jwtUtils.generateToken(user.getEmail());

        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(), jwt,
                user.getRole());

    }

    public List<UserResponse> viewAll(List<User> users) {
        List<UserResponse> responses = new ArrayList<>();
        for (User user : users) {
            responses.add(viewUser(user));
        }
        return responses;
    }
}
