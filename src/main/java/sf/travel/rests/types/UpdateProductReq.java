package sf.travel.rests.types;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateProductReq {
    private String name;
    private int price = -1;
    private String description;
    private String image;

}
