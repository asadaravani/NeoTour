package kg.group8.neotour.dto.response;

import kg.group8.neotour.dto.request.ReviewRequestDto;
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
    List<ReviewResponseDto> reviews;
}
