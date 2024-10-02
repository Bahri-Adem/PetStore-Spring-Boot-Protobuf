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

import com.example.petstoremonolithique.Entities.Pet;
import com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcPet;
import com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcPets;
import com.example.petstoremonolithique.Services.PetService;

import lombok.Data;

@Data
@RestController
@RequestMapping("/proto")
public class PetControllerProto {

	@Autowired
	private PetService petService;

	@GetMapping(value = "/getPets")
	public GrpcPets getPets() {
		List petList = new ArrayList<GrpcPet>();
		for (Pet pet : petService.getPets()) {
			petList.add(pet.toGrpc());
		}
		return GrpcPets.newBuilder().addAllGrpcPet(petList).build();
	}

	@PostMapping(value = "/addPet")
	public GrpcPet addPet(@RequestBody GrpcPet pet) {
		return petService.addPet(Pet.fromGrpc(pet)).toGrpc();
	}

	@PutMapping(value = "/updatePet/{id}")
	public GrpcPet updatePet(@PathVariable int id, @RequestBody GrpcPet pet) {
		return petService.updatePet(id, Pet.fromGrpc(pet)).toGrpc();
	}

	@DeleteMapping("/deletePet/{id}")
	public void deletePet(@PathVariable long id) {
		petService.deletePet(id);
	}

}
