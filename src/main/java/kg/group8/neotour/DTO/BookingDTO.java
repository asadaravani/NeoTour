package kg.group8.neotour.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO {
    private Long productId;
    private String phoneNumber;
    private Integer amountOfPeople;
    private String comment;
}
