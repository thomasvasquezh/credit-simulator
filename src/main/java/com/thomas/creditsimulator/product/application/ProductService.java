package com.thomas.creditsimulator.product.application;

import com.thomas.creditsimulator.common.NotFoundException;
import com.thomas.creditsimulator.product.api.request.UpdateProductRequest;
import com.thomas.creditsimulator.product.domain.Product;
import com.thomas.creditsimulator.product.domain.ProductStatus;
import com.thomas.creditsimulator.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product createProduct(Product product){
        validateBusinessRules(product);
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findByStatus(ProductStatus.ACTIVE);
    }

    public Product findById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Product not found with ID : " + id));
    }

    public Product update(Long id, UpdateProductRequest request) {
        Product existingProduct = findById(id);

        existingProduct.setName(request.name());
        existingProduct.setType(request.type());
        existingProduct.setMinAmount(request.minAmount());
        existingProduct.setMaxAmount(request.maxAmount());
        existingProduct.setMinTermMonths(request.minTermMonths());
        existingProduct.setMaxTermMonths(request.maxTermMonths());
        existingProduct.setInterestRate(request.interestRate());

        validateBusinessRules(existingProduct);

        return productRepository.save(existingProduct);
    }

    public void deleteById(Long id) {
        Product product = findById(id);
        product.setStatus(ProductStatus.INACTIVE);
        productRepository.save(product);
    }
    private void validateBusinessRules(Product product) {
        if (product.getMinAmount().compareTo(product.getMaxAmount()) > 0) {
            throw new IllegalArgumentException("Min amount (" + product.getMinAmount() +
                    ") cannot be greater than max amount (" + product.getMaxAmount() + ")");
        }

        if (product.getMinTermMonths() > product.getMaxTermMonths()) {
           throw new IllegalArgumentException("Min term (" + product.getMinTermMonths() +
                   " months) cannot be greater than max term (" + product.getMaxTermMonths() + " months)");
        }
    }


}
