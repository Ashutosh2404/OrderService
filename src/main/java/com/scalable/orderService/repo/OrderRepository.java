package com.scalable.orderService.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scalable.orderService.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findAllByOrderDateBetween(LocalDate startDate, LocalDate endDate);

}
