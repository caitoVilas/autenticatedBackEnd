package com.caito.service;

import com.caito.dto.ApplicationUserDTO;
import com.caito.dto.LoginDTO;
import com.caito.dto.LoginResponseDTO;
import com.caito.dto.NewUserDTO;

/**
 * @author caito Vilas
 */

public interface AuthenticationService {
    ApplicationUserDTO createUser(NewUserDTO dto);
    LoginResponseDTO login(LoginDTO dto);
}
