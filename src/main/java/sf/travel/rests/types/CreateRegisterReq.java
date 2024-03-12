package sf.travel.rests.types;

import lombok.Data;
import sf.travel.enums.UserType;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data

public class CreateRegisterReq {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private UserType type;
}
