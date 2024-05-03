package kg.group8.neotour.DTO;

import kg.group8.neotour.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    private String imagePath;
    private List<ReviewDTO> reviews;
}
