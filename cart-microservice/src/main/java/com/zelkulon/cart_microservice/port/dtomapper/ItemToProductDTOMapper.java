/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for cart-microservice
 * path to this file is: src/main/java/com/zelkulon/cart_microservice/port/dtomapper
 **/
package com.zelkulon.cart_microservice.port.dtomapper;

import com.zelkulon.cart_microservice.core.domain.model.Item;
import com.zelkulon.cart_microservice.port.dto.ProductChangeDTO;
import org.springframework.stereotype.Service;

@Service
public class ItemToProductDTOMapper {
    public ProductChangeDTO getProductChangeDTO(Item item, int diff){
        ProductChangeDTO productDTO = new ProductChangeDTO();
        productDTO.setId(item.getProductId());
        productDTO.setChangeAmount(diff);
        return productDTO;
    }
}
