package kg.group8.neotour.controller;

import kg.group8.neotour.DTO.ProductDTO;
import kg.group8.neotour.entity.Product;
import kg.group8.neotour.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("api/products")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;
    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("popular")
    public List<ProductDTO> getPopularProducts(){
        return productService.getPopularProducts();
    }
    @GetMapping("mostVisited")
    public List<ProductDTO> getMostVisitedProducts(){
        return productService.getMostVisitedProducts();
    }
    @GetMapping("featured")
    public List<ProductDTO> getFeaturedProducts(){
        return productService.getFeaturedProducts();
    }
    @GetMapping("europe")
    public List<ProductDTO> getEuropeanProducts(){
        return productService.getEuropeanProducts();
    }
    @GetMapping("asia")
    public List<ProductDTO> getAsianProducts(){
        return productService.getAsianProducts();
    }
    @GetMapping("recommended")
    public List<ProductDTO> getRecommendedProducts(){
        return productService.getRecommendedProducts();
    }

    @GetMapping("{id}")
    public Optional<Product> findProductById(@PathVariable Long id){
        return productService.findProductById(id);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO product){
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.updateProduct(productDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id){
        return ResponseEntity.ok().body(productService.deleteProductById(id));
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllProducts(){
        return ResponseEntity.ok(productService.deleteAllProducts());
    }
}
