package amazontesttaskhaniuk.userService;

import amazontesttaskhaniuk.entity.User;
import amazontesttaskhaniuk.exception.UserExistException;
import amazontesttaskhaniuk.jwtDto.JwtRequest;
import amazontesttaskhaniuk.repository.UserRepository;
import amazontesttaskhaniuk.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MyUserDetailsService myUserDetailsService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public void save(User user) throws UserExistException {
        Optional<User> user1 = userRepository.findByUsername(user.getUsername());
        if (user1.isEmpty()) {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
        } else throw new UserExistException("User already exist");
    }
    public String authenticateAndGenerateToken(JwtRequest jwtRequest) throws BadCredentialsException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
        );
        MyUserDetails userDetails = myUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        return jwtTokenUtil.generateToken(userDetails);
    }
}
