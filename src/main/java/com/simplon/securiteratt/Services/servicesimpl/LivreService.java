package com.simplon.securiteratt.Services.servicesimpl;

import com.simplon.securiteratt.DTO.LivreDTO;

import com.simplon.securiteratt.Services.ILivreservice;
import com.simplon.securiteratt.entity.Livre;
import com.simplon.securiteratt.Repositories.LivreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivreService implements ILivreservice {

    private final LivreRepository livreRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LivreService(LivreRepository livreRepository, ModelMapper modelMapper) {
        this.livreRepository = livreRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LivreDTO createLivre(LivreDTO livreDTO) {
        Livre livre = livreRepository.save(modelMapper.map(livreDTO, Livre.class));
        return modelMapper.map(livre, LivreDTO.class);
    }

    @Override
    public List<LivreDTO> getAllLivres() {
        List<Livre> livres = livreRepository.findByDeletedFalse();
        return livres.stream().map(l -> modelMapper.map(l, LivreDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<LivreDTO> getLivreById(Long id) {
        Optional<Livre> livreOptional = livreRepository.findByIdAndDeletedFalse(id);
        return livreOptional.map(l -> modelMapper.map(l, LivreDTO.class));
    }

    @Override
    public LivreDTO updateLivre(Long id, LivreDTO livreDTO) {
        livreDTO.setId(id);
        return modelMapper.map(livreRepository.save(modelMapper.map(livreDTO, Livre.class)), LivreDTO.class);
    }

    @Override
    public void deleteLivre(Long id) {
        Livre livre = livreRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (livre != null) {
            livre.setDeleted(true);
            livreRepository.save(livre);
        }
    }
}
