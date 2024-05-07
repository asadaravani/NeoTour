package kg.group8.neotour.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDTO {


    Long id;

    @NotEmpty(message = "name must not be null or empty")
    String name;

    @NotEmpty
    String location;

    @NotEmpty
    String continent;

    private String description;
    private String imagePath;
    private String season;


}
