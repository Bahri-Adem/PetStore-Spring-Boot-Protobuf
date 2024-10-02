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

import com.example.petstoremonolithique.Entities.Pet;
import com.example.petstoremonolithique.Services.PetService;

import lombok.Data;

@Data
@RestController
public class PetController {

	@Autowired
	private PetService petService;

	@GetMapping("/getPets")
	public List<Pet> getPets() {
		return this.petService.getPets();
	}

	@PostMapping("/addPet")
	public Pet addPet(@RequestBody Pet pet) {
		return petService.addPet(pet);
	}

	@PutMapping("/updatePet/{id}")
	public Pet updatePet(@PathVariable int id, @RequestBody Pet pet) {
		return petService.updatePet(id, pet);
	}

	@DeleteMapping("/deletePet/{id}")
	public void deletePet(@PathVariable long id) {
		petService.deletePet(id);
	}

}
