package sf.travel.rests.types;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateNewReq {
    @NotNull
    private String name;
    private String description;
}
