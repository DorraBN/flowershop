package com.project.flowers.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.flowers.entity.Product;
import com.project.flowers.entity.User;
import com.project.flowers.entity.UserProduct;

@Repository
public interface UserProductRepository extends JpaRepository<UserProduct, Long> {

    // Trouver tous les produits associés à un utilisateur spécifique
    List<UserProduct> findByUserId(Long userId);

    // Trouver un produit spécifique dans le panier d'un utilisateur
    UserProduct findByUserAndProduct(User user, Product product);

    // Supprimer un produit spécifique d'un panier d'un utilisateur
    void deleteByUserAndProduct(User user, Product product);
    
    // Trouver un produit par son id et l'utilisateur
    List<UserProduct> findByProductId(Long productId);

}
