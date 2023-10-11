package com.example.rest_api_test.service;

import com.example.rest_api_test.dto.OrderRequestDto;
import com.example.rest_api_test.dto.OrderResponseDto;
import com.example.rest_api_test.entity.Order;
import com.example.rest_api_test.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@Service
public class ProcessService {
    @Autowired
    OrderRepository orderRepository;

    @Value("${url.order}")
    private String url;
    public OrderResponseDto doPost(OrderRequestDto orderRequestDto){
        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.setUrlOrder(url + orderRequestDto.getTokenId());
        orderResponseDto.setInvoice(orderResponseDto.getInvoice());

        Order order = new Order();
        order.setAmount(orderRequestDto.getAmount());
        order.setCurrency(orderRequestDto.getCurrency());
        order.setStatus("OPEN");
        order.setCreatedDate(new Date());
        order.setTokenId(orderRequestDto.getTokenId());
        order.setInvoiceNumber(orderRequestDto.getInvoiceNumber());
        order.setUpdatedDate(new Date());
        orderRepository.save(order);
        return orderResponseDto;
    }

    public Order doGet(String tokenId){
        Order order = orderRepository.findOrderByTokenId(tokenId);
        return order;
    }

    public String doDelete(String tokenId){
        Order order = orderRepository.findOrderByTokenId(tokenId);
        orderRepository.deleteById(order.getId());
        return "Success Delete Order Id :"+order.getId();
    }
}
