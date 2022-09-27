package peaksoft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.AuthRequest;
import peaksoft.configs.security.JwtUtils;
import peaksoft.dto.responses.AuthResponse;
import peaksoft.models.User;
import peaksoft.repositories.UserRepository;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    public AuthResponse authenticate(AuthRequest authRequest) {

        User user = userRepository.findByEmail(authRequest.email()).orElseThrow(() -> new IllegalStateException("not found"));

        if (!passwordEncoder.matches(authRequest.password(), user.getPassword())) {
            throw new BadCredentialsException("BadCredentials");
        }

        String token = jwtUtils.generateToken(user.getEmail());

        return new AuthResponse(
            user.getId(),
                user.getEmail(),
                token,
                user.getRole()
        );
    }
}
