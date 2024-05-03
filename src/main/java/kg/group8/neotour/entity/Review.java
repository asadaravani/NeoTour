package kg.group8.neotour.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String reviewersImagePath;

    @Column
    private String reviewer;

    @Column
    private Double rating;

    @Column
    private String comment;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

}
