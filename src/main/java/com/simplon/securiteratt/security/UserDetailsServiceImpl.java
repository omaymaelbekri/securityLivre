package com.simplon.securiteratt.security;

import com.simplon.securiteratt.Services.servicesimpl.UtilisateurService;
import com.simplon.securiteratt.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UtilisateurService utilisateurService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurService.loadUserByEmail(email);
        if (utilisateur == null) throw new UsernameNotFoundException("NOT FOUND USER");
        GrantedAuthority authority = new SimpleGrantedAuthority(utilisateur.getRoles().name());
        return new User(utilisateur.getEmail(),utilisateur.getPassword(), Collections.singleton(authority));

    }}
