package com.example.petstoremonolithique.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.petstoremonolithique.Entities.Order;
import com.example.petstoremonolithique.Services.OrderService;

import lombok.Data;

@Data
@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;

	@GetMapping("/getOrders")
	public List<Order> getOrders() {
		return this.orderService.getOrders();
	}

	@GetMapping("/order/{id}")
	public Order getOrder(@PathVariable long id) {
		return orderService.getOrder(id);
	}

	@PostMapping("/addOrder")
	public Order addOrder(@RequestBody Order order) {
		return this.orderService.addOrder(order);
	}

	@PutMapping("/updateOrder/{id}")
	public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
		return this.orderService.updateOrder(id, order);
	}

	@DeleteMapping("/deleteOrder/{id}")
	public void deleteUser(@PathVariable Long id) {
		this.orderService.deleteOrder(id);
	}
}
