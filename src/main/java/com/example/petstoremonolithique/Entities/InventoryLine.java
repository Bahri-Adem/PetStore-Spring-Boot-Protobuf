package com.example.petstoremonolithique.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcInventoryLine;

import lombok.Data;

@Entity
@Data
@Table(name = "\"inventorylines\"")
public class InventoryLine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private PetStatus status;

	private Integer count;

	public InventoryLine(Long id, PetStatus status, Integer count) {
		this.id = id;
		this.status = status;
		this.count = count;
	}

	public InventoryLine() {
	}

	public void setId(@Nullable Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setStatus(@Nullable PetStatus status) {
		this.status = status;
	}

	public PetStatus getStatus() {
		return this.status;
	}

	public void setCount(@Nullable Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return this.count;
	}

	@NotNull
	public static InventoryLine fromGrpc(@NotNull GrpcInventoryLine inventoryLine) {
		return new InventoryLine(inventoryLine.getId(), PetStatus.fromGrpc(inventoryLine.getStatus()),
				inventoryLine.getCount());
	}

	@NotNull
	public GrpcInventoryLine toGrpc() {
		GrpcInventoryLine.Builder builder = GrpcInventoryLine.newBuilder();
		if (id != null) {
			builder.setId(id);
		}
		if (status != null) {
			builder.setStatus(status.toGrpc());
		}
		if (count != null) {
			builder.setCount(count);
		}
		return builder.build();
	}

	public enum PetStatus {

		Available, Pending, Sold;

		public GrpcInventoryLine.GrpcPetStatus toGrpc() {
			return switch (this) {
			case Available -> GrpcInventoryLine.GrpcPetStatus.Available;
			case Pending -> GrpcInventoryLine.GrpcPetStatus.Pending;
			case Sold -> GrpcInventoryLine.GrpcPetStatus.Sold;
			};
		}

		public static PetStatus fromGrpc(GrpcInventoryLine.GrpcPetStatus grpc) {
			return switch (grpc) {
			case Available -> PetStatus.Available;
			case Pending -> PetStatus.Pending;
			case Sold -> PetStatus.Sold;
			case UNRECOGNIZED -> throw new IllegalArgumentException("Enum value is not recognized");
			};
		}
	}
}
