package com.luv2code.demo.service;

import java.util.List;

import com.luv2code.demo.dto.request.OrderDtoRequest;
import com.luv2code.demo.entity.Order;

public interface OrderService {

	List<Order> findAllOrderByUser(long userId);

	void addOrder(OrderDtoRequest orderRequest);

	void deleteOrderById(long orderId);

}
