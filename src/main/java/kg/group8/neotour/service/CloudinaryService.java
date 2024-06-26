package kg.group8.neotour.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {
    String uploadImage(MultipartFile file) throws IOException;

    void deleteProductImage(String imageUrl) throws IOException;

    String extractPublicId(String imageUrl);
}
