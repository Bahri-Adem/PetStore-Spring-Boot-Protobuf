package com.example.petstoremonolithique.GrpcController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petstoremonolithique.Entities.Order;
import com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder;
import com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrders;
import com.example.petstoremonolithique.Entities.PetStoreGenerated.OrderIdRequest;
import com.example.petstoremonolithique.Entities.PetStoreGrpc;
import com.example.petstoremonolithique.Services.OrderService;
import com.google.protobuf.Empty;

import io.grpc.stub.StreamObserver;

@Service
public class OrderControllerGrpc extends PetStoreGrpc.PetStoreImplBase {

	@Autowired
	private OrderService orderService;

	@Override
	public void getOrders(Empty request, StreamObserver<GrpcOrders> responseObserver) {
		List<GrpcOrder> grpcOrderList = new ArrayList<>();
		for (Order order : orderService.getOrders()) {
			grpcOrderList.add(order.toGrpc());
		}
		GrpcOrders response = GrpcOrders.newBuilder().addAllGrpcOrder(grpcOrderList).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void getOrder(OrderIdRequest request, StreamObserver<GrpcOrder> responseObserver) {
		Order order = orderService.getOrder(request.getId());
		responseObserver.onNext(order.toGrpc());
		responseObserver.onCompleted();
	}

	@Override
	public void addOrder(GrpcOrder request, StreamObserver<GrpcOrder> responseObserver) {
		Order order = orderService.addOrder(Order.fromGrpc(request));
		responseObserver.onNext(order.toGrpc());
		responseObserver.onCompleted();
	}

	@Override
	public void updateOrder(GrpcOrder request, StreamObserver<GrpcOrder> responseObserver) {
		Order order = orderService.updateOrder(request.getId(), Order.fromGrpc(request));
		responseObserver.onNext(order.toGrpc());
		responseObserver.onCompleted();
	}

	@Override
	public void deleteOrder(OrderIdRequest request, StreamObserver<Empty> responseObserver) {
		orderService.deleteOrder(request.getId());
		responseObserver.onNext(Empty.getDefaultInstance());
		responseObserver.onCompleted();
	}
}
