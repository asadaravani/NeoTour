package kg.group8.neotour.controller;

import kg.group8.neotour.service.CloudinaryService;
import kg.group8.neotour.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("api/images")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CloudinaryController {
    CloudinaryService cloudinaryService;
    ProductService productService;

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile file, @RequestParam Long productId) {
        try {
            String imageURL =  cloudinaryService.uploadImage(file);
            return ResponseEntity.ok().body(productService.addImageURL(imageURL, productId));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public void deleteImage(@RequestParam("imageUrl") String imageUrl) {
        try {
            cloudinaryService.deleteImage(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
