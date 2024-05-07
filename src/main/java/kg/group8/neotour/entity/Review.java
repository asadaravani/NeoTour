package kg.group8.neotour.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String reviewersImagePath;

    @Column(nullable = false)
    String reviewer;

    @Column(nullable = false)
    Double rating;

    @Column(nullable = false)
    String comment;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

}
