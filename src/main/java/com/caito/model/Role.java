package com.caito.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author caito Vilas
 */

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    public Role(){
        super();
    }
    public Role(String authority){
        this.authority = authority;
    }
    private Role(Long id, String authority){
        this.id = id;
        this.authority = authority;
    }
    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority){
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
