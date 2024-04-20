package kg.group8.neotour.controller;

import kg.group8.neotour.service.CloudinaryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/images")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CloudinaryController {
    CloudinaryService cloudinaryService;
    @PostMapping("/upload")
    public String uploadImage(@RequestParam MultipartFile file) {
        try {
            return cloudinaryService.uploadImage(file);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading image";
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
