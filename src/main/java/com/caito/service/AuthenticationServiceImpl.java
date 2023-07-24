package com.caito.service;

import com.caito.dto.ApplicationUserDTO;
import com.caito.dto.LoginDTO;
import com.caito.dto.LoginResponseDTO;
import com.caito.dto.NewUserDTO;
import com.caito.model.ApplicationUser;
import com.caito.model.Role;
import com.caito.repository.RoleRepository;
import com.caito.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * @author caito Vilas
 */

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    public ApplicationUserDTO createUser(NewUserDTO dto) {
        log.info("inicio servicio crear usuario");
        Role role = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(role);
        ApplicationUser user = ApplicationUser.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .authorities(authorities)
                .build();
        ApplicationUser newUser = userRepository.save(user);
        return this.mapToUserDto(newUser);
    }

    @Override
    public LoginResponseDTO login(LoginDTO dto) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );
            String token = tokenService.generateJwt(auth);
            return LoginResponseDTO.builder()
                    .user(this.mapToUserDto(userRepository.findByUsername(dto.getUsername()).get()))
                    .jwt(token)
                    .build();
        }catch (AuthenticationException e){
            return new LoginResponseDTO(null,"");
        }
    }

    private ApplicationUserDTO mapToUserDto(ApplicationUser user){
        return ApplicationUserDTO.builder()
                .id(user.getUserId())
                .username(user.getUsername())
                .authorities(user.getAuthorities())
                .build();
    }

    private ApplicationUserDTO mapToDto(ApplicationUser user){
        return ApplicationUserDTO.builder()
                .username(user.getUsername())
                .id(user.getUserId())
                .authorities(user.getAuthorities())
                .build();
    }
}
