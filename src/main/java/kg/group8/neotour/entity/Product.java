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

    @Column(nullable = false)
    String name;

    @Column
    String imagePath;

    @Column(nullable = false)
    String location;

    @Column(nullable = false)
    String continent;

    @Column(nullable = false)
    String description;

    @Column
    Double rating;

    @OneToMany(mappedBy = "product")
    List<Review> reviews;

    @Column(nullable = false)
    String season;

    @Column
    BigDecimal orderCount = BigDecimal.ZERO;

    @Column
    BigDecimal reviewCount = BigDecimal.ZERO;


}
