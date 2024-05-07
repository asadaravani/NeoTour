package kg.group8.neotour.service;

import kg.group8.neotour.DTO.ProductDetailDTO;
import kg.group8.neotour.DTO.ProductPreviewDTO;
import kg.group8.neotour.DTO.ProductRequestDTO;
import kg.group8.neotour.entity.Product;
import kg.group8.neotour.exception.EmptyFieldException;
import kg.group8.neotour.exception.ProductNotFoundException;
import kg.group8.neotour.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public List<ProductPreviewDTO> getPopularProducts() {
        List<Product> popularProducts = productRepository.findAllByOrderByRatingDesc();
        return popularProducts.stream()
                .map(this::mapProductToDTO)
                .toList();
    }

    public List<ProductPreviewDTO> getMostVisitedProducts() {
        List<Product> mostVisitedProducts = productRepository.findMostVisitedProducts();
        return mostVisitedProducts.stream()
                .map(this::mapProductToDTO)
                .toList();
    }

    public List<ProductPreviewDTO> getFeaturedProducts() {
        List<Product> featuredProducts = productRepository.findFeaturedProducts();

        return featuredProducts.stream()
                .map(this::mapProductToDTO)
                .toList();
    }

    public List<ProductPreviewDTO> getEuropeanProducts() {
        List<Product> europeanProducts = productRepository.findEuropeanProducts();
        return europeanProducts.stream()
                .map(this::mapProductToDTO)
                .toList();
    }

    public List<ProductPreviewDTO> getAsianProducts() {
        List<Product> asianProducts = productRepository.findAsianProducts();
        return asianProducts.stream()
                .map(this::mapProductToDTO)
                .toList();
    }

    public List<ProductPreviewDTO> getRecommendedProducts() {
        List<Product> recommendedProducts = productRepository.findRecommendedProducts();
        return recommendedProducts.stream()
                .map(this::mapProductToDTO)
                .toList();
    }

    public ProductDetailDTO findProductById(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        Product product = productOptional.orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setId(product.getId());
        productDetailDTO.setImagePath(product.getImagePath());
        productDetailDTO.setName(product.getName());
        productDetailDTO.setLocation(product.getLocation());
        productDetailDTO.setDescription(product.getDescription());
        productDetailDTO.setReviews(product.getReviews());

        return productDetailDTO;
    }


    /**
     * Below endpoints for the developer only
     **/

    public String addProductList(List<ProductRequestDTO> products) {
        for (ProductRequestDTO productRequestDTO : products) {
            addProduct(productRequestDTO);
        }
        return "Successfully added products";
    }

    public Product addProduct(ProductRequestDTO productRequestDTO) throws EmptyFieldException {
        if (isEmptyOrNull(productRequestDTO.getName()) ||
                isEmptyOrNull(productRequestDTO.getLocation()) ||
                isEmptyOrNull(productRequestDTO.getContinent()) ||
                isEmptyOrNull(productRequestDTO.getDescription()) ||
                isEmptyOrNull(productRequestDTO.getImagePath())) {
            throw new EmptyFieldException("All fields must be filled");
        }

        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setLocation(productRequestDTO.getLocation());
        product.setContinent(productRequestDTO.getContinent());
        product.setDescription(productRequestDTO.getDescription());
        product.setImagePath(productRequestDTO.getImagePath());
        product.setSeason(productRequestDTO.getSeason());

        this.productRepository.save(product);

        return product;

    }

    public Product updateProduct(ProductRequestDTO productRequestDTO) {
        Optional<Product> product = productRepository.findById(productRequestDTO.getId());
        if (product.isPresent()) {
            Product updateProduct = product.get();
            updateProduct.setName(productRequestDTO.getName());
            updateProduct.setDescription(productRequestDTO.getDescription());
            updateProduct.setLocation(productRequestDTO.getLocation());
            updateProduct.setContinent(productRequestDTO.getContinent());
            updateProduct.setSeason(productRequestDTO.getSeason());
            updateProduct.setImagePath(productRequestDTO.getImagePath());
            this.productRepository.save(updateProduct);
            return updateProduct;
        } else {
            throw new ProductNotFoundException("Product not found!");
        }
    }

    public String deleteProductById(Long id) {
        Product product = getById(id);

        this.productRepository.delete(product);
        return "Product deleted with id: " + id;


    }

    public String deleteAllProducts() {
        if (this.productRepository.findAll().isEmpty()) {
            throw new ProductNotFoundException("DB is already clear!");
        }

        this.productRepository.deleteAll();

        return "All are deleted!!!";
    }

    private boolean isEmptyOrNull(String str) {
        return str == null || str.trim().isEmpty();
    }

    private ProductPreviewDTO mapProductToDTO(Product product) {
        ProductPreviewDTO productPreviewDTO = new ProductPreviewDTO();
        productPreviewDTO.setId(product.getId());
        productPreviewDTO.setName(product.getName());
        productPreviewDTO.setImagePath(product.getImagePath());

        return productPreviewDTO;
    }

    public ProductPreviewDTO addImageURL(String imageURL, Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product product = optionalProduct.orElseThrow(() -> new ProductNotFoundException("Product not found!"));
        product.setImagePath(imageURL);
        return mapProductToDTO(this.productRepository.save(product));
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }
}
