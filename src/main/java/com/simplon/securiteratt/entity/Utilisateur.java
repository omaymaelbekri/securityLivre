package com.simplon.securiteratt.entity;
import com.simplon.securiteratt.entity.enumms.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Utilisateur {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String email;
        private String password;

        private Role roles ;
        private Boolean deleted;
}
