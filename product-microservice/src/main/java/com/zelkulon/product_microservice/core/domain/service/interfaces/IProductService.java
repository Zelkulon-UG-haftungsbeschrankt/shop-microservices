/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for product-microservice
 * path to this file is: src/main/java/com/zelkulon/product_microservice/core/domain/service/interfaces
 **/
package com.zelkulon.product_microservice.core.domain.service.interfaces;

import com.zelkulon.product_microservice.core.domain.model.Product;

public interface IProductService {

    void createProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Product product);

    Product getProduct(int id);

    Iterable<Product> getAllProducts();

}
