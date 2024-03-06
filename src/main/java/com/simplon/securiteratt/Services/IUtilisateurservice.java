package com.simplon.securiteratt.Services;

import com.simplon.securiteratt.DTO.UtilisateurDTO;
import com.simplon.securiteratt.entity.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface IUtilisateurservice {

    UtilisateurDTO createUtilisateur(UtilisateurDTO utilisateurDTO);
    List<UtilisateurDTO> getAllUtilisateurs();
    Optional<UtilisateurDTO> getUtilisateurById(Long id);
    UtilisateurDTO updateUtilisateur(Long id , UtilisateurDTO utilisateurDTO);
    void deleteUtilisateur(Long id);
    Utilisateur loadUserByEmail(String email);




}
