package com.simplon.securiteratt.DTO;

import com.simplon.securiteratt.entity.enumms.Role;
import jakarta.persistence.Entity;
import lombok.Data;

@Data

public class UtilisateurDTO {
    private Long id;
    private String email;
    private String password;
    private Role roles ;
    Boolean deleted;
}
