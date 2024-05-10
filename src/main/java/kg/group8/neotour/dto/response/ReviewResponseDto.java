package kg.group8.neotour.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewResponseDto {
    String reviewersImagePath;
    String reviewer;
    String comment;
}
