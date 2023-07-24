package com.caito.dto;

import com.caito.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewUserDTO {
    private String username;
    private String password;
    private Set<Role> autorities;
}
