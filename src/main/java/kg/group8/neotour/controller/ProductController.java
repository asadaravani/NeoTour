package kg.group8.neotour.controller;

import kg.group8.neotour.dto.response.ProductDetailDto;
import kg.group8.neotour.dto.response.ProductPreviewDto;
import kg.group8.neotour.dto.request.ProductRequestDto;
import kg.group8.neotour.entity.Product;
import kg.group8.neotour.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/popular")
    public List<ProductPreviewDto> getPopularProducts() {
        return productService.getPopularProducts();
    }

    @GetMapping("/mostVisited")
    public List<ProductPreviewDto> getMostVisitedProducts() {
        return productService.getMostVisitedProducts();
    }

    @GetMapping("/featured")
    public List<ProductPreviewDto> getFeaturedProducts() {
        return productService.getFeaturedProducts();
    }

    @GetMapping("/europe")
    public List<ProductPreviewDto> getEuropeanProducts() {
        return productService.getEuropeanProducts();
    }

    @GetMapping("/asia")
    public List<ProductPreviewDto> getAsianProducts() {
        return productService.getAsianProducts();
    }

    @GetMapping("/recommended")
    public List<ProductPreviewDto> getRecommendedProducts() {
        return productService.getRecommendedProducts();
    }

    @GetMapping("/{id}")
    public ProductDetailDto findProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @PostMapping("/addProductList")
    public ResponseEntity<?> addProduct(@Validated @RequestBody List<ProductRequestDto> products) {
        return ResponseEntity.ok(productService.addProductList(products));
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequestDto product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductRequestDto productRequestDTO) {
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
