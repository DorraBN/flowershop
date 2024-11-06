package com.project.flowers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.flowers.entity.Product;
import com.project.flowers.entity.User;
import com.project.flowers.entity.UserProduct;
import com.project.flowers.repository.ProductRepository;
import com.project.flowers.repository.UserProductRepository;
import com.project.flowers.repository.UserRepository;

@Service
public class CartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserProductRepository userProductRepository;

    public void addProductToCart(Long userId, Long productId, int quantity) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        UserProduct userProduct = new UserProduct();
        userProduct.setUser(user);
        userProduct.setProduct(product);
        userProduct.setQuantity(quantity);

        userProductRepository.save(userProduct);
    }

    public void removeProductFromCart(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        UserProduct userProduct = userProductRepository.findByUserAndProduct(user, product);
        userProductRepository.delete(userProduct);
    }

    public double calculateTotal(Long userId) {
        List<UserProduct> userProducts = userProductRepository.findByUserId(userId);
        double total = 0;
        for (UserProduct userProduct : userProducts) {
            total += userProduct.getProduct().getPrice() * userProduct.getQuantity();
        }
        return total;
    }
}
