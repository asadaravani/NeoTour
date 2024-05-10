package kg.group8.neotour.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewRequestDto {
    Long id;

    @NotEmpty
    String reviewersImagePath;

    @NotEmpty
    String reviewer;

    @NotEmpty
    Long productId;

    @NotEmpty
    Double rating;

    @NotEmpty
    String comment;
}
