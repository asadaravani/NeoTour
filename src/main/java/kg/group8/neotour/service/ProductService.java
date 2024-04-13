package kg.group8.neotour.service;

import kg.group8.neotour.DTO.ProductDTO;
import kg.group8.neotour.entity.Product;
import kg.group8.neotour.exception.EmptyFieldException;
import kg.group8.neotour.exception.ProductNotFoundException;
import kg.group8.neotour.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::mapProductToDTO)
                .collect(Collectors.toList());

    }

    public List<ProductDTO> getPopularProducts(){
        List<Product> popularProducts = productRepository.findPopularProducts();
        return popularProducts.stream()
                .map(this::mapProductToDTO)
                .collect(Collectors.toList());
    }
    public List<ProductDTO> getMostVisitedProducts(){
        ProductDTO p1 = new ProductDTO();
        ProductDTO p2 = new ProductDTO();
        List<ProductDTO> ans = new ArrayList<>(2);
        ans.add(p1);
        ans.add(p2);
        return ans;
    }
    public List<ProductDTO> getFeaturedProducts(){
        ProductDTO p1 = new ProductDTO();
        ProductDTO p2 = new ProductDTO();
        List<ProductDTO> ans = new ArrayList<>(2);
        ans.add(p1);
        ans.add(p2);
        return ans;
    }
    public List<ProductDTO> getEuropeanProducts(){
        ProductDTO p1 = new ProductDTO();
        ProductDTO p2 = new ProductDTO();
        List<ProductDTO> ans = new ArrayList<>(2);
        ans.add(p1);
        ans.add(p2);
        return ans;
    }
    public List<ProductDTO> getAsianProducts(){
        ProductDTO p1 = new ProductDTO();
        ProductDTO p2 = new ProductDTO();
        List<ProductDTO> ans = new ArrayList<>(2);
        ans.add(p1);
        ans.add(p2);
        return ans;
    }
    public List<ProductDTO> getRecommendedProducts(){
        ProductDTO p1 = new ProductDTO();
        ProductDTO p2 = new ProductDTO();
        List<ProductDTO> ans = new ArrayList<>(2);
        ans.add(p1);
        ans.add(p2);
        return ans;
    }


    public Optional<Product> findProductById(Long id){
        return this.productRepository.findById(id);
    }
    public Product addProduct(ProductDTO productDTO) throws EmptyFieldException {
        if (isEmptyOrNull(productDTO.getName()) ||
                isEmptyOrNull(productDTO.getLocation()) ||
                isEmptyOrNull(productDTO.getContinent()) ||
                isEmptyOrNull(productDTO.getDescription()) ||
                isEmptyOrNull(productDTO.getImagePath())) {
            throw new EmptyFieldException("All fields must be filled");
        }
        else{
            Product product = new Product();
            product.setName(productDTO.getName());
            product.setLocation(productDTO.getLocation());
            product.setContinent(productDTO.getContinent());
            product.setDescription(productDTO.getDescription());
            product.setImagePath(productDTO.getImagePath());
            this.productRepository.save(product);
            return product;
        }
    }
    public Product updateProduct(ProductDTO productDTO) {
        Optional<Product> product = productRepository.findById(productDTO.getId());
        if(product.isPresent()){
            Product updateProduct = product.get();
            updateProduct.setName(productDTO.getName());
            updateProduct.setDescription(productDTO.getDescription());
            updateProduct.setLocation(productDTO.getLocation());
            updateProduct.setContinent(productDTO.getContinent());
            this.productRepository.save(updateProduct);
            return updateProduct;
        }
        else{
            throw new ProductNotFoundException("Product not found!");
        }
    }
    public String deleteProductById(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            this.productRepository.deleteById(id);
            return "Product deleted with id: " + id;
        }
        else{
            throw new ProductNotFoundException("Product not found!");
        }
    }
    public String deleteAllProducts()throws ProductNotFoundException{
        if(this.productRepository.findAll().isEmpty()){
            throw  new ProductNotFoundException("DB is already clear!");
        }
        else {
            this.productRepository.deleteAll();
            return "All are deleted!!!";
        }
    }
    private boolean isEmptyOrNull(String str) {
        return str == null || str.trim().isEmpty();
    }
    private ProductDTO mapProductToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setLocation(product.getLocation());
        productDTO.setContinent(product.getContinent());
        productDTO.setDescription(product.getDescription());
        productDTO.setImagePath(product.getImagePath());
        productDTO.setRating(product.getRating());
        productDTO.setOrderCount(product.getOrderCount());
        productDTO.setReviews(product.getReviews());
        return productDTO;
    }
}
