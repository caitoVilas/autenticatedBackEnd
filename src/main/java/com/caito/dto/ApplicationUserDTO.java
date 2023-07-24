package com.caito.dto;

import com.caito.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

/**
 * @author caito Vilas
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApplicationUserDTO {
    private Long id;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;
}
