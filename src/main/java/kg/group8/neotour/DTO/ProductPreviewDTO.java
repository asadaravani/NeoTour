package kg.group8.neotour.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPreviewDTO {
    private Long id;
    private String name;
    private String imagePath;
}
