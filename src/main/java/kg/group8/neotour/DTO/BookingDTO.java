package kg.group8.neotour.DTO;

import kg.group8.neotour.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO {
    private Long id;
    private Long productId;
    private Product product;
    private String phoneNumber;
    private int amountOfPeople;
    private String comment;
}
