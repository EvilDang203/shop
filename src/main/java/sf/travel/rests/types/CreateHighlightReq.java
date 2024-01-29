package sf.travel.rests.types;

import lombok.Data;
import sf.travel.enums.HighlightStatus;
import sf.travel.enums.Rank;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CreateHighlightReq {
    @NotNull
    private Rank rank;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    @NotNull
    private HighlightStatus status;
    @NotNull
    private Long travelId;
}
