package com.simplon.securiteratt.Repositories;

import com.simplon.securiteratt.entity.Livre;
import com.simplon.securiteratt.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivreRepository extends JpaRepository<Livre,Long> {
    List<Livre> findByDeletedFalse();
    Optional<Livre> findByIdAndDeletedFalse(Long id);
}
