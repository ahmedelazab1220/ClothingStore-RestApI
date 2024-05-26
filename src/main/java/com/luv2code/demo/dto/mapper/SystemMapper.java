package com.luv2code.demo.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.luv2code.demo.dto.request.OrderDtoRequest;
import com.luv2code.demo.dto.request.ProductDtoRequest;
import com.luv2code.demo.dto.request.RegisterRequest;
import com.luv2code.demo.dto.response.ProductDtoResponse;
import com.luv2code.demo.dto.response.UserDto;
import com.luv2code.demo.entity.Order;
import com.luv2code.demo.entity.Product;
import com.luv2code.demo.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SystemMapper {

	@Mapping(target = "role", ignore = true)
	User registerRequstToUser(RegisterRequest registerRequest);

	UserDto userToUserDto(User user);

	ProductDtoResponse productToProductDtoResponse(Product product);

	@Mapping(target = "category", ignore = true)
	Product productDtoRequestToProduct(ProductDtoRequest productDtoRequest);

	Order orderDtoRequestToOrder(OrderDtoRequest orderDtoRequest);
}
