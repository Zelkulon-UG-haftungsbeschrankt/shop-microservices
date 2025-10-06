/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for product-microservice
 * path to this file is: src/main/java/com/zelkulon/product_microservice/port/user/exception
 **/
package com.zelkulon.product_microservice.port.user.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(int id) {
        super("Could not find product"+ id);
    }

}
