package com.sliit.sepqm.testing.profile;

import com.sliit.sepqm.testing.database.User;
import com.sliit.sepqm.testing.database.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final UserRepository repo;

    public ProfileService(UserRepository repo) {
        this.repo = repo;
    }

    public User getById(Long id) {
        return repo.findById(id).orElseThrow();
    }
}
