package sf.travel.rests.types;

import lombok.Data;
import sf.travel.enums.UserType;

@Data

public class CreateRegisterReq {
    private String username;
    private String password;
    private String name;
    private String email;
    private UserType type;
}
