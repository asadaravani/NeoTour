package kg.group8.neotour.entity;

import jakarta.persistence.*;
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
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String imagePath;

    @Column
    private String location;

    @Column
    private String continent;

    @Column
    private String description;

    @Column
    private Double rating;


    @OneToMany
    private List<Review> reviews;


    @Column
    private String season;

    @Column
    private BigDecimal orderCount = BigDecimal.ZERO;

    @Column
    private BigDecimal reviewCount = BigDecimal.ZERO;

    public void updateRatingAndReviewCount() {
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
