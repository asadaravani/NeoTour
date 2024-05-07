package kg.group8.neotour.service;

import kg.group8.neotour.dto.ReviewDto;
import kg.group8.neotour.exception.EmptyFieldException;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getAllReviews();

    String addReview(ReviewDto reviewDTO) throws EmptyFieldException;

    String deleteReview(Long review);

    Object addImageURL(String imageURL, Long reviewId);


}
