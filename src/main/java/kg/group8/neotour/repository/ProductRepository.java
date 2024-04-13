package kg.group8.neotour.repository;

import kg.group8.neotour.DTO.ProductDTO;
import kg.group8.neotour.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p ORDER BY p.rating DESC")
    List<Product> findPopularProducts();

    @Query("SELECT p FROM Product p ORDER BY p.rating DESC")
    public List<ProductDTO> getMostVisitedProducts();

    @Query("SELECT p FROM Product p ORDER BY p.rating DESC")
    public List<ProductDTO> getFeaturedProducts();

    @Query("SELECT p FROM Product p ORDER BY p.rating DESC")
    public List<ProductDTO> getEuropeanProducts();

    @Query("SELECT p FROM Product p ORDER BY p.rating DESC")
    public List<ProductDTO> getAsianProducts();

    @Query("SELECT p FROM Product p ORDER BY p.rating DESC")
    public List<ProductDTO> getRecommendedProducts();

}
