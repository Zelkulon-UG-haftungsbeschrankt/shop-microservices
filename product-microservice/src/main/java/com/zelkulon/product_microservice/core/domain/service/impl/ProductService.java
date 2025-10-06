/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for product-microservice
 * path to this file is: src/main/java/com/zelkulon/product_microservice/core/domain/service/impl
 **/
package com.zelkulon.product_microservice.core.domain.service.impl;

import com.zelkulon.product_microservice.core.domain.model.Product;
import com.zelkulon.product_microservice.core.domain.service.interfaces.IProductRepository;
import com.zelkulon.product_microservice.core.domain.service.interfaces.IProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    private final IProductRepository productRepository;

    ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(Product product) {
        productRepository.save(product);
    }

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    //TODO getProduct
    @Override
    public Product getProduct(int id) {
        return null;
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
