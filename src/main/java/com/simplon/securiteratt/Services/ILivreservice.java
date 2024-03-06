package com.simplon.securiteratt.Services;

import com.simplon.securiteratt.DTO.LivreDTO;

import java.util.List;
import java.util.Optional;

public interface ILivreservice {
    LivreDTO createLivre(LivreDTO livreDTO);
    List<LivreDTO> getAllLivres();
    Optional<LivreDTO> getLivreById(Long id);
    LivreDTO updateLivre(Long id, LivreDTO livreDTO);
    void deleteLivre(Long id);
}
