package sf.travel.rests.types;

import lombok.Data;
import sf.travel.enums.Status;

import javax.validation.constraints.NotNull;

@Data
public class CreateOrderReq {
    @NotNull
    private Long travelId;
    @NotNull
    private Long customerId;
    @NotNull
    private String name;

    private String description;
    @NotNull
    private int price ;
    @NotNull
    private Status status;
}
