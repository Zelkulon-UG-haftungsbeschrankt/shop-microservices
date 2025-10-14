/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for order-microservice
 * path to this file is: src/main/java/com/zelkulon/order_microservice/port/dto
 **/
package com.zelkulon.order_microservice.port.dtoMapper;

import com.zelkulon.order_microservice.core.domain.model.Order;
import com.zelkulon.order_microservice.port.dto.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public class OrderDTOMapper {
    public OrderDTO getMapperOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderRegistry().getOrderId());
        orderDTO.setEmaiL(order.getOrderContact().getEmail());
        orderDTO.setUsername(order.getOrderRegistry().getUsername());
        orderDTO.setTotalAmount(order.getTotalAmount());
        return orderDTO;
    }
}
