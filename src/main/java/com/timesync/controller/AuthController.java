package com.timesync.controller;

import com.timesync.model.User;
import com.timesync.repository.UserRepository;
import com.timesync.security.JwtUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserRepository repo;
    private final JwtUtil jwt;

    public AuthController(UserRepository repo, JwtUtil jwt) {
        this.repo = repo;
        this.jwt = jwt;
    }

    @PostMapping("/register")
public User register(@RequestBody User user){
    user.setPassword(encoder.encode(user.getPassword()));
    return repo.save(user);
}

    @PostMapping("/login")
    public String login(@RequestBody User user){

        User dbUser = repo.findByEmail(user.getEmail());

        if(dbUser == null || !encoder.matches(user.getPassword(), dbUser.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }

        return jwt.generateToken(user.getEmail());
    }
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

}