package sf.travel.rests.types;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateProductReq {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String image;
    @NotNull
    private int price;
    @NotNull
    private Long categoryId;
    @NotNull
    private Long userId;
}
