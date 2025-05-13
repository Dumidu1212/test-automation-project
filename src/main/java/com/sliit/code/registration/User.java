// src/main/java/com/sliit/code/registration/User.java
package com.sliit.code.registration;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "users",
		uniqueConstraints = @UniqueConstraint(columnNames = "email")) // UNIQUE!
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)               // NOT-NULL
	private String name;

	@Column(nullable = false, unique = true) // NOT-NULL  + UNIQUE
	private String email;

	@Column(nullable = false)
	private String password;

	private String location;
	private String role;

	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	/* ---------- ctors ---------- */
	public User() { }

	public User(String name, String email, String pw, String location, String role) {
		this.name     = name;
		this.email    = email;
		this.password = pw;
		this.location = location;
		this.role     = role;
	}

	/* ---------- getters / setters ---------- */
	// (generate with IDE ↓↓↓ – only the ones the tests use are shown)

	public String getName()      { return name; }
	public void   setName(String n) { this.name = n; }

	public String getEmail()     { return email; }
	public void   setEmail(String e) { this.email = e; }

	public String getLocation()  { return location; }
	public void   setLocation(String l) { this.location = l; }

	public String getRole()      { return role; }
	public void   setRole(String r) { this.role = r; }

	public String getPassword()  { return password; }
	public void   setPassword(String p) { this.password = p; }

	public LocalDateTime getCreatedAt() { return createdAt; }

	/* ---------- JPA callback to set createdAt ---------- */
	@PrePersist
	void prePersist() {
		this.createdAt = LocalDateTime.now();
	}
}
