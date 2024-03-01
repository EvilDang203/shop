package sf.travel.rests.types;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductFilter extends PaginationParams{
    private String name;
    @Min(value = 0, message = "PriceMin must be greater than or equal to 0")
    private int priceMin = -1;
    @Min(value = 0, message = "PriceMax must be greater than or equal to 0")
    private int priceMax = -1;
}
