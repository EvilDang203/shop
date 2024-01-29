package sf.travel.rests.types;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateCustomerReq {
    @NotNull
    private String name;
    @NotNull
    private String email;
    private String dob;
}
