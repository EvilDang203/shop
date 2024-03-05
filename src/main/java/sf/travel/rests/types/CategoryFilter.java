package sf.travel.rests.types;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryFilter  extends PaginationParams{
    private String name;
}
