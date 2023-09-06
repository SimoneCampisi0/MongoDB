package com.simonecampisi.biblioteca.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractLibroResponse {
    private String id;

    private String titolo;

    private String autore;

    private Integer annoProduzione;

}
