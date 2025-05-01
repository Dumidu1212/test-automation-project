package com.sliit.sepqm.testing.registration;

import com.sliit.sepqm.testing.database.User;
import com.sliit.sepqm.testing.database.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public RegistrationService(UserRepository repo) {
        this.repo = repo;
    }

    public void register(RegistrationRequest req) {
        if (repo.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
        String hashed = encoder.encode(req.getPassword());
        User u = new User(req.getName(), req.getEmail(), hashed, req.getLocation(), req.getRole());
        repo.save(u);
    }
}
