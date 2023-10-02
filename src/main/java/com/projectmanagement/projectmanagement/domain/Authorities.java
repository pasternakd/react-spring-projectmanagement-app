package com.projectmanagement.projectmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authorities implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private User user;

    private String authority;

    public static Authorities of(@NotNull final String authority) {
        return Authorities.builder().authority(authority).build();
    }
}
