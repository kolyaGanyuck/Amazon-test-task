package amazontesttaskhaniuk.controller;

import amazontesttaskhaniuk.entity.User;
import amazontesttaskhaniuk.exception.UserExistException;
import amazontesttaskhaniuk.jwtDto.JwtRequest;
import amazontesttaskhaniuk.jwtDto.JwtResponse;
import amazontesttaskhaniuk.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/auth")
    public String auth(Principal principal) {
        return principal.getName();
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user) throws UserExistException {
        userService.save(user);
        return "User saved";
    }

    @PostMapping("/login")
    public ResponseEntity<?> token(@RequestBody JwtRequest jwtRequest) {
        try {
            String token = userService.authenticateAndGenerateToken(jwtRequest);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
