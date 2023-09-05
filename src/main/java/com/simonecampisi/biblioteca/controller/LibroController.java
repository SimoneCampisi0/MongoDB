package com.simonecampisi.biblioteca.controller;

import com.simonecampisi.biblioteca.dto.request.CreateLibroRequest;
import com.simonecampisi.biblioteca.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gestione-libri")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @PostMapping("/create-libro")
    public ResponseEntity<?> creaLibro(@RequestBody CreateLibroRequest createLibroRequest) {
        libroService.createLibro(createLibroRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
