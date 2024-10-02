package com.example.petstoremonolithique.Entities;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcOrder;
import com.google.protobuf.Timestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "\"orders\"")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long petId;

	private Integer quantity;

	private Instant shipDate;

	private OrderStatus status;

	private Boolean complete;

	public Order(Long id, Long petId, Integer quantity, Instant shipDate, OrderStatus status, Boolean complete) {
		this.id = id;
		this.petId = petId;
		this.quantity = quantity;
		this.shipDate = shipDate;
		this.status = status;
		this.complete = complete;
	}

	public Order() {
	}

	public void setId(@Nullable Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setPetId(@Nullable Long petId) {
		this.petId = petId;
	}

	public Long getPetId() {
		return this.petId;
	}

	public void setQuantity(@Nullable Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setShipDate(@Nullable Instant shipDate) {
		this.shipDate = shipDate;
	}

	public Instant getShipDate() {
		return this.shipDate;
	}

	public void setStatus(@Nullable OrderStatus status) {
		this.status = status;
	}

	public OrderStatus getStatus() {
		return this.status;
	}

	public void setComplete(@Nullable Boolean complete) {
		this.complete = complete;
	}

	public Boolean getComplete() {
		return this.complete;
	}

	@NotNull
	public static Order fromGrpc(@NotNull GrpcOrder order) {
		return new Order(order.getId(), order.getPetId(), order.getQuantity(),
				order.hasShipDate()
						? Instant.ofEpochSecond(order.getShipDate().getSeconds(), order.getShipDate().getNanos())
						: null,
				OrderStatus.fromGrpc(order.getStatus()), order.getComplete());
	}

	@NotNull
	public PetStoreGenerated.GrpcOrder toGrpc() {
		PetStoreGenerated.GrpcOrder.Builder builder = PetStoreGenerated.GrpcOrder.newBuilder();
		if (id != null) {
			builder.setId(id);
		}
		if (petId != null) {
			builder.setPetId(petId);
		}
		if (quantity != null) {
			builder.setQuantity(quantity);
		}
		if (shipDate != null) {
			builder.setShipDate(
					Timestamp.newBuilder().setSeconds(shipDate.getEpochSecond()).setNanos(shipDate.getNano()).build());
		}
		if (status != null) {
			builder.setStatus(status.toGrpc());
		}
		if (complete != null) {
			builder.setComplete(complete);
		}
		return builder.build();
	}

	public enum OrderStatus {

		Placed, Processing, Shipped;

		public PetStoreGenerated.GrpcOrder.GrpcOrderStatus toGrpc() {
			return switch (this) {
			case Placed -> PetStoreGenerated.GrpcOrder.GrpcOrderStatus.Placed;
			case Processing -> PetStoreGenerated.GrpcOrder.GrpcOrderStatus.Processing;
			case Shipped -> PetStoreGenerated.GrpcOrder.GrpcOrderStatus.Shipped;
			};
		}

		public static OrderStatus fromGrpc(PetStoreGenerated.GrpcOrder.GrpcOrderStatus grpc) {
			return switch (grpc) {
			case Placed -> OrderStatus.Placed;
			case Processing -> OrderStatus.Processing;
			case Shipped -> OrderStatus.Shipped;
			case UNRECOGNIZED -> throw new IllegalArgumentException("Enum value is not recognized");
			};
		}
	}
}
