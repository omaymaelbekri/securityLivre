package com.simplon.securiteratt.DTO;

import lombok.Data;

@Data

public class LivreDTO {
    private Long id;
    private String titre;
    private String auteur;
    private String genre;
    Boolean deleted;
}
