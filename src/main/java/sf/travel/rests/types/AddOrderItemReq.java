package sf.travel.rests.types;

import lombok.Data;

@Data
public class AddOrderItemReq {
    private Long productId;
    private int quantity ;
}
