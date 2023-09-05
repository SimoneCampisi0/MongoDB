package com.simonecampisi.biblioteca.dto.request;

import lombok.Data;

@Data
public abstract class AbstractLibroRequest {
    private String titolo;

    private String autore;

    private Integer annoProduzione;

    private boolean preso;
}
