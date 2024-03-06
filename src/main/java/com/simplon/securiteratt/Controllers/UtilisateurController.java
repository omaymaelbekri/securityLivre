package com.simplon.securiteratt.Controllers;

import com.simplon.securiteratt.DTO.UtilisateurDTO;
import com.simplon.securiteratt.Services.servicesimpl.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority()")
    public ResponseEntity<UtilisateurDTO> createUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) {
        UtilisateurDTO createdUtilisateur = utilisateurService.createUtilisateur(utilisateurDTO);
        return new ResponseEntity<>(createdUtilisateur, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority(Admin)")
    public ResponseEntity<List<UtilisateurDTO>> getAllUtilisateurs() {
        List<UtilisateurDTO> utilisateurDTOS = utilisateurService.getAllUtilisateurs();
        return new ResponseEntity<>(utilisateurDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(Admin)")
    public ResponseEntity<UtilisateurDTO> getGroup(@PathVariable(value = "id") Long utilisateur_id){
        UtilisateurDTO utilisateurDTOS=utilisateurService.getUtilisateurById(utilisateur_id).get();
        return new ResponseEntity<>(utilisateurDTOS, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(Admin)")
    public ResponseEntity<UtilisateurDTO> updateUtilisateur(@PathVariable Long id, @RequestBody UtilisateurDTO utilisateurDTO) {
        UtilisateurDTO updatedUtilisateur = utilisateurService.updateUtilisateur(id, utilisateurDTO);
        return new ResponseEntity<>(updatedUtilisateur, HttpStatus.OK);
    }

/*    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(Admin)")
    public ResponseEntity<String> deleteUtilisateurById(@PathVariable("id") Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.ok("Utilisateur with id : " + id + "was deleted");
    }
}

