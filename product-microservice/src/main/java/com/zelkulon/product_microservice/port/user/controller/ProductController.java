/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for product-microservice
 * path to this file is: src/main/java/com/zelkulon/product_microservice/port/user/controller
 **/
package com.zelkulon.product_microservice.port.user.controller;

import com.zelkulon.product_microservice.core.domain.model.Product;
import com.zelkulon.product_microservice.core.domain.service.interfaces.IProductService;
import com.zelkulon.product_microservice.port.user.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void create(@RequestBody Product product) {
        productService.createProduct(product);
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id) {
        Product product = productService.getProduct(id);

        if (product == null) {
            throw new ProductNotFoundException(id);
        }

        return product;
    }

    //TODO
    @PutMapping("/product")
    public Product update(@RequestBody Product product) {
        return null;
    }

    @DeleteMapping("/product")
    public @ResponseBody String delete() {
        return null;
    }

    @GetMapping("/products")
    public @ResponseBody Iterable<Product> getAll() {
        return productService.getAllProducts();
    }

}
