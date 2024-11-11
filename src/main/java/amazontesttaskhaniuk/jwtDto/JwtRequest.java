package amazontesttaskhaniuk.jwtDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class JwtRequest {
    String username;
    String password;
}
