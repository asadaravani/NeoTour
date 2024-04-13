package kg.group8.neotour.DTO;
import kg.group8.neotour.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Long id;
    private String name;
    private String location;
    private String continent;
    private String description;
    private String imagePath;
    private List<Review> reviews;
    private BigDecimal OrderCount;
    private double rating;

}
