package com.luv2code.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.demo.dto.request.OrderDtoRequest;
import com.luv2code.demo.entity.Order;
import com.luv2code.demo.service.OrderService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v5/orders")
@AllArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@GetMapping("/{theId}")
	@PreAuthorize("hasAnyRole('MANAGER' , 'USER')")
	public List<Order> getAllOrderByUser(@PathVariable(name = "theId") long theId) {

		return orderService.findAllOrderByUser(theId);

	}

	@PostMapping("")
	public ResponseEntity<?> addOrder(@RequestBody OrderDtoRequest orderDtoRequest) {

		orderService.addOrder(orderDtoRequest);

		return ResponseEntity.ok("Order added successfully");

	}

	@DeleteMapping("/{theId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteOrder(@PathVariable(name = "theId") int theId) {

		orderService.deleteOrderById(theId);

		return ResponseEntity.ok("Order Deleted successfully");

	}

}
