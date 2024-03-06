package com.simplon.securiteratt.Controllers;

import com.simplon.securiteratt.DTO.LivreDTO;

import com.simplon.securiteratt.Services.ILivreservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livres")
public class LivreController {

    private final ILivreservice livreservice;

    @Autowired
    public LivreController(ILivreservice livreService) {
        this.livreservice = livreService;
    }

    @PostMapping
    public ResponseEntity<LivreDTO> createLivre(@RequestBody LivreDTO livreDTO) {
        LivreDTO createdLivre = livreservice.createLivre(livreDTO);
        return new ResponseEntity<>(createdLivre, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority()")
    public ResponseEntity<List<LivreDTO>> getAllLivres() {
        List<LivreDTO> livreDTOs = livreservice.getAllLivres();
        return new ResponseEntity<>(livreDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority()")
    public ResponseEntity<LivreDTO> getLivreById(@PathVariable(value = "id") Long livreId) {
        Optional<LivreDTO> livreDTOOptional = livreservice.getLivreById(livreId);
        return livreDTOOptional.map(livreDTO -> new ResponseEntity<>(livreDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(Admin)")
    public ResponseEntity<LivreDTO> updateLivre(@PathVariable Long id, @RequestBody LivreDTO livreDTO) {
        LivreDTO updatedLivre = livreservice.updateLivre(id, livreDTO);
        return new ResponseEntity<>(updatedLivre, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(Admin)")
    public ResponseEntity<String> deleteLivreById(@PathVariable("id") Long id) {
        livreservice.deleteLivre(id);
        return ResponseEntity.ok("Livre with id : " + id + " was deleted");
    }
}
