package com.example.petstoremonolithique.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcPetPhoto;
import com.google.protobuf.ByteString;

import lombok.Data;

@Entity
@Data
@Table(name = "\"petphotos\"")
public class PetPhoto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long petID;

	private String metaData;

	private byte[] fileData;

	public PetPhoto(Long id, Long petID, String metaData, byte[] fileData) {
		this.id = id;
		this.petID = petID;
		this.metaData = metaData;
		this.fileData = fileData;
	}

	public PetPhoto() {
	}

	public void setId(@Nullable Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setPetID(@Nullable Long petID) {
		this.petID = petID;
	}

	public Long getPetID() {
		return this.petID;
	}

	public void setMetaData(@Nullable String metaData) {
		this.metaData = metaData;
	}

	public String getMetaData() {
		return this.metaData;
	}

	public void setFileData(@Nullable byte[] fileData) {
		this.fileData = fileData;
	}

	public byte[] getFileData() {
		return this.fileData;
	}

	@NotNull
	public static PetPhoto fromGrpc(@NotNull GrpcPetPhoto petPhoto) {
		return new PetPhoto(petPhoto.getId(), petPhoto.getPetID(), petPhoto.getMetaData(),
				petPhoto.getFileData().toByteArray());
	}

	@NotNull
	public GrpcPetPhoto toGrpc() {
		GrpcPetPhoto.Builder builder = GrpcPetPhoto.newBuilder();
		if (id != null) {
			builder.setId(id);
		}
		if (petID != null) {
			builder.setPetID(petID);
		}
		if (metaData != null) {
			builder.setMetaData(metaData);
		}
		if (fileData != null) {
			builder.setFileData(ByteString.copyFrom(fileData));
		}
		return builder.build();
	}
}
