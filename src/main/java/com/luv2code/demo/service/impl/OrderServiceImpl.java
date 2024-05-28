package com.luv2code.demo.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.luv2code.demo.dto.mapper.SystemMapper;
import com.luv2code.demo.dto.request.OrderDtoRequest;
import com.luv2code.demo.dto.request.OrderItemRequest;
import com.luv2code.demo.dto.response.OrderDtoResponse;
import com.luv2code.demo.dto.response.OrderItemResponse;
import com.luv2code.demo.entity.Order;
import com.luv2code.demo.entity.OrderItem;
import com.luv2code.demo.entity.Product;
import com.luv2code.demo.exc.custom.NotFoundException;
import com.luv2code.demo.exc.custom.NotFoundTypeException;
import com.luv2code.demo.exc.custom.QuantityNotAvailableException;
import com.luv2code.demo.repo.OrderRepository;
import com.luv2code.demo.repo.ProductRepository;
import com.luv2code.demo.repo.UserRepository;
import com.luv2code.demo.service.OrderService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final SystemMapper mapper;

	@Override
	public List<OrderDtoResponse> findAllOrderByUser(long userId) {

		return orderRepository.findAllByUserId(userId).stream()
				.map(order -> new OrderDtoResponse(order.getUser().getEmail(), order.getUser().getPhoneNumber(),
						order.getOrderDate(),
						order.getOrderItems().stream()
								.map(orderItem -> new OrderItemResponse(orderItem.getProduct().getProductName(),
										orderItem.getQuantity(), orderItem.getPrice()))
								.collect(Collectors.toList())))
				.collect(Collectors.toList());

	}

	@Override
	public void addOrder(OrderDtoRequest orderRequest) {

		Order order = mapper.orderDtoRequestToOrder(orderRequest);

		order.setUser(userRepository.findById(orderRequest.getUser_id()).get());

		List<OrderItemRequest> result = orderRequest.getOrderItems();

		List<OrderItem> orderItems = new ArrayList<>();

		for (OrderItemRequest tmp : result) {
			OrderItem x = new OrderItem();
			Product product = productRepository.findById(tmp.getProduct_id()).get();

			// check if quantity of product not available

			if (product.getStockQuantity() < tmp.getQuantity()) {
				throw new QuantityNotAvailableException(
						"Quantity Is Not Available For Product : " + product.getProductName());
			}

			// update quantity for specific product

			product.setStockQuantity(product.getStockQuantity() - tmp.getQuantity());

			productRepository.save(product);

			// add orderItem to list

			x.setPrice(tmp.getPrice());
			x.setQuantity(tmp.getQuantity());
			x.setProduct(product);
			x.setOrder(order);
			orderItems.add(x);
		}

		order.setOrderItems(orderItems);
		order.setOrderDate(LocalDateTime.now());

		// save order in database

		orderRepository.save(order);

	}

	@Override
	public void deleteOrderById(long orderId) {

		Optional<Order> order = orderRepository.findById(orderId);

		if (order.isEmpty()) {
			throw new NotFoundException(NotFoundTypeException.Order + " Not Found!");
		}

		orderRepository.deleteById(orderId);
	}

}
