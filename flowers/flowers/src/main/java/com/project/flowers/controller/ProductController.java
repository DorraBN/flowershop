package com.project.flowers.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.flowers.entity.Product;
import com.project.flowers.service.ProductService;

@RestController
@RequestMapping("/api/flowers")
public class ProductController {
    private static final String IMAGE_DIRECTORY = "src/main/resources/static/images/";

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> save(
            @RequestParam("flowerName") String flowerName,
            @RequestParam("price") Double price,
            @RequestParam("image") MultipartFile image
    ) {
        try {
            Product product = new Product();
            product.setFlowerName(flowerName);
            product.setPrice(price);

            if (!image.isEmpty()) {
                String imageName = image.getOriginalFilename();
                Path imagePath = Paths.get(IMAGE_DIRECTORY + imageName);
                
                // Ensure directory exists
                Files.createDirectories(imagePath.getParent());
                
                Files.write(imagePath, image.getBytes());
                String imageUrl = "/images/" + imageName;
                product.setImage(imageUrl);
            }

            productService.saveProduct(product);
            return ResponseEntity.ok("Product saved successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while saving the product: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.isPresent() ? ResponseEntity.ok(product) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    // Helper method to add image URL
   
}
