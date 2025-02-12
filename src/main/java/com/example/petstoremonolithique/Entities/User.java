package com.example.petstoremonolithique.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.example.petstoremonolithique.Entities.PetStoreGenerated.GrpcUser;

import lombok.Data;

@Entity
@Data
@Table(name = "\"users\"")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String username;

	private String firstname;

	private String lastname;

	private String email;

	private String passwordHash;

	private String salt;

	private String phone;

	private UserStatus status;

	public User(Long id, String username, String firstname, String lastname, String email, String passwordHash,
			String salt, String phone, UserStatus status) {
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.passwordHash = passwordHash;
		this.salt = salt;
		this.phone = phone;
		this.status = status;
	}

	public User() {
	}

	public void setId(@Nullable Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setUsername(@Nullable String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setFirstname(@Nullable String firstname) {
		this.firstname = firstname;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setLastname(@Nullable String lastname) {
		this.lastname = lastname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setEmail(@Nullable String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setPasswordHash(@Nullable String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setSalt(@Nullable String salt) {
		this.salt = salt;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setPhone(@Nullable String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setStatus(@Nullable UserStatus status) {
		this.status = status;
	}

	public UserStatus getStatus() {
		return this.status;
	}

	@NotNull
	public static User fromGrpc(@NotNull GrpcUser user) {
		return new User(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail(),
				user.getPasswordHash(), user.getSalt(), user.getPhone(), UserStatus.fromGrpc(user.getStatus()));
	}

	@NotNull
	public GrpcUser toGrpc() {
		GrpcUser.Builder builder = GrpcUser.newBuilder();
		if (id != null) {
			builder.setId(id);
		}
		if (username != null) {
			builder.setUsername(username);
		}
		if (firstname != null) {
			builder.setFirstname(firstname);
		}
		if (lastname != null) {
			builder.setLastname(lastname);
		}
		if (email != null) {
			builder.setEmail(email);
		}
		if (passwordHash != null) {
			builder.setPasswordHash(passwordHash);
		}
		if (salt != null) {
			builder.setSalt(salt);
		}
		if (phone != null) {
			builder.setPhone(phone);
		}
		if (status != null) {
			builder.setStatus(status.toGrpc());
		}
		return builder.build();
	}

	public enum UserStatus {

		Active, Inactive;

		public GrpcUser.GrpcUserStatus toGrpc() {
			return switch (this) {
			case Active -> GrpcUser.GrpcUserStatus.Active;
			case Inactive -> GrpcUser.GrpcUserStatus.Inactive;
			};
		}

		public static UserStatus fromGrpc(GrpcUser.GrpcUserStatus grpc) {
			return switch (grpc) {
			case Active -> UserStatus.Active;
			case Inactive -> UserStatus.Inactive;
			case UNRECOGNIZED -> throw new IllegalArgumentException("Enum value is not recognized");
			};
		}
	}
}
