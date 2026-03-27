package com.thomas.creditsimulator.product.infrastructure;

import com.thomas.creditsimulator.product.domain.Product;
import com.thomas.creditsimulator.product.domain.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findById(Long id);
    List<Product> findByStatus(ProductStatus status);
    Optional<Product> findByIdAndStatus(Long id, ProductStatus status);
}
