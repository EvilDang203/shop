package sf.travel.rests.types;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateCategoryReq {
    private String name;
    private String description;
    private Long userId;
}
