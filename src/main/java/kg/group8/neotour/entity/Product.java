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
    private double rating;


    @OneToMany
    private List<Review> reviews;


    @Column
    private String season;

    @Column
    private BigDecimal orderCount;


}
