package com.example.petstoremonolithique.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petstoremonolithique.Entities.Order;
import com.example.petstoremonolithique.Repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository storeRepository;

	public List<com.example.petstoremonolithique.Entities.Order> getOrders() {
		return this.storeRepository.findAll();
	}

	public Order getOrder(long orderId) {
		return storeRepository.findOrderById((long) orderId)
				.orElseThrow(() -> new IllegalStateException("Order with id " + orderId + " not found"));
	}

	public Order addOrder(Order order) {

		return storeRepository.save(order);
	}

	public Order updateOrder(Long id, Order updatedOrder) {
		Optional<Order> optionalOrder = storeRepository.findOrderById(id);
		if (optionalOrder.isPresent()) {
			Order existingOrder = optionalOrder.get();
			updateExistingOrder(existingOrder, updatedOrder);
			return storeRepository.save(existingOrder);
		} else {
			throw new IllegalStateException("Order with id " + id + " not found");
		}
	}

	private void updateExistingOrder(Order existingOrder, Order updatedOrder) {
		existingOrder.setPetId(updatedOrder.getPetId());
		existingOrder.setQuantity(updatedOrder.getQuantity());
		existingOrder.setShipDate(updatedOrder.getShipDate());
		existingOrder.setStatus(updatedOrder.getStatus());
		existingOrder.setComplete(updatedOrder.getComplete());

	}

	public void deleteOrder(Long id) {
		if (storeRepository.existsById(id)) {
			storeRepository.deleteById(id);
		} else {
			throw new IllegalStateException("Order with id " + id + " not found");
		}
	}
}
