package app.model.dto;

import app.model.enums.OrderState;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeStampDto {
    private OrderState state;
    private LocalDateTime time;
}
