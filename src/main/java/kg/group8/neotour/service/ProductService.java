package kg.group8.neotour.service;

import kg.group8.neotour.dto.response.ProductDetailDto;
import kg.group8.neotour.dto.response.ProductPreviewDto;
import kg.group8.neotour.dto.request.ProductRequestDto;
import kg.group8.neotour.entity.Product;
import kg.group8.neotour.exception.EmptyFieldException;
import kg.group8.neotour.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    List<ProductPreviewDto> getPopularProducts();

    List<ProductPreviewDto> getMostVisitedProducts();

    List<ProductPreviewDto> getFeaturedProducts();

    List<ProductPreviewDto> getEuropeanProducts();

    List<ProductPreviewDto> getAsianProducts();

    List<ProductPreviewDto> getRecommendedProducts();

    ProductDetailDto findProductById(Long id) throws ProductNotFoundException;

    String addProductList(List<ProductRequestDto> products);

    Product addProduct(ProductRequestDto productRequestDTO) throws EmptyFieldException;

    Product updateProduct(ProductRequestDto productRequestDTO);

    String deleteProductById(Long id);

    String deleteAllProducts();

    ProductPreviewDto addImageURL(String imageURL, Long productId);

    Product getById(Long id);
}
