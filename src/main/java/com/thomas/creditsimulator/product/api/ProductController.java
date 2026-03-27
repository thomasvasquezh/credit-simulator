package com.thomas.creditsimulator.product.api;

import com.thomas.creditsimulator.product.api.request.CreateProductRequest;
import com.thomas.creditsimulator.product.api.request.UpdateProductRequest;
import com.thomas.creditsimulator.product.api.response.ProductResponse;
import com.thomas.creditsimulator.product.application.ProductService;
import com.thomas.creditsimulator.product.domain.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request){
        Product product = productMapper.toDomain(request);
        Product saveProduct = productService.createProduct(product);
        ProductResponse response = productMapper.toResponse(saveProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductResponse> response = productMapper.toResponseList(products);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> productById(@PathVariable Long id){
        Product product = productService.findById(id);
        ProductResponse response = productMapper.toResponse(product);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request){
        Product updateProduct = productService.update(id,request);
        ProductResponse response = productMapper.toResponse(updateProduct);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
