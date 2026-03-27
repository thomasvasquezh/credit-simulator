package com.thomas.creditsimulator.product.api;

import com.thomas.creditsimulator.product.api.request.CreateProductRequest;
import com.thomas.creditsimulator.product.api.response.ProductResponse;
import com.thomas.creditsimulator.product.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public Product toDomain(CreateProductRequest request){

        return Product.builder()
                .name(request.name())
                .type(request.type())
                .minAmount(request.minAmount())
                .maxAmount(request.maxAmount())
                .minTermMonths(request.minTermMonths())
                .maxTermMonths(request.maxTermMonths())
                .interestRate(request.interestRate())
                .build();
    }

    public ProductResponse toResponse(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getType(),
                product.getMinAmount(),
                product.getMaxAmount(),
                product.getMinTermMonths(),
                product.getMaxTermMonths(),
                product.getInterestRate(),
                product.getStatus(),
                product.getCreatedAt()
        );
    }

    public List<ProductResponse> toResponseList(List<Product> products) {
        return products.stream()
                .map(this::toResponse)
                .toList();
    }


}
