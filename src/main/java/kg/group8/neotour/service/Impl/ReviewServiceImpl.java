package kg.group8.neotour.service.Impl;

import kg.group8.neotour.dto.request.ReviewRequestDto;
import kg.group8.neotour.dto.response.ReviewResponseDto;
import kg.group8.neotour.entity.Product;
import kg.group8.neotour.entity.Review;
import kg.group8.neotour.exception.EmptyFieldException;
import kg.group8.neotour.exception.ProductNotFoundException;
import kg.group8.neotour.repository.ProductRepository;
import kg.group8.neotour.repository.ReviewsRepository;
import kg.group8.neotour.service.ProductService;
import kg.group8.neotour.service.ReviewService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewServiceImpl implements ReviewService{

    ReviewsRepository reviewsRepository;
    ProductRepository productRepository;
    ProductService productService;

    @Override
    public List<ReviewResponseDto> getAllReviews() {
        return mapReviewResponseListToDto(reviewsRepository.findAll());
    }

    @Override
    public String addReview(ReviewRequestDto reviewRequestDTO) throws EmptyFieldException{
        if (reviewRequestDTO.getComment().isEmpty() || reviewRequestDTO.getReviewer().isEmpty()){
            throw new EmptyFieldException("Comment and reviewer's name must be filled");
        }
        Review review = new Review();
        review.setReviewersImagePath(reviewRequestDTO.getReviewersImagePath());
        review.setReviewer(reviewRequestDTO.getReviewer());
        review.setComment(reviewRequestDTO.getComment());
        review.setRating(reviewRequestDTO.getRating());
        review.setProduct(productService.getById(reviewRequestDTO.getProductId()));
        updateRatingAndReviewCount(reviewRequestDTO.getProductId());
        reviewsRepository.save(review);
        return "Comment added successfully";
    }
    @Override
    public String deleteReview(Long review) {
        reviewsRepository.deleteById(review);
        return "Review deleted successfully";
    }
    @Override
    public Object addImageURL(String imageURL, Long reviewId) {
        Optional<Review> optionalReview = reviewsRepository.findById(reviewId);
        Review review = optionalReview.orElseThrow(() -> new ProductNotFoundException("Review Not Found"));
        review.setReviewersImagePath(imageURL);
        reviewsRepository.save(review);
        return mapReviewToDTO(review);
    }

    private ReviewRequestDto mapReviewToDTO(Review review) {
        ReviewRequestDto reviewRequestDTO = new ReviewRequestDto();
        reviewRequestDTO.setId(review.getId());
        reviewRequestDTO.setComment(review.getComment());
        reviewRequestDTO.setReviewer(review.getReviewer());
        reviewRequestDTO.setRating(review.getRating());
        reviewRequestDTO.setProductId(review.getProduct().getId());
        reviewRequestDTO.setReviewersImagePath(review.getReviewersImagePath());
        return reviewRequestDTO;
    }

    private void updateRatingAndReviewCount(Long id) {
        Product product = productService.getById(id);
        if (product.getReviews() != null && !product.getReviews().isEmpty()) {
            double totalRating = 0.0;
            for (Review review : product.getReviews()) {
                totalRating += review.getRating();
            }
            double averageRating = totalRating / product.getReviews().size();
            product.setRating(averageRating);
            product.setReviewCount(BigDecimal.valueOf(product.getReviews().size()));
        } else {
            product.setRating(2.5);
            product.setReviewCount(BigDecimal.ZERO);
        }
        productRepository.save(product);
    }
    private List<ReviewResponseDto> mapReviewResponseListToDto(List<Review> reviews) {
        List<ReviewResponseDto> reviewResponseDtoList = new ArrayList<>();
        for (Review review : reviews) {
            ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
            reviewResponseDto.setReviewer(review.getReviewer());
            reviewResponseDto.setReviewersImagePath(review.getReviewersImagePath());
            reviewResponseDto.setComment(review.getComment());
            reviewResponseDtoList.add(reviewResponseDto);
        }
        return reviewResponseDtoList;
    }
}
