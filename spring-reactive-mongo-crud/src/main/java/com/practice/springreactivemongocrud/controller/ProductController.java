package com.practice.springreactivemongocrud.controller;

import com.practice.springreactivemongocrud.dto.ProductDto;
import com.practice.springreactivemongocrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDtoMono) {
        return productService.saveProduct(productDtoMono);
    }

    @GetMapping("{id}")
    public Mono<ProductDto> getProductById(@PathVariable String id) {
        return productService.getProduct(id);
    }

    @GetMapping
    public Flux<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("{id}")
    public Mono<ProductDto> updateProduct(@PathVariable String id,
                                          @RequestBody Mono<ProductDto> productDtoMono) {
        return productService.updateProduct(id, productDtoMono);
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }

}
