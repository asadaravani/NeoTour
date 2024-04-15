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
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String phoneNumber;

    @ManyToOne
    @JoinColumn
    private Product product;

    @Column
    private int amountOfPeople;

    @Column
    private String comment;

}
