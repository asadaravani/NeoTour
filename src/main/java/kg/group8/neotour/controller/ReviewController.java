package kg.group8.neotour.controller;

import kg.group8.neotour.DTO.ReviewDTO;
import kg.group8.neotour.entity.Review;
import kg.group8.neotour.exception.EmptyFieldException;
import kg.group8.neotour.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reviews")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ReviewController {
    private final ReviewService reviewService;
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok().body(reviewService.getReview());
    }
    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody ReviewDTO reviewDTO) {
        try {
            return ResponseEntity.ok().body(reviewService.addReview(reviewDTO));
        }catch (EmptyFieldException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
