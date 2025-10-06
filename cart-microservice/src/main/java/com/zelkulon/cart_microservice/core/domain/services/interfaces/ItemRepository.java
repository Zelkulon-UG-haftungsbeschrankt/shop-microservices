/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for cart-microservice
 * path to this file is: src/main/java/com/zelkulon/cart_microservice/core/domain/services/interfaces
 **/
package com.zelkulon.cart_microservice.core.domain.services.interfaces;

import com.zelkulon.cart_microservice.core.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findByUsername(String username);
}
