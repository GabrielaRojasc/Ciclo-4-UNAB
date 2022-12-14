package com.app.movie.controller;

import com.app.movie.dto.AuthDTO;
import com.app.movie.dto.AuthResponseDTO;
import com.app.movie.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    AuthService service;

    @PostMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AuthResponseDTO check(@RequestBody AuthDTO request) {
        return service.check(request);
    }

}
