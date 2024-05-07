package kg.group8.neotour.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {

    Long id;

    @NotEmpty(message = "name must not be null or empty")
    String name;

    @NotEmpty
    String location;

    @NotEmpty
    String continent;

    @NotEmpty
    String description;

    @NotEmpty
    String imagePath;

    @NotEmpty
    String season;


}
