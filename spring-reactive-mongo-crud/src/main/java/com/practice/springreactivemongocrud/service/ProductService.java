package com.practice.springreactivemongocrud.service;

import com.practice.springreactivemongocrud.dto.ProductDto;
import com.practice.springreactivemongocrud.entity.Product;
import com.practice.springreactivemongocrud.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Flux<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .map(product -> modelMapper.map(product, ProductDto.class));
    }

    public Mono<ProductDto> getProduct(String id) {
        return productRepository.findById(id)
                .map(product -> modelMapper.map(product, ProductDto.class));
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDto) {
        return productDto
                .map(product -> modelMapper.map(product, Product.class))
                .flatMap(product -> productRepository.save(product))
                .map(product -> modelMapper.map(product, ProductDto.class));
    }

    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDto) {
        return productRepository.findById(id)
                .flatMap(product -> productDto.map(product1 -> modelMapper.map(product1, Product.class))
                        .doOnNext(updateProduct -> {
                            updateProduct.setId(product.getId());
                        })
                        .flatMap(productRepository::save)
                        .map(product1 -> modelMapper.map(product, ProductDto.class))
                );
    }

    public Mono<Void> deleteProduct(String id) {
        return productRepository.deleteById(id);
    }
}
