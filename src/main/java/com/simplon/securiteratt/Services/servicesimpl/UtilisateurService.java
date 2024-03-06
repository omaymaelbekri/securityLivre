package com.simplon.securiteratt.Services.servicesimpl;

import com.simplon.securiteratt.DTO.UtilisateurDTO;
import com.simplon.securiteratt.Services.IUtilisateurservice;
import com.simplon.securiteratt.entity.Utilisateur;
import com.simplon.securiteratt.Repositories.UtilisateurRepository;
import com.simplon.securiteratt.security.UserDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UtilisateurService implements IUtilisateurservice {
    private final UtilisateurRepository utilisateurRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder=passwordEncoder;
    }
    @Override
    public UtilisateurDTO createUtilisateur(UtilisateurDTO utilisateurDTO) {
        String encodedPassword = passwordEncoder.encode(utilisateurDTO.getPassword());
        utilisateurDTO.setPassword(encodedPassword);

        Utilisateur utilisateur = utilisateurRepository.save( modelMapper.map(utilisateurDTO, Utilisateur.class));
        return modelMapper.map(utilisateur, UtilisateurDTO.class);
    }
    @Override
    public List<UtilisateurDTO> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findByDeletedFalse();
        return utilisateurs.stream().map(u -> modelMapper.map(u, UtilisateurDTO.class)).collect(Collectors.toList());
    }
    @Override
    public Optional<UtilisateurDTO> getUtilisateurById(Long id) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByIdAndDeletedFalse(id);
        return utilisateurOptional.map(u -> modelMapper.map(u, UtilisateurDTO.class));
    }

    /*public UtilisateurDTO updateUtilisateur(Long id, UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = modelMapper.map(utilisateurDTO, Utilisateur.class);
        utilisateur.setUserId(id);
        utilisateur = utilisateurRepository.save(utilisateur);
        return modelMapper.map(utilisateur, UtilisateurDTO.class);
    }*/

    @Override
    public UtilisateurDTO updateUtilisateur(Long id ,UtilisateurDTO utilisateurDTO) {
        utilisateurDTO.setId(id);
        return modelMapper.map(utilisateurRepository.save(modelMapper.map( utilisateurDTO, Utilisateur.class)),UtilisateurDTO.class);
    }
    @Override
    public void deleteUtilisateur(Long id) {
        Utilisateur utilisateur=utilisateurRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (utilisateur!= null){
            utilisateur.setDeleted(true);
            utilisateurRepository.save(utilisateur);
        }





}

    @Override
    public Utilisateur loadUserByEmail(String email) {
        return utilisateurRepository.findByEmailAndDeletedFalse(email) ;
    }
}
