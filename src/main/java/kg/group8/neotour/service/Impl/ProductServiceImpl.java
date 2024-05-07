package kg.group8.neotour.service.Impl;

import kg.group8.neotour.dto.response.ProductDetailDto;
import kg.group8.neotour.dto.response.ProductPreviewDto;
import kg.group8.neotour.dto.request.ProductRequestDto;
import kg.group8.neotour.dto.ReviewDto;
import kg.group8.neotour.entity.Product;
import kg.group8.neotour.entity.Review;
import kg.group8.neotour.exception.EmptyFieldException;
import kg.group8.neotour.exception.ProductNotFoundException;
import kg.group8.neotour.repository.ProductRepository;
import kg.group8.neotour.service.ProductService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    @Override
    public List<ProductPreviewDto> getPopularProducts() {
        List<Product> popularProducts = productRepository.findByOrderByRatingDescReviewCountDesc();
        return popularProducts.stream()
                .map(this::mapProductToDTO)
                .toList();
    }
    @Override
    public List<ProductPreviewDto> getMostVisitedProducts() {
        List<Product> mostVisitedProducts = productRepository.findAllByOrderByOrderCountDesc();
        return mostVisitedProducts.stream()
                .map(this::mapProductToDTO)
                .toList();
    }
    @Override
    public List<ProductPreviewDto> getFeaturedProducts() {
        List<Product> featuredProducts = productRepository.findAllByOrderByOrderCountAsc();

        return featuredProducts.stream()
                .map(this::mapProductToDTO)
                .toList();
    }
    @Override
    public List<ProductPreviewDto> getEuropeanProducts() {
        List<Product> europeanProducts = productRepository.findAllByContinent("Europe");
        return europeanProducts.stream()
                .map(this::mapProductToDTO)
                .toList();
    }
    @Override
    public List<ProductPreviewDto> getAsianProducts() {
        List<Product> asianProducts = productRepository.findAllByContinent("Asia");
        return asianProducts.stream()
                .map(this::mapProductToDTO)
                .toList();
    }
    @Override
    public List<ProductPreviewDto> getRecommendedProducts() {
        List<Product> recommendedProducts = productRepository.findAllBySeason("spring");
        return recommendedProducts.stream()
                .map(this::mapProductToDTO)
                .toList();
    }
    @Override
    public ProductDetailDto findProductById(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        Product product = productOptional.orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
        ProductDetailDto productDetailDTO = new ProductDetailDto();
        productDetailDTO.setId(product.getId());
        productDetailDTO.setImagePath(product.getImagePath());
        productDetailDTO.setName(product.getName());
        productDetailDTO.setLocation(product.getLocation());
        productDetailDTO.setDescription(product.getDescription());
        productDetailDTO.setReviews(mapReviewListToDto(product.getReviews()));
        return productDetailDTO;
    }

    @Override
    public String addProductList(List<ProductRequestDto> products) {
        for (ProductRequestDto productRequestDTO : products) {
            addProduct(productRequestDTO);
        }
        return "Successfully added products";
    }

    @Override
    public Product addProduct(ProductRequestDto productRequestDTO) throws EmptyFieldException {
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

    @Override
    public Product updateProduct(ProductRequestDto productRequestDTO) {
        Product updateProduct = productRepository.findById(productRequestDTO.getId())
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
        updateProduct.setName(productRequestDTO.getName());
        updateProduct.setDescription(productRequestDTO.getDescription());
        updateProduct.setLocation(productRequestDTO.getLocation());
        updateProduct.setContinent(productRequestDTO.getContinent());
        updateProduct.setSeason(productRequestDTO.getSeason());
        updateProduct.setImagePath(productRequestDTO.getImagePath());
        this.productRepository.save(updateProduct);
        return updateProduct;
    }
    @Override
    public String deleteProductById(Long id) {
        this.productRepository.deleteById(id);
        return "Product deleted with id: " + id;
    }
    @Override
    public String deleteAllProducts() {
        if (this.productRepository.findAll().isEmpty()) {
            throw new ProductNotFoundException("DB is already clear!");
        }
        this.productRepository.deleteAll();
        return "All are deleted!!!";
    }

    @Override
    public ProductPreviewDto addImageURL(String imageURL, Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product product = optionalProduct.orElseThrow(() -> new ProductNotFoundException("Product not found!"));
        product.setImagePath(imageURL);
        return mapProductToDTO(this.productRepository.save(product));
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    private boolean isEmptyOrNull(String str) {
        return str == null || str.trim().isEmpty();
    }

    private ProductPreviewDto mapProductToDTO(Product product) {
        ProductPreviewDto productPreviewDTO = new ProductPreviewDto();
        productPreviewDTO.setId(product.getId());
        productPreviewDTO.setName(product.getName());
        productPreviewDTO.setImagePath(product.getImagePath());
        return productPreviewDTO;
    }

    private List<ReviewDto> mapReviewListToDto(List<Review> reviews) {
        List<ReviewDto> reviewDtos = new ArrayList<>();
        reviews.forEach(review -> {
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setId(review.getId());
            reviewDto.setReviewersImagePath(review.getReviewersImagePath());
            reviewDto.setReviewer(review.getReviewer());
            reviewDto.setProductId(review.getProduct().getId());
            reviewDto.setRating(review.getRating());
            reviewDto.setComment(review.getComment());
            reviewDtos.add(reviewDto);
        });
        return reviewDtos;
    }
}
