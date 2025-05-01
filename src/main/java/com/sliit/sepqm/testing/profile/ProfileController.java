package com.sliit.sepqm.testing.profile;

import com.sliit.sepqm.testing.database.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProfileController {
    private final ProfileService svc;

    public ProfileController(ProfileService svc) {
        this.svc = svc;
    }

    @GetMapping("/users/{id}")
    public Profile profile(@PathVariable Long id) {
        return Profile.fromEntity(svc.getById(id));
    }

}
