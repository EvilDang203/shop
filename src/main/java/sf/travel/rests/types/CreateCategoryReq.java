package sf.travel.rests.types;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateCategoryReq {
    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Long userId;
}
