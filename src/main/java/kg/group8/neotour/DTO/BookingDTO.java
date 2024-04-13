package kg.group8.neotour.DTO;

import kg.group8.neotour.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long id;
    private Product product;
    private LocalDateTime localDateTime;
    private int amountOfPeople;
    private String comment;
    private String phoneNumber;
}
