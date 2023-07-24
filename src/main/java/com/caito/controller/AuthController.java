package com.caito.controller;

import com.caito.dto.ApplicationUserDTO;
import com.caito.dto.LoginDTO;
import com.caito.dto.LoginResponseDTO;
import com.caito.dto.NewUserDTO;
import com.caito.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author caito Vilas
 */

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/new-user")
    public ResponseEntity<ApplicationUserDTO> createUser(@RequestBody NewUserDTO dto){
        ApplicationUserDTO response = authenticationService.createUser(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO dto){
        return ResponseEntity.ok(authenticationService.login(dto));
    }
}
