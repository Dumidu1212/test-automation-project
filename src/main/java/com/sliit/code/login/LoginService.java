package com.sliit.code.login;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean authenticate(String username, String password) {
        // Hardcoded logic for demo; replace with DB/userRepo check
        return "admin".equals(username) && "password123".equals(password);
    }
}