package kg.group8.neotour.controller;

import kg.group8.neotour.dto.request.ReviewRequestDto;
import kg.group8.neotour.dto.response.ReviewResponseDto;
import kg.group8.neotour.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reviews")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews() {
        return ResponseEntity.ok().body(reviewService.getAllReviews());
    }

    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody ReviewRequestDto reviewRequestDTO) {
        return ResponseEntity.ok().body(reviewService.addReview(reviewRequestDTO));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok().body(reviewService.deleteReview(reviewId));
    }
}
