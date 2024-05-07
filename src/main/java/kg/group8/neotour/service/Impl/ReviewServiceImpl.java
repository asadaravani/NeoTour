package kg.group8.neotour.service.Impl;

import kg.group8.neotour.dto.ReviewDto;
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
    public List<ReviewDto> getAllReviews() {
        return mapReviewListToDto(reviewsRepository.findAll());
    }

    @Override
    public String addReview(ReviewDto reviewDTO) throws EmptyFieldException{
        if (reviewDTO.getComment().isEmpty() || reviewDTO.getReviewer().isEmpty()){
            throw new EmptyFieldException("Comment and reviewer's name must be filled");
        }
        Review review = new Review();
        review.setReviewersImagePath(reviewDTO.getReviewersImagePath());
        review.setReviewer(reviewDTO.getReviewer());
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        Review savedReview = reviewsRepository.save(review);

        Product product = productService.getById(reviewDTO.getProductId());
        product.getReviews().add(savedReview);

        updateRatingAndReviewCount(product);

        productRepository.save(product);

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

    private ReviewDto mapReviewToDTO(Review review) {
        ReviewDto reviewDTO = new ReviewDto();
        reviewDTO.setId(review.getId());
        reviewDTO.setComment(review.getComment());
        reviewDTO.setReviewer(review.getReviewer());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setReviewersImagePath(review.getReviewersImagePath());
        return reviewDTO;
    }

    private void updateRatingAndReviewCount(Product product) {
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

    }
    private List<ReviewDto> mapReviewListToDto(List<Review> reviews) {
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        for (Review review : reviews) {
            reviewDtoList.add(mapReviewToDTO(review));
        }
        return reviewDtoList;
    }
}
