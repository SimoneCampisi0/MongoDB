package com.simonecampisi.biblioteca.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "libri")
public class Libro {
    @Id
    private String id;

    private String titolo;

    private String autore;

    private Integer annoProduzione;

    private boolean isTaken;

}
