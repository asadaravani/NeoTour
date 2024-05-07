package kg.group8.neotour.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    String imagePath;

    @Column
    private String location;

    @Column
    private String continent;

    @Column
    private String description;

    @Column
    private Double rating;

    @OneToMany(mappedBy = "product")
    List<Review> reviews;


    @Column
    private String season;

    @Column
    private BigDecimal orderCount = BigDecimal.ZERO;

    @Column
    private BigDecimal reviewCount = BigDecimal.ZERO;

    public void updateRatingAndReviewCount() {//todo перенести в сервис
        if (this.reviews != null && !this.reviews.isEmpty()) {
            double totalRating = 0.0;
            for (Review review : this.reviews) {
                totalRating += review.getRating();
            }
            double averageRating = totalRating / this.reviews.size();
            setRating(averageRating);
            setReviewCount(BigDecimal.valueOf(this.reviews.size()));
        } else {
            setRating(2.5);
            setReviewCount(BigDecimal.ZERO);
        }

    }

}
