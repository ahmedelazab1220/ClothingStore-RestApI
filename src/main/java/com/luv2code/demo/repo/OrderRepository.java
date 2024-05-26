package com.luv2code.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luv2code.demo.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(value = "SELECT * FROM orders WHERE user_id = :userId", nativeQuery = true)
	List<Order> findAllByUserId(long userId);

}
