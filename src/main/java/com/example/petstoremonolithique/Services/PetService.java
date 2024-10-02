package com.example.petstoremonolithique.Services;

import com.example.petstoremonolithique.Entities.Order;
import com.example.petstoremonolithique.Entities.Pet;
import com.example.petstoremonolithique.Repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet getPet(long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Pet with id " + id + " not found"));
    }

    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet updatePet(int petId, Pet updatedPet) {
        Pet existingPet = getPet(petId);
        updateExistingPet(existingPet, updatedPet);
        return petRepository.save(existingPet);
    }
    public List<Pet> getPets() {
        return this.petRepository.findAll();
    }
    private void updateExistingPet(Pet existingPet, Pet updatedPet) {
        // Update fields here as needed
        existingPet.setCategory(updatedPet.getCategory());
        existingPet.setName(updatedPet.getName());
        existingPet.setPhotoUrls(updatedPet.getPhotoUrls());
        existingPet.setTags(updatedPet.getTags());
        existingPet.setStatus(updatedPet.getStatus());
    }

    public void deletePet(long id) {
        if (petRepository.existsById( id)) {
            petRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Pet with id " + id + " not found");
        }
    }

    public List<Pet> getPetsByStatus(String petStatus) {
        // Assuming petStatus is an enum or matches some criteria in your Pet entity
        return petRepository.findByStatus(petStatus);
    }
}
