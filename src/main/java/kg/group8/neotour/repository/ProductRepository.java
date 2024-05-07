package kg.group8.neotour.repository;

import kg.group8.neotour.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByOrderByRatingDescReviewCountDesc();

    List<Product> findAllByOrderByOrderCountDesc();

    List<Product> findAllByOrderByOrderCountAsc();

    List<Product> findAllByContinent(String continent);

    List<Product> findAllBySeason(String season);

}
