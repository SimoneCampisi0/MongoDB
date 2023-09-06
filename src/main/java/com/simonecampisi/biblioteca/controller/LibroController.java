package com.simonecampisi.biblioteca.controller;

import com.simonecampisi.biblioteca.dto.request.CreateLibroRequest;
import com.simonecampisi.biblioteca.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gestione-libri")
public class LibroController {
    @Autowired
    private LibroService libroService;


    @PostMapping("/aggiungi-libro")
    public ResponseEntity<?> aggiungiLibro(@RequestBody CreateLibroRequest createLibroRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(libroService.aggiungiLibro(createLibroRequest));
    }

    @DeleteMapping("/rimuovi-libro")
    public ResponseEntity<?> rimuoviLibro(@RequestParam String nomeLibro) {
        return ResponseEntity.status(HttpStatus.OK).body(libroService.rimuoviLibro(nomeLibro));
    }

    @GetMapping("/visualizza-libri")
    public ResponseEntity<?> listaLibri() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(libroService.listaLibri());
    }


    @GetMapping("/richiedi-libro")
    public ResponseEntity<?> richiediLibro(@RequestParam String nomeLibro) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(libroService.richiediLibro(nomeLibro));
    }

    @GetMapping("/restituisci-libro")
    public ResponseEntity<?> restituisciLibro(@RequestParam String nomeLibro) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(libroService.restituisciLibro(nomeLibro));
    }



}
