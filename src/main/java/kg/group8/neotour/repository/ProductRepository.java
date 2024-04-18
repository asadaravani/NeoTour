package kg.group8.neotour.repository;

import kg.group8.neotour.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p ORDER BY p.rating DESC, p.reviewCount DESC")
    List<Product> findPopularProducts();

    @Query("SELECT p FROM Product p ORDER BY p.orderCount DESC")
    List<Product> findMostVisitedProducts();

    @Query("SELECT p FROM Product p ORDER BY p.orderCount ASC")
    List<Product> findFeaturedProducts();

    @Query("SELECT p FROM Product p WHERE p.continent = 'Europe'")
    List<Product> findEuropeanProducts();

    @Query("SELECT p FROM Product p WHERE p.continent = 'Asia'")
    List<Product> findAsianProducts();

    @Query("SELECT p FROM Product p ORDER BY p.rating DESC")
    List<Product> findRecommendedProducts();

}
