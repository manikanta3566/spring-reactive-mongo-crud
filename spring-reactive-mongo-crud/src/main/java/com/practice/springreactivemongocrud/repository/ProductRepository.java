package com.practice.springreactivemongocrud.repository;

import com.practice.springreactivemongocrud.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
}
