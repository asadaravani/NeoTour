package kg.group8.neotour.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingDto {

    @NotEmpty
    Long productId;

    @NotEmpty
    String phoneNumber;

    @NotEmpty
    Integer amountOfPeople;

    String comment;
}
