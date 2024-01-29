package sf.travel.rests.types;

import lombok.Data;
import sf.travel.enums.HotelType;

import javax.validation.constraints.NotNull;

@Data
public class CreateHotelReq {
    @NotNull
    private String name;
    @NotNull
    private HotelType type;
    private String description;
    @NotNull
    private int price;
}
