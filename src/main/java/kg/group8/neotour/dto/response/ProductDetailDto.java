package kg.group8.neotour.dto.response;

import kg.group8.neotour.dto.ReviewDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailDto {
    Long id;
    String name;
    String location;
    String description;
    String imagePath;
    List<ReviewDto> reviews;
}
