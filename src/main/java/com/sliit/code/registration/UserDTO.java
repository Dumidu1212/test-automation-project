package com.sliit.code.registration;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Enter a valid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 5, max = 8, message = "Password must be between 5 and 8 characters")
    private String password;

    private String location;

    @NotNull(message = "Role is required")
    @Pattern(regexp = "^(student|teacher|admin)$", message = "Role must be student, teacher, or admin")
    private String role;

    public UserDTO() {
    }

    public UserDTO(String name, String email, String password, String location, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
