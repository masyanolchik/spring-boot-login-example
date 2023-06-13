package com.bezkoder.spring.login.controllers;

import com.bezkoder.spring.login.models.Promo;
import com.bezkoder.spring.login.repository.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/promo")
public class PromoController {
    @Autowired
    private PromoRepository promoRepository;

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllPromos() {
        List<Promo> promoList = promoRepository.findAll();
        return ResponseEntity.ok()
                .body(promoList);
    }
}
