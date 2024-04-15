package kg.group8.neotour.service;

import kg.group8.neotour.DTO.ReviewDTO;
import kg.group8.neotour.entity.Product;
import kg.group8.neotour.entity.Review;
import kg.group8.neotour.exception.EmptyFieldException;
import kg.group8.neotour.exception.ProductNotFoundException;
import kg.group8.neotour.repository.ProductRepository;
import kg.group8.neotour.repository.ReviewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {
    private ReviewsRepository reviewsRepository;
    private ProductRepository productRepository;

    public List<Review> getReview() {
        return this.reviewsRepository.findAll();
    }
    public String addReview(ReviewDTO reviewDTO) throws EmptyFieldException{
        if (reviewDTO.getComment().isEmpty() || reviewDTO.getReviewer().isEmpty()){
            throw new EmptyFieldException("Comment field must be filled");
        }
        Review review = new Review();
        Optional<Product> optionalProduct = productRepository.findById(reviewDTO.getProductId());
        Product product = optionalProduct.orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
        review.setReviewer(reviewDTO.getReviewer());
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        Review savedReview = reviewsRepository.save(review);

        product.getReviews().add(savedReview);

        //update product's rating
        product.updateRatingAndReviewCount();

        productRepository.save(product);

        return "Comment added successfully";
    }
    public void deleteReview(Review review) {

    }
}
