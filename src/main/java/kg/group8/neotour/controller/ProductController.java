package kg.group8.neotour.controller;

import kg.group8.neotour.DTO.ProductDetailDTO;
import kg.group8.neotour.DTO.ProductPreviewDTO;
import kg.group8.neotour.DTO.ProductRequestDTO;
import kg.group8.neotour.entity.Product;
import kg.group8.neotour.service.ProductService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {

    ProductService productService;

    @GetMapping("/popular")
    public List<ProductPreviewDTO> getPopularProducts() {
        return productService.getPopularProducts();
    }

    @GetMapping("/mostVisited")
    public List<ProductPreviewDTO> getMostVisitedProducts() {
        return productService.getMostVisitedProducts();
    }

    @GetMapping("/featured")
    public List<ProductPreviewDTO> getFeaturedProducts() {
        return productService.getFeaturedProducts();
    }

    @GetMapping("/europe")
    public List<ProductPreviewDTO> getEuropeanProducts() {
        return productService.getEuropeanProducts();
    }

    @GetMapping("/asia")
    public List<ProductPreviewDTO> getAsianProducts() {
        return productService.getAsianProducts();
    }

    @GetMapping("/recommended")
    public List<ProductPreviewDTO> getRecommendedProducts() {
        return productService.getRecommendedProducts();
    }

    @GetMapping("{id}")
    public ProductDetailDTO findProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    /**
     * Below endpoints for the developer only
     **/


    @PostMapping("/addProductList")
    public ResponseEntity<?> addProduct(@Validated @RequestBody List<ProductRequestDTO> products) {
        return ResponseEntity.ok(productService.addProductList(products));
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequestDTO product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productService.updateProduct(productRequestDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.deleteProductById(id));
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllProducts() {
        return ResponseEntity.ok(productService.deleteAllProducts());
    }
}
