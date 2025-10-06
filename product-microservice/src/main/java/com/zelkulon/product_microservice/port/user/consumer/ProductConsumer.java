/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for product-microservice
 * path to this file is: src/main/java/com/zelkulon/product_microservice/port/user/consumer
 **/
package com.zelkulon.product_microservice.port.user.consumer;

import com.zelkulon.product_microservice.core.domain.model.Product;
import com.zelkulon.product_microservice.core.domain.service.impl.ProductService;
import com.zelkulon.product_microservice.core.domain.service.interfaces.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductConsumer.class);

    @Autowired
    private IProductService productService;

    @RabbitListener(queues = {"product"})
    public void consumer(String message) {
        LOGGER.info(String.format("Received message : %s", message));
        productService.getProduct(123);
        //Todo
    }

}
