package com.example.petstoremonolithique.ProtoControllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.petstoremonolithique.Entities.Order;
import com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder;
import com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrders;
import com.example.petstoremonolithique.Services.OrderService;

import lombok.Data;

@Data
@RestController
@RequestMapping("/proto")
public class OrderControllerProto {
	@Autowired
	private OrderService orderService;

	@GetMapping(value = "/getOrders")
	public GrpcOrders getOrders() {
		List<GrpcOrder> orderList = new ArrayList<GrpcOrder>();
		for (Order order : orderService.getOrders()) {
			orderList.add(order.toGrpc());
		}
		return GrpcOrders.newBuilder().addAllGrpcOrder(orderList).build();
	}

	@GetMapping(value = "/order/{id}")
	public GrpcOrder getOrder(@PathVariable long id) {
		return orderService.getOrder(id).toGrpc();
	}

	@PostMapping(value = "/addOrder")
	public GrpcOrder addOrder(@RequestBody GrpcOrder order) {
		return orderService.addOrder(Order.fromGrpc(order)).toGrpc();
	}

	@PutMapping(value = "/updateOrder/{id}")
	public GrpcOrder updateOrder(@PathVariable Long id, @RequestBody GrpcOrder order) {
		return orderService.updateOrder(id, Order.fromGrpc(order)).toGrpc();
	}

	@DeleteMapping("/deleteOrder/{id}")
	public void deleteUser(@PathVariable Long id) {
		this.orderService.deleteOrder(id);
	}
}