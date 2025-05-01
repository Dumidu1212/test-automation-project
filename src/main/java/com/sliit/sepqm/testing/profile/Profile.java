package com.sliit.sepqm.testing.profile;

import java.util.Objects;

/**
 * Data Transfer Object for exposing user profiles safely.
 */
public class Profile {
    private Long id;
    private String name;
    private String email;
    private String location;
    private String role;

    public Profile() {
    }

    public Profile(Long id, String name, String email, String location, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.location = location;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public Profile setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Profile setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Profile setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Profile setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getRole() {
        return role;
    }

    public Profile setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id) &&
                Objects.equals(name, profile.name) &&
                Objects.equals(email, profile.email) &&
                Objects.equals(location, profile.location) &&
                Objects.equals(role, profile.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, location, role);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    /**
     * Convenient factory to map from your JPA User entity.
     */
    public static Profile fromEntity(com.sliit.sepqm.testing.database.User u) {
        return new Profile(
                u.getId(),
                u.getName(),
                u.getEmail(),
                u.getLocation(),
                u.getRole()
        );
    }
}
