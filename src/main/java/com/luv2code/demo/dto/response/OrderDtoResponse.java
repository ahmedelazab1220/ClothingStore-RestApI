package com.luv2code.demo.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDtoResponse {

	private String email;

	private String phoneNumber;

	private LocalDateTime order_date;

	private List<OrderItemResponse> orderItems;

}
