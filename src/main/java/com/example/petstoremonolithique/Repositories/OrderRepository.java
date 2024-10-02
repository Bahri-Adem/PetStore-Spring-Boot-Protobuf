package com.example.petstoremonolithique.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.petstoremonolithique.Entities.*;

@Repository
public interface OrderRepository extends JpaRepository<com.example.petstoremonolithique.Entities.Order, Long> {
	@Query("select o from Order o where o.id = ?1")
	Optional<Order> findOrderById(Long id);
}
