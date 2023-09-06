package com.simonecampisi.biblioteca.service;

import com.simonecampisi.biblioteca.dao.LibroRepo;
import com.simonecampisi.biblioteca.dto.request.CreateLibroRequest;
import com.simonecampisi.biblioteca.dto.response.CreateLibroResponse;
import com.simonecampisi.biblioteca.dto.response.StatoLibroResponse;
import com.simonecampisi.biblioteca.dto.response.ViewLibroResponse;
import com.simonecampisi.biblioteca.model.Libro;
import com.simonecampisi.biblioteca.service.helper.LibroHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LibroService {
    @Autowired
    private LibroRepo libroRepo;

    @Autowired
    private LibroHelper libroHelper;
    public CreateLibroResponse aggiungiLibro(CreateLibroRequest request) {
        Libro l = libroRepo.insert(Libro.builder()
                .titolo(request.getTitolo())
                .isTaken(request.isTaken())
                .annoProduzione(request.getAnnoProduzione())
                .autore(request.getAutore())
                .build());
        return libroHelper.buildCreateLibroResponse(l);
    }

    public List<ViewLibroResponse> listaLibri() {
        return libroRepo.findAll().stream()
                .map(libro -> libroHelper.buildLibroResponse(libro))
                .filter(l -> !l.isTaken())
                .toList();
    }

    public StatoLibroResponse rimuoviLibro(String nomeLibro) {
        StatoLibroResponse statoLibroResponse;

        if (!Objects.isNull(libroRepo.findByTitolo(nomeLibro))) {
            libroRepo.deleteLibroByTitolo(nomeLibro);
            statoLibroResponse = new StatoLibroResponse(true);
        } else {
            statoLibroResponse = new StatoLibroResponse(false);
        }
        return statoLibroResponse;
    }

    public StatoLibroResponse richiediLibro(String nomeLibro) {
        Libro l = libroRepo.findByTitolo(nomeLibro);
        StatoLibroResponse statoLibroResponse;
        if(l.isTaken()) {
            statoLibroResponse = new StatoLibroResponse(false);
        } else {
            l.setTaken(true);
            libroRepo.save(l);
            statoLibroResponse = new StatoLibroResponse(true);
        }
        return statoLibroResponse;
    }

    public StatoLibroResponse restituisciLibro(String nomeLibro) {
        Libro l = libroRepo.findByTitolo(nomeLibro);
        StatoLibroResponse statoLibroResponse;
        if(l.isTaken()) {
            l.setTaken(false);
            libroRepo.save(l);
            statoLibroResponse = new StatoLibroResponse(true);
        } else {
            statoLibroResponse = new StatoLibroResponse(false);
        }
        return statoLibroResponse;
    }
}
