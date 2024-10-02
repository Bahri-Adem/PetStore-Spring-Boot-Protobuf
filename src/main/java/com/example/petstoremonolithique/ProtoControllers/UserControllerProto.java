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

import com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcUser;
import com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcUsers;
import com.example.petstoremonolithique.Entities.User;
import com.example.petstoremonolithique.Services.UserService;

import lombok.Data;

@Data
@RestController
@RequestMapping("/proto")
public class UserControllerProto {
	@Autowired
	private UserService userService;

	@GetMapping(value = "/getUsers")
	public GrpcUsers getUsers() {
		List<GrpcUser> userList = new ArrayList<GrpcUser>();
		for (User user : userService.getUsers()) {
			userList.add(user.toGrpc());
		}
		return GrpcUsers.newBuilder().addAllGrpcUser(userList).build();
	}

	@PostMapping(value = "/addUser")
	public GrpcUser addUser(@RequestBody GrpcUser user) {
		return userService.addUser(User.fromGrpc(user)).toGrpc();
	}

	@PutMapping(value = "/updateUser/{id}")
	public GrpcUser updateUser(@PathVariable Long id, @RequestBody GrpcUser user) {
		return userService.updateUser(id, User.fromGrpc(user)).toGrpc();
	}

	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable Long id) {
		this.userService.deleteUser(id);
	}
}
