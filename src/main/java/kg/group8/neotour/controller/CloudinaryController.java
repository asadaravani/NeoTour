package kg.group8.neotour.controller;

import kg.group8.neotour.service.CloudinaryService;
import kg.group8.neotour.service.ProductService;
import kg.group8.neotour.service.ReviewService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/images")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CloudinaryController {
    CloudinaryService cloudinaryService;
    ProductService productService;
    ReviewService reviewService;

    @PostMapping(value = "/uploadProductImage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile file, @RequestParam Long productId) throws IOException {
        String imageURL =  cloudinaryService.uploadImage(file);
        return ResponseEntity.ok().body(productService.addImageURL(imageURL, productId));
    }
    @PostMapping(value = "/uploadAvatar", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadAvatar(@RequestParam MultipartFile file, @RequestParam Long reviewId) throws IOException {
        String imageURL =  cloudinaryService.uploadImage(file);
        return ResponseEntity.ok().body(reviewService.addImageURL(imageURL, reviewId));
    }

    @DeleteMapping("/delete")
    public void deleteImage(@RequestParam("imageUrl") String imageUrl) throws IOException {
        cloudinaryService.deleteProductImage(imageUrl);
    }
}
