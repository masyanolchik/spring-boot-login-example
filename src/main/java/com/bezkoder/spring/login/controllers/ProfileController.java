package com.bezkoder.spring.login.controllers;

import com.bezkoder.spring.login.models.User;
import com.bezkoder.spring.login.payload.request.ChangeContactsRequest;
import com.bezkoder.spring.login.payload.request.ChangePasswordRequest;
import com.bezkoder.spring.login.payload.response.MessageResponse;
import com.bezkoder.spring.login.payload.response.UserInfoResponse;
import com.bezkoder.spring.login.repository.UserRepository;
import com.bezkoder.spring.login.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/change_contacts")
    public ResponseEntity<?> changeContacts(@Valid @RequestBody ChangeContactsRequest changeContactsRequest) {
        if(!userRepository.existsByUsername(changeContactsRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username does not exist!"));
        }

        User user = userRepository.findByUsername(changeContactsRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Error: Username does not exist! " + changeContactsRequest.getUsername()));
        user.setName(changeContactsRequest.getName());
        user.setLastName(changeContactsRequest.getLastName());
        user.setPhoneNumber(changeContactsRequest.getPhoneNumber());
        user.setShippingAddress(changeContactsRequest.getShippingAddress());
        userRepository.save(user);
        return ResponseEntity.ok()
                .body(new UserInfoResponse(user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toList()),
                        user.getPhoneNumber(),
                        user.getShippingAddress(),
                        user.getName(), user.getLastName()));
    }
}
