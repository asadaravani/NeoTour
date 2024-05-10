package kg.group8.neotour.service;

import kg.group8.neotour.dto.request.ReviewRequestDto;
import kg.group8.neotour.dto.response.ReviewResponseDto;
import kg.group8.neotour.exception.EmptyFieldException;

import java.util.List;

public interface ReviewService {
    List<ReviewResponseDto> getAllReviews();

    String addReview(ReviewRequestDto reviewRequestDTO) throws EmptyFieldException;

    String deleteReview(Long review);

    Object addImageURL(String imageURL, Long reviewId);


}
