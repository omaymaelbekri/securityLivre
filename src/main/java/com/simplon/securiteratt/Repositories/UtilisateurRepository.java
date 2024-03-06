package com.simplon.securiteratt.Repositories;

import com.simplon.securiteratt.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {

    //Utilisateur findByEmail(String email);
    List<Utilisateur> findByDeletedFalse();
    Optional<Utilisateur> findByIdAndDeletedFalse(Long id);
    Utilisateur findByEmailAndDeletedFalse(String email);
}
