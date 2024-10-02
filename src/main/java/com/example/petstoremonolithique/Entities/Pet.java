package com.example.petstoremonolithique.Entities;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcPet;

import lombok.Data;

@Entity
@Data
@Table(name = "\"pets\"")
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private PetCategory category;
	@ElementCollection
	@CollectionTable(name = "pet_photos", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "photo_url")
	private List<String> photoUrls;

	private String tags;

	private PetStatus status;

	public Pet(Long id, String name, PetCategory category, List<String> photoUrls, String tags, PetStatus status) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.photoUrls = photoUrls;
		this.tags = tags;
		this.status = status;
	}

	public Pet() {
	}

	public void setId(@Nullable Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setName(@Nullable String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setCategory(@Nullable PetCategory category) {
		this.category = category;
	}

	public PetCategory getCategory() {
		return this.category;
	}

	public void setPhotoUrls(@NotNull List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public List<String> getPhotoUrls() {
		return this.photoUrls;
	}

	public void setTags(@Nullable String tags) {
		this.tags = tags;
	}

	public String getTags() {
		return this.tags;
	}

	public void setStatus(@Nullable PetStatus status) {
		this.status = status;
	}

	public PetStatus getStatus() {
		return this.status;
	}

	@NotNull
	public static Pet fromGrpc(@NotNull GrpcPet pet) {
		return new Pet(pet.getId(), pet.getName(), PetCategory.fromGrpc(pet.getCategory()), pet.getPhotoUrlsList(),
				pet.getTags(), PetStatus.fromGrpc(pet.getStatus()));
	}

	@NotNull
	public GrpcPet toGrpc() {
		GrpcPet.Builder builder = GrpcPet.newBuilder();
		if (id != null) {
			builder.setId(id);
		}
		if (name != null) {
			builder.setName(name);
		}
		if (category != null) {
			builder.setCategory(category.toGrpc());
		}
		builder.addAllPhotoUrls(photoUrls);
		if (tags != null) {
			builder.setTags(tags);
		}
		if (status != null) {
			builder.setStatus(status.toGrpc());
		}
		return builder.build();
	}

	public enum PetCategory {

		Dog, Cat, Bunny;

		public GrpcPet.GrpcPetCategory toGrpc() {
			return switch (this) {
			case Dog -> GrpcPet.GrpcPetCategory.Dog;
			case Cat -> GrpcPet.GrpcPetCategory.Cat;
			case Bunny -> GrpcPet.GrpcPetCategory.Bunny;
			};
		}

		public static PetCategory fromGrpc(GrpcPet.GrpcPetCategory grpc) {
			return switch (grpc) {
			case Dog -> PetCategory.Dog;
			case Cat -> PetCategory.Cat;
			case Bunny -> PetCategory.Bunny;
			case UNRECOGNIZED -> throw new IllegalArgumentException("Enum value is not recognized");
			};
		}
	}

	public enum PetStatus {

		Available, Pending, Sold;

		public GrpcPet.GrpcPetStatus toGrpc() {
			return switch (this) {
			case Available -> GrpcPet.GrpcPetStatus.Available;
			case Pending -> GrpcPet.GrpcPetStatus.Pending;
			case Sold -> GrpcPet.GrpcPetStatus.Sold;
			};
		}

		public static PetStatus fromGrpc(GrpcPet.GrpcPetStatus grpc) {
			return switch (grpc) {
			case Available -> PetStatus.Available;
			case Pending -> PetStatus.Pending;
			case Sold -> PetStatus.Sold;
			case UNRECOGNIZED -> throw new IllegalArgumentException("Enum value is not recognized");
			};
		}
	}
}
